package rent;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import common.LoginControl;
import common.Management;
import member.Member;
import seat.Seat;
import seat.SeatDAO;

public class SeatRentManagement extends Management {

	public SeatRentManagement() {

		boolean role = selectRole();

		while (true) {

			menuPrint(role);

			int menuNo = menuSelect();

			if (menuNo == 1 && role) {
				seatManage();
			} else if (menuNo == 2 && role) {
				rentManage();
			} else if (menuNo == 3) {
				showAllSeat();
			} else if (menuNo == 4) {
				rentSeat();
			} else if (menuNo == 5) {
				returnSeat();
			} else if (menuNo == 6) {
				showRentByMember();
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
			menu += " 1.좌석관리 " + "2.대여 내역 조회 ";
		}

		menu += " 3.전체 좌석 및 대여 상태 조회 " + "4.좌석 대여 " + "5.좌석 반납 " + "6.나의 대여 내역 조회 " + "9.뒤로가기";
		System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println(menu);
		System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────");
	}

	private void back() {
		//System.out.println("         ߍ__ߍ          ");
		//System.out.println("        (｡･◡･｡)         ");
		System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
		System.out.println("    메인으로 돌아갑니다.   ");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
	}

	private void seatManage() {
		
		int choice = inputSelect1();
		
		List<Seat> list = new ArrayList<>();
		//seat조회
		//데이터를 가져올 수 있는 구문
		
		
		if(choice == 1) {
			inputAll();
		} else if(choice == 2) {
			//좌석번호 입력
			int seatNum = inputNum();
			Seat seat = seatDAO.selectBySeatNum(seatNum);
			//좌석이 존재하는지 검사
			if(seat == null) {
				//System.out.println("        ߍ___ߍ          ");
				//System.out.println("       (｡`ㅅ´｡)         ");
				System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
				System.out.println("   등록되지않은 좌석입니다. ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
				return;
			} else{
			//db에 저장
			updateSeatPrice(seat);
			}
		} else if(choice == 3) {
			deleteSeat();
		} else {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println(" 올바른 형식으로 입력해주세요.");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
		}
		for(Seat seat : list) {
			System.out.println(seat);
		}
	}
	
	
	private int inputSelect1() {
		System.out.println("─────────────────────────────────────────────────────");
		System.out.println(" 선택 -> 1.좌석등록 | 2.좌석가격 수정 | 3.좌석삭제 | 9. 뒤로가기" );
		System.out.println("─────────────────────────────────────────────────────");
		int selected = Integer.parseInt(sc.nextLine());
		
		if(selected == 1) {
			System.out.println("*************************좌석등록");
		} else if(selected == 2) {
			System.out.println("*************************좌석가격 수정");
		} else if(selected ==3) {
			System.out.println("*************************좌석삭제");
		} else if(selected == 9){
			back();
		} else {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("     없는 메뉴입니다.");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
		}
		return selected;
	}
	

	// 신규좌석 등록
//	private void insertSeat() {
//
//		Seat seat = inputAll();
//		if(seat != null) {
//			System.out.println("이미 등록되어있는 좌석입니다.");
//			return;
//		}
//		
//		seatDAO.insert(seat);
//	}

	private void inputAll() {
		Seat seat;
		System.out.println("──────────────────────────────────────────────");
		System.out.print(" 등록할 좌석 번호 -> ");
		int seatNum = Integer.parseInt(sc.nextLine());
		
		seat = seatDAO.selectBySeatNum(seatNum);
		
		if(seat !=null) {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("  이미 등록되어있는 좌석입니다.");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			return;
		}
		
		seat = new Seat();
		seat.setSeatNum(seatNum);
		
		System.out.print(" 좌석 가격 ➤ ");
		int seatPrice = Integer.parseInt(sc.nextLine());
		
		
		if(seatPrice <= 0) {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("  입력할 수 없는 가격입니다.");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			return;
		}
		
		seat.setSeatPrice(seatPrice);
		
		seatDAO.insert(seat);

	}

	// 좌석가격 수정
	private void  updateSeatPrice(Seat seat) {
		System.out.println("──────────────────────────────────────────────");
		System.out.println(" 기존 좌석 가격 -> "+seat.getSeatPrice());
		System.out.println("──────────────────────────────────────────────");
		System.out.print(" 수정할 가격 (수정하지 않을 경우 -1 입력) -> ");

		int price = Integer.parseInt(sc.nextLine());

		if (price > -1) {
			seat.setSeatPrice(price);
			seatDAO.updateSeatNum(seat);
		}
		
	}

	//좌석 삭제
	private void deleteSeat() {
		
		int seatNum = inputNum();
		
		Seat seat = seatDAO.selectBySeatNum(seatNum);
		
		if(seat == null) {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("    등록된 좌석이 없습니다.  ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			return;
		} else {
			seatDAO.delete(seat.getSeatNum());
		}
		
	}
	
	
	
	// 좌석 대여
	private void rentSeat() {
		int seatNum = inputNum();

		//
		Seat seat = seatDAO.selectBySeatNum(seatNum);

		if (seat == null) {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("   등록되지 않은 좌석입니다.");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			return;
		} 
		
		//대여중인건지 확인
		
		int rental = seat.getSeatRental();
		
		if(rental == 1) {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("    대여 중인 좌석입니다.");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			return;
		} else {
			seat.setSeatRental(1);
			
			
		}
		
		
		
		////////////////////////////////////////
		// 대여시간, 가격

		Rent rent = inputHour();
		rent.setSeatNum(seat.getSeatNum());
		rent.setMemberId(LoginControl.getLoginInfo().getMemberId());
		/////////////////////////////////////////////
		
		rent.setSeatPrice(seat.getSeatPrice());
		rent.setRent_price();
		System.out.println("──────────────────────────────────────────────");
		System.out.println(" 총 대여 가격 -> "+rent.getRent_price());
		System.out.println();
		rentDAO.insert(rent);
		
		seatDAO.updateSeatRental(seat);

	}

	private Rent inputHour() {
		Rent rent = new Rent();

		System.out.println("──────────────────────────────────────────────");
		System.out.print(" 대여 희망 시간(숫자로 입력) -> ");
		rent.setRent_hour(Integer.parseInt(sc.nextLine()));
		return rent;
	}

	private int inputNum() {
		System.out.println("──────────────────────────────────────────────");
		System.out.print(" 좌석 번호 -> ");
		return Integer.parseInt(sc.nextLine());
	}

/*	
	//좌석 증축
	private void addSeat() {
		Seat seat = new Seat();
		SeatDAO seatDAO = new SeatDAO();
		
		int seatNum = inputNum();

		//
		seat = seatDAO.selectBySeatNum(seatNum);
		seatDAO.insert(seat);
		seat.setSeatAdd(1);
		
		if(seat.getSeatAdd() == 1) {
			seatDAO.seatArrangement();
			seatDAO.addPrint(seatNum);
			
		}
		
	}
*/	
	// 반납
	private void returnSeat() {

		int seatNum = inputNum();

		Seat seat = seatDAO.selectBySeatNum(seatNum);
		
		if (seat == null) {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("   등록되지않은 좌석입니다. ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			return;
		} else {
			seat.setSeatRental(0);
			seatDAO.updateSeatRental(seat);
			
			Rent rent = new Rent();
			rent.setSeatNum(seatNum);
			//
			rent.setMemberId(LoginControl.getLoginInfo().getMemberId());
			//rent = rentDAO.
			rentDAO.updateReturnTime(rent);
			
		}
		
//		List<Rent> list = rentDAO.selectOneSeatNum(seatNum);
//		for(Rent rent : list) {
//			if(rent.getReturn_time() != null) {
//				System.out.println("이미 반납한 좌석입니다.");
//				
//				return;
//			} 
//		
//	}
		
	}

	private int inputSelect2() {
		System.out.println("╭────────────────────────────────────────────────────────────────────────────╮");
		System.out.println(" 선택 -> 1.전체 좌석 대여 내역 조회 | 2.날짜별 대여 내역 조회 | 3.좌석별 대여 내역 조회 | 9.뒤로가기");
		System.out.println("╰────────────────────────────────────────────────────────────────────────────╯");
	
		int selected = Integer.parseInt(sc.nextLine());
		
		if(selected == 1) {
			System.out.println("*************************전체 좌석 대여 내역 조회");
		} else if(selected == 2) {
			System.out.println("*************************날짜별 대여 내역 조회");
		} else if(selected ==3) {
			System.out.println("*************************좌석별 대여 내역 조회");
		} else if(selected == 9){
			back();
			
		} else {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("      없는 메뉴입니다. ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
		}
		return selected;
		
	}
	
	
	private void rentManage() {
		int choice = inputSelect2();
		
		List<Rent> list = new ArrayList<>();
		
		if(choice == 1) {
			allRentStatus();
		} else if(choice == 2) {
			dateRentStatus();
		} else if(choice == 3) {
			seatNumRentStatus();
		} else {
			//System.out.println("        ߍ___ߍ          ");
			//System.out.println("       (｡`ㅅ´｡)         ");
			System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
			System.out.println("  올바른 형식으로 입력해주세요. ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
		}
		for(Rent rent : list) {
			System.out.println(rent);
		}
	}
	
	
	// 전체좌석 대여내역 조회
	private void allRentStatus() {
		// bookDAO의 selectRental메소드(렌탈가능상태의 책들보는것)에서 어떤 값들을 리스트로 받아옴
		List<Rent> list = rentDAO.selectAllSeatNum();

		// 그 리스트 수만큼 book정보에 담겨서 반복
		// 리스트에 데이터가 나열되어있을건데, 그 데이터들이 하나씩 book임시변수에 담김
		// 첫번째값이 임시변수에 담겨서 for안의 실행블록 실행 -> 그다음 두번째....반복
		for (Rent rent : list) {
			// 그 값을 출력
			System.out.println(rent);
		}
	}

	// 날짜별 대여내역 조회
	private void dateRentStatus() {
		System.out.println("──────────────────────────────────────────────");
		System.out.print(" 날짜를 입력하세요. (YYYY-MM-DD) -> ");
		String date = sc.nextLine();
		
		List<Rent> list = rentDAO.selectAllRentDate(date);

		for (Rent rent : list) {
			System.out.println(rent);
		}

	}

	// 좌석별 대여내역 조회
	private void seatNumRentStatus() {
		int seatNum = inputNum();
		List<Rent> list = rentDAO.selectOneSeatNum(inputNum());

		for (Rent rent : list) {
			System.out.println(rent);
		}

	}

	// 회원별 대여내역 조회
	private void showRentByMember() {
		//String memberId = inputId();
		List<Rent> list = rentDAO.selectOneMemberId(inputId());

				
		for (Rent rent : list) {
			System.out.println(rent);
		}

	}

	private String inputId() {
		System.out.println("──────────────────────────────────────────────");
		System.out.print(" 회원 아이디 -> ");
		return sc.nextLine();
	}

	
	
	// 대여가능 좌석 조회?
	private void rentAvailable() {

		// bookDAO의 selectRental메소드(렌탈가능상태의 책들보는것)에서 어떤 값들을 리스트로 받아옴
		List<Seat> list = seatDAO.selectBySeatRental(0);

		// 그 리스트 수만큼 book정보에 담겨서 반복
		// 리스트에 데이터가 나열되어있을건데, 그 데이터들이 하나씩 book임시변수에 담김
		// 첫번째값이 임시변수에 담겨서 for안의 실행블록 실행 -> 그다음 두번째....반복
		for (Seat seat : list) {
			// 그 값을 출력
			System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("현재 대여 가능 좌석 >> " + seat);
		}
	}

	// 좌석 전체 조회와 대여가능 좌석 조회
	private void showAllSeat() {

		seatDAO.selectAll();

		rentAvailable();

	}

}
