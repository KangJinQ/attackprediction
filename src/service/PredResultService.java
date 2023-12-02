package service;

import java.sql.SQLException;

import dao.PredResultDAO;
import domain.PredResult;

public class PredResultService {
	PredResultDAO predResultDao = new PredResultDAO();
	
	public PredResult findResultByPredId(int predId) {
		PredResult predResult = new PredResult();
		try {
			predResult = predResultDao.selectByPredId(predId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return predResult;
	}
	
}
