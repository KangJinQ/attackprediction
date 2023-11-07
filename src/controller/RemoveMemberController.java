package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MatchService;

/**
 * Servlet implementation class RemoveMemberController
 */
@WebServlet("/removeMember.do")
public class RemoveMemberController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MatchService matchservice = new MatchService();
		int uid = Integer.parseInt(request.getParameter("uid"));
		int mid = Integer.parseInt(request.getParameter("mid"));
		String matchSystem = request.getParameter("matchsystem");
		System.out.println("removeMember.do"+matchSystem);
		matchservice.removeMember(uid, mid);
		String op = (String) request.getParameter("op");
		if(op.equals("remove")){
			String matchName= matchservice.findMatchNameByMatchId(mid);
			matchservice.writeMail(uid, "您已被移出\"" + matchName + "\"比赛");
		}
		
		//tranfer
		request.getRequestDispatcher("/matchdetail.do?permission=creater&mid="+mid+"&matchsystem=" + matchSystem ).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
