package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import util.ConnUtil;
import util.DateUtil;

public class PredDAO {
	
	public boolean saveFile(String fileName, InputStream inputStream, String outputPath) {
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
	
	public boolean addPred(Integer userId, String pcapId, String pcapUrl, String predName, Date time) 
			throws SQLException {
		/**pre_id user_id pcap_id pcap_url pre_name time result state**/
		String sql = "insert into tab_prediction(user_id,pcap_id,pcap_url,pre_name,time,state) "
				+ " values(?,?,?,?,?,?)";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, userId);
		pstat.setString(2, pcapId);
		pstat.setString(3, pcapUrl);
		pstat.setString(4, predName);
		pstat.setString(5, DateUtil.getString(time));
		pstat.setInt(6, 0);
		int rowsAffected = pstat.executeUpdate();
		return rowsAffected > 0;
	}
	
	
}
