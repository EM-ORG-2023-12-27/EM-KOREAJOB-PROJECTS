package Domain.Dto;

public class MemberDto {
	private int MemberCode; 		// 멤버 코드
	protected String Id;			// 회원 ID
	private String pw;				// 회원 PW
	private String name;			// 회원 이름
	private String eMail;			// 회원 e-mail
	private String tellphone;		// 회원 전화번호
	private String Role;			
	
	
	// 생성자
	public MemberDto(int memberCode, String id, String pw, String name, String eMail, String tellphone, String role) {
		super();
		MemberCode = memberCode;
		Id = id;
		this.pw = pw;
		this.name = name;
		this.eMail = eMail;
		this.tellphone = tellphone;
		this.Role = role;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public MemberDto() {}
	
	// getter and Setter
	public int getMemberCode() {
		return MemberCode;
	}
	public void setMemberCode(int memberCode) {
		MemberCode = memberCode;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getTellphone() {
		return tellphone;
	}
	public void setTellphone(String tellphone) {
		this.tellphone = tellphone;
	}

	@Override
	public String toString() {
		return "MemberDto [MemberCode=" + MemberCode + ", Id=" + Id + ", pw=" + pw + ", name=" + name + ", eMail="
				+ eMail + ", tellphone=" + tellphone + ", Role=" + Role + "]";
	}


	}

