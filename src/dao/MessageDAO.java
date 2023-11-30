package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.ConnUtil;
import util.DateUtil;

public class MessageDAO {
	public List<String> findSentMailById(int uid) throws SQLException{
		List<String> messageList = new ArrayList<String>();
		String sql = "select tab_message.* from tab_message where origin_user = ? order by message_id DESC ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		ResultSet rs = pstat.executeQuery();
		while(rs.next()){
			messageList.add(rs.getString("message"));
			System.out.println("111");
		}
		return messageList;
	}
	
	public List<String> findReceivedMailById(int uid) throws SQLException{
		List<String> messageList = new ArrayList<String>();
		String sql = "select tab_message.* from tab_message where target_user = ? order by message_id DESC ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		ResultSet rs = pstat.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString("message"));
			messageList.add(rs.getString("message"));
		}
		return messageList;
	}
	
	public List<Date> findReceivedMailDateById(int uid) throws SQLException{
		List<Date> messageDateList = new ArrayList<Date>();
		String sql = "select tab_message.* from tab_message where target_user = ? order by message_id DESC ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		ResultSet rs = pstat.executeQuery();
		while(rs.next()){
			messageDateList.add(rs.getDate("time"));
		}
		return messageDateList;
	}
	
	public List<Date> findSentMailDateById(int uid) throws SQLException{
		List<Date> messageDateList = new ArrayList<Date>();
		String sql = "select tab_message.* from tab_message where origin_user = ? order by message_id DESC ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		ResultSet rs = pstat.executeQuery();
		while(rs.next()){
			messageDateList.add(rs.getDate("time"));
		}
		return messageDateList;
	}
	
	public void writeMessage(int targetUserId, int originUserId, String messageTopic, String messageContent, int state, Date time ) throws SQLException {
		
		String sql = "insert into tab_message(target_user,origin_user,message_topic,message,state,time) "
				+ " values(?,?,?,?,?,?)";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, targetUserId);
		pstat.setInt(2, originUserId);
		pstat.setString(3, messageTopic);
		pstat.setString(4, messageContent);
		pstat.setInt(5, state);
		pstat.setString(6, DateUtil.getString(time));
		pstat.executeUpdate();
		
	}
}
