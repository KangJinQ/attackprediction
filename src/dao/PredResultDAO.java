package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.PredResult;
import util.ConnUtil;

public class PredResultDAO {
	public PredResult selectByPredId(int predId) throws SQLException {
		PredResult predResult = new PredResult();
		String sql = "SELECT * "
				+ "FROM tab_prediction "
				+ "JOIN tab_result ON tab_prediction.result_id = tab_result.result_id "
				+ "WHERE tab_prediction.pre_id = ?;";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, predId);

		ResultSet rs = pstat.executeQuery();
		if(rs.next()) {
			predResult.setResultId(rs.getString("result_id"));
			predResult.setImgUrl(rs.getString("img_url"));
			
			predResult.setPathPro1(rs.getString("path1_probability"));
			predResult.setTarget1(rs.getString("target_1"));
			predResult.setPath1(rs.getString("path_1"));
			
			predResult.setPathPro2(rs.getString("path2_probability"));
			predResult.setTarget2(rs.getString("target_2"));
			predResult.setPath2(rs.getString("path_2"));
			
			predResult.setPathPro3(rs.getString("path3_probability"));
			predResult.setTarget3(rs.getString("target_3"));
			predResult.setPath3(rs.getString("path_3"));
		}
		return predResult;
	}
}
