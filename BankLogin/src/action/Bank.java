package action;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import adminFunction.*;
import model.user;
import service.*;

public class Bank {
	public static void main(String[] args) throws Exception{
		Bank init=new Bank();
		init.menu();

	}
	user us = new user();
	//主界面
	public void menu() throws Exception{
		Scanner input = new Scanner(System.in);
		while(true){
			System.out.println("========== 银行管理系统菜单 =============");
			System.out.printf("| %-12s | %-8s |\n", "1. 管理员", "2. 顾客");
			System.out.println("========================================");
			System.out.print("请选择<1-2>：");
			switch(input.nextInt()) {
			case 1:
				if(AdminService.Login()){
					System.out.println("管理员登录成功！");
					AdminUI();
					break;
				}else{
					System.out.println("管理员登录失败！");
				}
				break;
			case 2:
				us.setCustNumber(UserService.Login());
				if( us.getCustNumber()!= 0){
					System.out.println("顾客登录成功！");
					UserUI();
					
					break;
				}else{
					System.out.println("顾客登录失败！");
				}
				break;
			}
		}
	}
	//管理员界面
	public void AdminUI() throws Exception {
		Scanner input = new Scanner(System.in);
		AdminFunctionAll afa =new AdminFunctionAll();
		user us = new user();
		while(true) {
			System.out.println("========== 管理员菜单 ============");
			System.out.printf("%s %-15s %-12s\n", "|", "1. 添加顾客", "2. 删除顾客");
			System.out.printf("%s %-13s %-12s\n", "|", "3. 统计存储金额", "4. 富豪排行榜");
			System.out.printf("%s %-13s %-12s\n", "|", "5. 退出", "");
			System.out.println("=================================");
			System.out.print("请选择<1-5>：");
			switch(input.nextInt()) {
			case 1:
				if(afa.addUser(us)) {
					System.out.println("-------------------------------");
					System.out.println("开户成功！");
					List<user> AList=afa.selectAdd(us);
					us=AList.get(AList.size()-1);
					System.out.println("你的账号为："+us.getCustNumber());
					System.out.println("你的密码为："+us.getCustPwd());
					System.out.println("开户日期为："+us.getCustDate());
				}
				break;
			case 2:
				System.out.println("输入要删除的账号：");
				us.setCustNumber(input.nextInt());
				if(afa.deleteData(us)) {
					System.out.println("删除成功！");
				}else {
					System.out.println("操作失败！没有此账号！");
				}
				break;
			case 3:
				System.out.println("银行总存款："+afa.selectMoney(us));
				break;
			case 4:
				List<user> AList=afa.rankingList(us);;
				for(int j=0; j<AList.size(); j++){
					us=AList.get(j);
					System.out.println(us.getCustNumber()+"\t"+us.getCustName()+"\t"+us.getCustPwd()+"\t"+us.getCustldCard()+"\t"+us.getCustMoney()+"\t"+us.getCustDate());
				}
				break;
			case 5:
				afa.quitSystem();
				break;
			}
		}

	}
	
	//用户界面
	public void UserUI() {
		Scanner input = new Scanner(System.in);
		UserFunction uf = new UserFunction();
		while(true) {
			System.out.println("============ 顾客菜单 ============");
			System.out.printf("%s %-12s %-12s\n", "|", "1. 存款", "2. 取款");
			System.out.printf("%s %-12s %-12s\n", "|", "3. 转账", "4. 修改密码");
			System.out.printf("%s %-10s %-12s\n", "|", "5. 退出", "");
			System.out.println("==================================");
			System.out.print("请选择<1-5>：");
			
			switch(input.nextInt()) {
			case 1:
				System.out.println("输入存款：");
				us.setCustMoney(input.nextInt());
				if(uf.deposit(us)) {
					List<user> AList=uf.balanceEnquiry(us);
					us = AList.get(0);
					System.out.println("存款成功！！\n余额"+us.getCustMoney());
				}else {
					System.out.println("操作失败");
				}
				break;
			case 2:
				System.out.println("输入取款金额：");
				us.setCustMoney(input.nextInt());
				if(uf.withdrawal(us)) {
					List<user> AList=uf.balanceEnquiry(us);
					us = AList.get(0);
					System.out.println("取款成功！！\n余额"+us.getCustMoney());
				}else {
					System.out.println("操作失败");
				}
				break;
			case 3:
				System.out.println("输入转账账号：");
				us.setTemporary(input.nextInt());
				System.out.println("输入转账金额：");
				us.setCustMoney(input.nextInt());
				if(uf.transferAccounts(us)) {
					List<user> AList=uf.balanceEnquiry(us);
					us = AList.get(0);
					System.out.println("转账成功！当前余额："+us.getCustMoney());
				}
				break;
			case 4:
				List<user> AList=uf.balanceEnquiry(us);
				us = AList.get(AList.size()-1);
				System.out.println("输入你的旧密码：");
				String PWD = input.next();
				System.out.println(us.getCustPwd());
				if(us.getCustPwd().equals(PWD)) {
					System.out.println("输入你的新密码：");
					String newPWD = input.next();
					System.out.println("确认你的新密码：");
					String newPWD2 = input.next();
					if(newPWD.equals(newPWD2)) {
						us.setCustPwd(newPWD2);
						if(uf.changePassword(us)) {
							System.out.println("修改成功！");
						}else {
							System.out.println("修改失败！");
						}
					}else {
						System.out.println("操作失败！");
					}
				}
				
				break;
			case 5:
				uf.quitSystem();
				break;
			}
		}
	}

}

