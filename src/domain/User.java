package domain;

public class User {
	Integer userId;			//user_id		int
	String userName;		//user_name 	varchar
	String userPassword;	//user_password varchar
	String userPhone;		//user_phone 	varchar
	String userEmail;		//user_email	varchar
	Boolean userRole;		//user_role 	tinyint		0:user; 	1:superadmin	tinyint
	String actionPassword;	//action_pwd	varchar
	Boolean state;			//state			tinyint
	
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Boolean getUserRole() {
		return userRole;
	}

	public void setUserRole(Boolean userRole) {
		this.userRole = userRole;
	}

	public String getActionPassword() {
		return actionPassword;
	}

	public void setActionPassword(String actionPassword) {
		this.actionPassword = actionPassword;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userPhone="
				+ userPhone + ", userEmail=" + userEmail + ", userRole=" + userRole + ", actionPassword="
				+ actionPassword + ", state=" + state + "]";
	}
	
}
