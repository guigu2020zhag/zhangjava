package dao;

import java.util.List;

import model.*;

public interface AdminFunctionInterface {
	public boolean deleteData(user us);//ɾ���˿�
	public boolean addUser(user us);//��ӹ˿�
	public Double selectMoney(user us);//�洢���
	public List<user> rankingList(user us);//���а�
	public void quitSystem();//�˳�ϵͳ
}
