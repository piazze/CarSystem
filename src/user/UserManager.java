package user;

import java.util.Arrays;

/**
 * 用户管理类
 * @author Administrator
 *
 */
public class UserManager {
	
	//剩余尝试机会
	private final int AGINEST = 0x0003;
	
	/**
	 * 登录成功
	 */
	private final int SUCCESS = 1;
	
	/**
	 * 用户名或密码错误
	 */
	private final int FAULT = 2;
	
	/**
	 * 用户不存在
	 */
	private final int NONE_USER = 3;
	
	/**
	 * 账户被锁定
	 */
	private final int LOCKED_USER = 4;
	
	public UserManager() {
		super();
		
		User root = Root.getInstance();//超级管理员
		this.add(root);
		
		User n = new NormalUser();
		n.setUserName("nick");
		n.setUserPwd("123");
		this.add(n);
	}

	//保存用户的对象数组
	private User[] data = new User[10];
	
	//数组中实际元素的个数
	private int size;
	
	/**
	 * 添加元素
	 * @param user
	 * @return
	 */
	public boolean add(User user) {
		//判断数组是否扩容
		if (this.size + 1 > data.length) {
			//新数组的长度
			int oldValue = data.length;
			int newValue = oldValue + oldValue >> 1;
			
			this.data = Arrays.copyOf(data,newValue);//创建并复制数组
		}
		
		//将user存放到数组中
		this.data[this.size++] = user;
		return true;
	}
	
	/**
	 * 根据用户名密码登录
	 * @param userName 用户名
	 * @param userPwd  密码
	 * @return 在对象数组中查询出来的用户
	 */
	public DataWrap login(String userName,String userPwd) {
		for (int i = 0; i < this.size; i++) {
			if (this.data[i].getUserName().equals(userName)) {//用户名密码正确
				if (this.data[i].getUserPwd().equals(userPwd)) {
					 //重置登录剩余次数
					 if (this.data[i] instanceof NormalUser) {
						NormalUser n = (NormalUser) this.data[i];
						
						if (n.getAginest() == 0) { //账户被锁定
							DataWrap wrap = new DataWrap(LOCKED_USER,null);
							return wrap;
						}
						n.setAginest(AGINEST);
					}
					 
					 DataWrap wrap = new DataWrap(SUCCESS,this.data[i]);
					 return wrap;
				}else {
					int code = FAULT; //密码错误
					//如果登录的账户是普通用户,输入密码错误次数累加1
					 if (this.data[i] instanceof NormalUser) { //判断登录的用户是否为普通用户
						 boolean result = this.counter(this.data[i]);
						 if (!result) {
							code = LOCKED_USER; //账户被锁定
						 }
					 }
					
					DataWrap wrap = new DataWrap(code,null);
					return wrap;
				}
			}
		}
		DataWrap wrap = new DataWrap(NONE_USER,null);//不存在的用户
		return wrap;
	}
	
	/**
	 * 通过用户名统计密码错误次数
	 * @param userName
	 * @return false说明该账户被锁定
	 */
	private boolean counter(User user) {
		for (int i = 0; i < this.size; i++) {
			if (user == this.data[i]) {
				NormalUser n = (NormalUser) this.data[i];
				int left = n.getAginest();
				if (left <= 1) {
					n.setAginest(0);
					return false; //账户被锁定
				} else {
					n.setAginest(left - 1);
				}
				this.data[i] = n;
			}
		}
		return true;
	}
	
}
class DataWrap {
	private int code; //状态码，1：登录成功   2.密码错误   3.用户不存在  4.账户被锁定
	private User user;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public DataWrap(int code, User user) {
		super();
		this.code = code;
		this.user = user;
	}
	
}
