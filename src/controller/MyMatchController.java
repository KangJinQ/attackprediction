package controller;

import java.io.IOException;
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

/**
 * Servlet implementation class MyMatchController
 */
@WebServlet("/mymatch.do")
public class MyMatchController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MatchService matchService = new MatchService();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUserId();
		String op = request.getParameter("op");
		//System.out.println(op);
		List<Match> matchList = matchService.selectMatchByUid(uid, op);
		request.setAttribute("matchList", matchList);
		if (op.equals("join")) {
			request.getRequestDispatcher("/joinedmatch.jsp").forward(request, response);
		} else if (op.equals("create")) {
			request.getRequestDispatcher("/createdmatch.jsp").forward(request, response);
		} else if (op.equals("manage")) {
			request.getRequestDispatcher("/managedmatch.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
