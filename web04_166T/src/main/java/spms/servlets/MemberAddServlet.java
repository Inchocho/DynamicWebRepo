package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 신규 회원 추가
public class MemberAddServlet extends HttpServlet {
	
	//doGet 자동완성
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {	
		
		System.out.println("method='get'을 타면 여기가 콘솔에 나와요.");
		
		res.setContentType("text/html");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		
		String htmlStr = "";
		
		htmlStr	+= "<html><head><title>회원 등록</title></head>";
		htmlStr	+= "<body>";
		htmlStr	+= "<h1>회원등록</h1>";
		htmlStr	+= "<form action='add' method='post'>";	
		htmlStr	+= "이름: <input type='text' name='name'><br>";
		htmlStr	+= "이메일: <input type='text' name='email'><br>";
		htmlStr	+= "암호: <input type='password' name='password'><br>";
		htmlStr	+= "<input type='submit' value='추가'>";
		htmlStr += "<input type='reset' value='취소' onclick='location.href=\"./list\"'>";		
		htmlStr	+= "</form>";
		htmlStr	+= "</body></html>";		
				
		out.print(htmlStr);
	}
	
	//doPost 자동완성
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		System.out.println("method='post'를 타면 여기가 콘솔에 나와요.");
		//조회가 아닌 추가에서는 resultSet을 사용하지 않는다
		//Statement가 아닌 PreparedStatement를 사용한다
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      ServletContext sc = this.getServletContext();
	      
	      String driver = sc.getInitParameter("driver");
	      String url = sc.getInitParameter("url");
	      String user = sc.getInitParameter("user");
	      String password = sc.getInitParameter("password");
		
//		한글깨짐 인코딩처리
//		HttpServletRequest 객체로부터 파라미터값을 가져오는데
//		이때 인코딩처리가 안되있기 때문에 한글깨짐 문제가생겼던것이다)
		req.setCharacterEncoding("UTF-8");
		
		String emailStr = req.getParameter("email");
		String pwdStr = req.getParameter("password");
		String nameStr = req.getParameter("name");
		
		try {
			//1단계 : DB 드라이버 로드
			Class.forName(driver);
			
			//2단계 : DB(데이터베이스) 연결
			conn = DriverManager.getConnection(url, user, password);
			
			//SQL 생성(SQL문법 맞춰서 작성해야한다)
			String sql = "";
			
			sql += "INSERT INTO MEMBERS ";
			sql += "VALUE(MNO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)";
			sql += "VALUES(MEMBERS_MNO_SEQ.NEXTVAL, ?";
			sql += ", ?, ?, SYSDATE, SYSDATE)";
			
			//3단계 : SQL 실행 객체 준비
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emailStr);
			pstmt.setString(2, pwdStr);
			pstmt.setString(3, nameStr);
			
			//4단계 sql문실행
			//SELECT조회를 제외한 나머지는 executeUpdate입니다
			pstmt.executeUpdate();
			
			res.sendRedirect("./list");
			
			/*
			 * //5단계 : 등록성공 메시지 보여주는데 한글 안깨지게 하려고 //+ html로 보여주기위해서 ContentType과 인코딩을 설정해줌
			 * res.setContentType("text/html"); res.setCharacterEncoding("UTF-8");
			 * 
			 * PrintWriter out = res.getWriter();
			 * 
			 * String htmlStr = "";
			 * 
			 * htmlStr += "<html><head><title>회원등록결과</title>"; // 새로고침을 자동으로 수행하게 만듬(url은
			 * 동일디렉토리에서 list로) // 즉 member/add 에서 1초후 member/list로 새로고침됨 // content는
			 * 걸리는시간(content = 1 => 1초) htmlStr +=
			 * "<meta http-equiv='Refresh' content='5; url=list'>"; htmlStr += "</head>";
			 * htmlStr += "<body>"; htmlStr += "<p>등록 성공입니다!</p>"; htmlStr +=
			 * "</body></html>";
			 * 
			 * out.print(htmlStr);
			 */
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {

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
		}	
	}
	
}
