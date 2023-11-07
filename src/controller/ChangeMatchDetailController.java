package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Match;
import service.MatchService;
import util.DateUtil;

/**
 * Servlet implementation class ChangeLifeController
 */
@WebServlet("/changematchdetail.do")
public class ChangeMatchDetailController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String matchSystem = request.getParameter("matchsystem");
		System.out.println(op);
		int mid = Integer.parseInt(request.getParameter("mid"));
		MatchService matchService = new MatchService();
		//Integer match_type = Integer.parseInt(request.getParameter("matchtype"));

		if(op.equals("changeresult")){
			String match_result = request.getParameter("matchresult");
			if(match_result == null || match_result=="")match_result="暂无比赛结果";
			matchService.changeMatchResult(mid,match_result);
			request.getRequestDispatcher("/matchdetail.do?mid="+ mid +  "&permission=creater&matchsystem=" + matchSystem).forward(request, response);
		}
		else if(op.equals("deletematch")){
			System.out.println(mid);
			matchService.deleteMatchById(mid);	//删除id为mid的比赛相关所有信息
			request.getRequestDispatcher("/mymatch.do?op=create").forward(request, response);
		}else if(op.equals("changematch")){
			
			Integer match_type = Integer.parseInt(request.getParameter("matchtype"));
			String match_system = request.getParameter("matchsystem");
			Date match_start_time = DateUtil.getDate(request.getParameter("matchstarttime"));
			Date match_end_time = DateUtil.getDate(request.getParameter("matchendtime"));
			Integer  match_number= Integer.parseInt(request.getParameter("matchnumber"));
			String match_life = request.getParameter("matchlife");	
			String match_info = request.getParameter("matchinfo");
			
			if(match_info == null)match_info="暂无介绍";
			

			matchService.changeMatchDetail(mid, match_type, match_system, match_start_time, match_end_time,
					 match_life, match_number,match_info);
			request.getRequestDispatcher("/matchdetail.do?mid="+ mid +  "&permission=creater&matchsystem=" + matchSystem).forward(request, response);
		}else if(op.equals("reapply")){
			
			// find比赛信息
			Match match = matchService.findMatchByMatchId(mid);
			request.setAttribute("match", match);
			request.getRequestDispatcher("/reapply.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
