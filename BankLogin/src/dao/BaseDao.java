package dao;
/*
 * ʹ��jdbcʵ�����ݿ����Ӻ͹رչ���
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	//�������ݵ�URL·��
	private static final String URL="jdbc:mysql://localhost:3306/Bank?useSSL=false";
	//jdbc:mysql://hostname:port/database?useSSL=false
	//�������ݿ��¼�˺�
	private static final String USER="root";
	//�������ݿ��¼����
	private static final String PASSWORD="123456";
	private static Connection conn=null;
	//��ȡconn����
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
	//�ر����ݿ�����
		public static void close(ResultSet rs) throws Exception{
			try{
				if(rs!=null){
					//�жϽ�����Ƿ�Ϊnull
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
					//�ж�Statement�����Ƿ�Ϊnull
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
					//�ж�Connection�����Ƿ�Ϊnull
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
				throw new Exception();
			}
		}
}
