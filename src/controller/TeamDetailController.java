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
import service.TeamService;

/**
 * Servlet implementation class TeamDetailController
 */
@WebServlet("/teamdetail.do")
public class TeamDetailController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TeamService teamService = new TeamService();
		List<User> userList = new ArrayList<User>();
		
		int tid = Integer.parseInt(request.getParameter("tid"));
		userList = teamService.findUserByTid(tid);
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("/TheTeam.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
