package rent;

import java.sql.Date;
import java.sql.Timestamp;

public class Rent {

	private String memberId;
	private int seatNum;
	private int seatPrice;
	private Timestamp rent_date; //yyyy-MM-dd
	private int rent_hour;
	private int rent_price;
	private Timestamp return_time; //yyyy-mm-dd
	
	
	
	
	public String getMemberId() {
		return memberId;
	}





	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}





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





	public Timestamp getRent_date() {
		return rent_date;
	}





	public void setRent_date(Timestamp rent_date) {
		this.rent_date = rent_date;
	}





	public int getRent_hour() {
		return rent_hour;
	}





	public void setRent_hour(int rent_hour) {
		this.rent_hour = rent_hour;
	}





	public int getRent_price() {
		return rent_price;
	}





	public void setRent_price() {
		this.rent_price = this.seatPrice * this.rent_hour;
	}

	
	






	public Timestamp getReturn_time() {
		return return_time;
	}





	public void setReturn_time(Timestamp return_time) {
		this.return_time = return_time;
	}





	@Override
	public String toString() {
	
		
		return "회원 id : " + String.format("%-10s", memberId) + " | 좌석 번호 : " + String.format("%-5s", seatNum) + " | 좌석 가격 : "+ String.format("%-7s", seatPrice) +" | 대여 날짜 : " + String.format("%-25s", rent_date) + " | 대여 시간 : " +String.format("%-3s", rent_hour)+"시간"
				+" | 총 대여 비용 : " + String.format("%-7s", rent_price) + "| 반납 시간 : "+ String.format("%-25s", return_time) ;
	}
	
	
	
	
}
