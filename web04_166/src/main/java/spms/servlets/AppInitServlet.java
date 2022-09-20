package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet {

	@Override
	//init() 초기화 - 서버가 실행되면서 최초 한번 실행 (DB연결정보를 초기화함)
	public void init(ServletConfig config) throws ServletException {

		System.out.println("AppInitServlet 준비...");
		super.init(config);

		ServletContext sc = this.getServletContext();

		Connection conn = null;

		String driver = "";
		String url = "";
		String user = "";
		String password = "";

		try {
			driver = sc.getInitParameter("driver");
			url = sc.getInitParameter("url");
			user = sc.getInitParameter("user");
			password = sc.getInitParameter("password");

			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, password);

			sc.setAttribute("conn", conn);
			System.out.println("DB연결 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	//destroy() 서버 연결 종료시 실행됨
	public void destroy() {
		super.destroy();

		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		try {
			if (conn != null) {
				conn.close();
				System.out.println("DB연결 해제 자원 회수");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	//destroy() end
}
