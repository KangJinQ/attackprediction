package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.MessageDAO;

public class MessageService {
	private MessageDAO messageDAO = new MessageDAO();
	
	public List<String> findMailById(int uid, String op){
		List<String> messageList = new ArrayList<String>();
		try {
			if(op.equals("shou")) {
				messageList = messageDAO.findReceivedMailById(uid);
			}
			else if(op.equals("fa")) {
				System.out.println("faxiaoxi");
				messageList = messageDAO.findSentMailById(uid);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageList;
	}
	
	public List<Date> findMailDateById(int uid, String op){
		List<Date> messageDateList = new ArrayList<Date>();
		try {
			if(op.equals("shou")) {
				messageDateList = messageDAO.findReceivedMailDateById(uid);
			}
			else if(op.equals("fa")) {
				messageDateList = messageDAO.findSentMailDateById(uid);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageDateList;
	}
}
