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

/**
 * Servlet implementation class QuitMatchController
 */
@WebServlet("/quitmatch.do")
public class QuitMatchController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User user = (User) request.getAttribute("user");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUserId();
		System.out.println(uid);
		int mid = Integer.parseInt(request.getParameter("matchId"));
		String quitReason = request.getParameter("reason");
		String status = request.getParameter("status");
		System.out.println(mid);
		System.out.println(status);
		System.out.println(quitReason);
		MatchService matchService = new MatchService();
		
		//不知道需不需要
		String matchName= matchService.findMatchNameByMatchId(mid);
		matchService.writeMail(uid, "您已退出\"" + matchName + "\"比赛");
		
		matchService.changeJoinerStatus(mid, uid, status, quitReason);
		request.getRequestDispatcher("mymatch.do?op=join").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
