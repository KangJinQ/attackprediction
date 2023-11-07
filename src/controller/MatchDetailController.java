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

import domain.Match;
import domain.User;
import service.MatchService;
import service.TeamService;
import service.UserService;

/**
 * Servlet implementation class MatchDetailController
 */
@WebServlet("/matchdetail.do")
public class MatchDetailController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		int mid = Integer.parseInt(request.getParameter("mid")); // 将要打开比赛详情页面的比赛id
		String matchSystem = request.getParameter("matchsystem"); // 将要打开比赛详情界面的比赛类型（区分团体赛）
		
		String permission = request.getParameter("permission"); // 获得权限（组织者or参赛者）
		request.setAttribute("permission", permission);
		// find比赛信息
		MatchService matchService = new MatchService();
		Match match = matchService.findMatchByMatchId(mid);
		request.setAttribute("match", match);
		// 查找与比赛相关的参与者信息合集
		UserService userService = new UserService();
		TeamService teamService = new TeamService();
		int uid = user.getUserId();
		List<User> applyinguserList = userService.findJoinerByStatusAndMatchId("申请报名", mid);
		List<User> quitinguserList = userService.findJoinerByStatusAndMatchId("申请退赛", mid);
		List<User> joineduserList = userService.findJoinerByStatusAndMatchId("报名成功", mid);
		List<String> applyinguserTeamNameList = new ArrayList<String>();
		List<String> quitinguserTeamNameList = new ArrayList<String>();
		List<String> joineduserTeamNameList = new ArrayList<String>();
		for (int i = 0; i < applyinguserList.size(); i++) {
			User userTmp = applyinguserList.get(i);
			uid = userTmp.getUserId();
			// System.out.println(uid+"tmp");
			applyinguserTeamNameList.add(teamService.findTeamNameByUidAndMid(uid, mid));
		}
		for (int i = 0; i < quitinguserList.size(); i++) {
			User userTmp = quitinguserList.get(i);
			uid = userTmp.getUserId();
			// System.out.println(uid+"tmp");
			quitinguserTeamNameList.add(teamService.findTeamNameByUidAndMid(uid, mid));
		}
		for (int i = 0; i < joineduserList.size(); i++) {
			User userTmp = joineduserList.get(i);
			uid = userTmp.getUserId();
			// System.out.println(uid+"tmp");
			joineduserTeamNameList.add(teamService.findTeamNameByUidAndMid(uid, mid));
			// System.out.println(joineduserTeamNameList.get(i));
		}
		// System.out.println(applyinguserTeamNameList);

		request.setAttribute("applyinguserList", applyinguserList);
		request.setAttribute("quitinguserList", quitinguserList);
		request.setAttribute("joineduserList", joineduserList);
		request.setAttribute("applyinguserTeamNameList", applyinguserTeamNameList);
		request.setAttribute("quitinguserTeamNameList", quitinguserTeamNameList);
		request.setAttribute("joineduserTeamNameList", joineduserTeamNameList);
		// 查找与比赛相关的组织者与管理者信息合集
		User creater = userService.findCreater(mid);
		List<User> managerList = userService.findManager(mid);
		List<String> managerTeamNameList = new ArrayList<String>();
		request.setAttribute("creater", creater);
		request.setAttribute("managerList", managerList);
		for (int i = 0; i < managerList.size(); i++) {
			User userTmp = managerList.get(i);
			uid = userTmp.getUserId();
			// System.out.println(uid+"tmp");
			managerTeamNameList.add(teamService.findTeamNameByUidAndMid(uid, mid));
			// System.out.println(joineduserTeamNameList.get(i));
		}
		request.setAttribute("managerTeamNameList", managerTeamNameList);
		System.out.println("matchDetail"+matchSystem);
		if (matchSystem.equals("团体赛")) {
			request.getRequestDispatcher("/TheMatchForTeam.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/TheMatch.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
