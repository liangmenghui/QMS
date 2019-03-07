package com.unind.qms.web.basic.service.internal;

import com.unind.base.components.file.FtpClientService;
import com.unind.base.configure.AppConfig;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.fs.dao.file.FsFileDao;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.web.basic.service.FileQmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Configuration
public class FileQmsImpl extends BaseOprService implements FileQmsService {
    @Autowired
    private FsFileDao fsFileDao;
    @Autowired
    private FtpClientService ftpClientService;
    @Autowired
    private AppConfig appConfig;

    public ApiResponseResult upload(FsFile fsFile, MultipartFile file) {
        if(null==file || file.isEmpty()) {
            return ApiResponseResult.failure("上传文件不能为空");
        }
        String qmsPath = appConfig.getString("fs.qms.path");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());

        String path = qmsPath + "/" + ymd;

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateFileName = df.format(new Date()) + "_" + new Random().nextInt(1000);

        try {
            fsFile.setBsFileSize(file.getSize());
            if(null==fsFile.getBsContentType()) {
                fsFile.setBsContentType(file.getContentType());
            }
            if(null==file.getOriginalFilename()) {
                fsFile.setBsFileType("Unknown");
                return ApiResponseResult.failure("无法识别该文件类型！");
            }

            String originalFiletype = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
            fsFile.setBsFileType(originalFiletype);

//            String originalFilename = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
            fsFile.setBsName(file.getOriginalFilename());
            fsFile.setBsFileName(dateFileName + originalFiletype);
            fsFile.setBsFilePath("/"+ymd);
            ApiResponseResult result = ftpClientService.uploadFile(path, dateFileName+fsFile.getBsFileType(), new ByteArrayInputStream(file.getBytes()));
            if(result.isResult()) {
                fsFile.setBsCreatedTime(new Date());
                fsFileDao.save(fsFile);
                return ApiResponseResult.success().data(fsFile);
            }
        } catch (IOException e) {
            logger.error("upload file exception", e);
        }
        return ApiResponseResult.failure("上传文件发生异常");
    }

    public ApiResponseResult get(Long fsFileId, HttpServletResponse response) throws BusinessException {
        FsFile fsFile = fsFileDao.findOne(fsFileId);
        if(null==fsFile) {
            return ApiResponseResult.failure("文件不存在或已被删除");
        }
        String path = appConfig.getString("fs.qms.path")+fsFile.getBsFilePath();
        ApiResponseResult result = ftpClientService.download(path, fsFile.getBsFileName());
        try {
//            String fileName = new String(fsFile.getBsName().getBytes("UTF-8"), "ISO-8859-1")+ fsFile.getBsFileType();
            String fileName = URLEncoder.encode(fsFile.getBsName(), "UTF-8");
//            response.setContentType("application/octet-stream");
            response.setContentType(fsFile.getBsContentType());
            // 设置response的Header
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fsFile.getBsName()+fsFile.getBsFileType()).getBytes("UTF-8"), "ISO-8859-1"));
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName );
            response.addHeader("Content-Length", "" + fsFile.getBsFileSize());
            OutputStream os = response.getOutputStream();
            byte[] bytes = (byte[]) result.getData();
            os.write(bytes);
            os.flush();
            os.close();
        } catch (IOException e) {
            logger.error("download file exception", e);
        }
        return null;
    }
}
