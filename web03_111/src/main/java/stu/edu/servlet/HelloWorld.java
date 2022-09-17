package stu.edu.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//Servlet이 되어야만 Java Server Page가 html화 되어 화면이 되는것이다
public class HelloWorld implements Servlet{

	ServletConfig config;
	
	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		System.out.println("getServletConfig 호출");
		
		return this.config;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		System.out.println("getServletInfo 호출");
		
		return "";
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// 최초 한번만 호출된다(init = 초기화 initialized)
		System.out.println("init 호출을 변경");
		
		this.config = config;	//이시점에서 config에는 web에 있는 정보가 다 들어있다
		
		String emailStr = config.getInitParameter("email");
		
		System.out.println(emailStr);
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 작업할때마다 서비스는 무한으로 실행됨
		System.out.println("service 호출을 변경함");
		System.out.println("이번에는 좀 더 빠르게 작업한다");
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		// 서버를 내릴때 destroy이가 실행된다(객체 소멸, 자원을 회수) 
		System.out.println("destroy 호출");
	}
	

}
