package com.wangzhixuan.commons.ueditor.upload;

import java.util.Map;

import org.apache.shiro.codec.Base64;

import com.wangzhixuan.commons.ueditor.PathFormat;
import com.wangzhixuan.commons.ueditor.define.AppInfo;
import com.wangzhixuan.commons.ueditor.define.BaseState;
import com.wangzhixuan.commons.ueditor.define.FileType;
import com.wangzhixuan.commons.ueditor.define.State;
import com.wangzhixuan.commons.ueditor.manager.IUeditorFileManager;


public final class Base64Uploader {

	public static State save(IUeditorFileManager fileManager, String content, Map<String, Object> conf) {
		byte[] data = decode(content);
		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}

		String suffix = FileType.getSuffix("JPG");

		String savePath = PathFormat.parse((String) conf.get("savePath"),
				(String) conf.get("filename"));

		savePath = savePath + suffix;
		String rootPath = (String) conf.get("rootPath");

		State storageState = fileManager.saveFile(data, rootPath, savePath);

		if (storageState.isSuccess()) {
			storageState.putInfo("url", PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}
		return storageState;
	}

	private static byte[] decode(String content) {
		return Base64.decode(content);
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}

}