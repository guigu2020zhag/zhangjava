package service;

import java.util.Scanner;

import dao.UserDaoImpl;
import model.user;

public class UserService {
	private static UserDaoImpl userDao=new UserDaoImpl();
	//登录验证
		public static int Login() {
			Scanner in=new Scanner(System.in);
			System.out.println("请输入银行卡号：");
			int custNumber=in.nextInt();
			System.out.println("请输入密码：");
			String custPassword =in.next();
			return userDao.login(custNumber, custPassword);
		}
}
