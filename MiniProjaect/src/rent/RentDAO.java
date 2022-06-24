package rent;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class RentDAO extends DAO{

	private static RentDAO rentDAO = null;
	private RentDAO() {}
	public static RentDAO getInstance() {
		if(rentDAO == null) {
			rentDAO = new RentDAO();
		}
		return rentDAO;
	}
	
	
	//CRUD 
	
	//등록
	public void insert(Rent rent) {
		
		try {
			connect();
			
			String sql ="INSERT INTO rent (member_id, seat_num, seat_price, rent_date, rent_hour, rent_price) VALUES (?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rent.getMemberId());
			pstmt.setInt(2, rent.getSeatNum());
			pstmt.setInt(3, rent.getSeatPrice());
			pstmt.setDate(4, rent.getRent_date());
			pstmt.setInt(5, rent.getRent_hour());
			pstmt.setInt(6, rent.getRent_price());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("정상적으로 등록되었습니다.");
			} else {
				System.out.println("정상적으로 등록되지 않았습니다.");
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally{
			disconnect();
		}
		
	}
		
		
		//전체 조회 -> 회원번호로 대여내역 조회
			//누적하고싶으면? ->while문 돌려서 리스트에 담기
	
		public List<Rent> selectOneMemberId(String memberId) {
			List<Rent> list = new ArrayList<>();
			
			try {
				connect();
				
				String sql ="SELECT * FROM rent WHERE member_id = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Rent rent = new Rent();
					
					rent.setMemberId(rs.getString("member_id"));
					rent.setSeatNum(rs.getInt("seat_num"));
					rent.setSeatPrice(rs.getInt("seat_price"));
					rent.setRent_date(rs.getDate("rent_date"));
					rent.setRent_hour(rs.getInt("rent_hour"));
					rent.setRent_price(rs.getInt("rent_price"));
					
					list.add(rent);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
			return list;
		}
		
		
		//전체 조회 -> 좌석 번호로 대여 내역 조회
		
		public List<Rent> selectOneSeatNum(int seatNum) {
			List<Rent> list = new ArrayList<>();
			
			try {
				connect();
				
				String sql ="SELECT * FROM rent WHERE seat_num = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, seatNum);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Rent rent = new Rent();
					
					rent.setMemberId(rs.getString("member_id"));
					rent.setSeatNum(rs.getInt("seat_num"));
					rent.setSeatPrice(rs.getInt("seat_price"));
					rent.setRent_date(rs.getDate("rent_date"));
					rent.setRent_hour(rs.getInt("rent_hour"));
					rent.setRent_price(rs.getInt("rent_price"));
					
					list.add(rent);
				}	
				
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
			return list;
		}
		
		
		//전체 조회 -> 해당 날짜의 대여내역 조회
		
		public List<Rent> selectAllRentDate(Date rent_date){
			List<Rent> list = new ArrayList<>();
			
			try {
				connect();
				
				String sql = "SELECT r.rent_date, s.seat_num, s.seat_price, r.rent_hour, r.rent_price "
						+ "FROM seats s JOIN rent "
						+ "ON s.seat_price = r.seat_price "
						+ "WHERE rent_date = ? ORDER BY rent_date;";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setDate(1, rent_date);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Rent rent = new Rent();
					
					rent.setMemberId(rs.getString("member_id"));
					rent.setSeatNum(rs.getInt("seat_num"));
					rent.setSeatPrice(rs.getInt("seat_price"));
					rent.setRent_date(rs.getDate("rent_date"));
					rent.setRent_hour(rs.getInt("rent_hour"));
					rent.setRent_price(rs.getInt("rent_price"));
					
					list.add(rent);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return list;
		}
		
		
		//전체 조회 -> 전체 좌석 대여 내역 조회? 
		public List<Rent> selectAllSeatNum(){
			List<Rent> list = new ArrayList<>();
			
			try {
				connect();
				
				String sql = "SELECT * FROM rent ORDER BY seat_num";
				
				stmt = conn.createStatement();
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Rent rent = new Rent();
					
					rent.setMemberId(rs.getString("member_id"));
					rent.setSeatNum(rs.getInt("seat_num"));
					rent.setSeatPrice(rs.getInt("seat_price"));
					rent.setRent_date(rs.getDate("rent_date"));
					rent.setRent_hour(rs.getInt("rent_hour"));
					rent.setRent_price(rs.getInt("rent_price"));
					
					list.add(rent);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return list;
		}
		
	}
