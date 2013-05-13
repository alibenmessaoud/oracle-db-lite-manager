package com.dbop.beans;

public class TBSFile {
	public String smallFileName;
	public String smallFileSize;
	public String smallFileSizeUnit;
	public String reuse;
	public String autoextend; //on | off
	
	
	public TBSFile(String smallfileName, String smallFileSize,
			String smallfileSizeUnit, String reuse, String autoextend) {
		super();
		this.smallFileName = smallfileName;
		this.smallFileSize = smallFileSize;
		this.smallFileSizeUnit = smallfileSizeUnit;
		this.reuse = reuse;
		this.autoextend = autoextend;
	}
	
	@Override
	public String toString() {
		return "TBSFile [smallFileName=" + smallFileName + ", smallFileSize="
				+ smallFileSize + ", smallFileSizeUnit=" + smallFileSizeUnit
				+ ", reuse=" + reuse + ", autoextend=" + autoextend + "]";
	}

	public TBSFile() {
		super();
	}


	public String getSmallfileName() {
		return smallFileName;
	}


	public String getSmallFileSize() {
		return smallFileSize;
	}


	public String getSmallfileSizeUnit() {
		return smallFileSizeUnit;
	}


	public String getReuse() {
		return reuse;
	}


	public String getAutoextend() {
		return autoextend;
	}


	public void setSmallfileName(String smallfileName) {
		this.smallFileName = smallfileName;
	}


	public void setSmallFileSize(String smallFileSize) {
		this.smallFileSize = smallFileSize;
	}


	public void setSmallfileSizeUnit(String smallfileSizeUnit) {
		this.smallFileSizeUnit = smallfileSizeUnit;
	}


	public void setReuse(String reuse) {
		this.reuse = reuse;
	}


	public void setAutoextend(String autoextend) {
		this.autoextend = autoextend;
	}


	
	
}
