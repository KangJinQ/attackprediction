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

import domain.Match;
import domain.User;
import service.MatchService;
import util.DateUtil;

/**
 * Servlet implementation class AddMatchController
 */
@WebServlet("/addmatch.do")
public class AddMatchController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MatchService matchService = new MatchService();

		String match_name = request.getParameter("matchname");
		Integer match_type = Integer.parseInt(request.getParameter("matchtype"));
		String match_system = request.getParameter("matchsystem");
		Date match_start_time = DateUtil.getDate(request.getParameter("matchstarttime"));
		Date match_end_time = DateUtil.getDate(request.getParameter("matchendtime"));
		String match_info = request.getParameter("matchinfo");
		if(match_info == null)match_info="暂无介绍";
		String match_status = "未审批";
		String match_life = "准备中";
		Integer match_number = Integer.parseInt(request.getParameter("matchnumber"));
		Integer match_numbered = 0;

		Match match;
		try {
			match = matchService.findMatchByMatchName(match_name);

			if (match == null) {
				try {
					matchService.addMatch(match_name, match_type, match_system, match_start_time, match_end_time,
							match_info, match_status, match_life, match_number, match_numbered);

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					response.sendRedirect(request.getContextPath() + "/addmatch.jsp");
				}
			} else {
				// 有重名比赛存在
				request.setAttribute("msg", "相同比赛名已存在");
				// 重定向到创建比赛页面
				request.getRequestDispatcher("/addmatch.jsp").forward(request, response);
				// response.sendRedirect(request.getContextPath()+"/register.do");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Match match_now;
		try {
			match_now = matchService.findMatchByMatchName(match_name);
			int mid = match_now.getMatchId();

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			int uid = user.getUserId();
			matchService.addMatchCreater(mid, uid);
			request.setAttribute("msg", "创建连接成功，等待审批");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
