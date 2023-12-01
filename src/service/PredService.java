package service;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;

import dao.PredDAO;

public class PredService {
	private PredDAO predDao = new PredDAO();
	
	public boolean uploadFile(String fileName, InputStream fileContent, Integer userId, String predName) throws SQLException {
        // 在这里可以进行业务逻辑处理，例如文件大小限制、权限检查等
		String pcapUrl = "C:\\Users\\kjq20\\Desktop\\admin" + File.separator + fileName;
        // 保存文件
        // boolean fileSaved = predDao.addPred(userId, pcapId, pcapUrl, predName, time);
		boolean fileSaved = predDao.saveFile(fileName, fileContent, pcapUrl);
        if (fileSaved) {
            // 保存文件信息到数据库
        	Date time = new Date();
            return predDao.addPred(userId, fileName, pcapUrl, predName, time);
        }

        return false;
    }
}
