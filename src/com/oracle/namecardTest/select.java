package com.oracle.namecardTest;

import java.sql.*;	// java의 sql에 있는 라이브러리 import

public class select {

	public static void main(String[] args) {
		//(oracle) jdbc:oracle:thin:@localhost:1521:xe
		//(mySQL) jdbc:mysql://localhost:3306/db이름
		// 미리 입력할 오라클 주소, 계정, 비밀번호 를 변수로 저장.
		String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		// 오라클 사용시 지정된 경로, 127.0.0.1 내컴퓨터의 주소. 내컴퓨터에 오라클이 설치 되어 있다면.
        String DB_USER = "scott";			// 오라클의 scott 계정에
        String DB_PASSWORD = "1234";		// 비밀번호가 1234
        // conn은 오라클 연결을 위한 객체
        Connection conn = null;
        // stmt는 sql문을 실행하기 위한 객체
        Statement stmt = null;
        // rs는 sql문 실행 후 결과 값을 받아오는 객체. 리턴값 저장을 위해.
        ResultSet rs = null;
        
        String query = "SELECT * FROM namecard ORDER BY NO";
       
        try {
        	//1. 오라클 드라이버 로딩   ( ojdbc6_g.jar ). 올려놓은 드라이버파일을 읽음.    		
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e ) { //클래스를 못찾았을때 (드라이버 없을때 )
            e.printStackTrace();
        }

        try { //DB 연결 부분은 try-catch 문으로 예외 발생시 처리
        	//2. DB 연결. 
        	//getConnection static메소드임(따로 객체생성필요없음). DB에 연결을 한다. 매개변수는 위에서 정의.
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //3. SQL 문 준비
            // connect의 메소드 중하나인 createStatement sql문을 database에 전달하기 위해서, 객체 생성.
            stmt = conn.createStatement();
            //4. SQL 쿼리 실행하고 결과 받기
            //만약 쿼리문이 insert, update, delete 라면, 리턴 받지는 않는다.
            //이경우 executeUpdate를 이용한다.
            //결과값을 받아야하는것이기에, rs에 넣음. select문은 조회,출력 쿼리문.
            //이경우 executeQuery를 이용.
            rs = stmt.executeQuery(query);	// INSERT 명령문을 쓰기에, rs는 제외.
            
            while(rs.next()) {
            	int no = rs.getInt("NO");
            	String name = rs.getString("NAME");
            	String mobile = rs.getString("MOBILE");
            	String email = rs.getString("EMAIL");
            	String company = rs.getString("COMPANY");
            	System.out.println(no+" | "+name+" | "+mobile+" | "+email+" | "+company);
            }
            
            System.out.println("입력완료");
            
        } catch (Exception e) {	// 첫 TRY문 
            e.printStackTrace();
        } finally {
            try {	// close의 순서는 입력의 역순으로, rs->stmt->conn
                stmt.close();		//State문 닫기
                conn.close();
            } catch (SQLException e) {}	// 연결을 close할때도 예외처리 한다.
        }	// finally문에서 반드시 close를 해줘야한다.
        
    }
}
