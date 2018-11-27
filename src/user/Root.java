package user;

/**
 * 超级管理员
 * @author Administrator
 *
 */
public class Root extends User {
	/*
	 * Root r = new Root();
	 * Root r2 = new Root();
	 * 超级管理员只能有一个
	 * 使用单例模式设计Root
	 * 
	 * 1.构造函数私有化
	 * 2.声明一个静态的本类对象的变量
	 * 3.对外提供静态的公共的方法返回本类对象
	 * */
	private Root() {
		super("root", "root","");
	}
	
	//懒汉式
	/*private static Root instance = null;
	
	public static User getInstance() {
		if (instance == null) {
			instance = new Root();
		}
		return instance;
	}*/
	
	//饿汉式
	private static Root instance = new Root();
	
	/**
	 * 单例模式获取超级管理员的对象
	 * @return
	 */
	public static User getInstance() {
		return instance;
	}
}
