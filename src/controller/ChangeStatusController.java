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
 * Servlet implementation class ChangeStatusController
 */
@WebServlet("/changestatus.do")
public class ChangeStatusController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		int mid = Integer.parseInt(request.getParameter("mid"));
		String rejectReason = request.getParameter("reason");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//System.out.println(rejectReason);
		int uid = user.getUserId();
		//System.out.println(rejectReason+"1");
		MatchService matchService = new MatchService();
		matchService.changeStatus(mid, status, rejectReason);
		matchService.refuseRecord(mid, uid, rejectReason);
		request.getRequestDispatcher("/matchapprovalshow.do").forward(request, response);
		//int mid = request.getAttribute(mid);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
