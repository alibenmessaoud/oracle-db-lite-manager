package com.dbop.beans;

import java.util.ArrayList;

public class TBSFiles {
	
	public TBSFile tbsFiles[] ;
	public ArrayList<TBSFile>  tbsFilesList;
	public ArrayList<TBSFile> getTbsFilesList() {
		return tbsFilesList;
	}

	public void setTbsFilesList(ArrayList<TBSFile> tbsFilesList) {
		this.tbsFilesList = tbsFilesList;
	}

	public int count ;
	
	public TBSFiles() {
		tbsFiles = new TBSFile[100];
		count = 0;
	}
	
	public void addFile(TBSFile file)
	{
		tbsFiles[count] = file;
		count++;
	}

	public TBSFile[] getTbsFiles() {
		return tbsFiles;
	}

	public int getCount() {
		return count;
	}

	public void setTbsFiles(TBSFile[] tbsFiles) {
		this.tbsFiles = tbsFiles;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public TBSFiles insertFiles(String smallFileNames[], String smallFileSizes[],String smallFileSizeUnits[],
			String reuses[], String autoextends[])
	{
		int smallFilesNber = smallFileNames.length;
		for (int i = 0; i < smallFilesNber; i++) {
			this.addFile(new TBSFile("", "", "", "", ""));
		}
		for (int i = 0; i < smallFilesNber; i++) {
			tbsFiles[i].setSmallfileName(smallFileNames[i]);
			tbsFiles[i].setSmallFileSize(smallFileSizes[i]);
			tbsFiles[i].setSmallfileSizeUnit(smallFileSizeUnits[i]);
				//System.out.println(reuses.length+"-----");
			//tbsFiles[i].setReuse(reuses[i]);
				//System.out.println(i);
			//tbsFiles[i].setAutoextend(autoextends[i]);
		}
		//System.out.println(count);
		return this;
	}
	
	
}
