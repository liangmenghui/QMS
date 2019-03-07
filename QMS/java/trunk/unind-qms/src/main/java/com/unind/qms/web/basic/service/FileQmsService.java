package com.unind.qms.web.basic.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.web.basic.entity.FeedbackHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * Qms文件管理
 * @author chen
 *
 */
public interface FileQmsService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult upload(FsFile fsFile, MultipartFile file) throws BusinessException;

	public ApiResponseResult get(Long fsFileId, HttpServletResponse response) throws BusinessException;

}
