package dao;

import java.util.List;

import model.user;

public interface UserFunctionInterface {
	public boolean deposit(user us);//存款
	public boolean withdrawal(user us);//取款
	public boolean transferAccounts(user us);//转账
	public boolean changePassword(user us);//修改密码
	public void quitSystem();//退出
	
}
