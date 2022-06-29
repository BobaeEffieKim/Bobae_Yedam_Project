package seat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class SeatDAO extends DAO{

	private static SeatDAO seatDAO = null;
	private SeatDAO() {}
	
	public static SeatDAO getInstance() {
		if(seatDAO == null) {
			seatDAO = new SeatDAO();
		}
		return seatDAO;
	}
	
	
	//CRUD
	
	//등록 - 좌석 수를 늘릴 경우
	public void insert(Seat seat) {
		
		try {
			connect();
			
			String sql = "INSERT INTO seats (seat_num, seat_price) VALUES (?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seat.getSeatNum());
			pstmt.setInt(2, seat.getSeatPrice());
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				//System.out.println("         ߍ__ߍ          ");
				//System.out.println("        (｡･◡･｡)         ");
				System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
				System.out.println("  정상적으로 등록되었습니다. ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			} else {
				//System.out.println("         ߍ__ߍ          ");
				//System.out.println("        (ㅠ-ㅠ)         ");
				System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
				System.out.println("    등록에 실패하였습니다.  ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
	}
	
	//삭제 
	public void delete(int seatNum) {
		try {
			connect();
			
			String sql = "DELETE FROM seats WHERE seat_num =" + seatNum;
			stmt = conn.createStatement();
			
			int result = stmt.executeUpdate(sql);
			if(result > 0) {
				//System.out.println("         ߍ__ߍ          ");
				//System.out.println("        (｡･◡･｡)         ");
				System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
				System.out.println("  정상적으로 삭제되었습니다. ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			} else {
				//System.out.println("         ߍ__ߍ          ");
				//System.out.println("        (ㅠ-ㅠ)         ");
				System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
				System.out.println("    삭제에 실패하였습니다. ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		
	}
	
	
	//수정 - 대여가 
	public void updateSeatNum(Seat seat) {
		try {
			connect();
			
			String sql = "UPDATE seats SET seat_price = ? WHERE seat_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seat.getSeatPrice());
			pstmt.setInt(2, seat.getSeatNum());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				//System.out.println("           ߍ__ߍ          ");
				//System.out.println("          (｡･◡･｡)         ");
				System.out.println("┏━━━━━━━━━O━━━O━━━━━━━━━━━┓");
				System.out.println(" 대여가격이 정상적으로 수정되었습니다. ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			} else {
				//System.out.println("         ߍ__ߍ          ");
				//System.out.println("        (ㅠ-ㅠ)         ");
				System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
				System.out.println(" 대여가격 수정에 실패하였습니다. ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	
	//수정 - 렌탈여부
	public void updateSeatRental(Seat seat) {
		try {
			connect();
			
			String sql = "UPDATE seats SET seat_rental = ? WHERE seat_num = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seat.getSeatRental());
			pstmt.setInt(2, seat.getSeatNum());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				//System.out.println("           ߍ__ߍ          ");
				//System.out.println("          (｡･◡･｡)         ");
				System.out.println("┏━━━━━━━━━O━━━O━━━━━━━━━━━┓");
				System.out.println(" 대여상태가 정상적으로 수정되었습니다. ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			} else {
				//System.out.println("         ߍ__ߍ          ");
				//System.out.println("        (ㅠ-ㅠ)         ");
				System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
				System.out.println(" 대여상태 수정에 실패하였습니다. ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
//	private void updateReturnTime(Seat seat) {
//		try {
//			connect();
//			
//			String sql = "UPDATE rent SET return_time = ? WHERE seat_rental =  ";
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} finally {
//			disconnect();
//		}
//	}
//	
//	
	
	//단건 조회 - 좌석 번호로 
	public Seat selectBySeatNum(int seatNum) {
		
		Seat seat = null;
		
		try {
			connect();
			
			String sql = "SELECT * FROM seats WHERE seat_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				seat = new Seat();
				seat.setSeatNum(rs.getInt("seat_num"));
				seat.setSeatPrice(rs.getInt("seat_price"));
				seat.setSeatRental(rs.getInt("seat_rental"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return seat;
	}
	
	
	//단건 조회 - 렌탈 여부 -> 여기서 아님 대여관리클래스에서??
	public List<Seat> selectBySeatRental(int seatRental) {
		
		List<Seat> list = new ArrayList<Seat>();
		
		
		try {
			connect();
			
			String sql = "SELECT* FROM seats WHERE seat_rental = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatRental);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Seat seat = new Seat();
				
				seat.setSeatNum(rs.getInt("seat_num"));
				seat.setSeatPrice(rs.getInt("seat_price"));
				seat.setSeatRental(rs.getInt("seat_rental"));
				
				list.add(seat);
			}	
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	
	//전체 조회 - 좌석 배치도와 전체 좌석
	public List<Seat> selectAll(){
		
		seatArrangement();
		
		List<Seat> list = new ArrayList<Seat>();
		
		try {
			connect();
			
			String sql = "SELECT* FROM seats ORDER BY seat_num";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Seat seat = new Seat();
				
				seat.setSeatNum(rs.getInt("seat_num"));
				seat.setSeatPrice(rs.getInt("seat_price"));
				seat.setSeatRental(rs.getInt("seat_rental"));
				
				list.add(seat);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	public void seatArrangement() {
		System.out.println();
		System.out.println("               [[ 좌석 배치도 ]]                ");
		System.out.println();
		System.out.println("───────────────────────────────────────────");
		System.out.println("|     |     |     |     |     |     |     |");
		System.out.println("|  1  |  2  |  3  |  4  |  5  |  6  |  7  |");
		System.out.println("|     |     |     |     |     |     |     |");
		System.out.println("───────────────────────────────────────────");
		System.out.println("            |     |     |     |            ");
		System.out.println("            |  8  |  9  |  10 |            ");
		System.out.println("            |     |     |     |            ");
		System.out.println("            ───────────────────            ");
		System.out.println();
		System.out.println();
		System.out.println("───────           ───────           ───────");
		System.out.println("|     |           |     |           |     |");
		System.out.println("| 11  |           | 12  |           | 13  |");
		System.out.println("|     |           |     |           |     |");
		System.out.println("───────           ───────           ───────");
		System.out.println();
		System.out.println();
		System.out.println("───────           ───────           ───────");
		System.out.println("|     |           |     |           |     |");
		System.out.println("| 14  |           | 15  |           | 16  |");
		System.out.println("|     |           |     |           |     |");
		System.out.println("───────           ───────           ───────");
		System.out.println();
		System.out.println();
		System.out.println("───────           ───────           ───────");
		System.out.println("|     |           |     |           |     |");
		System.out.println("| 17  |           | 18  |           | 19  |");
		System.out.println("|     |           |     |           |     |");
		System.out.println("───────           ───────           ───────");
		System.out.println();
		
	}
	
/*	
	public void addSeat(Seat seat) {
		try {
			connect();
			
			String sql = "UPDATE seats SET seat_add = ? WHERE seat_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seat.getSeatAdd());
			pstmt.setInt(2, seat.getSeatNum());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("           ߍ__ߍ          ");
				System.out.println("          (｡･◡･｡)         ");
				System.out.println("┏━━━━━━━━━O━━━O━━━━━━━━━━━┓");
				System.out.println("  좌석 추가 완료 ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			} else {
				System.out.println("         ߍ__ߍ          ");
				System.out.println("        (ㅠ-ㅠ)         ");
				System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
				System.out.println(" 좌석 추가 실패 ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
	}
	
	
	public void addPrint(int seatNum) {
		
		
		System.out.println();
		System.out.println("                  ───────           ");
		System.out.println("                  |     |           ");
		System.out.println("                  |"+seatNum+ "|           ");
		System.out.println("                  |     |           ");
		System.out.println("                  ───────           ");
		System.out.println();
		
	}
*/	
	
}
