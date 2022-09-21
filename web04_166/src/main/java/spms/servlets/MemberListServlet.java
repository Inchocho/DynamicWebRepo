package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
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
@WebServlet(value="/member/list")
public class MemberListServlet extends HttpServlet{
	
	private static final long serialVersionUid = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		  Connection conn = null;
		  
		try {
		    ServletContext sc = this.getServletContext();
		    conn = (Connection)sc.getAttribute("conn");
		    
		    MemberDao memberDao = new MemberDao();
		    //Dao는 setConnection이 반드시 필요(여기서 로직이 실행되므로)
		    memberDao.setConnection(conn);
		    
		    ArrayList<MemberDto> memberList = null;
		    
		    memberList = (ArrayList<MemberDto>) memberDao.selectList();
		    
		    request.setAttribute("memberList", memberList);

		    RequestDispatcher rd
		    	= request.getRequestDispatcher("./MemberListView.jsp");
		    
		    rd.forward(request, response);
		    
		} catch (Exception e) {
			request.setAttribute("error", e);
			
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/Error.jsp");
			
			dispatcher.forward(request, response);
			
		} 
			
	}	      
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
	}
}
