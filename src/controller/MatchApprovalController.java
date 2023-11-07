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

import cn.hutool.socket.protocol.MsgEncoder;
import domain.Match;
import service.MatchService;


@WebServlet("/matchapprovalshow.do")
public class MatchApprovalController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		MatchService matchService = new MatchService();
		List<Match> matchList = new ArrayList<Match>();
		matchList = matchService.selectByStatus("未审批");
		//System.out.println(matchList.isEmpty());
		request.setAttribute("matchList", matchList);
		request.getRequestDispatcher("/verify.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
