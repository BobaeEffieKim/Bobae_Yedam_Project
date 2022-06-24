package common;

import java.util.Scanner;

import member.Member;
import member.MemberDAO;

public class LoginControl {

	private Scanner sc = new Scanner(System.in);
	private static Member loginInfo = null;
	static Member getLoginInfo() {
		return loginInfo;
	}
	
	public LoginControl() {
		
		while(true) {
			menuPrint();
			
			int menuNo = menuSelect();
			
			if(menuNo == 1) {
				join();
				
			} else if(menuNo == 2) {
				login();
				
			} else if(menuNo == 3) {
				exit();
				break;
			} else {
				showInputError();
			}
			
		}
		
	}
	
	
	private void menuPrint() {
		
		System.out.println("======================");
		System.out.println("1.회원가입  2.로그인  3.종료");
		System.out.println("======================");
	}
	
	private int menuSelect() {
		
		int menuNo = 0;
		
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch(NumberFormatException e) {
			System.out.println("숫자 형식으로 입력해주세요.");
		}
		return menuNo;
	}
	
	private void exit() {
		System.out.println("프로그램을 종료합니다.");
	}
	
	private void showInputError() {
		System.out.println("메뉴를 확인해주시기 바랍니다.");
	}
	
	
	private void join() {
		
		
		MemberDAO mDAO = new MemberDAO();
		
		mDAO.join(loginInfo);
	}
	
	
	private void login() {
		
		Member inputInfo = inputMember();
		
		loginInfo = MemberDAO.getInstance().logIn(inputInfo);
		
		if(loginInfo == null)
			return;
		
		new Management().run();
	}
	
	
	private Member inputMember() {
		
		Member info = new Member();
		
		System.out.println("아이디 > ");
		info.setMemberId(sc.nextLine());
		
		System.out.println("비밀번호 > ");
		info.setMemberPwd(sc.nextLine());
		
		return info;
	}
	
	
	
	
	
	
}
