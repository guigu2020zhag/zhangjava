package service;

import java.util.Scanner;

import dao.AdminDaolmpl;
import model.user;

public class AdminService {
	private static AdminDaolmpl adminDao=new AdminDaolmpl();
	//登录验证
	public static Boolean Login() {
		Scanner in=new Scanner(System.in);
		System.out.println("请输入管理员名称：");
		String name=in.next();
		System.out.println("请输入管理员密码：");
		String password =in.next();
	
		return adminDao.login(name, password);
	}
}
