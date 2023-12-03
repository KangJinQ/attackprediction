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
import service.PredService;
import util.DateUtil;

/**
 * Servlet implementation class ChangeLifeController
 */
@WebServlet("/changepreddetail.do")
public class ChangePredDetailController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String next = request.getParameter("next");
		System.out.println(op);
		int pre_id = Integer.parseInt(request.getParameter("pre_id"));
		PredService predService = new PredService();
		if(op.equals("deletepred")){
			System.out.println(pre_id);
			predService.deletePredById(pre_id);	
		}
		else if(op.equals("returnpred")){
			System.out.println(pre_id);
			predService.returnPredById(pre_id);
		}
		
		if(next.equals("all")) {
			request.getRequestDispatcher("/allpred.do").forward(request, response);
		} else if(next.equals("my")) {
			request.getRequestDispatcher("/mypred.do").forward(request, response);

		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
