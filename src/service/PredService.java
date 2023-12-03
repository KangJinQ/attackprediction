package service;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import domain.Match;
import domain.Pred;
import dao.PredDAO;
import util.DateUtil;

public class PredService {
	private PredDAO predDao = new PredDAO();
	
	public boolean uploadFile(String fileName, InputStream fileContent, 
			Integer userId, String predName, Date time) throws SQLException {
        // 在这里可以进行业务逻辑处理，例如文件大小限制、权限检查等
		String pcapUrl = "D:\\Works\\Java\\attackprediction\\WebContent\\result" + File.separator + DateUtil.getString(time) + fileName;
        // 保存文件
        // boolean fileSaved = predDao.addPred(userId, pcapId, pcapUrl, predName, time);
		boolean fileSaved = predDao.saveFile(fileContent, pcapUrl);

        return fileSaved;
    }
	
	public boolean savePred(Integer userId, String fileName,  
				String predName, String resultId, Date time) throws SQLException {
		String pcapUrl = "D:\\Works\\Java\\attackprediction\\WebContent\\result" + File.separator + DateUtil.getString(time) + fileName;
		return predDao.addPred(userId, fileName, pcapUrl, predName, time, resultId);
	}
	
	public void deletePredById(int predId) {
		try {
			predDao.changePredIntAttr(predId, "state", 3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void returnPredById(int predId) {
		try {
			predDao.changePredIntAttr(predId, "state", 2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Pred findPredByPredId(int predId) {
		Pred pred = new Pred();
		try {
			pred = predDao.selectByPredId(predId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pred;
	}
	
	public List<Pred> selectPredByUid(int uid){
		List<Pred> predList = new ArrayList<Pred>();
		try {
			predList = predDao.selectByUid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return predList;
	}
	
	public String sendRequestion2Python(String pcapName, Date time) {
        try {
            String url = "http://localhost:8000/jsprequest";  
            String pcapUrl = "D:\\Works\\Java\\attackprediction\\WebContent\\result" + File.separator + DateUtil.getString(time) + pcapName;
            String params = "url=" + URLEncoder.encode(pcapUrl, "UTF-8");  // 参数

            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            // 设置请求方法和头信息
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            // 发送参数
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(params);
            wr.flush();
            wr.close();

            // 读取响应
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
