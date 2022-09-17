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
@WebServlet(value = "/member/delete")
public class MemberDeleteServlet extends HttpServlet{

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse res) 
      throws ServletException, IOException {
//	  이작업을 이제 필터로 처리할거임(9.16 Filter)
//	  CharacterEncodingFilter.java + web.xml 참조	     
//	  req.setCharacterEncoding("UTF-8");
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      //ResultSet은 SELECT문에서만 쓰인다
//    ResultSet rs = null;
      
      ServletContext sc = this.getServletContext();
      
      String driver = sc.getInitParameter("driver");
      String url = sc.getInitParameter("url");
      String user = sc.getInitParameter("user");
      String password = sc.getInitParameter("password");

      int mNo = Integer.parseInt(req.getParameter("mNo"));
      String myName = req.getParameter("myName");
      
      System.out.println(mNo + "<- mNo 값 잘가져오나 확인용");
      
      String sql = "";

      try {
         // 1단계 db 드라이버 로드
         Class.forName(driver);
         
         // 2단계 데이터베이스 연결
         conn = DriverManager.getConnection(url, user, password);
         
         sql = "DELETE FROM MEMBERS";
         sql += " WHERE MNO = ?"; 
         
         // 3단계 sql 실행 객체 준비
         pstmt = conn.prepareStatement(sql);      
         
         //의미 첫번째 ?에 들어갈 값이 mNo라는뜻 
         //즉 http://localhost:8090/web04_166/member/delete?mNo=(리스트에서 선택한 번호)
         pstmt.setInt(1, mNo);
         
         // 4단계 sql문 실행                  
         pstmt.executeUpdate();
         
         // 삭제버튼을 누르면 삭제실행 -> 회원목록으로 이동
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
      
   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse res) 
      throws ServletException, IOException {
	   
   }
   
}