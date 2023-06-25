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
			//1 ����sql���
			String sql="delete from customer" +" where custNumber=?";
			conn=BaseDao.getConnection(); //��ȡ���ݿ�����
			//2 ��ȡPreparedStatement����
			pStat=conn.prepareStatement(sql); //Ԥ����sql���
			//3 ��ռλ����ʹ��Ԥ����ķ�ʽ�滻��Ա�����˺ţ�1��ʾһ����ռλ��
			pStat.setInt(1,us.getCustNumber());
			//4 ִ��sql���
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
		//		System.out.print("��ţ�");
		//		us.setCustNumber(input.nextInt());
		System.out.print("�û�����");
		us.setCustName(input.next());
		System.out.print("���룺");
		us.setCustPwd(input.next());
		System.out.print("���֤��");
		us.setCustldCard(input.next());
		System.out.print("��");
		us.setCustMoney(input.nextInt());
		//		System.out.print("���ڣ�");
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateString = currentDate.format(formatter);
		us.setCustDate(dateString);
		System.out.println("���ڿ����С�����������");
		try{
			String sql="insert into customer(custNumber,custName,custPwd,custidCard,custMoney,custDate)"+"values(?,?,?,?,?,?)";
			conn=BaseDao.getConnection(); //��ȡ���ݿ�����
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
			//���� ResultSet ʵ��
			ArrayList<user> AList=new ArrayList<user>();
			//�жϽ�������Ƿ�������
			while(rs.next()){
				//���� ResultSet ʵ��
//				ArrayList<user> AList=new ArrayList<user>();
				//�жϽ�������Ƿ�������
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
					//����ȡ������ֵ��ӵ� List ������
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
			//���� ResultSet ʵ��
			ArrayList<user> AList=new ArrayList<user>();
			//�жϽ�������Ƿ�������
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
				//����ȡ������ֵ��ӵ� List ������
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
