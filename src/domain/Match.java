package domain;

import java.util.Date;

public class Match {
	private Integer matchId;	//match_id	int
	private String matchName;	//match_name varchar
	private Integer matchType;	//match_type int 0/1(公有比赛/私有比赛)
	private String matchSystem;	//match_system varchar(联赛/淘汰赛...)
	private Date matchStartTime;//match_start_time datetime
	private Date matchEndTime;	//match_end_time datetime
	private String matchInfo;	//match_info	varchar
	private String matchStatus;	//match_status	varchar(未审批/已通过/已拒绝)
	private String matchLife;	//match_life	varchar(未开始/进行中/已结束)
	private Integer matchNumber;//match_number	int(参与的队伍/人数上限)
	private Integer matchNumbered;	//match_numbered int(已经参与的人数)
	private String matchResult;	//match_life	varchar()
	
	public String getMatchResult() {
		return matchResult;
	}
	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
	public String getMatchName() {
		return matchName;
	}
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}
	public Integer getMatchType() {
		return matchType;
	}
	public void setMatchType(Integer matchType) {
		this.matchType = matchType;
	}
	public String getMatchSystem() {
		return matchSystem;
	}
	public void setMatchSystem(String matchSystem) {
		this.matchSystem = matchSystem;
	}
	public Date getMatchStartTime() {
		return matchStartTime;
	}
	public void setMatchStartTime(Date matchStartTime) {
		this.matchStartTime = matchStartTime;
	}
	public Date getMatchEndTime() {
		return matchEndTime;
	}
	public void setMatchEndTime(Date matchEndTime) {
		this.matchEndTime = matchEndTime;
	}
	public String getMatchInfo() {
		return matchInfo;
	}
	public void setMatchInfo(String matchInfo) {
		this.matchInfo = matchInfo;
	}
	public String getMatchStatus() {
		return matchStatus;
	}
	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}
	public String getMatchLife() {
		return matchLife;
	}
	public void setMatchLife(String matchLife) {
		this.matchLife = matchLife;
	}
	public Integer getMatchNumber() {
		return matchNumber;
	}
	public void setMatchNumber(Integer matchNumber) {
		this.matchNumber = matchNumber;
	}
	public Integer getMatchNumbered() {
		return matchNumbered;
	}
	public void setMatchNumbered(Integer matchNumbered) {
		this.matchNumbered = matchNumbered;
	}
	@Override
	public String toString() {
		return "Match [matchId=" + matchId + ", matchName=" + matchName + ", matchType=" + matchType + ", matchSystem="
				+ matchSystem + ", matchStartTime=" + matchStartTime + ", matchEndTime=" + matchEndTime + ", matchInfo="
				+ matchInfo + ", matchStatus=" + matchStatus + ", matchLife=" + matchLife + ", matchNumber="
				+ matchNumber + ", matchNumbered=" + matchNumbered + ", matchResult=" + matchResult + "]";
	}
	
}
