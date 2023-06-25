package dao;
/*
 * 使用jdbc实现数据库连接和关闭功能
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	//连接数据的URL路径
	private static final String URL="jdbc:mysql://localhost:3306/Bank?useSSL=false";
	//jdbc:mysql://hostname:port/database?useSSL=false
	//连接数据库登录账号
	private static final String USER="root";
	//连接数据库登录密码
	private static final String PASSWORD="123456";
	private static Connection conn=null;
	//获取conn连接
	static{
		try {  
			Class.forName("com.mysql.jdbc.Driver");          
			conn=DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public  static  Connection  getConnection() throws SQLException{
		return  DriverManager.getConnection(URL,USER,PASSWORD);
	}
	//关闭数据库连接
		public static void close(ResultSet rs) throws Exception{
			try{
				if(rs!=null){
					//判断结果集是否为null
					rs.close();
				}
			}catch(Exception e){
				e.printStackTrace();
				throw new Exception();
			}
		}
		public static void close(PreparedStatement pstat) throws Exception{
			try{
				if(pstat!=null){
					//判断Statement对象是否为null
					pstat.close();
				}
			}catch(Exception e){
				e.printStackTrace();
				throw new Exception();
			}
		}
		public static void close(Connection conn) throws Exception{
			try{
				if(conn!=null){
					//判断Connection对象是否为null
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
				throw new Exception();
			}
		}
}
