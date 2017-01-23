package com.epam.project;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

public class UploadFile {
	private String fileName;
	private String dateMod;
	private String dateCreated;
	private String lastAccess;
	private long size;
	private int wordCount;
	private int lineCount;
	private int paragraphCount;
	private int capitalwords;
	private String message="";
	public int getCapitalwords() {
		return capitalwords;
	}

	public void setCapitalwords(int capitalwords) {
		this.capitalwords = capitalwords;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDateMod() {
		return dateMod;
	}

	public void setDateMod(String dateMod) {
		this.dateMod = dateMod;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(String lastAccess) {
		this.lastAccess = lastAccess;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	public int getParagraphCount() {
		return paragraphCount;
	}

	public void setParagraphCount(int nparas) {
		this.paragraphCount = nparas;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// getting properties of a file
	public void fileProperties(File file, String name) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastmod = sdf.format(file.lastModified());
		Path path = Paths.get(name);
		BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
		FileTime cdate = attr.creationTime();
		FileTime atime = attr.lastAccessTime();
		String[] dc = cdate.toString().split("T");
		String[] access = atime.toString().split("T");
		// long length = file.length();
		setFileName(file.getName());
		setDateCreated(dc[0]);
		setDateMod(lastmod);
		setLastAccess(access[0]);
		setSize(file.length());
	}

	// extracting details from a text file

	public void readTextfile(File file) throws IOException {
		int errorAtLine = 0, paraNum = 1;
		String line;
		BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
		while ((line = br.readLine()) != null) {
			// line2 = line;
			/*
			 * statements under this takes care of count of words,
			 * lines,paragraphs
			 */
			lineCount++;
			String[] words = line.split(" ");
			wordCount += words.length;
			StringTokenizer st = new StringTokenizer(line, " ");
			if (st.countTokens() == 0) {
				paragraphCount++;
			}
			// finding errors of uppercase letters
			errorAtLine++;
			// StringTokenizer st = new StringTokenizer(line, " ");
			if (st.countTokens() == 0) {
				paraNum++;
			}
			String[] capsArray = line.split("\\. ");
			for (int j = 0; j < capsArray.length - 1; j++) {
				char ch = capsArray[j + 1].charAt(0);
				int key = ch;
				if (!(key >= 65 && key <= 90)) {
					message += "<br>Error in paragraph " + paraNum + " at line " + errorAtLine
							+ " missed Capitalization after (.)";
				}
			}

			if (errorAtLine == 1) {
				int key3 = line.charAt(0);
				if (!(key3 >= 65 && key3 <= 90))
					;
				message += "<br>Uppercase error at first line at 'starting'";
			}
			/* finding errors regarding full stop */
			String[] wordsArray = line.split(" ");
			for (int k = 1; k < wordsArray.length; k++) {
				// System.out.println(wordsArray[k]);
				int key2 = wordsArray[k].charAt(0);
				if (key2 > 64 && key2 < 91) {
					capitalwords++;
					int fullstop = wordsArray[k - 1].charAt(wordsArray[k - 1].length() - 1);
					boolean condition = fullstop != 44 && !(wordsArray[k - 1].compareTo("-") == 0)
							&& !(wordsArray[k - 1].compareToIgnoreCase("the") == 0)
							&& !(wordsArray[k - 1].compareToIgnoreCase("an") == 0)
							&& !(wordsArray[k - 1].compareToIgnoreCase("a") == 0);
					if (condition) {
						message += "<br> Error in paragraph " + paraNum + " at line " + errorAtLine// stop
								+ " missing '.' at word '" + wordsArray[k - 1] + "' ";
						// System.out.println(wordsArray[k-1]);
					}
				}
			}
		}

		// return message;

	}

	public void otherFiles(File file) {
		// TODO Auto-generated method stub
		message+=file.getName()+"is not a readable file";
		
	}
}