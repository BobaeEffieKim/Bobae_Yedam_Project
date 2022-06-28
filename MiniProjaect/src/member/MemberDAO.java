package member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import common.DAO;

public class MemberDAO extends DAO{

	private static MemberDAO dao = null;
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if(dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}
	
	
	//CRUD
	
	//등록 - 회원가입
	public Member join(Member member) {
		
		Member joinInfo = null;
		
		try {
			connect();
			
			String sql = "INSERT INTO members(member_num, member_name, member_phone, member_id, member_pwd) VALUES(member_num_seq.nextval, ?, ?, ?, ? )";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getMemberPhone());
			pstmt.setString(3, member.getMemberId());
			pstmt.setString(4, member.getMemberPwd());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("회원가입이 완료되었습니다.");
			} else {
				System.out.println("회원가입에 실패하였습니다.");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return joinInfo;
	}
	
	
	//수정 - 폰번호
	public void updateMemberPhone(Member member) {
		try {
			connect();
			
			String sql = "UPDATE members SET member_phone = ? WHERE member_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPhone());
			pstmt.setString(2, member.getMemberId());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("정상적으로 수정되었습니다.");
			} else {
				System.out.println("정상적으로 수정되지 않았습니다.");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
	}
	
	
	//수정 - 비번 -> 분실시 관리자에게 요청하면 수정
	public void updateMemberPwd(Member member) {
		try {
			connect();
			
			String sql = "UPDATE members SET member_pwd = ? WHERE member_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPwd());
			pstmt.setString(2, member.getMemberId());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("정상적으로 수정되었습니다.");
			} else {
				System.out.println("정상적으로 수정되지 않았습니다.");
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	
	//삭제 - 회원 탈퇴 시 
	public void delete(String memberId) {
		
		try {
			connect();
			
			String sql = "DELETE FROM members WHERE member_id = '" + memberId +"'";
			
			stmt = conn.createStatement();
			
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println("정상적으로 삭제되었습니다.");
			} else {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	
	//로그인
	public Member logIn(Member member) {
		Member loginInfo = null;
		
		try {
			connect();
			
			String sql = "SELECT* FROM members WHERE member_id = '"+ member.getMemberId()+ "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				
				if(rs.getString("member_pwd").equals(member.getMemberPwd())) {
					
					loginInfo = new Member();
					loginInfo.setMemberId(rs.getString("member_id"));
					loginInfo.setMemberPwd(rs.getString("member_pwd"));
					loginInfo.setMemberRole(rs.getInt("memberRole"));
					
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
			}
				else {
					System.out.println("아이디가 존재하지 않습니다.");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return loginInfo;
	}
	
	
	//단건 조회 -> 회원 id로 지정하여 정보 조회 
	public Member selectOneById(String memberId) {
		
		Member member = null;
		
		try {
			connect();
			
			String sql = "SELECT * FROM members WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
					member = new Member();
					member.setMemberNum(rs.getInt("member_num"));
					member.setMemberName(rs.getString("member_name"));
					member.setMemberPhone(rs.getString("member_phone"));
					member.setMemberId(rs.getString("member_id"));
					member.setMemberPwd(rs.getString("member_pwd"));
					member.setMemberRole(rs.getInt("memberRole"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return member;
		
	}
	
	
	//전체 조회 -> 전체 회원 정보 조회
	public List<Member> selectAll(){
		List<Member> list = new ArrayList<>();
		
			try {
				connect();
				
				String sql = "SELECT*FROM members "
							+ "ORDER BY member_num ";
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {	//데이터가 몇개인지 모르니까 와일문 돌리기
					Member member = new Member();
					member.setMemberNum(rs.getInt("member_num"));
					member.setMemberName(rs.getString("member_name"));
					member.setMemberPhone(rs.getString("member_phone"));
					member.setMemberId(rs.getString("member_id"));
					member.setMemberPwd(rs.getString("member_pwd"));
					member.setMemberRole(rs.getInt("memberRole"));
					list.add(member);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
			return list;
			
		}
			
			
		
	
}
