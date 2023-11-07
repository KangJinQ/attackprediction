package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TeamDAO;
import domain.Team;
import domain.User;

public class TeamService {
	TeamDAO teamDao = new TeamDAO();

	public void addTeam(String teamName, int teamNumber, int mid, int uid) {
		try {
			teamDao.addTeam(teamName, teamNumber, mid, uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Team> findTeamByUid(int uid) {
		List<Team> teamList = new ArrayList<Team>();
		try {
			teamList = teamDao.findTeamByUid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teamList;
	}

	public void addMember(int tid, int uid) {
		try {
			teamDao.addMember(tid, uid);
			teamDao.teamNumberedPlusOne(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int findTidByUid(int uid) {
		int newestTid = -1;
		try {
			newestTid = teamDao.findTidByUid(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newestTid;
	}

	public int findTidByUidAndMid(int uid, int mid){
		int tid = 0;
		try {
			tid = teamDao.findTidByUidAndMid(uid, mid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tid;
	}
	
	public List<Integer> findAllUidByTid(int tid){
		List<Integer> uidList = new ArrayList<Integer>();
		try {
			uidList = teamDao.findAllUidByTid(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uidList;
	}

	public String findTeamNameByUidAndMid(int uid, int mid) {
		String applyinguserTeamName = new String();
		try {
			applyinguserTeamName = teamDao.findTeamNameByUidAndMid(uid, mid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applyinguserTeamName;
	}

	public void quitTeam(int uid, int tid) {

		try {
			teamDao.quitTeam(uid, tid);
			teamDao.teamNumberedMinusOne(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public String findMatchNameByMid(int mid) {
		String matchName = new String();
		try {
			matchName = teamDao.findMatchNameByMid(mid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matchName;
	}
	
	public List<User> findUserByTid(int tid) {
		List<User> userList = new ArrayList<User>();
		try {
			userList = teamDao.findUserByTid(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;	
	}
	
}
