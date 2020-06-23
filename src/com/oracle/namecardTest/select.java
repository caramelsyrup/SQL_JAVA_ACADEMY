package com.oracle.namecardTest;

import java.sql.*;	// java�� sql�� �ִ� ���̺귯�� import

public class select {

	public static void main(String[] args) {
		//(oracle) jdbc:oracle:thin:@localhost:1521:xe
		//(mySQL) jdbc:mysql://localhost:3306/db�̸�
		// �̸� �Է��� ����Ŭ �ּ�, ����, ��й�ȣ �� ������ ����.
		String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		// ����Ŭ ���� ������ ���, 127.0.0.1 ����ǻ���� �ּ�. ����ǻ�Ϳ� ����Ŭ�� ��ġ �Ǿ� �ִٸ�.
        String DB_USER = "scott";			// ����Ŭ�� scott ������
        String DB_PASSWORD = "1234";		// ��й�ȣ�� 1234
        // conn�� ����Ŭ ������ ���� ��ü
        Connection conn = null;
        // stmt�� sql���� �����ϱ� ���� ��ü
        Statement stmt = null;
        // rs�� sql�� ���� �� ��� ���� �޾ƿ��� ��ü. ���ϰ� ������ ����.
        ResultSet rs = null;
        
        String query = "SELECT * FROM namecard ORDER BY NO";
       
        try {
        	//1. ����Ŭ ����̹� �ε�   ( ojdbc6_g.jar ). �÷����� ����̹������� ����.    		
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e ) { //Ŭ������ ��ã������ (����̹� ������ )
            e.printStackTrace();
        }

        try { //DB ���� �κ��� try-catch ������ ���� �߻��� ó��
        	//2. DB ����. 
        	//getConnection static�޼ҵ���(���� ��ü�����ʿ����). DB�� ������ �Ѵ�. �Ű������� ������ ����.
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //3. SQL �� �غ�
            // connect�� �޼ҵ� ���ϳ��� createStatement sql���� database�� �����ϱ� ���ؼ�, ��ü ����.
            stmt = conn.createStatement();
            //4. SQL ���� �����ϰ� ��� �ޱ�
            //���� �������� insert, update, delete ���, ���� ������ �ʴ´�.
            //�̰�� executeUpdate�� �̿��Ѵ�.
            //������� �޾ƾ��ϴ°��̱⿡, rs�� ����. select���� ��ȸ,��� ������.
            //�̰�� executeQuery�� �̿�.
            rs = stmt.executeQuery(query);	// INSERT ��ɹ��� ���⿡, rs�� ����.
            
            while(rs.next()) {
            	int no = rs.getInt("NO");
            	String name = rs.getString("NAME");
            	String mobile = rs.getString("MOBILE");
            	String email = rs.getString("EMAIL");
            	String company = rs.getString("COMPANY");
            	System.out.println(no+" | "+name+" | "+mobile+" | "+email+" | "+company);
            }
            
            System.out.println("�Է¿Ϸ�");
            
        } catch (Exception e) {	// ù TRY�� 
            e.printStackTrace();
        } finally {
            try {	// close�� ������ �Է��� ��������, rs->stmt->conn
                stmt.close();		//State�� �ݱ�
                conn.close();
            } catch (SQLException e) {}	// ������ close�Ҷ��� ����ó�� �Ѵ�.
        }	// finally������ �ݵ�� close�� ������Ѵ�.
        
    }
}
