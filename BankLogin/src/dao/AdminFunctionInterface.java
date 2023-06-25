package dao;

import java.util.List;

import model.*;

public interface AdminFunctionInterface {
	public boolean deleteData(user us);//删除顾客
	public boolean addUser(user us);//添加顾客
	public Double selectMoney(user us);//存储金额
	public List<user> rankingList(user us);//排行榜
	public void quitSystem();//退出系统
}
