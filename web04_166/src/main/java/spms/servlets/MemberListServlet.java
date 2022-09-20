package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

import spms.dto.MemberDto;

//@WebServlet(value ="주소")를 통해 web.xml에서 서블릿선언,맵핑과정을 단축함
@WebServlet(value="/member/list")
//MVC패턴중 실제업무를 수행하는 MODEL
public class MemberListServlet extends HttpServlet{
	
	private static final long serialVersionUid = 1L;

	@Override
	protected void doGet(HttpServletRequest request
			, HttpServletResponse response)
			throws ServletException, IOException {
		
		  Connection conn = null;
		  Statement stmt = null;	
		  ResultSet rs = null;
		
		try {
			//ServletContext를 통해 AppInitServlet에 담긴 sc.setAttribute("conn", conn); (DB연결정보)
			//를 가져와서 사용 (DB연결 user / pwd / driver정보등 공통정보를 가져와서사용
		    ServletContext sc = this.getServletContext();
		    
		    conn = (Connection)sc.getAttribute("conn");
			
			stmt = conn.createStatement();
			
			String sql = "SELECT MNO, PWD, MNAME, EMAIL, CRE_DATE"
					+ " FROM MEMBERS"
					+ " ORDER BY MNO ASC";			
			
			System.out.println("쿼리 수행 성공");
			rs = stmt.executeQuery(sql);
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			ArrayList<MemberDto> memberList = new ArrayList<>();
			
			int mno = 0;
			String mname = "";
			String email = "";
			Date creDate = null;
			
			while(rs.next()) {
				mno = rs.getInt("MNO");
				mname = rs.getString("MNAME");
				email = rs.getString("EMAIL");
				creDate = rs.getDate("CRE_DATE");
				
				MemberDto memberDto = new MemberDto();
				
				memberDto.setNo(mno);
				memberDto.setName(mname);
				memberDto.setEmail(email);
				memberDto.setCreateDate(creDate);
				
				//[1]memberList에 값을 담았음
				memberList.add(memberDto);
			}
			
			//jsp로 객체 그자체로 넘김(setAttribute를 통해 객체전달)
			//void javax.servlet.ServletRequest.setAttribute(String name, Object o)
			//Parameter는 문자열만 가능 애초에 httpServletRequest에는 setParameter가없음
			//key,value 형식으로 전달함(key: memberList, value: [1]memberList)
			
			request.setAttribute("memberList", memberList);
			
			//javax.servlet.ServletException:File &quot not found 에러떴었음 (경로에러임)
			//RequestDispatcher를 통해 request,response객체의 값을 들고 
			//MemberListView.jsp로 이동한다(그래서 jsp를 사용가능)
			
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/member/MemberListView.jsp");
			
			dispatcher.include(request, response);			
			
		} catch (Exception e) {
//			throw new ServletException(e); -> 개발자용
			//에러확인하려고 서비스에서 오라클서비스랑 리스너 중지했었음
			//예외발생시 키:error에 파라미터(매개변수) e를 담음(Exception e)
			request.setAttribute("error", e);
			
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/Error.jsp");
			
			dispatcher.forward(request, response);
			
		} finally {
			//	6단계 jdbc 객체 메모리 회수
			//	rs(resultSet)가 null이 아니라면 rs를 종료(자원 회수)
			if(rs != null) {
				try {
					rs.close();
					System.out.println("ResultSet 종료");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
					System.out.println("쿼리 종료");
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
