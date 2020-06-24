package com.oracle.namecard;
//테이블 NAMECARD의 열들을 멤버변수로 하는 클래스 Namecard
// db - 클래스 
public class Namecard {
	private int no;
    private String name;
    private String mobile;
    private String email;
    private String company;
    
    // 생성자
    public Namecard() {}
    //no가 없는 생성자. 매개변수가 입력되면, 변수에 저장되도록 기능. 시퀀스를 사용하면 되기에.
    // Namecard.java 파일에 sql에 저장되는 문장에서 시퀀스를 볼수 있다.
    public Namecard(String name, String mobile, 
            String email, String company) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.company = company;
    }
    //no가 있는 생성자.
    public Namecard(int no, String name, 
            String mobile, String email, String company) {
        this.no = no;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.company = company;
    }
    // 멤버변수들을 getter/setter 세팅
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    //toString메소드는 클래스 변수들을 일렬로 문자열로 만들어 리턴
    public String toString() {
    	// 문자열을 만들려고 StringBuilder를 이용.
        StringBuilder sb = new StringBuilder();
        sb.append("[NO: ");
        sb.append(no);
        sb.append("] ");
        sb.append(name);
        sb.append(" ,Mobile: ");
        sb.append(mobile);
        sb.append(" ,Email: ");
        sb.append(email);
        sb.append(" ,Company: ");
        sb.append(company);
     // 멤버변수의 값들을 모아서 하나의 문자열로 리턴
        return sb.toString();
    }
    
}
