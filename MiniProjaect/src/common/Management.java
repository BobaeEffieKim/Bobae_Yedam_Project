package common;

import java.util.Scanner;

import member.MemberDAO;
import member.MemberManagement;
import rent.RentDAO;
import rent.SeatRentManagement;
import seat.SeatDAO;

public class Management {

	
	protected Scanner sc = new Scanner(System.in);
	protected SeatDAO seatDAO = SeatDAO.getInstance();
	protected MemberDAO memberDAO = MemberDAO.getInstance();
	protected RentDAO rentDAO = RentDAO.getInstance();
	
	
	public void run() {
		
		while(true) {
			
			menuPrint();
			
			int menuNo = menuSelect();
			
			if(menuNo == 1) {
				new MemberManagement();
			} else if(menuNo == 2) {
				new SeatRentManagement();
			} else if(menuNo == 9) {
				exit();
				break;
			} else {
				showInputError();
			}
		}
	}
	

	protected void menuPrint() {
		System.out.println();
		System.out.println("╭──────────────────────────────────────╮");
		System.out.println("  1.회원 관리  2.좌석 및 좌석대여 관리  9.종료");
		System.out.println("╰──────────────────────────────────────╯");
		System.out.println();
	}
	
	
	protected int menuSelect() {
		
		int menuNo = 0;
		
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch(NumberFormatException e) {
			System.out.println("        ߍ___ߍ          ");
			System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println(" 숫자 형식으로 입력해주세요. ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
		}
		return menuNo;
	}
	
	protected void exit() {
		System.out.println("        ߍ___ߍ          ");
		System.out.println("       (｡･◡･｡)         ");
		System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
		System.out.println("    프로그램을 종료합니다.  ");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
	}
	
	protected void showInputError() {
		System.out.println("        ߍ___ߍ          ");
		System.out.println("       (｡`ㅅ´｡)         ");
		System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
		System.out.println(" 메뉴를 참고하여 입력해주세요. ");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
	}
	
	protected boolean selectRole() {
		
		int memberRole = LoginControl.getLoginInfo().getMemberRole();
		
		if(memberRole == 0) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	
	
	
}
