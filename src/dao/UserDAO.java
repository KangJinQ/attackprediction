package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Date;
//import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import domain.Match;
import domain.User;
import util.ConnUtil;
//import util.DateUtil;
//import util.SHAUtil;

public class UserDAO {

	/**
	 * 根据用户名查找用户对象
	 * @param userName
	 * @return user对象/null
	 * @throws SQLException
	 */
	public User existsUser(String userName) throws SQLException {
		String sql = "select * from tab_user where user_name = ?"; // sql语句
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, userName);
		ResultSet rs = pstat.executeQuery();
		if (rs.next()) {
			User user = new User();
			user.setUserId(rs.getInt("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setUserPassword(rs.getString("user_password"));
			user.setUserPhone(rs.getString("user_phone"));
			user.setUserEmail(rs.getString("user_email"));
			user.setUserRole(rs.getBoolean("user_role"));
			user.setActionPassword(rs.getString("action_pwd"));
			user.setState(rs.getBoolean("state"));
			return user; // 返回user对象
		} else {
			return null; // 如果没查到对应名字的user对象 返回null
		}
	}

	/**
	 * 添加用户
	 * 
	 * @throws SQLException
	 */
	public void register(String userName, String userPassword, String userPhone, 
			String userEmail, Boolean userRole, String actionPassword, Boolean state)
			throws SQLException {
		// String userSalt = UUID.randomUUID().toString();

		String sql = "insert into tab_user(user_name,user_password,user_phone,user_email,user_role,action_pwd,state) "
				+ " values(?,?,?,?,?,?,?)";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, userName);
		pstat.setString(2, userPassword);
		pstat.setString(3, userPhone);
		pstat.setString(4, userEmail);
		pstat.setBoolean(5, false);
		pstat.setString(6, actionPassword);
		pstat.setBoolean(7, false);
		pstat.executeUpdate();
	}
	
	/**
	 * 获得所有用户信息
	 * @return 用户List
	 * @throws SQLException
	 */
	public List<User> getAllUser() throws SQLException {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM tab_user where state != 1 and user_role != 1 ORDER BY user_id ASC ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);

		ResultSet rs = pstat.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setUserId(rs.getInt("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setUserPassword(rs.getString("user_password"));
			user.setUserPhone(rs.getString("user_phone"));
			user.setUserEmail(rs.getString("user_email"));
			user.setUserRole(rs.getBoolean("user_role"));
			user.setActionPassword(rs.getString("action_pwd"));
			user.setState(rs.getBoolean("state"));
			userList.add(user);
		}
		return userList;
	}

}
