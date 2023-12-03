package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Match;
import domain.Pred;
import util.ConnUtil;
import util.DateUtil;

public class PredDAO {
	public List<Pred> selectByUid(int uid) throws SQLException{
		List<Pred> predList = new ArrayList<Pred>();
		PreparedStatement pstat = null;
		String sql = "";
		if(uid > 0) {
			sql = "SELECT * from tab_prediction "
				+ " WHERE user_id = ? and state != 3 "
				+ " ORDER BY pre_id DESC ";
			Connection conn = ConnUtil.getConn();
			 pstat = conn.prepareStatement(sql);
			pstat.setInt(1, uid);
		} else {
			sql = "SELECT * from tab_prediction "
				+ " ORDER BY pre_id DESC ";
			Connection conn = ConnUtil.getConn();
			 pstat = conn.prepareStatement(sql);
		}
		ResultSet rs = pstat.executeQuery();
		while (rs.next()) {
			Pred pred = new Pred();
			pred.setPredId(rs.getInt("pre_id"));
			pred.setUserId(rs.getInt("user_id"));
			pred.setPcapId(rs.getString("pcap_id"));
			pred.setPcapUrl(rs.getString("pcap_url"));
			pred.setPredName(rs.getString("pre_name"));
			pred.setTime(rs.getDate("time"));
			pred.setResult(rs.getString("result_id"));
			pred.setState(rs.getInt("state"));
			
			// System.out.println(match.getMatchName());
			predList.add(pred);
		}
		return predList;
	}
	
	public Pred selectByPredId(int predId) throws SQLException {
		Pred pred = new Pred();
		String sql = "SELECT * "
				+ "FROM tab_prediction "
				+ "JOIN tab_result ON tab_prediction.result_id = tab_result.result_id "
				+ "WHERE tab_prediction.pre_id = ?;";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, predId);

		ResultSet rs = pstat.executeQuery();
		if (rs.next()) {
			pred.setPredId(rs.getInt("pre_id"));
			pred.setUserId(rs.getInt("user_id"));
			pred.setPcapId(rs.getString("pcap_id"));
			pred.setPcapUrl(rs.getString("pcap_url"));
			pred.setPredName(rs.getString("pre_name"));
			pred.setTime(rs.getDate("time"));
			pred.setResult(rs.getString("result_id"));
			pred.setState(rs.getInt("state"));
		}
		return pred;
	}
	
	public boolean saveFile(InputStream inputStream, String outputPath) {
        // String savePath = "C:\\Users\\kjq20\\Desktop\\admin" + File.separator + fileName;

        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            int bytesRead;
            byte[] buffer = new byte[4096];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public void changePredIntAttr(int predId, String attr, int attr_value) 
			throws SQLException {
		String sql = "UPDATE tab_prediction SET "
				+ attr + " = ? WHERE pre_id = ?";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, attr_value);
		pstat.setInt(2, predId);
		pstat.executeUpdate();
	}
	
	public boolean addPred(Integer userId, String pcapId, String pcapUrl, String predName, Date time, String resultId) 
			throws SQLException {
		/**pre_id user_id pcap_id pcap_url pre_name time result state**/
		String sql = "insert into tab_prediction(user_id,pcap_id,pcap_url,pre_name,time,result_id,state) "
				+ " values(?,?,?,?,?,?,?)";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, userId);
		pstat.setString(2, pcapId);
		pstat.setString(3, pcapUrl);
		pstat.setString(4, predName);
		pstat.setString(5, DateUtil.getString(time));
		pstat.setString(6, resultId);
		pstat.setInt(7, 0);
		int rowsAffected = pstat.executeUpdate();
		return rowsAffected > 0;
	}
	
	
}
