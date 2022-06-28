package member;

public class Member {

	private int memberNum;
	private String memberName;
	private String memberPhone;
	private String memberId;
	private String memberPwd;
	//memberRole 0 : 관리자, 1 : 일반사원 
	private int memberRole;
	
	
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(int memberRole) {
		this.memberRole = memberRole;
	}
	
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	
	
	@Override
	public String toString() {
		String info = "";
		if(memberRole == 0) {
			info = "관리자 계정 : " + memberId;
		} else {
			info = "일반 계정 : " + memberId +"\n(이름 ▶▶ " + memberName
					+ ", 전화번호 ▶▶ "+memberPhone+")\n";
		}
		return info;
	}
	
}
