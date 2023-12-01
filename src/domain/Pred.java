package domain;

import java.util.Date;

public class Pred {
	private Integer predId;		//pre_id	int
	private Integer userId;		//user_id	int
	private Integer pcapId;		//pcap_id	int
	private String pcapUrl;		//pcap_url	varchar
	private String predName;	//pre_name	varchar
	private Date time;			//time	datetime
	private String result;		//result	varchar
	private Integer state;		//state	int
	
	public Integer getPredId() {
		return predId;
	}
	public void setPredId(Integer predId) {
		this.predId = predId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPcapId() {
		return pcapId;
	}
	public void setPcapId(Integer pcapId) {
		this.pcapId = pcapId;
	}
	public String getPcapUrl() {
		return pcapUrl;
	}
	public void setPcapUrl(String pcapUrl) {
		this.pcapUrl = pcapUrl;
	}
	public String getPredName() {
		return predName;
	}
	public void setPredName(String predName) {
		this.predName = predName;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "Pred [predId=" + predId + ", userId=" + userId + ", pcapId=" + pcapId + ", pcapUrl=" + pcapUrl
				+ ", predName=" + predName + ", time=" + time + ", result=" + result + ", state=" + state + "]";
	}

}
