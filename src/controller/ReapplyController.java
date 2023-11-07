package controller;

import java.io.IOException;
import java.util.Date;
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
import util.DateUtil;

/**
 * Servlet implementation class ReapplyController
 */
@WebServlet("/reapply.do")
public class ReapplyController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mid = Integer.parseInt(request.getParameter("mid"));	//将要打开比赛详情页面的比赛id
		// find比赛信息
		MatchService matchService = new MatchService();
		Match match = matchService.findMatchByMatchId(mid);
		request.setAttribute("match", match);
		
		Integer match_type = Integer.parseInt(request.getParameter("matchtype"));
		String match_system = request.getParameter("matchsystem");
		Date match_start_time = DateUtil.getDate(request.getParameter("matchstarttime"));
		Date match_end_time = DateUtil.getDate(request.getParameter("matchendtime"));
		Integer match_number = Integer.parseInt(request.getParameter("matchnumber"));	
		String match_info = request.getParameter("matchinfo");
		if(match_info == null)match_info="暂无介绍";
		//System.out.println(match_info);
		//System.out.println(match_type);
		//request.setAttribute("match_type", match_type);
			
		//setAttribute 加载我组织的比赛
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUserId();
		String op = new String("create");
		List<Match> matchList = matchService.selectMatchByUid(uid, op);
		request.setAttribute("matchList", matchList);
		
		matchService.reapplyMatch(mid, match_type, match_system, match_start_time, match_end_time, match_number, match_info, uid);
		request.getRequestDispatcher("/mymatch.do?op=create").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
