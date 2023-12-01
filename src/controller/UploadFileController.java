package controller;

import java.io.IOException;
import java.io.InputStream;

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
	    PredDAO predDao = new PredDAO();
	
	    System.out.println("来咯");
	    InputStream fileContent = null;
	    String fileName = null;
	
	    try {
	        Part filePart = request.getPart("f");
	        fileName = getSubmittedFileName(filePart);
	        
	        if (fileName != null && fileName.endsWith(".csv")) {
		        fileContent = filePart.getInputStream();
		
		        // 保存文件
		        boolean fileSaved = predDao.saveFile(fileName, fileContent);
		
		        if (fileSaved) {
		            // 保存文件信息到数据库
		            boolean fileInfoSaved = predDao.saveFileInfo(fileName);
		
		            if (fileInfoSaved) {
		                request.setAttribute("message", "文件上传成功！");
		            } else {
		                request.setAttribute("message", "文件信息保存失败！");
		            }
		        } else {
		            request.setAttribute("message", "文件保存失败！");
		        }
	        } else {
	        	request.setAttribute("message", "请上传.csv文件");
	        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("message", "文件上传失败：" + e.getMessage());
		    } finally {
		        if (fileContent != null) {
		            fileContent.close();
		        }
		    }
	    response.sendRedirect(request.getContextPath() + "/addpred.jsp");
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int userId = user.getUserId();

		PredService predService = new PredService();
		
	    InputStream fileContent = null;
	    String fileName = null;
	
	    try {
	        Part filePart = request.getPart("f");
	        fileName = getSubmittedFileName(filePart);
	        
	        if (fileName != null && fileName.endsWith(".csv")) {
		        fileContent = filePart.getInputStream();
		
		        // 保存文件
		        boolean fileSaved = predService.uploadFile(fileName, fileContent, userId, predName);
		        if (fileSaved) {
		        	request.setAttribute("message", "文件上传成功！");
		        } else {
		        	request.setAttribute("message", "文件保存失败！");
		        }
		    } else {
		    	request.setAttribute("message", "请上传.csv文件");
		    }
		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("message", "文件上传失败：" + e.getMessage());
		    } finally {
		        if (fileContent != null) {
		            fileContent.close();
		        }
		    }
	    response.sendRedirect(request.getContextPath() + "/addpred.jsp");
	}

}
