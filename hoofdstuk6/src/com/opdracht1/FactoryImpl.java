package com.opdracht1;

public class FactoryImpl extends Factory {
	public FileClientDao createFileClientDao() {
		return new FileClientDao();
	}
	
	public JDBCClientDao createJDBCClientDao() {
		return new JDBCClientDao();
	}
}