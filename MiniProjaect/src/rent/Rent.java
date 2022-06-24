package rent;

import java.sql.Date;

public class Rent {

	private String memberId;
	private int seatNum;
	private int seatPrice;
	private Date rent_date;
	private int rent_hour;
	private int rent_price;
	
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
	public Date getRent_date() {
		return rent_date;
	}
	public void setRent_date(Date rent_date) {
		this.rent_date = rent_date;
	}
	public int getRent_hour() {
		return rent_hour;
	}
	public void setRent_hour(int rent_hour) {
		this.rent_hour = rent_hour;
	}
	
	
	public int getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(int seatPrice) {
		this.seatPrice = seatPrice;
	}
	public int getRent_price() {
		return rent_price;
	}
	public void setRent_price(int rent_price) {
		this.rent_price = rent_price;
	}
	
	
	@Override
	public String toString() {
		return "회원 id : " + memberId + ", 좌석 번호 : " + seatNum + ", 좌석 가격 : "+ seatPrice +", 대여 날짜 : " + rent_date + ", 대여 시간 : "
				+ rent_hour +", 총 대여 비용 : " + rent_price ;
	}
	
	
	
	
}
