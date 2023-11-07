package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Match;
import service.MatchService;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/search.do")
public class SearchController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getParameter("name");
		MatchService matchService = new MatchService();
		List<Match> searchList = new ArrayList<Match>();
		searchList = matchService.searchMatchByName(name);
		String source = (String) request.getParameter("source");
		request.setAttribute("allmatchList", searchList);
		if(source.equals("joinmatch")){
			request.getRequestDispatcher("/joinmatch.jsp").forward(request, response);
		}else if(source.equals("allmatch")){
			request.getRequestDispatcher("/allmatch.jsp").forward(request, response);
		}
		//System.out.println(searchList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
