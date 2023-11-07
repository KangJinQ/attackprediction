package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.TeamService;

/**
 * Servlet implementation class AddTeamController
 */
@WebServlet("/addteam.do")
public class AddTeamController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUserId();
		String teamName = request.getParameter("teamName");
		int teamNumber = Integer.parseInt(request.getParameter("teamNumber"));
		int mid = Integer.parseInt(request.getParameter("mid"));
		
		TeamService teamService = new TeamService();
		teamService.addTeam(teamName, teamNumber, mid, uid);
		int newestTid = teamService.findTidByUid(uid);
		teamService.addMember(newestTid, uid);
		request.getRequestDispatcher("/myteam.do").forward(request, response);
		//teamService.addTeamMember();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
