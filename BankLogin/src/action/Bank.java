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
	//������
	public void menu() throws Exception{
		Scanner input = new Scanner(System.in);
		while(true){
			System.out.println("========== ���й���ϵͳ�˵� =============");
			System.out.printf("| %-12s | %-8s |\n", "1. ����Ա", "2. �˿�");
			System.out.println("========================================");
			System.out.print("��ѡ��<1-2>��");
			switch(input.nextInt()) {
			case 1:
				if(AdminService.Login()){
					System.out.println("����Ա��¼�ɹ���");
					AdminUI();
					break;
				}else{
					System.out.println("����Ա��¼ʧ�ܣ�");
				}
				break;
			case 2:
				us.setCustNumber(UserService.Login());
				if( us.getCustNumber()!= 0){
					System.out.println("�˿͵�¼�ɹ���");
					UserUI();
					
					break;
				}else{
					System.out.println("�˿͵�¼ʧ�ܣ�");
				}
				break;
			}
		}
	}
	//����Ա����
	public void AdminUI() throws Exception {
		Scanner input = new Scanner(System.in);
		AdminFunctionAll afa =new AdminFunctionAll();
		user us = new user();
		while(true) {
			System.out.println("========== ����Ա�˵� ============");
			System.out.printf("%s %-15s %-12s\n", "|", "1. ��ӹ˿�", "2. ɾ���˿�");
			System.out.printf("%s %-13s %-12s\n", "|", "3. ͳ�ƴ洢���", "4. �������а�");
			System.out.printf("%s %-13s %-12s\n", "|", "5. �˳�", "");
			System.out.println("=================================");
			System.out.print("��ѡ��<1-5>��");
			switch(input.nextInt()) {
			case 1:
				if(afa.addUser(us)) {
					System.out.println("-------------------------------");
					System.out.println("�����ɹ���");
					List<user> AList=afa.selectAdd(us);
					us=AList.get(AList.size()-1);
					System.out.println("����˺�Ϊ��"+us.getCustNumber());
					System.out.println("�������Ϊ��"+us.getCustPwd());
					System.out.println("��������Ϊ��"+us.getCustDate());
				}
				break;
			case 2:
				System.out.println("����Ҫɾ�����˺ţ�");
				us.setCustNumber(input.nextInt());
				if(afa.deleteData(us)) {
					System.out.println("ɾ���ɹ���");
				}else {
					System.out.println("����ʧ�ܣ�û�д��˺ţ�");
				}
				break;
			case 3:
				System.out.println("�����ܴ�"+afa.selectMoney(us));
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
	
	//�û�����
	public void UserUI() {
		Scanner input = new Scanner(System.in);
		UserFunction uf = new UserFunction();
		while(true) {
			System.out.println("============ �˿Ͳ˵� ============");
			System.out.printf("%s %-12s %-12s\n", "|", "1. ���", "2. ȡ��");
			System.out.printf("%s %-12s %-12s\n", "|", "3. ת��", "4. �޸�����");
			System.out.printf("%s %-10s %-12s\n", "|", "5. �˳�", "");
			System.out.println("==================================");
			System.out.print("��ѡ��<1-5>��");
			
			switch(input.nextInt()) {
			case 1:
				System.out.println("�����");
				us.setCustMoney(input.nextInt());
				if(uf.deposit(us)) {
					List<user> AList=uf.balanceEnquiry(us);
					us = AList.get(0);
					System.out.println("���ɹ�����\n���"+us.getCustMoney());
				}else {
					System.out.println("����ʧ��");
				}
				break;
			case 2:
				System.out.println("����ȡ���");
				us.setCustMoney(input.nextInt());
				if(uf.withdrawal(us)) {
					List<user> AList=uf.balanceEnquiry(us);
					us = AList.get(0);
					System.out.println("ȡ��ɹ�����\n���"+us.getCustMoney());
				}else {
					System.out.println("����ʧ��");
				}
				break;
			case 3:
				System.out.println("����ת���˺ţ�");
				us.setTemporary(input.nextInt());
				System.out.println("����ת�˽�");
				us.setCustMoney(input.nextInt());
				if(uf.transferAccounts(us)) {
					List<user> AList=uf.balanceEnquiry(us);
					us = AList.get(0);
					System.out.println("ת�˳ɹ�����ǰ��"+us.getCustMoney());
				}
				break;
			case 4:
				List<user> AList=uf.balanceEnquiry(us);
				us = AList.get(AList.size()-1);
				System.out.println("������ľ����룺");
				String PWD = input.next();
				System.out.println(us.getCustPwd());
				if(us.getCustPwd().equals(PWD)) {
					System.out.println("������������룺");
					String newPWD = input.next();
					System.out.println("ȷ����������룺");
					String newPWD2 = input.next();
					if(newPWD.equals(newPWD2)) {
						us.setCustPwd(newPWD2);
						if(uf.changePassword(us)) {
							System.out.println("�޸ĳɹ���");
						}else {
							System.out.println("�޸�ʧ�ܣ�");
						}
					}else {
						System.out.println("����ʧ�ܣ�");
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

