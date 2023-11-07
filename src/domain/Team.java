package domain;

public class Team { 
	int teamId;			//队伍id team_id int
	String teamName;	//队伍名称 team_name varchar
	int teamNumber;		//队伍最大人数 team_number int
	int teamNumbered;	//队伍已有人数 team_numbered int
	int mid;			//对应比赛id mid int
	int uid;			//创始人id uid int
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}
	public int getTeamNumbered() {
		return teamNumbered;
	}
	public void setTeamNumbered(int teamNumbered) {
		this.teamNumbered = teamNumbered;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", teamName=" + teamName + ", teamNumber=" + teamNumber + ", teamNubered="
				+ teamNumbered + ", mid=" + mid + ", uid=" + uid + "]";
	}
	

}
