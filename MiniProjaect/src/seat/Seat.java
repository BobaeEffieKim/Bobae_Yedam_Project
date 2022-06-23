package seat;


public class Seat {

	private int seatNum;
	private int seatPrice;
	private int seatRental;
	
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public int getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(int seatPrice) {
		this.seatPrice = seatPrice;
	}
	public int getSeatRental() {
		return seatRental;
	}
	public void setSeatRental(int seatRental) {
		this.seatRental = seatRental;
	}
	
	@Override
	public String toString() {
		String str = "";
		
		if(seatRental == 0) {
			str = "대여 가능";
		} else {
			str = "대여중";
		}
		return "좌석 번호 : " + seatNum + ", 좌석 가격 : " + seatPrice
				+ ", 대여 가능 여부 : " + str;
	}
}
