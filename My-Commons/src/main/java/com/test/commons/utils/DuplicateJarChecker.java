package com.test.commons.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DuplicateJarChecker {

	public static void main(String args[]) throws Exception {
		if (args.length < 1) {
			System.out.println("Usage : java DuplicateJarChecker <path for WEB-INF/lib folder>");
			System.exit(0);
		}
		String path = args[0].replace('\\', '/');
		List<File> files = getFiles(path, ".jar");
		findDuplicateFiles(files);
	}

	public static void findDuplicateFiles(List<File> files) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> dupMap = new HashMap<String, String>();
		System.out.println("Searching for duplicate files within " + files.size() + " files");
		for (File file : files) {
			String name = getFileName(file.getName());
			if (map.containsKey(name)) {
				dupMap.put(file.getName(), map.get(name));

			} else {
				map.put(name, file.getName());
			}
		}
		PrintMap(dupMap);
	}

	public static void PrintMap(Map<String, String> map) {
		if (!map.isEmpty()) {
			System.out.println("*********** Duplicate Jars ***********");
			Iterator<String> itr = map.keySet().iterator();
			int i = 1;
			while (itr.hasNext()) {
				String key = itr.next();
				System.out.println(i + ". " + key + "  - " + map.get(key));
				i++;
			}
			System.out.println("*********************************");
		}
	}

	public static String getFileName(String name) {
		StringBuilder builder = new StringBuilder();
		char[] chars = name.toCharArray();
		for (int cnt = 0; cnt < chars.length; cnt++) {
			if ('-' == chars[cnt] && Character.isDigit(chars[cnt + 1]))
				break;
			builder.append(chars[cnt]);
		}

		for (char char1 : chars) {

			if (Character.isDigit(char1))
				break;
			builder.append(char1);
		}
		return builder.toString();
	}

	public static List<File> getFiles(final String dir, final String extension) {
		List<File> files = new ArrayList<File>();
		File directory = new File(dir);
		if (!directory.isDirectory()) {
			System.out.println("No directory provided");
			return files;
		}
		FilenameFilter filefilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				boolean yes = name.endsWith(extension);
				return yes;
			}
		};
		files.addAll(Arrays.asList(directory.listFiles(filefilter)));
		return files;
	}
}
