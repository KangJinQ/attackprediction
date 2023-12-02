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

import domain.Match;
import service.MatchService;

/**
 * Servlet implementation class AllMatchController
 */
@WebServlet("/allpred.do")
public class AllPredController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// HttpSession session = request.getSession();
		MatchService matchService = new MatchService();
		List<Match> allmatchList = new ArrayList<Match>();
		allmatchList = matchService.selectByStatus("宸查�氳繃");
		request.setAttribute("allmatchList", allmatchList);
		String page = request.getParameter("page");
		// System.out.println(page);
		if (page == null) {
			request.getRequestDispatcher("/joinmatch.jsp").forward(request, response);
		} else if (page.equals("showallmatch")) {
			request.getRequestDispatcher("/allmatch.jsp").forward(request, response);
		} else if (page.equals("joinmatch")) {
			request.getRequestDispatcher("/joinmatch.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
