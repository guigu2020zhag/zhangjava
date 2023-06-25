package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaolmpl extends BaseDao implements AdminDao{
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	//µÇÂ¼²éÑ¯
	@Override
	public Boolean login(String adminName, String adminPwd){
		boolean flag=false;
		try{
			conn=getConnection();
			String sql="select * from adminstrator where adminName=? and adminPwd=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, adminName);
			ps.setString(2, adminPwd);	
			rs=ps.executeQuery();
			if(rs.next()){
				flag=true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	

}
