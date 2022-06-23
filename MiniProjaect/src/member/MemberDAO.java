package member;

import java.sql.SQLException;

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
			pstmt.setInt(2, member.getMemberPhone());
			pstmt.setInt(3, member.getMemberPhone());
			pstmt.setString(4, member.getMemberId());
			pstmt.setString(5, member.getMemberPwd());
			
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
			
			String sql = "UPDATE members SET member_phone = ? WHERE member_name = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getMemberPhone());
			pstmt.setString(2, member.getMemberName());
			
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
	
	//로그인
	
	//단건 조회 -> 회원 id로 지정하여 정보 조회 
	
	//전체 조회 -> 전체 회원 정보 조회
	
}
