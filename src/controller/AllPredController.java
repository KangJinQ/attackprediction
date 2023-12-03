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
import domain.User;
import service.PredService;

/**
 * Servlet implementation class AllMatchController
 */
@WebServlet("/allpred.do")
public class AllPredController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PredService predService = new PredService();
		HttpSession session = request.getSession();
		List<Pred> predList = predService.selectPredByUid(0);
		System.out.println(predList);
		request.setAttribute("predList", predList);
		request.getRequestDispatcher("/allpred.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
