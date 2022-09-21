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

		Connection conn = null;
		RequestDispatcher rd = null;

		try {
			int no = Integer.parseInt(req.getParameter("mNo"));
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			
			memberDao.setConnection(conn);
			memberDao.updateOne(email, name, no);

			res.sendRedirect("./list");

		} catch (Exception e) {
			//printStackTrace() 개발자를 위한 오류 - 콘솔창에 오류가뜸
			e.printStackTrace();
			
			req.setAttribute("error", e);
			
			rd = req.getRequestDispatcher("/Error.jsp");
			
			rd.forward(req, res);
		} 

	} // doPost end

}