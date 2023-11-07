package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

//import com.edu.ustb.test.DBCPTest;
/**
 * 操作数据库连接对象的“工具类”
 * @author KangJQ
 *
 */
public class ConnUtil {
	private static DataSource ds;
	private static ThreadLocal<Connection>local = new ThreadLocal<>();//这是一个每个用户都有的一个保存Connection对象的容器，避免千军万马过独木桥
	static{//静态代码块——只运行一次
			try {
				//加载资源文件信息的对象
				Properties prop = new Properties();
				//从mysql5.properties文件中加载信息
				prop.load(ConnUtil.class.getResourceAsStream("/mysql5.properties"));
				//使用加载的信息创建数据源对象
				ds = BasicDataSourceFactory.createDataSource(prop);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 获得一个数据库连接对象的方法
	 * @return 连接对象
	 * @throws SQLException 
	 */
	public static Connection getConn() throws SQLException{
		Connection conn = local.get();			//从容其中取出连接对象
		if(conn == null || conn.isClosed()){	//如果链接对象不存在，或连接对象已经关闭
			conn = ds.getConnection();			//从数据源中取出一个连接创建连接对象
			local.set(conn);					//将连接放入自己的local容器中
		}
		return conn;
	}

	/**
	 * 关闭数据库连接对象的方法
	 */
	public static void closeConn(){
		Connection conn = local.get();	//得到自己容器中的连接
		try {
			if(conn != null || !conn.isClosed()){	//有连接没关闭
				conn.close();	//释放连接
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{	//必须执行的部分，无论什么情况都执行
			conn = null;	//连接清空
			local.set(null);//自己的容器清空
		}
		
	}
	
}
