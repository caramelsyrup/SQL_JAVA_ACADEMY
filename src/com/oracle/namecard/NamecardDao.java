package com.oracle.namecard;

import java.sql.*;
import java.util.ArrayList;
// Dao: 데이터에 접근하는 객체
// DAO 클래스를 정의하여 객체 생성시
// INSERT, SELECT, UPDATE, DELETE. 의 DML 작업을 할수 있도록
public class NamecardDao {
    static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";  
    static final String USER = "scott";
    static final String PASSWORD = "1234";
    
    // 1.드라이버로드. 각종 DML 작업을 하려면, 무조건 드라이버로드를 해야한다. 메소드를 만들어 놓음.
    public NamecardDao() {
        try { // 오라클 DB 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    //2.DB에 연결하는 메소드. Connection으로 리턴을 하는 메소드임.
    private Connection getConnection() throws SQLException {
    	// DB 연결을 메소드로 만들기
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    		//먼저 ResultSet 이 널값이 아니면 종료, 다음 Statement 종료, 마지막으로 커넥션 종료
    private void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // 3.입력 메소드
    public void insert(Namecard card) {
        Connection con = null;			// 초기화 단계.
        PreparedStatement pstmt = null;	// 초기화 단계.
        
        String sql = "INSERT INTO namecard VALUES (CARD_NO.NEXTVAL, ?, ?, ?, ?)";

        try {
        	//DB연결, getConnection을 미리 정의를 해놓고, SQL문을 사용할때마다 사용을 한다.
            con = getConnection();
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, card.getName());
            pstmt.setString(2, card.getMobile());
            pstmt.setString(3, card.getEmail());
            pstmt.setString(4, card.getCompany());
            pstmt.executeUpdate();
            con.commit();	// 데이터 저장.
            System.out.println("입력완료");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        } finally {
        	// null은 rs는 없는 sql문을 사용했기 때문에.
            close(null, pstmt, con);	
        }// 작업이 끝난 후에는 반드시 닫아준다.
    }
    
    //번호 no 행 삭제    
    public void delete(int no) {
       //작성하기
    	 Connection con = null;			
         PreparedStatement pstmt = null;	
         
         String sql = "DELETE FROM namecard WHERE NO = ?";

         try {
         	//DB연결
             con = getConnection();
             pstmt = con.prepareStatement(sql);
             pstmt.setInt(1, no);	// delete메소드의 매개변수가 곧 db테이블의 no이다.
             pstmt.executeUpdate();
             con.commit();	// 데이터 저장.
             System.out.println("삭제완료");
         } catch (SQLException e) {
             e.printStackTrace();
             System.out.println(sql);
         } finally {
         	// null은 rs는 없는 sql문을 사용했기 때문에.
             close(null, pstmt, con);	
         }// 작업이 끝난 후에는 반드시 닫아준
    }
    
    //번호 no 행 검색
    public Namecard selectOne(int no) {
       //작성하기
        return null;
    }
    
    //이름으로 찾기
    public ArrayList<Namecard> selectByKeyword(String keyword) {       
        ArrayList<Namecard> matched = new ArrayList<Namecard>();
        // 작성하기  
        return matched;
    }
    
    //Namecard의 모든 행/열을 가져오기 번호순으로 ArrayList를 사용해서 자료들을 배열화.
    public ArrayList<Namecard> selectAll() {
        ArrayList<Namecard> all = new ArrayList<Namecard>();
        // 작성하기
        return all; 
    }        
    
    //수정하기
    public void update(Namecard card) {
    	//작성하기
    }

}
