package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
//import java.util.UUID;
import java.util.Date;
import java.util.List;

import domain.Match;
import domain.User;
import util.ConnUtil;
//import util.DateUtil;
//import util.SHAUtil;
import util.DateUtil;

public class MatchDAO {

	/**
	 * 根据比赛名称查找比赛
	 * 
	 * @param matchName
	 * @return
	 * @throws SQLException
	 */
	public Match findMatchByMatchName(String matchName) throws SQLException {
		String sql = "select *from tab_match where match_name = ?"; // ��sqlע�빥��
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, matchName);
		ResultSet rs = pstat.executeQuery();

		if (rs.next()) {
			Match match = new Match();
			match.setMatchId(rs.getInt("match_id"));
			match.setMatchName(rs.getString("match_name"));
			match.setMatchType(rs.getInt("match_type"));
			match.setMatchSystem(rs.getString("match_system"));
			match.setMatchStartTime(rs.getDate("match_start_time"));
			match.setMatchEndTime(rs.getDate("match_end_time"));
			match.setMatchInfo(rs.getString("match_info"));
			match.setMatchStatus(rs.getString("match_status"));
			match.setMatchLife(rs.getString("match_life"));
			match.setMatchNumber(rs.getInt("match_number"));
			match.setMatchNumbered(rs.getInt("match_numbered"));
			match.setMatchResult(rs.getString("match_result"));

			return match; // ����û��� �Ѵ��ڣ��򷵻�user
		} else {
			return null; // �û���δռ�ã�����null
		}
	}

	/**
	 * 根据比赛id查找比赛
	 * 
	 * @param mid
	 * @return
	 * @throws SQLException
	 */
	public Match findMatchByMatchId(int mid) throws SQLException {
		String sql = "select *from tab_match where match_id = ?"; // 构造sql语句
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		ResultSet rs = pstat.executeQuery();

		if (rs.next()) {
			Match match = new Match();
			match.setMatchId(rs.getInt("match_id"));
			match.setMatchName(rs.getString("match_name"));
			match.setMatchType(rs.getInt("match_type"));
			match.setMatchSystem(rs.getString("match_system"));
			match.setMatchStartTime(rs.getDate("match_start_time"));
			match.setMatchEndTime(rs.getDate("match_end_time"));
			match.setMatchInfo(rs.getString("match_info"));
			match.setMatchStatus(rs.getString("match_status"));
			match.setMatchLife(rs.getString("match_life"));
			match.setMatchNumber(rs.getInt("match_number"));
			match.setMatchNumbered(rs.getInt("match_numbered"));
			match.setMatchResult(rs.getString("match_result"));
			
			return match;
		} else {
			return null;
		}
	}

	public void addMatch(String matchName, Integer matchType, String matchSystem, Date matchStartTime,
			Date matchEndTime, String matchInfo, String matchStatus, String matchLife, Integer matchNumber,
			Integer matchNumbered) throws SQLException {
		// String userSalt = UUID.randomUUID().toString();

		String sql = "insert into tab_match(match_name,match_type,match_system,match_start_time,match_end_time,"
				+ "match_info,match_status,match_life,match_number,match_numbered,match_result) " + " values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, matchName);
		pstat.setInt(2, matchType);
		pstat.setString(3, matchSystem);
		pstat.setString(4, DateUtil.getString(matchStartTime));
		pstat.setString(5, DateUtil.getString(matchEndTime));
		pstat.setString(6, matchInfo);
		pstat.setString(7, matchStatus);
		pstat.setString(8, matchLife);
		pstat.setInt(9, matchNumber);
		pstat.setInt(10, matchNumbered);
		pstat.setString(11, "暂无比赛结果");
		pstat.executeUpdate();
	}

	/**
	 * 创建比赛
	 * 
	 * @param mid
	 * @param uid
	 * @throws SQLException
	 */
	public void addMatchCreater(int mid, int uid) throws SQLException {
		String sql = "insert into tab_create_match(uid,create_date,mid,status) values(?,?,?,?)";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		pstat.setString(2, DateUtil.getString(new Date()));
		pstat.setInt(3, mid);
		pstat.setString(4, "未审批");
		pstat.executeUpdate();
	}

	/**
	 * 加入比赛
	 * 
	 * @param mid
	 * @param uid
	 * @throws SQLException
	 */
	public void addMatchJoiner(int mid, int uid, int tid) throws SQLException {
		String sql = "insert into tab_join_match(mid,join_date,uid,status,tid) values(?,?,?,?,?)";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		pstat.setString(2, DateUtil.getString(new Date()));
		pstat.setInt(3, uid);
		pstat.setString(4, "申请报名");
		pstat.setInt(5, tid);
		pstat.executeUpdate();
	}

	public void addMatchJoiner(int mid, int uid) throws SQLException {
		String sql = "insert into tab_join_match(mid,join_date,uid,status) values(?,?,?,?) ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		pstat.setString(2, DateUtil.getString(new Date()));
		pstat.setInt(3, uid);
		pstat.setString(4, "申请报名");
		pstat.executeUpdate();
	}

	public void quitMatch(int mid, int uid) throws SQLException {
		String sql = "delete from tab_join_match where mid = ? and uid = ? ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		pstat.setInt(2, uid);
		pstat.executeUpdate();
	}

	/**
	 * 参赛人员数量 ++
	 * 
	 * @param mid
	 * @throws SQLException
	 */
	public void matchNumberedPlusOne(int mid) throws SQLException {
		String sql = "UPDATE tab_match set match_numbered = match_numbered + 1 where match_id = ?";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		pstat.executeUpdate();
	}

	/**
	 * 参赛人员 --
	 * 
	 * @param mid
	 * @throws SQLException
	 */
	public void matchNumberedMinusOne(int mid) throws SQLException {
		String sql = "UPDATE tab_match set match_numbered = match_numbered - 1 where match_id = ?";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		pstat.executeUpdate();
	}

	/**
	 * 根据状态(已通过/已拒绝)查找比赛
	 * 
	 * @param status
	 * @return
	 * @throws SQLException
	 */
	public List<Match> selectByStatus(String status) throws SQLException {
		List<Match> matchList = new ArrayList<Match>();

		String sql = "SELECT tab_match.*, tab_create_match.*, tab_user.* FROM tab_match "
				+ " LEFT JOIN tab_create_match ON tab_match.match_id = tab_create_match.mid "
				+ " LEFT JOIN tab_user ON tab_create_match.uid = tab_user.user_id "
				+ " WHERE tab_create_match.status = ? ORDER BY tab_create_match.mid ";
		// + " WHERE tab_create_match.uid = 1 ORDER BY
		// tab_create_match.create_date DESC ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, status);
		ResultSet rs = pstat.executeQuery();
		// System.out.println(rs.next());
		while (rs.next()) {
			Match match = new Match();
			// System.out.println(rs.getInt("match_id"));
			match.setMatchId(rs.getInt("match_id"));
			match.setMatchName(rs.getString("match_name"));
			match.setMatchType(rs.getInt("match_type"));
			match.setMatchSystem(rs.getString("match_system"));
			match.setMatchStartTime(rs.getDate("match_start_time"));
			match.setMatchEndTime(rs.getDate("match_end_time"));
			match.setMatchInfo(rs.getString("match_info"));
			match.setMatchStatus(rs.getString("match_status"));
			match.setMatchLife(rs.getString("match_life"));
			match.setMatchNumber(rs.getInt("match_number"));
			match.setMatchNumbered(rs.getInt("match_numbered"));
			match.setMatchResult(rs.getString("match_result"));
			matchList.add(match);
		}
		return matchList;
	}

	/**
	 * 改变“未审批”->“已通过/已拒绝”
	 * 
	 * @param mid
	 * @param uid
	 * @throws SQLException
	 */
	public void changeStatus(int mid, String status, String rejectReason) throws SQLException {
		String sql = "update tab_match,tab_create_match "
				+ "set tab_match.match_status = ?, tab_create_match.status = ? "
				+ "where tab_match.match_id = ? and tab_create_match.mid = ? ";
		if (status.equals("已拒绝")) {
			sql = "update tab_match,tab_create_match "
					+ "set tab_match.match_status = ?, tab_create_match.status = ?, tab_create_match.reject_reason = ?"
					+ "where tab_match.match_id = ? and tab_create_match.mid = ? ";
		}
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);

		pstat.setString(1, status);
		pstat.setString(2, status);
		pstat.setInt(3, mid);
		pstat.setInt(4, mid);
		if (status.equals("已拒绝")) {
			pstat.setString(3, rejectReason);
			pstat.setInt(4, mid);
			pstat.setInt(5, mid);
		}
		pstat.executeUpdate();
	}

	/**
	 * 查询某用户 已报名参加的比赛
	 * 
	 * @param uid
	 * @param op
	 *            (join or create)
	 * @return
	 * @throws SQLException
	 */
	public List<Match> selectMatchByUid(int uid, String op) throws SQLException {
		List<Match> matchList = new ArrayList<Match>();
		String sql = null;
		if (op.equals("join")) {
			sql = "SELECT tab_match.*, tab_join_match.* FROM tab_match "
					+ " LEFT JOIN tab_join_match ON tab_match.match_id = tab_join_match.mid "
					+ " WHERE tab_join_match.uid = ? ORDER BY match_start_time DESC ";
		} else if (op.equals("create")) {
			sql = "SELECT tab_match.*, tab_create_match.* FROM tab_match "
					+ " LEFT JOIN tab_create_match ON tab_match.match_id = tab_create_match.mid "
					+ " WHERE tab_create_match.uid = ? ORDER BY match_start_time DESC ";
		} else if (op.equals("manage")) {
			sql = "SELECT tab_match.*, tab_manage_match.* FROM tab_match "
					+ " LEFT JOIN tab_manage_match ON tab_match.match_id = tab_manage_match.mid "
					+ " WHERE tab_manage_match.uid = ? ORDER BY match_start_time DESC ";
		}
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		// pstat.setString(1, op);
		pstat.setInt(1, uid);

		ResultSet rs = pstat.executeQuery();
		while (rs.next()) {
			Match match = new Match();
			match.setMatchId(rs.getInt("match_id"));
			match.setMatchName(rs.getString("match_name"));
			match.setMatchType(rs.getInt("match_type"));
			match.setMatchSystem(rs.getString("match_system"));
			match.setMatchStartTime(rs.getDate("match_start_time"));
			match.setMatchEndTime(rs.getDate("match_end_time"));
			match.setMatchInfo(rs.getString("match_info"));
			match.setMatchStatus(rs.getString("match_status"));
			match.setMatchLife(rs.getString("match_life"));
			match.setMatchNumber(rs.getInt("match_number"));
			match.setMatchNumbered(rs.getInt("match_numbered"));
			match.setMatchResult(rs.getString("match_result"));
			// System.out.println(match.getMatchName());
			matchList.add(match);
		}
		return matchList;
	}

	public void changeJoinerStatus(int mid, int uid, String status) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE tab_join_match set `status` = ? where mid = ? and uid = ?";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, status);
		pstat.setInt(2, mid);
		pstat.setInt(3, uid);
		pstat.executeUpdate();
	}

	public void changeJoinerQuitReason(int mid, int uid, String quitReason) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE tab_join_match set quit_reason = ? where mid = ? and uid = ?";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, quitReason);
		pstat.setInt(2, mid);
		pstat.setInt(3, uid);
		pstat.executeUpdate();
	}

	/**
	 * @throws SQLException
	 * 
	 */
	public void refuseRecord(int mid, int uid, String refuse_reason) throws SQLException {
		String sql = "insert into tab_refuse_record(mid,refuse_reason,uid,refuse_date) values(?,?,?,?) ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		pstat.setString(2, refuse_reason);
		pstat.setInt(3, uid);
		pstat.setString(4, DateUtil.getString(new Date()));
		pstat.executeUpdate();
	}

	/**
	 * 移除比赛的参加用户
	 * 
	 * @throws SQLException
	 * 
	 */
	public void removeMember(int uid, int mid) throws SQLException {
		String sql = "delete from tab_join_match where uid = ? and mid = ?";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		pstat.setInt(2, mid);
		pstat.executeUpdate();
	}

	/**
	 * 通过参加者申请，将状态改为‘报名成功’
	 * 
	 * @param uid
	 * @param mid
	 * @param status
	 * @throws SQLException
	 */
	public void matchMemberApprove(int uid, int mid, String status) throws SQLException {
		String sql = "update tab_join_match " + "set tab_join_match.status = ? "
				+ "where tab_join_match.uid = ? and tab_join_match.mid = ? ";
		/*
		 * if (status.equals("已拒绝")) { sql =
		 * "update tab_match,tab_create_match " +
		 * "set tab_match.match_status = ?, tab_create_match.status = ?, tab_create_match.reject_reason = ?"
		 * + "where tab_match.match_id = ? and tab_create_match.mid = ? "; }
		 */
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);

		pstat.setString(1, status);
		pstat.setInt(2, uid);
		pstat.setInt(3, mid);
		/*
		 * if (status.equals("已拒绝")) { pstat.setString(3, rejectReason);
		 * pstat.setInt(4, mid); pstat.setInt(5, mid); }
		 */
		pstat.executeUpdate();
	}

	/**
	 * 依据比赛id删除比赛所有相关内容（包括create和join表中的信息）
	 * 
	 * @param mid
	 * @throws SQLException
	 */
	public void deleteJoinedMatchById(int mid) throws SQLException {
		String sql = "delete from tab_join_match where mid = ?";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		pstat.executeUpdate();
	}

	public void deleteCreatedMatchById(int mid) throws SQLException {
		String sql = "delete from tab_create_match where mid = ?";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		pstat.executeUpdate();
	}

	public void deleteMatchById(int mid) throws SQLException {
		String sql = "delete from tab_match where match_id = ?";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		pstat.executeUpdate();
	}

	/**
	 * thematch.jsp修改比赛详情
	 * @param match_result 
	 */
	public void changeMatchDetail(int match_id, Integer match_type, String match_system, Date match_start_time,
			Date match_end_time, String match_life, Integer match_number, String match_info) throws SQLException {
		String sql = "update tab_match set match_type = ?, match_system = ?, match_start_time = ?, match_end_time = ?, "
				+ " match_life = ?, match_number = ?, match_info = ? " + " where match_id = ?";

		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, match_type);
		pstat.setString(2, match_system);
		pstat.setString(3, DateUtil.getString(match_start_time));
		pstat.setString(4, DateUtil.getString(match_end_time));
		pstat.setString(5, match_life);
		pstat.setInt(6, match_number);
		pstat.setString(7, match_info);
		pstat.setInt(8, match_id);
		
		pstat.executeUpdate();

	}

	/**
	 * 再次申请
	 */
	public void reapplyMatch(int match_id, Integer match_type, String match_system, Date match_start_time,
			Date match_end_time, Integer match_number, String match_info) throws SQLException {
		String sql = "update tab_match set match_type = ?, match_system = ?, match_start_time = ?, match_end_time = ?, "
				+ " match_number = ?, match_info = ?, match_status = ?" + " where match_id = ?";

		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, match_type);
		pstat.setString(2, match_system);
		pstat.setString(3, DateUtil.getString(match_start_time));
		pstat.setString(4, DateUtil.getString(match_end_time));
		pstat.setInt(5, match_number);
		pstat.setString(6, match_info);
		pstat.setString(7, "未审批");
		pstat.setInt(8, match_id);

		pstat.executeUpdate();

	}

	public void changeTabCreateMatchStatus(int mid, int uid, String status) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE tab_create_match set status = ? where mid = ? and uid = ?";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, status);
		pstat.setInt(2, mid);
		pstat.setInt(3, uid);
		pstat.executeUpdate();
	}

	/**
	 * 添加管理员
	 * 
	 * @param mid
	 * @param uid
	 * @throws SQLException
	 */
	public void addMatchManager(int mid, int uid) throws SQLException {
		String sql = "insert into tab_manage_match(uid,manage_date,mid) values(?,?,?) ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		pstat.setString(2, DateUtil.getString(new Date()));
		pstat.setInt(3, mid);
		pstat.executeUpdate();
	}

	public User findManagerById(int mid, int uid) throws SQLException {
		String sql = "SELECT tab_user.* FROM tab_user LEFT JOIN tab_manage_match ON tab_user.user_id = tab_manage_match.uid where tab_manage_match.mid = ? and tab_manage_match.uid = ? ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		pstat.setInt(2, uid);
		ResultSet rs = pstat.executeQuery();
		if (rs.next()) {
			User user = new User();
			user.setUserId(rs.getInt("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setUserPassword(rs.getString("user_password"));
			user.setUserPhone(rs.getString("user_phone"));
			user.setUserEmail(rs.getString("user_email"));
			user.setUserIdentity(rs.getString("user_identity"));
			return user; // 返回查询到的user对象
		} else {
			return null; // 不存在用户返回null
		}
	}
	
	public void writeMail(int uid, String message) throws SQLException{
		String sql = "insert into tab_mailbox(uid,record_date,message) values(?,?,?) ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		pstat.setString(2, DateUtil.getString(new Date()));
		pstat.setString(3, message);
		pstat.executeUpdate();
	}
	
	public String findMatchNameByMatchId(int mid) throws SQLException {
		String sql = "select match_name from tab_match where match_id = ?"; // 构造sql语句
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		ResultSet rs = pstat.executeQuery();
		String res = "初始化";
		if(rs.next()){
			res = rs.getString("match_name");
		}
		return res;
	}
	
	public List<String> findMailById(int uid) throws SQLException{
		List<String> messageList = new ArrayList<String>();
		String sql = "select tab_mailbox.* from tab_mailbox where uid = ? order by id DESC ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		ResultSet rs = pstat.executeQuery();
		while(rs.next()){
			messageList.add(rs.getString("message"));
		}
		return messageList;
	}
	
	public List<Date> findMailDateById(int uid) throws SQLException{
		List<Date> messageDateList = new ArrayList<Date>();
		String sql = "select tab_mailbox.* from tab_mailbox where uid = ? order by id DESC ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		ResultSet rs = pstat.executeQuery();
		while(rs.next()){
			messageDateList.add(rs.getDate("record_date"));
		}
		return messageDateList;
	}
	
	/**
	 * 通过输入的name进行模糊查询，用来进行赛事检索
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public List<Match> searchMatchByName(String name) throws SQLException {
		List<Match> searchList = new ArrayList<Match>();

		String sql = "SELECT tab_match.*, tab_create_match.*, tab_user.* FROM tab_match "
				+ " LEFT JOIN tab_create_match ON tab_match.match_id = tab_create_match.mid "
				+ " LEFT JOIN tab_user ON tab_create_match.uid = tab_user.user_id "
				+ " WHERE tab_match.match_name LIKE ? and match_status = '已通过' ORDER BY tab_create_match.mid ";
		// + " WHERE tab_create_match.uid = 1 ORDER BY
		// tab_create_match.create_date DESC ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		String namee = "%" + name + "%";
		pstat.setString(1, namee);
		ResultSet rs = pstat.executeQuery();
		// System.out.println(rs.next());
		while (rs.next()) {
			Match match = new Match();
			// System.out.println(rs.getInt("match_id"));
			match.setMatchId(rs.getInt("match_id"));
			match.setMatchName(rs.getString("match_name"));
			match.setMatchType(rs.getInt("match_type"));
			match.setMatchSystem(rs.getString("match_system"));
			match.setMatchStartTime(rs.getDate("match_start_time"));
			match.setMatchEndTime(rs.getDate("match_end_time"));
			match.setMatchInfo(rs.getString("match_info"));
			match.setMatchStatus(rs.getString("match_status"));
			match.setMatchLife(rs.getString("match_life"));
			match.setMatchNumber(rs.getInt("match_number"));
			match.setMatchNumbered(rs.getInt("match_numbered"));
			match.setMatchResult(rs.getString("match_result"));
			searchList.add(match);
		}
		return searchList;
	}

	/**
	 * 填写比赛结果
	 * @param mid
	 * @param match_result
	 * @throws SQLException 
	 */
	public void changeMatchResult(int mid, String match_result) throws SQLException {
		String sql = "update tab_match set match_result = ? "
				+ " where match_id = ?";

		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);	
		pstat.setString(1, match_result);
		pstat.setInt(2, mid);
		
		pstat.executeUpdate();
		
	}

}
