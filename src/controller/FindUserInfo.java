package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.UserService;

/**
 * Servlet implementation class FindUserByIdController
 */
@WebServlet("/finduserinfo.do")
public class FindUserInfo extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		UserService userService = new UserService();
		List<User> allUserList = new ArrayList<User>();
		System.out.println(allUserList);
		allUserList = userService.getAllUser();
		request.setAttribute("allUserList", allUserList);
		//String msg = (String) request.getAttribute("msg");
		//request.setAttribute("msg", msg);
		String matchsystem = request.getParameter("matchsystem");
		String op = request.getParameter("op");
		if(op.equals("check")){
			request.getRequestDispatcher("/alluser.jsp?op=check&matchsystem="+matchsystem).forward(request, response);
		}else if(op.equals("setmanager")){
			int mid = Integer.parseInt(request.getParameter("mid"));
			request.setAttribute("mid", mid);
			request.getRequestDispatcher("/alluser.jsp?op=setmanager&matchsystem="+matchsystem).forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
