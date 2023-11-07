package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Match;
import domain.Team;
import domain.User;
import util.ConnUtil;

public class TeamDAO {

	public void addTeam(String teamName, int teamNumber, int mid, int uid) throws SQLException {
		String sql = "insert into tab_team(team_name,team_number,team_numbered,mid,uid) "
				+ " values(?,?,?,?,?)";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, teamName);
		pstat.setInt(2, teamNumber);
		pstat.setInt(3, 1);
		pstat.setInt(4, mid);
		pstat.setInt(5, uid);
		pstat.executeUpdate();
	}

	public List<Team> findTeamByUid(int uid) throws SQLException {
		List<Team> teamList = new ArrayList<Team>();
		String sql = "SELECT * FROM tab_join_team "
				+ "LEFT JOIN tab_team ON tab_team.team_id = tab_join_team.tid "
				+ "where tab_join_team.uid = ? ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);

		ResultSet rs = pstat.executeQuery();
		while (rs.next()) {
			Team team = new Team();
			team.setTeamId(rs.getInt("team_id"));
			team.setTeamName(rs.getString("team_name"));
			team.setTeamNumber(rs.getInt("team_number"));
			team.setTeamNumbered(rs.getInt("team_numbered"));
			team.setMid(rs.getInt("mid"));
			team.setUid(rs.getInt("uid"));
			teamList.add(team);
		}
		return teamList;
	}
	
	public String findMatchNameByMid(int mid) throws SQLException {	
		String matchName = new String();
		String sql = "SELECT * FROM tab_team "
				+ "LEFT JOIN tab_match ON tab_team.mid = tab_match.match_id "
				+ "where tab_team.mid = ? ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, mid);
		ResultSet rs = pstat.executeQuery();
		rs.next();
		matchName = rs.getString("match_name");
		//System.out.println(teamName);
		
		return matchName;
	}
	

	public void addMember(int tid, int uid) throws SQLException {
		String sql = "insert into tab_join_team(tid, uid) "
				+ " values(?,?)";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, tid);
		pstat.setInt(2, uid);
		pstat.executeUpdate();

	}
	
	/**
	 * 队伍人员 ++
	 * 
	 * @param mid
	 * @throws SQLException
	 */
	public void teamNumberedPlusOne(int tid) throws SQLException {
		String sql = "UPDATE tab_team set team_numbered = team_numbered + 1 where team_id = ?";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, tid);
		pstat.executeUpdate();
	}

	/**
	 * 队伍人员 --
	 * 
	 * @param mid
	 * @throws SQLException
	 */
	public void teamNumberedMinusOne(int tid) throws SQLException {
		String sql = "UPDATE tab_team set team_numbered = team_numbered - 1 where team_id = ?";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, tid);
		pstat.executeUpdate();
	}

	public int findTidByUid(int uid) throws SQLException {
		String sql = "SELECT * FROM tab_team "
				+ " WHERE tab_team.uid = ? ORDER BY tab_team.team_id DESC";
		// + " WHERE tab_create_match.uid = 1 ORDER BY
		// tab_create_match.create_date DESC ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		ResultSet rs = pstat.executeQuery();
		// System.out.println(rs.next());
		rs.next();
		int newestTid = rs.getInt("team_id");
		return newestTid;
	}

	public int findTidByUidAndMid(int uid, int mid) throws SQLException {
		String sql = "SELECT * FROM tab_team "
				+ " WHERE tab_team.uid = ? and mid = ? ";
		// + " WHERE tab_create_match.uid = 1 ORDER BY
		// tab_create_match.create_date DESC ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		pstat.setInt(2, mid);
		ResultSet rs = pstat.executeQuery();
		// System.out.println(rs.next());
		rs.next();
		int tid = rs.getInt("team_id");
		return tid;
	}
	
	/**
	 * 通过队伍id查找该队伍中的所有成员
	 */
	public List<Integer> findAllUidByTid(int tid) throws SQLException {
		List<Integer> uidList = new ArrayList<Integer>();
		String sql = "SELECT * FROM tab_join_team where tid = ? ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, tid);

		ResultSet rs = pstat.executeQuery();
		while (rs.next()) {
			Integer uid = rs.getInt("uid");
			uidList.add(uid);
		}
		return uidList;
	}

	public String findTeamNameByUidAndMid(int uid, int mid) throws SQLException {	
		String applyinguserTeamName = new String();
		String sql = "SELECT * FROM tab_team "
				+ "LEFT JOIN tab_join_team ON tab_team.team_id = tab_join_team.tid "
				+ "where tab_join_team.uid = ? and tab_team.mid = ? ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		pstat.setInt(2, mid);

		ResultSet rs = pstat.executeQuery();
		rs.next();
		applyinguserTeamName = rs.getString("team_name");
		//System.out.println(teamName);
		
		return applyinguserTeamName;
	}

	public void quitTeam(int uid, int tid) throws SQLException {
		String sql = "delete from tab_join_team where uid = ? and tid = ? ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, uid);
		pstat.setInt(2, tid);
		pstat.executeUpdate();
		
	}
	
	public List<User> findUserByTid(int tid) throws SQLException {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM tab_join_team "
				+ "LEFT JOIN tab_user ON tab_join_team.uid = tab_user.user_id "
				+ "where tab_join_team.tid = ? ";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setInt(1, tid);

		ResultSet rs = pstat.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setUserId(rs.getInt("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setUserPassword(rs.getString("user_password"));
			user.setUserPhone(rs.getString("user_phone"));
			user.setUserEmail(rs.getString("user_email"));
			user.setUserIdentity(rs.getString("user_identity"));
			userList.add(user);
		}
		return userList;
	}
}
