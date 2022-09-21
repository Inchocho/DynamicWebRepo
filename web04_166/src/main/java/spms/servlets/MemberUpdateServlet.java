package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.dto.MemberDto;

//@WebServlet(value ="주소")를 통해 web.xml에서 서블릿선언,맵핑과정을 단축함
@WebServlet(value="/member/update")
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Connection conn = null;
		
		//반복사용되는 RequestDispatcher타입 객체를 생성함
		RequestDispatcher rd = null;

		MemberDto memberDto = new MemberDto();
		int no = 0;

		try {
			no = Integer.parseInt(req.getParameter("no"));
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");
			
		    MemberDao memberDao = new MemberDao();
		    memberDao.setConnection(conn);
		    
		    memberDto = memberDao.selectOne(no);
			
		    req.setAttribute("memberDto", memberDto);

			rd = req.getRequestDispatcher("./MemberUpdateForm.jsp");
			
			rd.forward(req, res);

		} catch (Exception e) {
			//printStackTrace() 개발자를 위한 오류 - 콘솔창에 오류가뜸
			e.printStackTrace();
			
			req.setAttribute("error", e);

			rd = req.getRequestDispatcher("/Error.jsp");
			
			rd.forward(req, res);
		} 

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
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