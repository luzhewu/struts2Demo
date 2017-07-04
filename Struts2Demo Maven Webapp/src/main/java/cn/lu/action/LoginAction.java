package cn.lu.action;

/**
 * 登陆action
 * @author lzw
 * 2017-7-4
 * 每个人都有自己的梦想。努力拼搏吧!不要让自己后悔!
 */
public class LoginAction implements Action {

	@Override
	public String execute() {
		System.out.println("LoginAction....");
		return "success";
	}

}
