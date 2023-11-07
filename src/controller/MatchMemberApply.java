package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MatchService;

/**
 * Servlet implementation class matchMemberApply
 */
@WebServlet("/matchmemberapply.do")
public class MatchMemberApply extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MatchService matchService = new MatchService();
		int uid = Integer.parseInt(request.getParameter("uid"));
		int mid = Integer.parseInt(request.getParameter("mid"));
		String matchSystem = request.getParameter("matchsystem");
		System.out.println("matchmemberaplly.do"+matchSystem);
		String matchName= matchService.findMatchNameByMatchId(mid);
		String status = new String();
		if(request.getParameter("status") != null){
			status = request.getParameter("status");
		}
		
		if(status.equals("同意")){
			matchService.writeMail(uid, "您已被同意加入\"" + matchName + "\"比赛");
			matchService.matchMemberApprove(uid, mid, "报名成功");
		}
		else if(status.equals("拒绝")){
			//通知用户被拒绝：待定
			matchService.writeMail(uid, "您已被拒绝加入\"" + matchName + "\"比赛");
			matchService.removeMember(uid, mid);
		}
		request.getRequestDispatcher("/matchdetail.do?mid="+mid+"&matchsystem=" + matchSystem+"&permission=creater").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
