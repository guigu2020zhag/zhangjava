package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import adminFunction.UserFunction;
import model.user;

public class UserDaoImpl extends BaseDao implements UserDao{
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	@Override
	public int login(int custNumber, String custPwd) {
		int flag=0;
		try{
			conn=getConnection();
			String sql="select * from customer where custNumber=? and custPwd=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, custNumber);
			ps.setString(2, custPwd);	
			rs=ps.executeQuery();
			
			if(rs.next()){
				flag=rs.getInt("custNumber");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

}
