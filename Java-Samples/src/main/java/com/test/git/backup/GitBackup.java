package com.test.git.backup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GitBackup {
	private static String ignored = "hellooo,delllo";//".classpath,.gitignore,.project,.settings,.gradle,js-build";
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
	private static String source = "";
	private static String target = "";

	public static void main(String[] args) throws Exception {
		initialize(args);
		List<File> filesList = readChangedFiles(source);
		List<String> ignoredFilesList = getPaths(ignored);
		List<File> backupFilesList = getBackupFiles(filesList, ignoredFilesList);
		backupFiles(target, backupFilesList);
	}

	private static void initialize(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage : java com.test.git.backup.Main <source folder> <target folder>");
			System.exit(0);
		}
		File srcFile = new File(args[0]);
		File tarFile = new File(args[1]);
		if (!srcFile.exists()) {
			System.out.println("Invalid source folder : " + srcFile.getAbsolutePath());
			System.exit(0);
		}
		if (!tarFile.exists()) {
			System.out.println("Invalid target folder : " + srcFile.getAbsolutePath());
			System.exit(0);
		}
		source = srcFile.getAbsolutePath();
		target = tarFile.getAbsolutePath();
	}

	private static void backupFiles(String target, List<File> backupFilesList) throws Exception {
		int size = backupFilesList.size();
		System.out.println("********* Backup Report *********");
		System.out.println("Files to backup : " + size);

		if (size > 0) {
			File targetFolder = new File(target, format.format(new Date())+"-size-"+size);
			targetFolder.mkdirs();
			for (File file : backupFilesList) {
				int sourceLegth = source.length() + 1;
				String srcFilePath = file.getAbsolutePath();
				String srcFileFolder = srcFilePath.substring(sourceLegth);
				File targetFile = new File(targetFolder, srcFileFolder);
				if (!targetFile.exists()) {
					targetFile.getParentFile().mkdirs();
					targetFile.createNewFile();
				}
				System.out.println("Backing Up : " + targetFile.getAbsolutePath());
				copyFileUsingStream(file, targetFile);
			}
		}
		System.out.println("********* All Backed Up *********");
	}

	private static List<File> getBackupFiles(List<File> filesList, List<String> ignoredFilesList) {
		List<File> backupFilesList = new ArrayList<File>();
		for (File file : filesList) {
			if (!ignore(ignoredFilesList, file)) {
				if(!file.isDirectory()) {
					backupFilesList.add(file);
				}
			}
		}
		return backupFilesList;
	}

	private static List<File> readChangedFiles(String projectFolder) throws Exception {
		List<File> filesList = new ArrayList<File>();
		ProcessBuilder builder = new ProcessBuilder("cmd.exe");
		builder.redirectErrorStream(true);
		Process process = builder.start();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
		out.write("cd " + projectFolder);
		out.newLine();
		out.flush();
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
		out.write("git status");
		out.newLine();
		out.flush();
		out.write("YES-YES");
		out.newLine();
		out.flush();
		String line = in.readLine();
		int index = 0;
		while (index <= 0) {
			index = line.indexOf("YES-YES");
			if (line.indexOf("/") > -1) {
				File file = new File(projectFolder, line);
				if (file.exists()) {
					filesList.add(file);
				} else {
					System.out.println("File not exists : " + file.getAbsolutePath());
				}
			}
			line = trim(in.readLine());
		}
		process.destroy();
		return filesList;
	}

	private static boolean ignore(List<String> ignoredFiles, File file) {
		if (file != null) {
			String path = file.getAbsolutePath();
			for (String iFile : ignoredFiles) {
				int index = path.indexOf(iFile);
				if (index > -1) {
					return true;
				}
			}
		}
		return false;
	}

	private static List<String> getPaths(String ignored) {
		List<String> files = new ArrayList<String>();
		String[] paths = ignored.split("[ ]*[,][ ]*");
		if (paths != null) {
			files.addAll(Arrays.asList(paths));
		}
		return files;
	}

	private static String trim(String str) {
		if (str != null) {
			str = str.replaceAll("modified:", "");
			str = str.replaceAll("new file:", "");			
			return str.trim();
		}
		return "";
	}

	private static void copyFileUsingStream(File source, File dest) throws Exception {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
		}
	}
}
