package dao;

import java.util.List;

import model.user;

public interface UserFunctionInterface {
	public boolean deposit(user us);//���
	public boolean withdrawal(user us);//ȡ��
	public boolean transferAccounts(user us);//ת��
	public boolean changePassword(user us);//�޸�����
	public void quitSystem();//�˳�
	
}
