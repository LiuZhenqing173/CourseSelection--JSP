package com.mountain.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mountain.domain.Student;
import com.mountain.service.UserService;
/**
 * 处理登陆，注销的操作
 * 	 此处的注销功能没有实现，是在JavaScript中直接实现了
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理登陆的操作
		//0.设置文字的编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//1.可以引入一个操作类型的参数
		String op = request.getParameter("op");
		if ("login".equals(op)) {
			try {
				login(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("logout".equals(op)) {
			logout(request,response);
		}

	}
	/**
	 * 实现的是注销功能
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//也可以使用重定向    
		request.getSession().removeAttribute("stu");
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}
	/**
	 * 实现登陆的功能
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws Exception 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		/*0.获得out对象
		  1.获得参数，并封装成对象
		  2.调用业务代码，实现登陆操作
		  3.判断登陆是否成功
		  4.进一步判断是否要记住用户名
		 */
		//0.获得out对象
		PrintWriter out = response.getWriter();
		//1.获得参数，并封装数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2.调用业务代码，实现登陆操作
			UserService us = new UserService();
			Student stu = us.login(username,password);
			//3.判断是否登陆成功
			if (stu == null) {
				//登陆失败
				out.write("账号或密码错误，请在2秒后请重新登陆");
				response.setHeader("Refresh", "2,URL="+request.getContextPath()+"/login.jsp");
				//response.sendRedirect(request.getContextPath()+"/login.jsp");
			}else{
				//登陆成功
				//将学生对象放在域中
				request.getSession().setAttribute("stu", stu);
				out.write("恭喜您,登陆成功！！！，2后将登陆选课系统");
				response.setHeader("Refresh", "2;URL="+request.getContextPath()+"/admin/login/home.jsp");
				//感觉用重定向会更好一些
				//response.sendRedirect(request.getContextPath()+"/Welcome.jsp");
				
				//4.进一步判断是否记住用户名
				String remember = request.getParameter("remember");
				if ("true".equals(remember)) {
					//5.写cookie信息
					//用户名存在中文经过 BASE64编码也可以保存到cookie文件中
				
				/*	String afterUsername = new BASE64Encoder().encode(stu.getStuId().getBytes());
					Cookie cookie = new Cookie("ename",afterUsername);//用户名可能是中文？？？
*/					Cookie cookie = new Cookie("uname", stu.getStuId());
					cookie.setMaxAge(Integer.MAX_VALUE);
					cookie.setPath(request.getContextPath());//只要当前的应用程序无论在哪里运行，可以直接带过去
					
					response.addCookie(cookie);//服务器将cookie能这过set-cookie响应头发送给客户端
				}else{//当登陆时不选中记住账号，好要把cookie中的信息给删除掉
					Cookie cookie = new Cookie("uname","");
					cookie.setMaxAge(0);
					cookie.setPath(request.getContextPath());
					
					response.addCookie(cookie);
				}
			}
		}
	
			

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
