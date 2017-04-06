package com.opdracht1;

public abstract class Factory {
	public abstract FileClientDao createFileClientDao();
	
	public abstract JDBCClientDao createJDBCClientDao();
}