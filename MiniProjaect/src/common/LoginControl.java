package common;

import java.util.Scanner;

import member.Member;
import member.MemberDAO;

public class LoginControl {

	private Scanner sc = new Scanner(System.in);
	private static Member loginInfo = null;
	public static Member getLoginInfo() {
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
		
		Member member = new Member();
		
		//아이디 설정
		System.out.println("아이디 > ");
		String id = sc.nextLine();
		Member member = 
		
		if()
		
		
		member.setMemberId(sc.nextLine());
		
		//중복 확인
		// 비교 -> 디비에 있눈 데이터와 비교(디비에서 검색->쿼리문 필요) => 조회가 된다면 중복  
		if(member != ) {
			
		}
		
		//비번 설정
		System.out.println("비밀번호 > ");
		member.setMemberPwd(sc.nextLine());
			//변수에 값 넣어주기 
		
		//비번 확인 = 첫번째 입력과 두번째 입력이 같은 지 확인
		//String.equals 이용
		
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
