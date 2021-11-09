package com.scsb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	
	final static Logger logger = LoggerFactory.getLogger(LogUtil.class);

	public static void setSaveLog(String memeberId, String message) {
		String className = Thread.currentThread().getStackTrace()[2].getFileName();
		try {
			className = className.substring(0, className.indexOf("."));
		} catch (Exception ex) {
		}
		String methodName = className + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
		logger.info(WebUtil.removeControlCharacter("[" + methodName + "] {" + memeberId + "} - " + message));
	}

	public static void setActionLog(String action, String message) {
		String className = Thread.currentThread().getStackTrace()[2].getFileName();
		try {
			className = className.substring(0, className.indexOf("."));
		} catch (Exception ex) {
		}
		String methodName = className + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
		logger.info(WebUtil.removeControlCharacter(methodName + "-ACTION:" + action + ";MESSAGE : " + message));
	}

	public static void setErrorLog(String action, Exception e) {
		String className = Thread.currentThread().getStackTrace()[2].getFileName();
		try{
			className = className.substring(0, className.indexOf("."));
		} catch (Exception ex) {}
		String methodName = className + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
		logger.error(WebUtil.removeControlCharacter(methodName + "-ACTION:" + action + " is error!"), e);
	}

	public static void setInfoLog(String message) {
		String className = Thread.currentThread().getStackTrace()[2].getFileName();
		try {
			className = className.substring(0, className.indexOf("."));
		} catch (Exception ex) {
		}
		String methodName = className + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
		logger.info(WebUtil.removeControlCharacter("[" + methodName + "]-" + message));
	}

	public static void setDebugLog(String message) {
		String className = Thread.currentThread().getStackTrace()[2].getFileName();
		try {
			className = className.substring(0, className.indexOf("."));
		} catch (Exception ex) {
		}
		String methodName = className + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
		logger.debug(WebUtil.removeControlCharacter("[" + methodName + "]-" + message));
	}
}