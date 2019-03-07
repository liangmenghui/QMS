package com.unind.base.components.file.internal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unind.base.components.file.FtpClientService;
import com.unind.base.configure.AppConfig;
import com.unind.base.data.ApiResponseResult;

@Service
public class FtpClientImpl implements FtpClientService {
	private static final Logger logger = LoggerFactory.getLogger(FtpClientImpl.class);
	@Autowired
	private AppConfig appConfig;

	public ApiResponseResult uploadFile(String path, String filename, InputStream input) {
		String url = appConfig.getString("fs.ftp.url");
		int port = appConfig.getInt("fs.ftp.port", 22);
		String username = appConfig.getString("fs.ftp.username");
		String password = appConfig.getString("fs.ftp.passwrod");
		String rootPath = appConfig.getString("fs.ftp.rootPath", "");
		if(!StringUtils.startsWith(path, "/")) {
			path = File.separator + path;
		}
		return this.uploadFile(url, port, username, password, rootPath + path, filename, input);
	}

	@Override
	public ApiResponseResult uploadFile(String url, int port, String username, String password, String path, String filename, InputStream input) {
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return ApiResponseResult.failure("上传失败");
			}
			this.changeWorkingDirectory(ftp, path);
			ftp.storeFile(filename, input);

			input.close();
			ftp.logout();
			return ApiResponseResult.success("上传成功");
		} catch (IOException e) {
			e.printStackTrace();
			return ApiResponseResult.failure("上传异常");
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
	}

	public ApiResponseResult downloadFile(String path, String fileName, String localPath) {
		String url = appConfig.getString("fs.ftp.url");
		int port = appConfig.getInt("fs.ftp.port", 22);
		String username = appConfig.getString("fs.ftp.username");
		String password = appConfig.getString("fs.ftp.passwrod");
		String rootPath = appConfig.getString("fs.ftp.rootPath", "");
		if(!StringUtils.startsWith(path, "/")) {
			path = File.separator + path;
		}
		return this.downloadFile(url, port, username, password, rootPath + path, fileName, localPath);
	}

	@Override
	public ApiResponseResult downloadFile(String url, int port, String username, String password, String path, String fileName, String localPath) {
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return ApiResponseResult.failure("下载失败");
			}
			ftp.changeWorkingDirectory(path);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());

					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

			ftp.logout();
			return ApiResponseResult.success("下载成功");
		} catch (IOException e) {
			e.printStackTrace();
			return ApiResponseResult.failure("下载失败");
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
	}

	protected void changeWorkingDirectory(FTPClient ftp, String path) throws IOException {
		boolean changed = ftp.changeWorkingDirectory(path);
		if(changed) {
			return;
		}
		String[] dirs = StringUtils.split(path, "/");
		if(null==dirs) {
			return;
		}
		String pathname = "";
		for (String dir : dirs) {
			pathname += File.separator + dir;
			if(ftp.makeDirectory(pathname)) {
				logger.debug("create ftp directory with {}", pathname);
			}
		}
	}
}
