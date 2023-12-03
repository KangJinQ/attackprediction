package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import domain.User;
import exception.NameException;
import exception.PassException;
import util.ConnUtil;

public class UserService {
	private UserDAO userDao = new UserDAO();
	
	public User isExistById(int user_id) {
		try {
			if(userDao.existsUserById(user_id)!=null) {
				return userDao.existsUserById(user_id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断用户名是否存在
	 * @param user_name 
	 * @return user/null
	 */
	public User isExistsUser(String user_name) 
			throws SQLException{
		try {
			if(userDao.existsUser(user_name)!=null)return userDao.existsUser(user_name);
			else return null;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ConnUtil.getConn().rollback();	//回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		} finally{
			ConnUtil.closeConn();
		}
	}
	
	/**
	 * 注册用户
	 * @param user_name
	 * @param user_pass
	 * @param user_address
	 * @param user_phone
	 * @throws SQLException
	 */
	public void register(String userName, String userPassword, String userPhone, 
			String userEmail, Boolean userRole, String actionPassword, Boolean state) 
			throws SQLException{
		try {
			userDao.register(userName, userPassword, userPhone, userEmail, userRole, actionPassword, state);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ConnUtil.getConn().rollback();	
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		} finally{
			ConnUtil.closeConn();
		}
	}
	
	/**
	 * 检测用户名密码
	 * @param userName 
	 * @param userPassword 
	 * @return user
	 * @throws NameException 
	 * @throws PassException
	 */
	public User isLogin(String userName, String userPassword) throws NameException, PassException{
		try {
			User user = this.userDao.existsUser(userName);
			if(user == null){
				throw new NameException("用户名错误");
			} 
			if(userPassword.equals(user.getUserPassword())){
				return user;
			} else{
				throw new PassException("密码错误");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			ConnUtil.closeConn();
		}
	}
	
	public void addAdminByUid(int uid) {
		try {
			userDao.changeAttrWithBoolValue(uid, "user_role", true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<User> getAllUser() {
		List<User> userList = new ArrayList<User>();
		try {
			userList = userDao.getAllUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
}
