package user;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		UserManager m = new UserManager();
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println("请选择：1.登录  2.退出");
			int choose = input.nextInt();
			if (choose == 1) {
				System.out.println("请输入用户名：");
				String userName = input.next();
				System.out.println("请输入密码：");
				String userPwd = input.next();
				DataWrap login = m.login(userName, userPwd);
				if (login.getCode() == 1) {
					System.out.println("登录成功");
				} else if (login.getCode() == 2) {
					System.out.println("密码错误");
				} else if (login.getCode() == 3) {
					System.out.println("用户不存在");
				} else if (login.getCode() == 4) {
					System.out.println("账户被锁定");
				}
			} else if (choose == 2) {
				System.out.println("谢谢使用");
				break;
			}
		}
	}

}
