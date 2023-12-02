package domain;

public class PredResult {
	private String resultId;		// result_id varchar
	private String imgUrl;			// img_url	varchar
	
	private float pathPro1;			// path1_probability	float
	private String target1;			// target_1	varchar
	private String path1;			// path_1	varchar
	
	private float pathPro2;			// path2_probability	float
	private String target2;			// target_2	varchar
	private String path2;			// path_2	varchar
	
	private float pathPro3;			// path3_probability	float
	private String target3;			// target_3	varchar
	private String path3;					// path_3	varchar
	public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public float getPathPro1() {
		return pathPro1;
	}
	public void setPathPro1(float pathPro1) {
		this.pathPro1 = pathPro1;
	}
	public String getTarget1() {
		return target1;
	}
	public void setTarget1(String target1) {
		this.target1 = target1;
	}
	public String getPath1() {
		return path1;
	}
	public void setPath1(String path1) {
		this.path1 = path1;
	}
	public float getPathPro2() {
		return pathPro2;
	}
	public void setPathPro2(float pathPro2) {
		this.pathPro2 = pathPro2;
	}
	public String getTarget2() {
		return target2;
	}
	public void setTarget2(String target2) {
		this.target2 = target2;
	}
	public String getPath2() {
		return path2;
	}
	public void setPath2(String path2) {
		this.path2 = path2;
	}
	public float getPathPro3() {
		return pathPro3;
	}
	public void setPathPro3(float pathPro3) {
		this.pathPro3 = pathPro3;
	}
	public String getTarget3() {
		return target3;
	}
	public void setTarget3(String target3) {
		this.target3 = target3;
	}
	public String getPath3() {
		return path3;
	}
	public void setPath3(String path3) {
		this.path3 = path3;
	}
	
	@Override
	public String toString() {
		return "PredResult [resultId=" + resultId + ", imgUrl=" + imgUrl + ", pathPro1=" + pathPro1 + ", target1="
				+ target1 + ", path1=" + path1 + ", pathPro2=" + pathPro2 + ", target2=" + target2 + ", path2=" + path2
				+ ", pathPro3=" + pathPro3 + ", target3=" + target3 + ", path3=" + path3 + "]";
	}



}
