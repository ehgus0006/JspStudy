package ch09.update;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import work.crypt.BCrypt;
import work.crypt.SHA256;


public class UpdateDBBean {
	
	private static UpdateDBBean instance = new UpdateDBBean();
	
	public static UpdateDBBean getInstance() {
		return instance;
	}
	
	private UpdateDBBean() {};
	
	private Connection getConnection() throws Exception{

		Connection conn = null;
		
		String url = "jdbc:mysql://127.0.0.1:3306/study?serverTimezone=UTC&amp;useSSL=false";
		String ids = "root";
		String pw = "rlaehgus12!";
		
		Class.forName("com.mysql.jdbc.Driver");
		
		conn = (Connection) DriverManager.getConnection(url,ids,pw);
		
		return conn;
		
	}
	
	// member table의 내용을 얻어내며, cryptProcessList.jsp 페이지에서 사용
	public List<UpdateDataBean> getMembers() throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		List<UpdateDataBean> memberList = null;
		
		int x = 0;
		
		try {
			conn = getConnection();
			
			pstmt = (PreparedStatement)conn.prepareStatement("select count(*) from member");
			rs = pstmt.executeQuery();
			
			if(rs.next()) x=rs.getInt(1);
			System.out.println("count="+ x);
			
			pstmt = (PreparedStatement)conn.prepareStatement("select id, passwd from member");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberList = new ArrayList<UpdateDataBean>(x);
				do {
					UpdateDataBean member = new UpdateDataBean();
					
					member.setId(rs.getString("id"));
					member.setPasswd(rs.getString("passwd"));
					memberList.add(member);
				}while(rs.next());
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{rs.close();}catch(SQLException sqle){}
			}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException sqle){}
			}
			if(conn != null){
				try{conn.close();}catch(SQLException sqle){}
			}
		
		}
		return memberList;
	}
	
	public void updateMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		SHA256 sha = SHA256.getInsatnce();
		
		try {
			conn = getConnection();
			pstmt = (PreparedStatement)conn.prepareStatement("select id, passwd from member");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("id");
				String orgPass = rs.getString("passwd");
				
				//getSha256() 메소드를 통해 변경
				
				String shaPass = sha.getSha256(orgPass.getBytes());
				
				// sha256 방식으로 암호화된 값을 Bcrypt 클래스의 hashPw() 메소드를 사용해서 Bcrypt 방식으로 암호화 
				// Bcrypt.gensalt() 메소드는 salt 값을 난수로 사용해 생성
				String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
				
				pstmt = (PreparedStatement)conn.prepareStatement("update member set passwd=? where id=?");
				pstmt.setString(1, bcPass);
				pstmt.setString(2, id);
				pstmt.executeLargeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{rs.close();}catch(SQLException sqle){}
			}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException sqle){}
			}
			if(conn != null){
				try{conn.close();}catch(SQLException sqle){}
			}
		
		}
		
	}

}
