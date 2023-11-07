package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.MatchService;
import service.TeamService;
import service.UserService;

/**
 * Servlet implementation class JoinTeamController
 */
@WebServlet("/jointeam.do")
public class JoinTeamController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUserId();
		int tid = Integer.parseInt(request.getParameter("teamId"));
		int mid = Integer.parseInt(request.getParameter("mid"));

		TeamService teamService = new TeamService();
		UserService userService = new UserService();
		MatchService matchService = new MatchService();
		String msg = "没事";
		if (userService.haveJoinedMatch(uid, mid) == null) {
			teamService.addMember(tid, uid);
			try {
				matchService.addMatchJoiner(mid, uid, tid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/myteam.do?").forward(request, response);
		} else {
			msg = "您已经加入了本比赛或者申请暂未通过，请退出后重试。";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath() + "/error.jsp"); // getContextPath()��ø�Ŀ¼��
			//request.getRequestDispatcher("/myteam.do").forward(request, response);
			//request.getRequestDispatcher("/matchdetail.do?mid="+mid+"&matchsystem=${match.matchSystem }&permission=joiner").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
