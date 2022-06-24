package member;

import java.util.List;
import java.util.Scanner;

import common.Management;
import rent.Rent;

public class MemberManagement extends Management{
	Scanner sc = new Scanner(System.in);
	public MemberManagement() {
		
		boolean role = selectRole();
		
		
		while(true) {
			
			menuPrint(role);
			
			
			int menuNo = menuSelect();
			
			if(menuNo == 1 && role) {
				
				selectOne();
				
			} else if(menuNo == 2 && role) {
				selectAll();
				
			} else if(menuNo == 3 && role) {
				deleteMember();
				
			} else if(menuNo == 4) {
				updateMember();
				
			} else if(menuNo == 9) {
				back();
				break;
			} else {
				showInputError();
			}
			
		}
		
	}
	
		protected void menuPrint(boolean role) {
			
			String menu = "";
			
			if(role) {
				
				menu += "1.회원정보 조회  "+"2.전체회원 조회  "+"3.회원 삭제  ";
				
			}
			
			menu += "4.회원정보 수정  "+"9.뒤로가기";
			
				System.out.println("====================================================================");
				System.out.println(menu);
				System.out.println("====================================================================");
			
		}
		
		private void back() {
			System.out.println("메인으로 돌아갑니다.");
		}
		
		
		private void deleteMember(){
			
			String memberId = inputId();
			
			Member member = memberDAO.selectOneById(memberId);
			
			if(member == null) {
				System.out.println("등록된 정보가 없습니다.");
				return;
			}
			
			List<Rent> isSelected = rentDAO.selectOneMemberId(memberId);
			
			if(isSelected != null) {
				
				int deleteCheck = 0;
				System.out.println("정말 삭제하시겠습니까?");
				System.out.println("1. 예, 삭제하겠습니다.  | 2. 아니오, 메인으로 돌아가겠습니다.");
				
				
				deleteCheck =Integer.parseInt(sc.nextLine());
				
				if(deleteCheck == 1) {
					memberDAO.delete(member.getMemberId());
					
				} else if(deleteCheck == 2) {
					back();
				} else {
					System.out.println("잘못 입력된 형식입니다.");
				}
			}
		}
		
		private String inputId() {
			System.out.println("회원 id > ");
			return sc.nextLine();
		}
		
		
		
		private void updateMember() {
			
			int updateMenu =0;
			System.out.println("수정 항목 선택 > ");
			System.out.println("1.phone number  |  2.password");
			updateMenu = Integer.parseInt(sc.nextLine());
			
			if(updateMenu == 1) {
				updatePhone();
			} else if(updateMenu == 2) {
				updatePwd();
			} else {
				System.out.println("잘못 입력된 형식입니다.");
			}
		}
		
		
		private void updatePhone() {
			
			String memberId = inputId();
			
			Member member = memberDAO.selectOneById(memberId);
			
			if(member == null) {
				System.out.println("등록된 정보가 없습니다.");
				return;
			}
			
			member = inputUpdatePhone(member);
			
			memberDAO.updateMemberPhone(member);
			
		}
		
		private Member inputUpdatePhone(Member member) {
			
			System.out.println("기존 전화번호 > " + member.getMemberPhone());
			System.out.println("수정할 전화번호(수정하지 않을 경우 0 입력) > ");
			
			
			//members 테이블의 데이터타입을 문자로 바꿔주고 진행하기!!!!
			//현재 관리자 계정도 삭제되어있는 상태!
			String phone = sc.nextLine();
			
			
			if(!phone.equals("0")) {
				member.setMemberPhone(phone);
			}
			
			return member;
		}
		
		
		
		
		
		
		
		
		
		
	
}
