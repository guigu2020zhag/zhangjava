package service;

import java.util.Scanner;

import dao.UserDaoImpl;
import model.user;

public class UserService {
	private static UserDaoImpl userDao=new UserDaoImpl();
	//��¼��֤
		public static int Login() {
			Scanner in=new Scanner(System.in);
			System.out.println("���������п��ţ�");
			int custNumber=in.nextInt();
			System.out.println("���������룺");
			String custPassword =in.next();
			return userDao.login(custNumber, custPassword);
		}
}
