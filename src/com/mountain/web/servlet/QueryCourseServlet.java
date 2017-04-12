package com.mountain.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mountain.domain.QueryGrade;
import com.mountain.domain.Student;
import com.mountain.service.OperateGradeService;

/**
 * 进行一些查询的操作
 * 查询已经选过的课程
 * 查询学分
 * 查询未选的课程
 * @author Administrator
 *
 */
public class QueryCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理登陆的操作
		//0.设置文字的编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//1.可以引入一个操作类型的参数
		/**
		 * op参数在left.jsp张
		 * opq参数在SelectedCourse.jsp中 
		 */
		String op = request.getParameter("op");
		//遇到的问题：有可能就是op参数位置的不同，获取到的数值一直没有变数，所以又在一个新的地方设置变量，重新获取参数，解决问题
		String opq = request.getParameter("opq");
		
		if ("selected".equals(op)) {
			//查询已经选过的课程   op参数在left.jsp张
			querySelectedCourse(request,response,"/frontstage/SelectedCourse.jsp");
		
		}else if ("sel".equals(opq)) {
			//根据下拉菜单中来查询已经开过的课程   opq参数在SelectedCourse.jsp中
			
			//通过下拉菜单查看可以选的课程
			/*6.JSP下拉框使用onchange事件提交表单到本文件后，会有刷新，怎么保持下拉框的选中状态为刚才
			 选择的下拉框选项？*/
			String p= request.getParameter("pp");
			request.setAttribute("qq", p);
			
			querySelectedCourseByItem(request,response,"/frontstage/SelectedCourse.jsp","pp");
		
		}else if ("credit".equals(op)){
			//查询所选课程的学分	
			queryCredit(request,response);
		
		}else if("should".equals(op)){
			//查看可以选的课程
			queryShouldSelectCourse(request,response,"/frontstage/showShouldSelectCouce.jsp");
		}else if("shouldSelect".equals(opq)){
			//通过下拉菜单查看可以选的课程
			/*6.JSP下拉框使用onchange事件提交表单到本文件后，会有刷新，怎么保持下拉框的选中状态为刚才
			 选择的下拉框选项？*/
			String p= request.getParameter("pp");
			request.setAttribute("qq", p);
			//处理下拉菜单
			queryShouldSelectCourseByItem(request,response,"/frontstage/showShouldSelectCouce.jsp","pp");
		}
				
	}
	/**
	 * 通过下拉菜单查看可以选的课程
	 * @param request
	 * @param response
	 * @param str 传的是转发的地址
	 * @param ite 传的是获取下拉表单的的名字
	 */
	public static void queryShouldSelectCourseByItem(HttpServletRequest request,
			HttpServletResponse response,String str,String ite) {
		//封装数据
		String item = request.getParameter(ite);
		
		//直接调用业务逻辑
		OperateGradeService qs = new OperateGradeService();
		//获取session域中student对象
		Student stu = (Student) request.getSession().getAttribute("stu");
		try {
			List<QueryGrade> sList = qs.querryShouldSelectCourseByItem(stu.getStuId(),stu.getStuId(),item);
			
			//将获取的新集合放在新的session域中
			request.setAttribute("sList",sList );
			//在利用转发
			request.getRequestDispatcher(str).forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 可以选的课程(未选的课程)
	 * 在这里我设置为共有属性，是为了在预选课程中，继续使用该方法
	 * @param request
	 * @param response
	 * @param str 传的是转发的地址
	 */
	 public static void queryShouldSelectCourse(HttpServletRequest request,
			HttpServletResponse response ,String str) {
		//直接调用业务逻辑
		OperateGradeService qs = new OperateGradeService();
		//获取session域中student对象
		Student stu = (Student) request.getSession().getAttribute("stu");
		try {
			List<QueryGrade> sList = qs.querryShouldSelectCourse(stu.getStuId(),stu.getStuId());
			
			//将获取的新集合放在新的session域中
			request.setAttribute("sList",sList );
			//在利用转发
			request.getRequestDispatcher(str).forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询学分 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void queryCredit(HttpServletRequest request,
			HttpServletResponse response) {		
		/**
		 * 思路：通过sql语句获取QueryGrade对象，其中有课程的分数与该课程的学分，在这里可以遍历每一门课，
		 * 		如果课程的分数>60则表示通过，并且把该学分给该同学，如果小于<60，则该们的学分为0
		 * 		注意：我的思路是先创建一个与把刚开始查出来的数据放进去
		 * 			 然后在重新建一个域，存进去，<60的利用setCredit()方法，赋值
		 *   那么就可以利用QueryGradeDao的queryGrade()方法，里面的字段有-----课程名，课程的学分，开始的学期，分数，课程id，学院名
		 *   
		 *   总计：可以去除每一次遍历的学分，把它们加起来
		 */
		//调用业务代码
		OperateGradeService qs = new OperateGradeService();
		//过去域中的登陆学生的id
		Student stu = (Student) request.getSession().getAttribute("stu");
		try {
			List<QueryGrade> clist = qs.queryGrade(stu.getStuId());
			request.setAttribute("qList", clist);
			double subTotal = 0 ;
			//遍历岁取到的对象集合
			for (QueryGrade queryGrade : clist) {
				//若果成绩小于60直接赋值credit为0
				if (queryGrade.getGrade() < 60) {
					queryGrade.setCredit(0);
				}
				//否则按照原样输出
				
				//总计学分
				//1.把每次遍历的学分取出来
				double credit = queryGrade.getCredit();
				//2.把每次total加起来再放入total中
				
				subTotal += credit;
					//放入total中
				queryGrade.setTotal(subTotal);
			}
			//将获取的新集合放在新的session域中
			request.setAttribute("cList",clist );
			//在利用转发
			request.getRequestDispatcher("/frontstage/CourseScore.jsp").forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	/**
	 * 根据下拉菜单中的学期来进行查询已经选过的课程
	 * @param request
	 * @param response
	 * @param str 要进行转发的地址
	 * @param str1 获取下拉菜单的名字的字符串
	 * @throws Exception 
	 */
	public static void querySelectedCourseByItem(HttpServletRequest request,
			HttpServletResponse response,String str,String str1)  {
		//封装数据
		String item = request.getParameter(str1);
		//调用业务逻辑
		
		//2.调用业务代码，实现登陆操作
		OperateGradeService qs = new OperateGradeService();
		Student stu = (Student) request.getSession().getAttribute("stu");
		//在这里本想再创建一个新的方法来获取mList集合的，但是由于才用的JavaBean是与获得成绩是同样的，因此我们想或取下拉菜单中的值来传入，已在数据库中查询
		//因此就可以直接使用queryGradeUseItem来获取lsit集合
		//其实也想过直接用pList  session域中的值来获取对象，想想又可能UseItem这个方法必须在事件提交后才能使用，所以呢，就不可以直接使用（没有尝试）
		try {
			List<QueryGrade> mList = qs.queryGradeUseItem(stu.getStuId(), item);
			//将获取到的list集合放到域中
			request.setAttribute("mList",mList);
			//在利用转发
			request.getRequestDispatcher(str).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 查询已经选过的课程	
	 * @param request
	 * @param response
	 * @param str 要进行转发的地址
	 * @throws Exception 
	 */
	public static void querySelectedCourse(HttpServletRequest request,
			HttpServletResponse response,String str) {
		//2.调用业务代码，实现登陆操作
		OperateGradeService qs = new OperateGradeService();
		Student stu = (Student) request.getSession().getAttribute("stu");
		try {
			List<QueryGrade> mList = qs.querySelectedCourse(stu.getStuId());
			//将获取到的list集合放到域中
			request.setAttribute("mList",mList);
			
			//在利用转发
			request.getRequestDispatcher(str).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
