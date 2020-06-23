package com.oracle.test;

import java.sql.*;	// java의 sql에 있는 라이브러리 import

public class GetEmp {

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
        
        String query = "SELECT * FROM emp";
        // scott계정에 있는 emp테이블의 모든 자료 조회
        // sql에서 작성하던 문법 그대로써서 query에 저장.
        String query2 = "SELECT * FROM emp WHERE empno = 7369";
        
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
            rs = stmt.executeQuery(query2);
           
            while (rs.next()) { // 테이블의 한 행씩 실행, 테이블의 행이 더이상 없으면 종료.
                String empno = rs.getString("EMPNO");  
                // 첫번째 열, getString에 열순서대신 열의 이름을 넣어도 자료를 받음.
                String ename = rs.getString(2);
                String job = rs.getString(3);
                String mgr = rs.getString(4);
                String hiredate = rs.getString(5);
                String sal = rs.getString(6);
                String comm = rs.getString(7);
                String depno = rs.getString(8);	// 총 8개의 열이다. sql에가서 테이블 확인.
                 
                System.out.println(empno + " |" + ename + " | " + job + " | " + mgr 
                	+ " | " + hiredate + " | " + sal + " | " + comm + " | " + depno); 
            }
            
        } catch (Exception e) {	// 첫 TRY문 
            e.printStackTrace();
        } finally {
            try {	// close의 순서는 입력의 역순으로, rs->stmt->conn
                rs.close(); 		//ResultSet (쿼리 결과) 닫기
                stmt.close();		//State문 닫기
                conn.close();
            } catch (SQLException e) {}	// 연결을 close할때도 예외처리 한다.
        }	// finally문에서 반드시 close를 해줘야한다.
        
    }
}
