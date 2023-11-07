package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.MatchService;
import service.TeamService;

@WebServlet("/joinmatch.do")
public class JoinMatchController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TeamService teamService = new TeamService();
		MatchService matchservice = new MatchService();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String matchsystem = request.getParameter("matchsystem");
		// 获取要报名的比赛和用户的id
		int mid = Integer.parseInt(request.getParameter("mid"));
		int uid = user.getUserId();
		try {
			if (matchsystem.equals("团体赛")) {
				int tid = teamService.findTidByUid(uid);
				//String team_name = request.getParameter("等待设置报名页面");
				List<Integer> uidList = new ArrayList<Integer>();
				uidList = teamService.findAllUidByTid(tid);
				for(int i=0;i<uidList.size();i++){
					uid = uidList.get(i);
					matchservice.addMatchJoiner(mid, uid, tid);
				} 
				//matchservice.addMatchJoiner(mid, uid, tid);
				System.out.println(uidList);
			} else {
				matchservice.addMatchJoiner(mid, uid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/allmatch.do").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
