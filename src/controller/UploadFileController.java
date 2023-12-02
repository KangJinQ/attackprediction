package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.PredDAO;
import domain.User;
import service.PredService;

/**
 * Servlet implementation class UploadFileController
 */
@WebServlet("/uploadfile.do")
@MultipartConfig
public class UploadFileController extends HttpServlet {
	
	private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1)
                        .substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());

	    // RequestDispatcher dispatcher = request.getRequestDispatcher("addpred.jsp");
	    // dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// 1 ǰ�˻�ò���
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int userId = user.getUserId();
		String predName = request.getParameter("predname");
		
		System.out.println(predName);
		
		PredService predService = new PredService();
		
	    InputStream fileContent = null;
	    String fileName = null;
	
	    try {
	        Part filePart = request.getPart("f");
	        fileName = getSubmittedFileName(filePart);
	        System.out.println(fileName);
	        
	        if (fileName != null && fileName.endsWith(".csv")) {
		        fileContent = filePart.getInputStream();
		        // �����ļ�
		        Date time = new Date();
		        boolean fileSaved = predService.uploadFile(fileName, fileContent, userId, predName, time);
		        String resultId = predService.sendRequestion2Python(fileName, time);
		        
		        if (fileSaved) {
		        	predService.savePred(userId, fileName, predName, resultId, time);
		        	request.setAttribute("msg", "�ļ��ϴ��ɹ���");
		        } else {
		        	request.setAttribute("msg", "�ļ�����ʧ�ܣ�");
		        }
		        request.getRequestDispatcher("/addpred.jsp").forward(request, response);
		    } else {
		    	request.setAttribute("msg", "���ϴ�.csv�ļ�");
		    	response.sendRedirect(request.getContextPath() + "/addpred.jsp");
		    }
		    } catch (Exception e) {
		        e.printStackTrace();
		        request.getRequestDispatcher("/addpred.jsp").forward(request, response);
		        // request.setAttribute("msg", "�ļ��ϴ�ʧ�ܣ�" + e.getMessage());
		    } finally {
		        if (fileContent != null) {
		            fileContent.close();
		        }
		    }
	}

}
