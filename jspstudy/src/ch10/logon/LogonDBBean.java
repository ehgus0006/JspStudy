package ch10.logon;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import ch09.update.UpdateDBBean;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class LogonDBBean {

	private static LogonDBBean instance = new LogonDBBean();
	
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	private LogonDBBean() {};
	
	private Connection getConnection() throws Exception{

		Connection conn = null;
		
		String url = "jdbc:mysql://127.0.0.1:3306/study?serverTimezone=UTC&amp;useSSL=false";
		String ids = "root";
		String pw = "rlaehgus12!";
		
		Class.forName("com.mysql.jdbc.Driver");
		
		conn = (Connection) DriverManager.getConnection(url,ids,pw);
		
		return conn;
		
	}
	
	public int userCheck(String id, String passwd) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = getConnection();
			
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			pstmt = conn.prepareStatement("select passwd from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 아이디가 잇으면 수행
				String dbpasswd = rs.getString("passwd");
				if(BCrypt.checkpw(shaPass, dbpasswd))
				x=1; // 인증성공
				else
				x=0; // 비밀번호 틀림
			}else { // 아이디가 없으면 수행
				x=-1;
				
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
		
		return x;
	}
}
