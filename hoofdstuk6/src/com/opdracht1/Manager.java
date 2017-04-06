package com.opdracht1;

public class Manager {
	public static void main(String args[]) {
		Factory factory = new FactoryImpl();
		
		FileClientDao fcd = factory.createFileClientDao();
		
		JDBCClientDao jdbc = factory.createJDBCClientDao();
	}
}