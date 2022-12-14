package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.dto.MemberDto;

@WebServlet(value = "/auth/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("./LoginForm.jsp");

		rd.forward(req, resp);

	}

//	내가 만든 loginChk메소드를 사용한경우
//	@Override 
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//
//		Connection conn = null;
//
//		String name = "";
//		String email = req.getParameter("email");
//		String pwd = req.getParameter("password");
//
//		RequestDispatcher rd = null;
//
//		try {
//			MemberDto memberDto = new MemberDto();
//
//			ServletContext sc = this.getServletContext();
//			conn = (Connection) sc.getAttribute("conn");
//
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
//			memberDto = memberDao.loginChk(email, pwd);
//
//			HttpSession session = req.getSession();
//			session.setAttribute("memberDto", memberDto);
//
//			// 하나씩 지우는건 remove 다 지우는건 invalidate(LogoutServlet참조)
////				session.removeAttribute(name);
//
//			// 보안을 위해 sendRedirect로 새로 화면을 연다
//			// (req,resp 객체의 저장된값이 삭제됨)
//			resp.sendRedirect("../member/list");
//
//			// rs.next() boolean타입 true false(default true)
//
//		} catch (Exception e) {
//			// printStackTrace() 개발자를 위한 오류 - 콘솔창에 오류가뜸
//			e.printStackTrace();
//
//			req.setAttribute("error", e);
//
//			rd = req.getRequestDispatcher("/Error.jsp");
//
//			rd.forward(req, resp);
//		}
//
//	}
	
	//강사님이랑 만드는 MVC패턴 로그인(로그인 실패시도 처리함) - 메소드명 memberExist
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Connection conn = null;
		
		String email = req.getParameter("email");
		String pwd = req.getParameter("password");
		
		RequestDispatcher rd = null;


		try {
			ServletContext sc = this.getServletContext();
			
			conn = (Connection) sc.getAttribute("conn");

			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			MemberDto memberDto = memberDao.memberExist(email, pwd);
			
			// 회원이 없다면 로그인 실패 페이지로 이동
			if(memberDto == null) {
				
				rd = req.getRequestDispatcher("./LoginFail.jsp");
			
				rd.forward(req, resp);

			} else {
			
			//회원이 존재한다면 세션에 담고
			//회원 전체 조회 페이지로 이동
			
				HttpSession session = req.getSession();
				session.setAttribute("memberDto", memberDto);
				
				resp.sendRedirect("../member/list");
			}
			
		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", e);

			rd = req.getRequestDispatcher("/Error.jsp");

			rd.forward(req, resp);
		}

	}	
}
