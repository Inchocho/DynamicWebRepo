package spms.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.dto.MemberDto;

@WebServlet(value = "/member/add")
public class MemberAddServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.sendRedirect("./MemberForm.jsp");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
	    Connection conn = null;

	    MemberDto memberDto = new MemberDto();
	    
	    int result = 0;
		try {
			String email = req.getParameter("email");
			String pwd = req.getParameter("password");
			String name = req.getParameter("name");
			
			memberDto.setEmail(email);
			memberDto.setPassword(pwd);
			memberDto.setName(name);			
			
			System.out.println("memberDto로 값이 잘들어감 1번");
		    ServletContext sc = this.getServletContext();
		    
		    conn = (Connection)sc.getAttribute("conn");	
		    
		    MemberDao memberDao = new MemberDao();
		    memberDao.setConnection(conn);
		   
		    System.out.println("여기까지 도착하니? 2번");
		    
		    // 0이면 넣은 데이터가 없다 0 이외에는 성공
		    result = memberDao.memberInsert(memberDto);
		    System.out.println(result +": 값이 0이 아니면 성공");
		    
		    if(result == 0) {
		    	System.out.println("회원가입실패");
		    }
		    
		    System.out.println("여기까지 도착하니?2");
			res.sendRedirect("./list");
			
		} catch (Exception e) {
			req.setAttribute("error", e);
			
			RequestDispatcher dispatcher = 
					req.getRequestDispatcher("/Error.jsp");			
			dispatcher.forward(req, res);
			
		} 
		
	}
	
}
