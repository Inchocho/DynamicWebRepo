package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		  Connection conn = null;
		  Statement stmt = null;	
		  ResultSet rs = null;
		
	      ServletContext sc = this.getServletContext();
	      
	      String driver = sc.getInitParameter("driver");
	      String url = sc.getInitParameter("url");
	      String user = sc.getInitParameter("user");
	      String password = sc.getInitParameter("password");

		try {
			//1단계 : DB 드라이버 로드
			Class.forName(driver);
			
			//2단계 : DB(데이터베이스) 연결
			conn = DriverManager.getConnection(url, user, password);
			
			//3단계 : SQL 실행 객체 준비
			stmt = conn.createStatement();
			
			//SQL 생성(SQL문법 맞춰서 작성해야한다)
			String sql = "SELECT MNO, PWD, MNAME, EMAIL, CRE_DATE"
					+ " FROM MEMBERS"
					+ " ORDER BY MNO ASC";			
			
			//4단계 : SQL문 수행(SELECT 조회시 executeQuery)
			rs = stmt.executeQuery(sql);
			
			//5단계 : SELECT 결과물 활용하기
			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");
			
			PrintWriter out = res.getWriter();
			
			String htmlStr = "";	
			
			htmlStr += "";
			
			htmlStr += "<p>";
			htmlStr += "<a href='./add'>신규회원</a>";
			htmlStr += "</p>";			
			
			out.println("<html><head><title>회원목록</title></head>");
			out.println("<body><h1>회원목록</h1>");
			out.println(htmlStr);
			
			
			while(rs.next() == true) {
				out.println(
					rs.getInt("MNO") + "," +					
					"<a href='./update?mNo=" +
						rs.getInt("MNO") + 
					"'>" +
					rs.getString("MNAME") + "</a>," +
					rs.getString("EMAIL") + "," +					
					rs.getDate("CRE_DATE") + 
					"<a href='./delete?mNo=" +
						rs.getInt("mNo") +
						"'>[삭제]</a>" +
					"<br>"
				);
			}
			out.println("</body></html>");
			
			System.out.println(rs.getFetchSize());
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			//	6단계 jdbc 객체 메모리 회수
			//	rs(resultSet)가 null이 아니라면 rs를 종료(자원 회수)
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
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
		}
		
	}	      
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
	}
}
