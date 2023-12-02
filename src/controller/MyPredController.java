package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Pred;
import domain.User;
import service.PredService;

/**
 * Servlet implementation class MyMatchController
 */
@WebServlet("/mypred.do")
public class MyPredController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PredService predService = new PredService();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUserId();
		List<Pred> predList = predService.selectPredByUid(uid);
		System.out.println(predList);
		request.setAttribute("predList", predList);
		request.getRequestDispatcher("/mypred.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
