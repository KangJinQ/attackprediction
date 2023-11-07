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

import domain.Team;
import domain.User;
import service.TeamService;

/**
 * Servlet implementation class MyTeamController
 */
@WebServlet("/myteam.do")
public class MyTeamController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUserId();
		
		TeamService teamService = new TeamService();
		List<Team> teamList = teamService.findTeamByUid(uid);
		List<String> matchNameList = new ArrayList<String>();
		request.setAttribute("teamList", teamList);
		int mid;
		for(int i = 0;i<teamList.size();i++){
			Team team = new Team();
			team = teamList.get(i);
			mid = team.getMid();
			matchNameList.add(teamService.findMatchNameByMid(mid));
		}
		request.setAttribute("matchNameList", matchNameList);
		//System.out.println(teamList);
		request.getRequestDispatcher("/myteam.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
