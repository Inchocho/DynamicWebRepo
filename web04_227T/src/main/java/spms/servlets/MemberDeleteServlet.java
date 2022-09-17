package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberDeleteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");
		 
	      Connection conn = null;
	      PreparedStatement pstmt = null;
		
	      ServletContext sc = this.getServletContext();
	      
	      String driver = sc.getInitParameter("driver");
	      String url = sc.getInitParameter("url");
	      String user = sc.getInitParameter("user");
	      String password = sc.getInitParameter("password");
	      
	      int mNo = Integer.parseInt(req.getParameter("mNo"));
	      
	      String sql = "";
	      
	      try {
	         // 1단계 db 드라이버 로드
	         Class.forName(driver);
	         System.out.println("오라클 드라이버 로드");
	         
	         // 2단계 데이터베이스 연결
	         conn = DriverManager.getConnection(url, user, password);

	         sql += "DELETE FROM MEMBERS";	         
	         sql += " WHERE MNO = ?";
	                                                   
	         // 3단계 sql 실행 객체 준비
	         pstmt = conn.prepareStatement(sql);      
	         
	         // ? 순서대로 변수(파라미터) 입력 1번째 물음표 , email ...~
	         pstmt.setInt(1, mNo);
	         
	         // 4단계 sql문 실행
	         //SELECT조회를 제외한 나머지는 executeUpdate입니다
	         pstmt.executeUpdate();
	                  
	         res.sendRedirect("./list");
	         
	      } catch (ClassNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	         // 6 단계 jdbc 객체 메모리 회수
	         if(pstmt != null) {
	            try {
	               pstmt.close();
	            } catch (SQLException e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	            }
	         }
	         
	         if(conn != null) {
	            try {
	               conn.close();
	            } catch (SQLException e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	            }
	         }
	      } // finally end
	      
	   } // doPost end	      
	
//	   	      
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
	}
}
