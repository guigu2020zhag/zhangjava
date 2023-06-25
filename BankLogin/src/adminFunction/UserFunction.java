package adminFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import action.Bank;
import dao.BaseDao;
import dao.UserFunctionInterface;
import model.user;

public class UserFunction implements UserFunctionInterface{

	private int yhkID;
	public int getYhkID() {
		return yhkID;
	}
	public void setYhkID(int yhkID) {
		this.yhkID = yhkID;
	}
	@Override
	public boolean deposit(user us) {
		Connection conn = null;
		PreparedStatement pStat = null;
		ResultSet rs = null;
		try{
			this.setYhkID(us.getCustNumber());
			String sql="update customer set custMoney=custMoney+?"+" where custNumber=?";
			conn=BaseDao.getConnection(); //获取数据库连接
			pStat=conn.prepareStatement(sql); //预处理sql语句
			pStat.setDouble(1,us.getCustMoney());
			pStat.setInt(2,us.getCustNumber());
			return pStat.executeUpdate()>0?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				BaseDao.close(rs);
				BaseDao.close(pStat);
				BaseDao.close(conn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return false;
	}
	public List<user> balanceEnquiry(user us) {
		Connection conn = null;
		PreparedStatement pStat = null;
		ResultSet rs = null;
		try{
			ArrayList<user> AList=new ArrayList<user>();
			String sql=" select * from customer where custNumber=?";
			conn= BaseDao.getConnection();
			pStat=conn.prepareStatement(sql);
			pStat.setInt(1,us.getCustNumber());
			rs=pStat.executeQuery();
			while(rs.next()) {
				int a = rs.getInt("custMoney");
				String b = rs.getString("custPwd");
				us.setCustMoney(a);
				us.setCustPwd(b);
				AList.add(us);
			}		
			return AList;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				BaseDao.close(rs);
				BaseDao.close(pStat);
				BaseDao.close(conn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	@Override
	public boolean withdrawal(user us) {
		Connection conn = null;
		PreparedStatement pStat = null;
		ResultSet rs = null;
		
		try{
			this.setYhkID(us.getCustNumber());
			String sql="update customer set custMoney=custMoney-?"+" where custNumber=?";
			conn=BaseDao.getConnection(); //获取数据库连接
			pStat=conn.prepareStatement(sql); //预处理sql语句
			pStat.setDouble(1,us.getCustMoney());
			pStat.setInt(2,us.getCustNumber());
			return pStat.executeUpdate()>0?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				BaseDao.close(rs);
				BaseDao.close(pStat);
				BaseDao.close(conn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean transferAccounts(user us) {
		int a = us.getTemporary();
		us.setTemporary(us.getCustNumber());
		us.setCustNumber(a);
		boolean bl1 = deposit(us); 
		int b = us.getTemporary();
		us.setTemporary(us.getCustNumber());
		us.setCustNumber(b);
		boolean bl2 = withdrawal(us);
		if(bl1&&bl2) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean changePassword(user us) {
		Connection conn = null;
		PreparedStatement pStat = null;
		ResultSet rs = null;
		try{
			this.setYhkID(us.getCustNumber());
			//1 定义sql语句
			String sql="update customer set custPwd=?"+" where custNumber=?";
			conn=BaseDao.getConnection(); //获取数据库连接
			pStat=conn.prepareStatement(sql); //预处理sql语句
			pStat.setString(1,us.getCustPwd());
			pStat.setInt(2,us.getCustNumber());
			return pStat.executeUpdate()>0?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				BaseDao.close(rs);
				BaseDao.close(pStat);
				BaseDao.close(conn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void quitSystem() {
		// TODO Auto-generated method stub
		Bank b = new Bank();
		try {
			b.menu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
