package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.MatchService;
import service.TeamService;

/**
 * Servlet implementation class QuitTeamController
 */
@WebServlet("/quitteam.do")
public class QuitTeamController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TeamService teamService = new TeamService();
		MatchService matchService = new MatchService();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUserId();
		int tid = Integer.parseInt(request.getParameter("tid"));
		int mid = Integer.parseInt(request.getParameter("mid"));
		
		teamService.quitTeam(uid,tid);
		matchService.removeMember(uid, mid);
		
		request.getRequestDispatcher("/myteam.do").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
