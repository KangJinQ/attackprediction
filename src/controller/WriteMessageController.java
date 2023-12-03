package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.MessageService;
import service.UserService;
import util.DateUtil;

/**
 * Servlet implementation class WriteMessageController
 */
@WebServlet("/writemessage.do")
public class WriteMessageController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService();
		HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
		int uid = user.getUserId();
		int target_uid = Integer.valueOf(request.getParameter("target_uid"));
		String topic = request.getParameter("topic");
		String content = request.getParameter("content");
		MessageService messageService = new MessageService();
		
		User targetUser = userService.isExistById(target_uid);
		if(targetUser == null) {
			request.setAttribute("msg", "查无此人");
		}else {
			try {
				messageService.writeMessage(target_uid, uid, topic, content, 0, DateUtil.getString(new Date()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("msg", "查发送成功");
		}
		request.getRequestDispatcher("/mailbox.do?op=fa").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



}
