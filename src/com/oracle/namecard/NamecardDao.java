package com.oracle.namecard;

import java.sql.*;
import java.util.ArrayList;
// Dao: �����Ϳ� �����ϴ� ��ü
// DAO Ŭ������ �����Ͽ� ��ü ������
// INSERT, SELECT, UPDATE, DELETE. �� DML �۾��� �Ҽ� �ֵ���
public class NamecardDao {
    static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";  
    static final String USER = "scott";
    static final String PASSWORD = "1234";
    
    // 1.����̹��ε�. ���� DML �۾��� �Ϸ���, ������ ����̹��ε带 �ؾ��Ѵ�. �޼ҵ带 ����� ����.
    public NamecardDao() {
        try { // ����Ŭ DB ����̹� �ε�
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    //2.DB�� �����ϴ� �޼ҵ�. Connection���� ������ �ϴ� �޼ҵ���.
    private Connection getConnection() throws SQLException {
    	// DB ������ �޼ҵ�� �����
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    		//���� ResultSet �� �ΰ��� �ƴϸ� ����, ���� Statement ����, ���������� Ŀ�ؼ� ����
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
    // 3.�Է� �޼ҵ�
    public void insert(Namecard card) {
        Connection con = null;			// �ʱ�ȭ �ܰ�.
        PreparedStatement pstmt = null;	// �ʱ�ȭ �ܰ�.
        
        String sql = "INSERT INTO namecard VALUES (CARD_NO.NEXTVAL, ?, ?, ?, ?)";

        try {
        	//DB����, getConnection�� �̸� ���Ǹ� �س���, SQL���� ����Ҷ����� ����� �Ѵ�.
            con = getConnection();
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, card.getName());
            pstmt.setString(2, card.getMobile());
            pstmt.setString(3, card.getEmail());
            pstmt.setString(4, card.getCompany());
            pstmt.executeUpdate();
            con.commit();	// ������ ����.
            System.out.println("�Է¿Ϸ�");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        } finally {
        	// null�� rs�� ���� sql���� ����߱� ������.
            close(null, pstmt, con);	
        }// �۾��� ���� �Ŀ��� �ݵ�� �ݾ��ش�.
    }
    
    //��ȣ no �� ����    
    public void delete(int no) {
       //�ۼ��ϱ�
    	 Connection con = null;			
         PreparedStatement pstmt = null;	
         
         String sql = "DELETE FROM namecard WHERE NO = ?";

         try {
         	//DB����
             con = getConnection();
             pstmt = con.prepareStatement(sql);
             pstmt.setInt(1, no);	// delete�޼ҵ��� �Ű������� �� db���̺��� no�̴�.
             pstmt.executeUpdate();
             con.commit();	// ������ ����.
             System.out.println("�����Ϸ�");
         } catch (SQLException e) {
             e.printStackTrace();
             System.out.println(sql);
         } finally {
         	// null�� rs�� ���� sql���� ����߱� ������.
             close(null, pstmt, con);	
         }// �۾��� ���� �Ŀ��� �ݵ�� �ݾ���
    }
    
    //��ȣ no �� �˻�
    public Namecard selectOne(int no) {
       //�ۼ��ϱ�
        return null;
    }
    
    //�̸����� ã��
    public ArrayList<Namecard> selectByKeyword(String keyword) {       
        ArrayList<Namecard> matched = new ArrayList<Namecard>();
        // �ۼ��ϱ�  
        return matched;
    }
    
    //Namecard�� ��� ��/���� �������� ��ȣ������ ArrayList�� ����ؼ� �ڷ���� �迭ȭ.
    public ArrayList<Namecard> selectAll() {
        ArrayList<Namecard> all = new ArrayList<Namecard>();
        // �ۼ��ϱ�
        return all; 
    }        
    
    //�����ϱ�
    public void update(Namecard card) {
    	//�ۼ��ϱ�
    }

}
