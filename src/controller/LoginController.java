package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import exception.NameException;
import exception.PassException;
import service.UserService;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 前端获得参数
		String userName = request.getParameter("username");
		String userPass = request.getParameter("userpassword");

		// 2 调用业务
		UserService userService = new UserService();
		try {
			User user = userService.isLogin(userName, userPass);

			// 获得当前页面session״̬
			HttpSession session = request.getSession();
			// 设置session变量user
			session.setAttribute("user", user);
			System.out.println(user.getUserName());
			System.out.println(user);
			// 3 重定向
			response.sendRedirect(request.getContextPath() + "/index.jsp"); // getContextPath()��ø�Ŀ¼��
		} catch (NameException e) {
			e.printStackTrace();
			// 设置变量error
			request.setAttribute("error", e.getMessage());
			// 重定向
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			// response.sendRedirect(request.getContextPath()+"/login.jsp");
		} catch (PassException e1) {
			e1.printStackTrace();
			// 返回错误信息
			request.setAttribute("error", e1.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}