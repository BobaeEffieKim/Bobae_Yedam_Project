package member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.Management;
import rent.Rent;

public class MemberManagement extends Management {
	Scanner sc = new Scanner(System.in);

	public MemberManagement() {

		boolean role = selectRole();

		while (true) {

			menuPrint(role);

			int menuNo = menuSelect();

			if (menuNo == 1 && role) {

				selectOne();

			} else if (menuNo == 2 && role) {
				selectAll();

			} else if (menuNo == 3 ) {
				deleteMember();

			} else if (menuNo == 4) {
				updateMember();

			} else if (menuNo == 9) {
				back();
				break;
			} else {
				showInputError();
			}

		}

	}

	protected void menuPrint(boolean role) {

		String menu = "";

		if (role) {

			menu += " 1.회원정보 조회  " + "2.전체회원 조회  ";

		}

		menu += " 3.회원계정 삭제  "  +  "4.회원정보 수정  " + "9.뒤로가기";

		System.out.println();
		System.out.println("───────────────────────────────────────────────────────────────");
		System.out.println(menu);
		System.out.println("───────────────────────────────────────────────────────────────");
		System.out.println();
	}

	private void back() {
		//System.out.println("         ߍ__ߍ          ");
		//System.out.println("        (｡･◡･｡)         ");
		System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
		System.out.println("     메인으로 돌아갑니다.    ");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
	}

	private void deleteMember() {

		String memberId = inputId();

		Member member = memberDAO.selectOneById(memberId);

		List<Rent> list = rentDAO.selectOneMemberId(memberId);
			for(Rent rent : list) {
				if(rent.getReturn_time() == null) {
					//System.out.println("                ߍ___ߍ                  ");
					//System.out.println("               (｡`ㅅ´｡)                 ");
					System.out.println("┏━━━━━━━━━━━━━━━O━━━O━━━━━━━━━━━━━━━━━┓");
					System.out.println(" 대여 중인 좌석이 있습니다. 먼저 좌석을 반납해주세요.");
					System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
					
					return;
				} 
			
		}
		
		if (member == null) {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("   등록된 정보가 없습니다.    ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			return;
		}

	//	if()
		
		List<Rent> isSelected = rentDAO.selectOneMemberId(memberId);

		if (isSelected != null) {

			int deleteCheck = 0;
			//System.out.println("         ߍ__ߍ          ");
			//System.out.println("        (ㅠ-ㅠ)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println(" 정말 계정을 삭제하시겠습니까?  ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			
			System.out.println("──────────────────────────────────────────────");
			System.out.println(" 1. 예, 삭제하겠습니다.  | 2. 아니오, 메인으로 돌아가겠습니다.");
			System.out.println("──────────────────────────────────────────────");

			deleteCheck = Integer.parseInt(sc.nextLine());

			if (deleteCheck == 1) {
				memberDAO.delete(member.getMemberId());

			} else if (deleteCheck == 2) {
				back();
			} else {
				//System.out.println("        ߍ___ߍ          ");
				//System.out.println("       (｡`ㅅ´｡)         ");
				System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
				System.out.println("   잘못 입력된 형식입니다.   ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			}
		}
	}

	private String inputId() {
		System.out.println("──────────────────────────────────────────────");
		System.out.print(" 회원 아이디 -> ");
		return sc.nextLine();
	}

	private void updateMember() {

		int updateMenu = 0;
		System.out.println("──────────────────────────────────────────────");
		System.out.print(" 수정 항목 번호 선택 -> ");
		System.out.println();
		System.out.println("─────────────────────────────");
		System.out.println(" 1.phone number  |  2.password");
		System.out.println("─────────────────────────────");
		updateMenu = Integer.parseInt(sc.nextLine());

		if (updateMenu == 1) {
			updatePhone();
		} else if (updateMenu == 2) {
			updatePwd();
		} else {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("   잘못 입력된 형식입니다.   ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
		}
	}

	private void updatePhone() {

		String memberId = inputId();

		Member member = memberDAO.selectOneById(memberId);

		if (member == null) {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("    등록된 정보가 없습니다.   ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			return;
		}

		member = inputUpdatePhone(member);

		memberDAO.updateMemberPhone(member);

	}

	private Member inputUpdatePhone(Member member) {
		System.out.println("──────────────────────────────────────────────");
		System.out.print(" 기존 전화번호 -> "+ member.getMemberPhone());
		System.out.println();
		System.out.println("──────────────────────────────────────────────");
		System.out.println(" 수정할 전화번호(수정하지 않을 경우 0 입력) -> ");

		// members 테이블의 데이터타입을 문자로 바꿔주고 진행하기!!!!
		// 현재 관리자 계정도 삭제되어있는 상태!
		String phone = sc.nextLine();

		if (!phone.equals("0")) {
			member.setMemberPhone(phone);
		}

		return member;
	}

	private void updatePwd() {

		String memberId = inputId();

		Member member = memberDAO.selectOneById(memberId);

		if (member == null) {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("    등록된 정보가 없습니다.   ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			return;
		}
		
		memberDAO.updateMemberPwd(inputUpdatePwd(member));

	}

	private Member inputUpdatePwd(Member member) {
		// Member member = null;

		while (true) {
//			System.out.println("기존 비밀번호 입력 > ");
//
//			member.setMemberPwd(sc.nextLine());

			System.out.println("──────────────────────────────────────────────");
			System.out.println(" 비밀번호 재입력 -> ");
			String pwdCheck = sc.nextLine();
			//System.out.println(member.getMemberPwd());
			if (!pwdCheck.equals(member.getMemberPwd())) {
				
				//System.out.println("        ߍ___ߍ          ");
				//System.out.println("       (｡`ㅅ´｡)         ");
				System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
				System.out.println("  비밀번호가 일치하지 않습니다. \n  비밀번호를 다시 입력해주세요.   ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			} else {
				break;
			}
		}

		System.out.println("──────────────────────────────────────────────");
		System.out.println(" 수정할 비밀번호(수정하지 않을 경우 0 입력) -> ");
		// members 테이블의 데이터타입을 문자로 바꿔주고 진행하기!!!!
		// 현재 관리자 계정도 삭제되어있는 상태!
		String pwd = sc.nextLine();

		if (!pwd.equals("0")) {
			member.setMemberPwd(pwd);
			return member;
		}
		return member;
	}

	private void selectOne() {

		String memberId = inputId();

		Member member = memberDAO.selectOneById(memberId);

		if (member == null) {

			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("    등록된 회원이 아닙니다.   ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");

			return;
		}
		System.out.println(member);
	}

	private void selectAll() {
		List<Member> list = memberDAO.selectAll();

		for (Member member : list) {
			System.out.println(member);
		}

	}

}
