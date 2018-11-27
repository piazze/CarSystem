package user;

/**
 * 普通管理员
 * @author Administrator
 *
 */
public class Admin extends User {

	public Admin() {
		super();
	}

	public Admin(String userName, String userPwd,String phone) {
		super(userName, userPwd,phone);
	}
	
}
