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

import domain.Pred;
import domain.PredResult;
import domain.User;
import service.PredResultService;
import service.PredService;
import service.UserService;

/**
 * Servlet implementation class MatchDetailController
 */
@WebServlet("/showpreddetail.do")
public class ShowPredDetailController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		int predId = Integer.parseInt(request.getParameter("pre_id"));
		int userId = user.getUserId();
		
		PredService predService = new PredService();
		Pred pred = predService.findPredByPredId(predId);
		request.setAttribute("pred", pred);
		System.out.println(pred);
		
		PredResultService predResultService = new PredResultService();
		PredResult predResult = predResultService.findResultByPredId(predId);
		request.setAttribute("predResult", predResult);
		System.out.println(predResult);
		
		request.getRequestDispatcher("/thepred.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
