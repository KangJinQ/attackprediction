package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

//import com.edu.ustb.test.DBCPTest;
/**
 * �������ݿ����Ӷ���ġ������ࡱ
 * @author KangJQ
 *
 */
public class ConnUtil {
	private static DataSource ds;
	private static ThreadLocal<Connection>local = new ThreadLocal<>();//����һ��ÿ���û����е�һ������Connection���������������ǧ���������ľ��
	static{//��̬����顪��ֻ����һ��
			try {
				//������Դ�ļ���Ϣ�Ķ���
				Properties prop = new Properties();
				//��mysql5.properties�ļ��м�����Ϣ
				prop.load(ConnUtil.class.getResourceAsStream("/mysql5.properties"));
				//ʹ�ü��ص���Ϣ��������Դ����
				ds = BasicDataSourceFactory.createDataSource(prop);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * ���һ�����ݿ����Ӷ���ķ���
	 * @return ���Ӷ���
	 * @throws SQLException 
	 */
	public static Connection getConn() throws SQLException{
		Connection conn = local.get();			//��������ȡ�����Ӷ���
		if(conn == null || conn.isClosed()){	//������Ӷ��󲻴��ڣ������Ӷ����Ѿ��ر�
			conn = ds.getConnection();			//������Դ��ȡ��һ�����Ӵ������Ӷ���
			local.set(conn);					//�����ӷ����Լ���local������
		}
		return conn;
	}

	/**
	 * �ر����ݿ����Ӷ���ķ���
	 */
	public static void closeConn(){
		Connection conn = local.get();	//�õ��Լ������е�����
		try {
			if(conn != null || !conn.isClosed()){	//������û�ر�
				conn.close();	//�ͷ�����
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{	//����ִ�еĲ��֣�����ʲô�����ִ��
			conn = null;	//�������
			local.set(null);//�Լ����������
		}
		
	}
	
}
