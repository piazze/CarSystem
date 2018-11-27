package user;

/**
 * 
 * @author Administrator
 *
 */
public class NormalUser extends User{

	public NormalUser() {
		super();
	}

	public NormalUser(String userName, String userPwd,String phone) {
		super(userName, userPwd,phone);
	}

	private String id;//身份证

	private int aginest = 3; //密码尝试剩余次数
	
	public int getAginest() {
		return aginest;
	}

	public void setAginest(int aginest) {
		this.aginest = aginest;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
