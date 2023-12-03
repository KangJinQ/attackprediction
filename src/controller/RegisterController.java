package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserService;
import util.DateUtil;

@WebServlet("/register.do")
public class RegisterController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = new UserService();
		String user_name = request.getParameter("userName");
		String user_password = request.getParameter("userPassword");
		String user_phone = request.getParameter("userPhone");
		String user_email = request.getParameter("userEmail");
		Boolean user_role = Boolean.valueOf(request.getParameter("userRole"));
		String action_pwd = request.getParameter("actionPwd");
//		Boolean state = Boolean.valueOf(request.getParameter("state"));
		Boolean state = false;
		String msg = "";
		User user;
		try {
			user = userService.isExistsUser(user_name);
			System.out.println(user);
			if (user_name != "" && user_password != "" && user_phone != "" && user_email != ""
					&& user == null) {
				// ×¢²á
				try {
					userService.register(user_name, user_password, user_phone, user_email, user_role, action_pwd, state);
					System.out.println("×¢²á³É¹¦");
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
//					response.sendRedirect(request.getContextPath() + "/login.jsp");
					request.getRequestDispatcher("/01.html").forward(request, response);
				}
			} else {
				// ×¢²áÊ§°Ü»ØÏÔ
				System.out.println("×¢²áÊ§°Ü");
				msg="×¢²áÊ§°Ü£¬ÓÃ»§ÃûÒÑ´æÔÚ£¿";
				request.setAttribute("msg", msg);
				// 
				request.getRequestDispatcher("/register.jsp").forward(request, response);
				// response.sendRedirect(request.getContextPath()+"/register.do");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
