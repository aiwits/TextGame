package org.xl.framework.context.support;

import org.xl.framework.build.factory.BeanFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.stream.Collectors;

public final class ScanClass {
	private static final Map<String, Class<?>> classMap = new HashMap<>();
	private static final Scan scan = new Scan();
	private static final Log log = Log.getLog(ScanClass.class);

	private ScanClass() {}

	public static class Scan {
		public Map<String, Class<?>> getScanClassMap() {
			return classMap;
		}
	}

	public static Scan scan(String pack) {
		if(classMap.isEmpty()) {
			List<Object> objectList = new ArrayList<>();
			log.log("扫描的包路径：" + pack);
			if(pack.endsWith(".*")) {
				pack = pack.substring(0, pack.length() - 2);
			}
			parsePackage(pack, objectList);
			log.log("扫描的类：", classMap);
		}

		return scan;
	}

	private static void parsePackage(String pack, List<Object> objectList) {
		String packName = pack.replace('.', '/');
		URL url = Thread.currentThread().getContextClassLoader().getResource(packName);
		String protocol = url != null ? url.getProtocol() : "null";

		if(protocol.equals("file")) {
			String decode = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
			getClassByPack(decode, pack);
		} else if(protocol.equals("jar")) {
			String decode = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
			getClassByJarPack(url, pack);
		} else {
			log.log("不支持" + protocol);
			System.exit(0);
		}
	}

	private static void getClassByJarPack(URL url, String pack) {
		try {
			String packName = pack.replace('.', '/');
			JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
			List<JarEntry> jarEntryList = jarURLConnection.getJarFile()
					.stream()
					.filter(jarEntry -> jarEntry.getName().contains(packName))
					.collect(Collectors.toList());
			jarEntryList.forEach(jarEntry -> {
				String name = jarEntry.getName();
				if(name.endsWith(".class")) {
					String className = name.substring(0, name.length() - 6).replaceAll("/", ".");
					try {
						Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass(className);
						classMap.put(className, aClass);
					} catch(ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static void getClassByPack(String decode, String pack) {
		File packDir = new File(decode);
		File[] files = packDir.listFiles();
		assert files != null;
		for(File file : files) {
			if(file.isFile()) {
				String fileName = file.getName();
				if(fileName.endsWith(".class")) {
					String packName = file.getAbsolutePath().replaceAll("\\\\", ".");
					String[] split = packName.split(pack);
					String className = pack + split[split.length - 1].substring(0, split[split.length - 1].length() - 6);
					try {
						classMap.put(className, Class.forName(className));
					} catch(ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			} else if(file.isDirectory()) {
				getClassByPack(file.getAbsolutePath(), pack);
			}
		}
	}
}
