package common;

import java.sql.SQLException;
import java.util.Scanner;

import member.Member;
import member.MemberDAO;

public class LoginControl {
	private MemberDAO mDAO = MemberDAO.getInstance();
	private Scanner sc = new Scanner(System.in);
	private static Member loginInfo = null;
	public static Member getLoginInfo() {
		return loginInfo;
	}
	
	public LoginControl() {
		
	
//		|￣￣￣￣￣￣￣￣￣￣￣￣￣￣|
//    	Your text here
//    |＿＿＿＿＿＿＿＿＿＿＿＿＿＿|
//          \ (•◡•) /
//           \    /
	
		System.out.println();
		System.out.println("|￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
		System.out.println("   YEDAM 스터디 카페에 오신 것을 환영합니다 ^0^    "  );
		System.out.println("   ✩ ✩ ✩ ✩ ✩ 메뉴를 입력해주세요 ✩ ✩ ✩ ✩ ✩     ");
		System.out.println("|＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|");
		System.out.println("               \\ (•◡•) /");
		System.out.println("                \\    /");
		
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
		System.out.println();
		System.out.println("     ╭─────────────────────────╮");
		System.out.println("      1.회원가입   2.로그인   3.종료");
		System.out.println("     ╰─────────────────────────╯");
		System.out.println();
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
		
		
		//아이디 설정
		Member member = null;
		while(true) {
		
		System.out.println("아이디 > ");
		
		String id = sc.nextLine();
		member = mDAO.selectOneById(id);
		
		
		
		if(member != null) {
			System.out.println("사용 중인 아이디입니다.");
		} else {
			member = new Member();
			member.setMemberId(id);
			break;
		}
		
		
		}
		
		//비번 설정
		
		while(true) {
		System.out.println("비밀번호 > ");
		member.setMemberPwd(sc.nextLine());
			//변수에 값 넣어주기 
		
		System.out.println("비밀번호 재입력 > ");
		String pwdCheck = sc.nextLine();
		if(!pwdCheck.equals(member.getMemberPwd())) {
			System.out.println("일치하지 않습니다. 비밀번호를 다시 입력하세요.");
		} else {
			break;
		}
		}
		
		
		//이름
		System.out.println("이름 > ");
		member.setMemberName(sc.nextLine());
		
		//전화번호 설정
		System.out.println("전화번호(010-0000-0000 형식) > ");
		member.setMemberPhone(sc.nextLine());
		
		Member joinInfo = MemberDAO.getInstance().join(member);
		if(joinInfo == null)
			return;
		mDAO.join(joinInfo);
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
