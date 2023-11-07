package service;

import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.Date;
import java.util.List;

import dao.MatchDAO;
import domain.Match;
import util.ConnUtil;

public class MatchService {
	private MatchDAO matchDao = new MatchDAO();

	/**
	 * 根据比赛名称查找比赛
	 * 
	 * @param match_name
	 * @return match
	 * @throws SQLException
	 */
	public Match findMatchByMatchName(String match_name) throws SQLException {
		try {
			if (matchDao.findMatchByMatchName(match_name) != null)
				return matchDao.findMatchByMatchName(match_name);
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ConnUtil.getConn().rollback(); // ����ع�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		} finally {
			ConnUtil.closeConn();
		}
	}

	public Match findMatchByMatchId(int mid) {
		Match match = new Match();
		try {
			match = matchDao.findMatchByMatchId(mid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return match;
	}

	/**
	 * 添加比赛
	 * 
	 * @param matchName
	 * @param matchType
	 * @param matchSystem
	 * @param matchStartTime
	 * @param matchEndTime
	 * @param matchInfo
	 * @param matchStatus
	 * @param matchLife
	 * @param matchNumber
	 * @throws SQLException
	 */
	public void addMatch(String matchName, Integer matchType, String matchSystem, Date matchStartTime,
			Date matchEndTime, String matchInfo, String matchStatus, String matchLife, Integer matchNumber,
			Integer matchNumbered) throws SQLException {
		try {
			matchDao.addMatch(matchName, matchType, matchSystem, matchStartTime, matchEndTime, matchInfo, matchStatus,
					matchLife, matchNumber, matchNumbered);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ConnUtil.getConn().rollback(); // ����ع�
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		} finally {
			ConnUtil.closeConn();
		}
	}

	/**
	 * 添加比赛创始人
	 * 
	 * @param mid
	 * @param uid
	 */
	public void addMatchCreater(int mid, int uid) {
		try {
			ConnUtil.getConn().setAutoCommit(false);
			this.matchDao.addMatchCreater(mid, uid);
			ConnUtil.getConn().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ConnUtil.getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		} finally {
			ConnUtil.closeConn();
		}
	}

	/**
	 * 根据比赛状态查找比赛
	 * 
	 * @param status
	 * @return matchList
	 */
	public List<Match> selectByStatus(String status) {
		List<Match> matchList = new ArrayList<Match>();
		try {
			matchList = matchDao.selectByStatus(status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matchList;
	}

	/**
	 * 根据uid查找对应用户下的比赛(join/create)
	 * 
	 * @param uid
	 * @param op
	 * @return
	 */
	public List<Match> selectMatchByUid(int uid, String op) {
		List<Match> matchList = new ArrayList<Match>();
		try {
			matchList = matchDao.selectMatchByUid(uid, op);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matchList;
	}

	/**
	 * 审批比赛改变比赛状态，已通过or已拒绝
	 * 
	 * @param mid
	 * @param status
	 * @param rejectReason
	 */
	public void changeStatus(int mid, String status, String rejectReason) {
		try {
			matchDao.changeStatus(mid, status, rejectReason);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 比赛多一个参加团队，状态为申请报名
	 * 
	 * @param mid
	 * @param uid
	 * @param tid
	 * @throws SQLException
	 */
	public void addMatchJoiner(int mid, int uid, int tid) throws SQLException {
		try {
			matchDao.addMatchJoiner(mid, uid, tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加比赛参与者，状态为申请报名
	 * 
	 * @param mid
	 * @param uid
	 * @throws SQLException
	 */
	public void addMatchJoiner(int mid, int uid) throws SQLException {
		try {
			matchDao.addMatchJoiner(mid, uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// public void quitMatch(int mid, int uid){
	// try {
	// matchDao.quitMatch(mid, uid);
	// matchDao.matchNumberedMinusOne(mid);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public void changeJoinerStatus(int mid, int uid, String status, String quitReason) {
		try {
			matchDao.changeJoinerStatus(mid, uid, status);
			matchDao.changeJoinerQuitReason(mid, uid, quitReason);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 超级管理员生成拒绝比赛申请的理由
	 * 
	 * @param mid
	 * @param uid
	 * @param refuse_reason
	 */
	public void refuseRecord(int mid, int uid, String refuse_reason) {
		try {
			matchDao.refuseRecord(mid, uid, refuse_reason);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移除mid比赛的参赛人员uid
	 * 
	 * @param uid
	 * @param mid
	 */
	public void removeMember(int uid, int mid) {
		try {
			matchDao.removeMember(uid, mid);
			matchDao.matchNumberedMinusOne(mid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 审核用户参加比赛的申请
	 * @param uid
	 * @param mid
	 * @param status
	 */
	public void matchMemberApprove(int uid, int mid, String status) {
		try {
			matchDao.matchMemberApprove(uid, mid, status);
			matchDao.matchNumberedPlusOne(mid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除序号为mid的比赛相关所有信息
	 * @param mid
	 */
	public void deleteMatchById(int mid) {
		try {
			matchDao.deleteJoinedMatchById(mid);
			matchDao.deleteCreatedMatchById(mid);
			matchDao.deleteMatchById(mid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void changeMatchDetail(int match_id, Integer match_type, String match_system, Date match_start_time, Date match_end_time,
			String match_life, Integer match_number, String match_info) {
		try {
			matchDao.changeMatchDetail(match_id, match_type, match_system, match_start_time, match_end_time,
					 match_life, match_number,match_info);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void reapplyMatch(int match_id, Integer match_type, String match_system, Date match_start_time, Date match_end_time,
			Integer match_number, String match_info, int uid){
		try {
			matchDao.reapplyMatch(match_id, match_type, match_system, match_start_time, match_end_time,
					 match_number,match_info);
			matchDao.changeTabCreateMatchStatus(match_id, uid, "未审批");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addMatchManager(int mid, int uid) {
		try {
			matchDao.addMatchManager(mid, uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public boolean existManager(int mid, int uid) {
		try {
			if(matchDao.findManagerById(mid, uid) != null){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 写入邮箱信息
	 */
	public void writeMail(int uid, String message){
		try {
			matchDao.writeMail(uid, message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String findMatchNameByMatchId(int mid){
		String res = "初始化Service";
		try {
			res = matchDao.findMatchNameByMatchId(mid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public List<String> findMailById(int uid){
		List<String> messageList = new ArrayList<String>();
		try {
			messageList = matchDao.findMailById(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageList;
	}
	
	public List<Date> findMailDateById(int uid){
		List<Date> messageDateList = new ArrayList<Date>();
		try {
			messageDateList = matchDao.findMailDateById(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageDateList;
	}

	/**
	 * 用来根据比赛名称进行模糊查询
	 * @param name
	 * @return 一个Match列表
	 */
	public List<Match> searchMatchByName(String name){
		List<Match> searchList = new ArrayList<Match>();
		try {
			searchList = matchDao.searchMatchByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchList;
	}

	public void changeMatchResult(int mid, String match_result) {
		
		try {
			matchDao.changeMatchResult(mid,match_result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
