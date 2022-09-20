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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(value ="주소")를 통해 web.xml에서 서블릿선언,맵핑과정을 단축함
@WebServlet(value="/member/update")
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String myName = req.getParameter("myName");		

		String sql = "";

		try {
			int mNo = Integer.parseInt(req.getParameter("no"));
			
			System.out.println("mNo 가져오나 확인용: " + mNo + " 연습용 추가 파라미터 이름: " + myName);			

			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			sql = "SELECT MNAME, EMAIL, PWD, CRE_DATE";
			sql += " FROM MEMBERS";
			sql += " WHERE MNO = ?";

			// 3단계 sql 실행 객체 준비
			pstmt = conn.prepareStatement(sql);

			// 의미 첫번째 ?에 들어갈 값이 mNo라는뜻
			// 즉 http://localhost:8090/web04_166/member/update?mNo=1
			pstmt.setInt(1, mNo);

			// 4단계 sql문 실행
			rs = pstmt.executeQuery();

			String mName = "";
			String email = "";
			// Date import -> java.util로
			Date creDate = null;

			while (rs.next()) {
				// 코드 스타일(DB에 있는 테이블 속성값을 가져오는것이므로 대문자로)
				mName = rs.getString("MNAME");
				email = rs.getString("EMAIL");
				creDate = rs.getDate("CRE_DATE");
			}

			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");

			PrintWriter out = res.getWriter();

			String htmlStr = "";

			htmlStr += "<html><head><title>회원 정보</title></head>";
			htmlStr += "<body>";
			htmlStr += "<h1>회원정보</h1>";
			htmlStr += "<form action='update' method='post'>";
			htmlStr += "번호: <input type='text' name='mNo' " + "value='" + mNo + "' readonly><br>";
			htmlStr += "이름: <input type='text' name='name'" + " value='" + mName + "'><br>";
			htmlStr += "이메일: <input type='text' name='email'" + " value='" + email + "'><br>";
			htmlStr += "가입일: " + creDate + "<br>";
			htmlStr += "<input type='submit' value='저장'>";
			htmlStr += "<input type='reset' value='취소' onclick='location.href=\"./list\"'>";
			htmlStr += "</form>";
			htmlStr += "</body>";
			htmlStr += "</html>";

			out.println(htmlStr);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// 6 단계 jdbc 객체 메모리 회수
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} // finally end

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

//	  이작업을 이제 필터로 처리할거임(9.16 Filter)
//	  CharacterEncodingFilter.java + web.xml 참조	     
//	  req.setCharacterEncoding("UTF-8");

		Connection conn = null;
		PreparedStatement pstmt = null;

		String email = req.getParameter("email");
		String name = req.getParameter("name");

		int mNo = Integer.parseInt(req.getParameter("mNo"));

		String sql = "";

		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			sql += "UPDATE MEMBERS";
			sql += " SET EMAIL=?, MNAME=?, MOD_DATE = SYSDATE";
			sql += " WHERE MNO = ?";

			// 3단계 sql 실행 객체 준비
			pstmt = conn.prepareStatement(sql);

			// ? 순서대로 변수(파라미터) 입력 1번째 물음표 , email ...~
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			pstmt.setInt(3, mNo);

			// 4단계 sql문 실행
			// SELECT조회를 제외한 나머지는 executeUpdate입니다
			System.out.println(mNo + " 번 수정하였습니다 콘솔");
			pstmt.executeUpdate();

			res.sendRedirect("./list");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 6 단계 jdbc 객체 메모리 회수
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} // finally end

	} // doPost end

}