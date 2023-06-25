package adminFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import action.Bank;
import dao.AdminFunctionInterface;
import dao.BaseDao;
import model.user;

public class AdminFunctionAll implements AdminFunctionInterface{
	Connection conn=null;
	PreparedStatement pStat=null;
	@Override
	public boolean deleteData(user us) {
		try{
			//1 定义sql语句
			String sql="delete from customer" +" where custNumber=?";
			conn=BaseDao.getConnection(); //获取数据库连接
			//2 获取PreparedStatement对象
			pStat=conn.prepareStatement(sql); //预处理sql语句
			//3 将占位符？使用预处理的方式替换成员工的账号，1表示一个？占位符
			pStat.setInt(1,us.getCustNumber());
			//4 执行sql语句
			return pStat.executeUpdate()>0?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pStat!=null){
					pStat.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean addUser(user us) {
		Connection conn=null;
		PreparedStatement pStat=null;
		Scanner input = new Scanner(System.in);
		//		System.out.print("编号：");
		//		us.setCustNumber(input.nextInt());
		System.out.print("用户名：");
		us.setCustName(input.next());
		System.out.print("密码：");
		us.setCustPwd(input.next());
		System.out.print("身份证：");
		us.setCustldCard(input.next());
		System.out.print("余额：");
		us.setCustMoney(input.nextInt());
		//		System.out.print("日期：");
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateString = currentDate.format(formatter);
		us.setCustDate(dateString);
		System.out.println("正在开户中······");
		try{
			String sql="insert into customer(custNumber,custName,custPwd,custidCard,custMoney,custDate)"+"values(?,?,?,?,?,?)";
			conn=BaseDao.getConnection(); //获取数据库连接
			pStat=conn.prepareStatement(sql);
			pStat.setInt(1,us.getCustNumber());
			pStat.setString(2, us.getCustName());
			pStat.setString(3, us.getCustPwd());
			pStat.setString(4,us.getCustldCard());
			pStat.setDouble(5,us.getCustMoney());
			pStat.setString(6,us.getCustDate());			
			return pStat.executeUpdate()>0?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pStat!=null){
					pStat.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return false;
	}
	public List<user> selectAdd(user us){
		Connection conn = null;
		PreparedStatement pStat = null;
		ResultSet rs = null;
		try{
			String sql=" select * from customer";
			conn= BaseDao.getConnection();
			pStat=conn.prepareStatement(sql);
			rs=pStat.executeQuery();
			//返回 ResultSet 实例
			ArrayList<user> AList=new ArrayList<user>();
			//判断结果集中是否还有数据
			while(rs.next()){
				//返回 ResultSet 实例
//				ArrayList<user> AList=new ArrayList<user>();
				//判断结果集中是否还有数据
				while(rs.next()){
					int custNumber = rs.getInt("custNumber");
					String custNamr = rs.getString("custName");
					String custPwd = rs.getString("custPwd");
					String custidCard = rs.getString("custidCard");
					int custMoney = rs.getInt("custMoney");
					String custDate = rs.getString("custDate");
					user a = new user();
					a.setCustNumber(custNumber);
					a.setCustName(custNamr);
					a.setCustPwd(custPwd);
					a.setCustldCard(custidCard);
					a.setCustMoney(custMoney);
					a.setCustDate(custDate);
					//将获取到的列值添加到 List 集合中
					AList.add(a);
				}
				return AList;
			}
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
	public Double selectMoney(user us) {
		Connection conn = null;
		PreparedStatement pStat = null;
		ResultSet rs = null;
		try{
			String sql="select custMoney from customer";
			conn= BaseDao.getConnection();
			pStat=conn.prepareStatement(sql);
			rs=pStat.executeQuery();
			ArrayList<user> AList=new ArrayList<user>();
			Double sum = 0.0;
			while(rs.next()){
				int money = rs.getInt("custMoney");
				user a = new user();
				a.setCustMoney(money);
				sum += a.getCustMoney();
			}
			return sum;
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
	public List<user> rankingList(user us) {
		Connection conn = null;
		PreparedStatement pStat = null;
		ResultSet rs = null;
		try{
			String sql=" select * from customer order by custMoney desc";
			conn= BaseDao.getConnection();
			pStat=conn.prepareStatement(sql);
			rs=pStat.executeQuery();
			//返回 ResultSet 实例
			ArrayList<user> AList=new ArrayList<user>();
			//判断结果集中是否还有数据
			while(rs.next()){
				int custNumber = rs.getInt("custNumber");
				String custNamr = rs.getString("custName");
				String custPwd = rs.getString("custPwd");
				String custidCard = rs.getString("custidCard");
				int custMoney = rs.getInt("custMoney");
				String custDate = rs.getString("custDate");
				user a = new user();
				a.setCustNumber(custNumber);
				a.setCustName(custNamr);
				a.setCustPwd(custPwd);
				a.setCustldCard(custidCard);
				a.setCustMoney(custMoney);
				a.setCustDate(custDate);
				//将获取到的列值添加到 List 集合中
				AList.add(a);
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
	public void quitSystem() {
		Bank b = new Bank();
		try {
			b.menu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
