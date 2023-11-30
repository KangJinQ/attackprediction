package domain;

import java.util.Date;

public class Message {
	private Integer messageId;		//message_id	int
	private Integer targetUserId;	//target_user	int	
	private Integer originUserId;	//origin_user	int
	private String messageTopic;	//message_topic varchar	255	
	private String message;			//message		varchar	255	
	private Integer state;			//state			int	1	
	private Date time;				//time			datetime	
	
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(Integer targetUserId) {
		this.targetUserId = targetUserId;
	}
	public Integer getOriginUserId() {
		return originUserId;
	}
	public void setOriginUserId(Integer originUserId) {
		this.originUserId = originUserId;
	}
	public String getMessageTopic() {
		return messageTopic;
	}
	public void setMessageTopic(String messageTopic) {
		this.messageTopic = messageTopic;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", targetUserId=" + targetUserId + ", originUserId=" + originUserId
				+ ", messageTopic=" + messageTopic + ", message=" + message + ", state=" + state + ", time=" + time
				+ "]";
	}

}
