package com.oracle.namecardTest;

import java.sql.*;	// java�� sql�� �ִ� ���̺귯�� import

public class preparedStatement {
	static	String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	static  String DB_USER = "scott";			
	static	String DB_PASSWORD = "1234";
	
	public static void main(String[] args) {
		//(oracle) jdbc:oracle:thin:@localhost:1521:xe
		//(mySQL) jdbc:mysql://localhost:3306/db�̸�
		
        // conn�� ����Ŭ ������ ���� ��ü
        Connection con = null;
        // rs�� sql�� ���� �� ��� ���� �޾ƿ��� ��ü. ���ϰ� ������ ����.
        PreparedStatement pstmt = null;
        
        String sql = "UPDATE NAMECARD SET NAME = ? , EMAIL = ? , COMPANY = ? WHERE NO = ?";
       
        try {
        	//1. ����Ŭ ����̹� �ε�   ( ojdbc6_g.jar ). �÷����� ����̹������� ����.    		
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e ) { //Ŭ������ ��ã������ (����̹� ������ )
            e.printStackTrace();
        }
        
        
        try { //DB ���� �κ��� try-catch ������ ���� �߻��� ó��
        	con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        	
        	pstmt = con.prepareStatement(sql);	// SQL�� �ԷµǾ� ����� �� �ִ� �ۼ��� �������� �д´�.
        	pstmt.setString(1, "IORI");			// ù��° ����ǥ, �ش� ���� �����͸� �Է�.
        	pstmt.setString(2, "DOYU");			// �ι�° ����ǥ, �ش� ���� �����͸� �Է�.
        	pstmt.setString(3, "CHINA");		// ����° ����ǥ,
        	pstmt.setInt(4,4);					// �׹�° ����ǥ, NO���� ���� �������̱⿡ int��.
        	pstmt.executeUpdate();				// executeUpdate �޼ҵ� �����Ͽ�, �������� �ݿ�.
        	
        	con.commit();						// commit�޼ҵ� �����Ͽ�, �������׵� ���� ������ ����.
            System.out.println("�Է¿Ϸ�");		// ����� �Ǿ����� ���� �޽��� ���.
            
        } catch (Exception e) {	// ù TRY�� 
            e.printStackTrace();
            System.out.println(sql);
        } finally {
            try {	// close�� ������ �Է��� ��������, ptmt->con
                pstmt.close();		//State�� �ݱ�
            } catch (SQLException e) {
            	e.printStackTrace();
            }	// ������ close�Ҷ��� ����ó�� �Ѵ�.
            try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }	// finally������ �ݵ�� close�� ������Ѵ�.
        
    }
}
