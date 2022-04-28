package org.xl.framework.context.support;

import org.apache.log4j.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 自定义日志类
 */
public class Log {
	private static String path = "C:\\var\\log\\GameFramework.log";
	private static DateFormat dateFormat;
	private final String MESSAGE_FORMAT;
	private static final Logger logger = Logger.getLogger(Log.class.getName());
	private Class<?> aClass;

	private static FileAppender appender;

	private Log(Class<?> aClass) {
		this.aClass = aClass;
		MESSAGE_FORMAT = " -- " + aClass.getName() + " -- ";
		loggerInit();
	}

	private static void loggerInit() {
		try {
			if(dateFormat == null) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			SimpleLayout layout = new SimpleLayout();
			appender = new FileAppender(layout, path);

			logger.addAppender(appender);
			logger.setLevel(Level.INFO);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static Log getLog(Class<?> aClass) {
		return new Log(aClass);
	}

	private void setAClass(Class<?> aClass) {
		this.aClass = aClass;
	}

	public void setAppend(boolean append) {
		appender.setAppend(append);
	}

	public void log(String message) {
		logger.info(dateFormat.format(new Date()) + MESSAGE_FORMAT + message);
	}

	public void error(String message) {
		log("error: " + message);
	}

	public void log(String message, Map<?, ?> map) {
		StringBuilder builder = new StringBuilder();
		builder.append(message);
		map.forEach((k, v) -> builder.append(k).append("->").append(v).append(System.getProperty("line.separator")));
		logger.info(dateFormat.format(new Date()) + MESSAGE_FORMAT + builder);
	}
}
