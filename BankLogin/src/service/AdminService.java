package service;

import java.util.Scanner;

import dao.AdminDaolmpl;
import model.user;

public class AdminService {
	private static AdminDaolmpl adminDao=new AdminDaolmpl();
	//��¼��֤
	public static Boolean Login() {
		Scanner in=new Scanner(System.in);
		System.out.println("���������Ա���ƣ�");
		String name=in.next();
		System.out.println("���������Ա���룺");
		String password =in.next();
	
		return adminDao.login(name, password);
	}
}
