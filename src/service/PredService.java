package service;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;

import dao.PredDAO;

public class PredService {
	private PredDAO predDao = new PredDAO();
	
	public boolean uploadFile(String fileName, InputStream fileContent, Integer userId, String predName) throws SQLException {
        // ��������Խ���ҵ���߼����������ļ���С���ơ�Ȩ�޼���
		String pcapUrl = "C:\\Users\\kjq20\\Desktop\\admin" + File.separator + fileName;
        // �����ļ�
        // boolean fileSaved = predDao.addPred(userId, pcapId, pcapUrl, predName, time);
		boolean fileSaved = predDao.saveFile(fileName, fileContent, pcapUrl);
        if (fileSaved) {
            // �����ļ���Ϣ�����ݿ�
        	Date time = new Date();
            return predDao.addPred(userId, fileName, pcapUrl, predName, time);
        }

        return false;
    }
}
