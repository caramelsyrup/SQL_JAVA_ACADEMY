package com.oracle.namecardTest;

import java.sql.*;	// java의 sql에 있는 라이브러리 import

public class preparedStatement {
	static	String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	static  String DB_USER = "scott";			
	static	String DB_PASSWORD = "1234";
	
	public static void main(String[] args) {
		//(oracle) jdbc:oracle:thin:@localhost:1521:xe
		//(mySQL) jdbc:mysql://localhost:3306/db이름
		
        // conn은 오라클 연결을 위한 객체
        Connection con = null;
        // rs는 sql문 실행 후 결과 값을 받아오는 객체. 리턴값 저장을 위해.
        PreparedStatement pstmt = null;
        
        String sql = "UPDATE NAMECARD SET NAME = ? , EMAIL = ? , COMPANY = ? WHERE NO = ?";
       
        try {
        	//1. 오라클 드라이버 로딩   ( ojdbc6_g.jar ). 올려놓은 드라이버파일을 읽음.    		
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e ) { //클래스를 못찾았을때 (드라이버 없을때 )
            e.printStackTrace();
        }
        
        
        try { //DB 연결 부분은 try-catch 문으로 예외 발생시 처리
        	con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        	
        	pstmt = con.prepareStatement(sql);	// SQL에 입력되어 실행될 수 있는 작성된 쿼리문을 읽는다.
        	pstmt.setString(1, "IORI");			// 첫번째 물음표, 해당 열의 데이터를 입력.
        	pstmt.setString(2, "DOYU");			// 두번째 물음표, 해당 열의 데이터를 입력.
        	pstmt.setString(3, "CHINA");		// 세번째 물음표,
        	pstmt.setInt(4,4);					// 네번째 물음표, NO열은 숫자 데이터이기에 int형.
        	pstmt.executeUpdate();				// executeUpdate 메소드 실행하여, 수정사항 반영.
        	
        	con.commit();						// commit메소드 실행하여, 변동사항들 전부 영구히 저장.
            System.out.println("입력완료");		// 제대로 되었으면 성공 메시지 띄움.
            
        } catch (Exception e) {	// 첫 TRY문 
            e.printStackTrace();
            System.out.println(sql);
        } finally {
            try {	// close의 순서는 입력의 역순으로, ptmt->con
                pstmt.close();		//State문 닫기
            } catch (SQLException e) {
            	e.printStackTrace();
            }	// 연결을 close할때도 예외처리 한다.
            try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }	// finally문에서 반드시 close를 해줘야한다.
        
    }
}
