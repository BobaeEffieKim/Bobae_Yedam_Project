package rent;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
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
			
			String sql ="INSERT INTO rent (member_id, seat_num, seat_price, rent_date, rent_hour, rent_price, return_time) VALUES (?, ?, ?, default, ?, ?, null)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rent.getMemberId());
			pstmt.setInt(2, rent.getSeatNum());
			pstmt.setInt(3, rent.getSeatPrice());
			pstmt.setInt(4, rent.getRent_hour());
			pstmt.setInt(5, rent.getRent_price());
			//pstmt.setDate(6, rent.getReturn_time());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("        ߍ___ߍ          ");
				System.out.println("       (｡･◡･｡)         ");
				System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
				System.out.println("  정상적으로 등록되었습니다. ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
			} else {
				System.out.println("        ߍ___ߍ          ");
				System.out.println("       (·ㅠ-ㅠ)         ");
				System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
				System.out.println("    등록에 실패하였습니다.  ");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
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
					rent.setRent_date(rs.getTimestamp("rent_date"));
					rent.setRent_hour(rs.getInt("rent_hour"));
					rent.setRent_price();
					rent.setReturn_time(rs.getTimestamp("return_time"));
					
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
					rent.setRent_date(rs.getTimestamp("rent_date"));
					rent.setRent_hour(rs.getInt("rent_hour"));
					rent.setRent_price();
					rent.setReturn_time(rs.getTimestamp("return_time"));
					
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
		
		public List<Rent> selectAllRentDate(String rent_date){
			List<Rent> list = new ArrayList<>();
			
			try {
				connect();
				
				//between이 예를들어 1시~2시까지 라고 설정하면 1시와 2시도 포함하니까 (타임스탬프 + 1일 더하기 )-1초 해준것
				
				String sql = "SELECT * "
						+ "FROM rent "
						+ "WHERE rent_date BETWEEN "
						+ "(SELECT TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS') FROM dual) "
						+ "AND "
						+ "(SELECT TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS') + (INTERVAL '1' DAY) - (INTERVAL '1' SECOND) FROM dual)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rent_date);
				pstmt.setString(2, rent_date);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Rent rent = new Rent();
					
					//rent.setMemberId(rs.getString("member_id"));
					rent.setSeatNum(rs.getInt("seat_num"));
					rent.setSeatPrice(rs.getInt("seat_price"));
					rent.setRent_date(rs.getTimestamp("rent_date"));
					rent.setRent_hour(rs.getInt("rent_hour"));
					rent.setMemberId(rs.getString("member_id"));
					rent.setReturn_time(rs.getTimestamp("return_time"));
					rent.setRent_price();
					//rent.setReturn_time(rs.getTimestamp("return_time"));
					
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
				
				String sql = "SELECT * FROM rent ORDER BY member_id, rent_date, seat_num";
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					Rent rent = new Rent();
					
					rent.setMemberId(rs.getString("member_id"));
					rent.setSeatNum(rs.getInt("seat_num"));
					rent.setSeatPrice(rs.getInt("seat_price"));
					rent.setRent_date(rs.getTimestamp("rent_date"));
					rent.setRent_hour(rs.getInt("rent_hour"));
					rent.setRent_price();
					rent.setReturn_time(rs.getTimestamp("return_time"));
					
					list.add(rent);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return list;
		}
		
		//반납 시간 업데이트
		
		public void updateReturnTime(Rent rent) {
		
			
			try {
				connect();
				
				String sql = "UPDATE rent set return_time = sysdate " 
						+"WHERE seat_num = ? AND member_id = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rent.getSeatNum());
				pstmt.setString(2, rent.getMemberId());
		
				int result = pstmt.executeUpdate();
				
				if(result > 0) {
					System.out.println("        ߍ___ߍ          ");
					System.out.println("       (｡･◡･｡)         ");
					System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
					System.out.println("    반납이 완료되었습니다.  ");
					System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
				} else {
					System.out.println("        ߍ___ߍ          ");
					System.out.println("       (·ㅠ-ㅠ)         ");
					System.out.println("┏━━━━━━━O━━━O━━━━━━━━━┓");
					System.out.println("    반납에 실패하였습니다.  ");
					System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
				}
				
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}
		
		
		//단건조회
		
		public Rent selectOneById(String memberId) {
			
			Rent rent = null;
			
			try {
				connect();
				
				String sql = "SELECT seat_num FROM rents WHERE member_id = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					rent = new Rent();
					rent.setMemberId(rs.getString("member_id"));
					rent.setRent_date(rs.getTimestamp("rent_date"));
					rent.setRent_hour(rs.getInt("rent_hour"));
					rent.setRent_price();
					rent.setReturn_time(rs.getTimestamp("return_time"));
					rent.setSeatNum(rs.getInt("seat_num"));
					rent.setSeatPrice(rs.getInt("seat_price"));
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return rent;
		}
		
		
		
		
		//연체
//        public String lateWarn(int seatNum) {
//           String lateWarn = "";
//           try {
//              connect();
//              String sql = "SELECT return_time, rent_hour, " 
//                      +"CASE WHEN return_time < (rent_date + (INTERVAL '?' day)) " 
//                      +"THEN 0 "
//                      +"WHEN return_time > (rent_date + (INTERVAL '?' day)) "
//                      +"THEN 1 "
//                      +"END late FROM rent WHERE seat_num = "+ seatNum;
//              
//              pstmt = conn.prepareStatement(sql);
//              pstmt.setInt(1, );
//              
//              if(rs.next()) {
//                 int late = rs.getInt("late");
//                 if(late>0) {
//                    
//                    lateWarn += "\n <( ‵□′)>── [ "+ late + " ] 일 연체되어 연체료 [ "+late*500+" ] 원이 발생했습니다 ─Ｃε(┬﹏┬)3  \n";
//                 }
//              }else {
//                 
//              }
//           }catch(SQLException e) {
//              e.printStackTrace();
//           }finally {
//              disconnect();
//           }
//           return lateWarn;
//        }
//		
//		
		//현재 대여 중인 좌석이 있는지 확인하는 기능 
		
//		public Rent rentCheck() {
//			
//			Rent rent = null;
//			
//			try {
//				connect();
//				
//				String sql = "";
//				
//			} catch(SQLException e) {
//				e.printStackTrace();
//			} finally {
//				disconnect();
//			}
//			
//			
//			
//			return rent;
//		}
//		
		
	}
