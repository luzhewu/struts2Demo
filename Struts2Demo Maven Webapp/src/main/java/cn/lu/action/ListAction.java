package cn.lu.action;

public class ListAction implements Action {

	@Override
	public String execute() {
		System.out.println("ListAction.....");
		return "success";
	}

}
