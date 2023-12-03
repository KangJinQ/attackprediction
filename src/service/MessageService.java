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
	
	public List<String> findUserNameById(int uid, String op){
		List<String> UserNameList = new ArrayList<String>();
		try {
			if(op.equals("shou")) {
				UserNameList = messageDAO.findReceivedMailUserNameById(uid);
			}
			else if(op.equals("fa")) {
				UserNameList = messageDAO.findSentMailUserNameById(uid);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return UserNameList;
	}
	
	public void writeMessage(int targetUserId, int originUserId, String messageTopic, String messageContent, int state, String time ) throws SQLException {
		messageDAO.writeMessage(targetUserId, originUserId, messageTopic, messageContent, state, time);
	}
}
