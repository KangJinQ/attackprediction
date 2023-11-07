package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MatchService;

/**
 * Servlet implementation class AddManagerController
 */
@WebServlet("/addmanager.do")
public class AddManagerController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid = Integer.parseInt(request.getParameter("uid"));
		int mid = Integer.parseInt(request.getParameter("mid"));
		//System.out.println(uid+",,,"+mid);
		MatchService matchService = new MatchService();
		String matchsystem = request.getParameter("matchsystem");
		String msg = new String();
		if(matchService.existManager(mid, uid)==false){
			//System.out.println("1");
			msg = "";
			request.setAttribute("msg", msg);
			String op = "success";
			request.setAttribute("op", op);
			String matchName= matchService.findMatchNameByMatchId(mid);
			matchService.writeMail(uid, "您已被添加为\"" + matchName + "\"比赛的管理员");
			System.out.println(matchName);
			//System.out.println("不存在管理员");
			matchService.addMatchManager(mid, uid);
			request.getRequestDispatcher("/matchdetail.do?permission=creater&op=success&mid="+mid+"&matchsystem="+matchsystem).forward(request, response);
		} else{
			//System.out.println("2");
			msg = "该用户已经是比赛的管理员,如要移除权限请联系超级管理员";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/finduserinfo.do?op=setmanager&mid="+mid+"&matchsystem="+matchsystem).forward(request, response);
		}
		System.out.println(msg);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
