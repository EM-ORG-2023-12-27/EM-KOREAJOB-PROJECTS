package Domain.Dto;

public class ComuDto extends MemberDto{ //MemberDto에서 id를 받아옴
	protected int comuCode; // 커뮤니티 코드 (커뮤니티 구분하는 코드) ex, 1. 정고공유게시판, 2. 기업정보게시판 ...etc
	private String Title;	// 제목
	private String Content; // 내용
	private String Date; 		// sql date 받아오기
	
	
	//디폴트생성자/ 모든인자 생성자 추가하기
	public ComuDto() {};
	public ComuDto(int comuCode, String id, String title,  String date, String content) {
		super();
		this.comuCode = comuCode;
		Id = id;
		Title = title;
		Date = date;
		Content = content;
	}
	
	//getter and setter
	public int getComuCode() {
		return comuCode;
	}
	public void setComuCode(int comuCode) {
		this.comuCode = comuCode;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
		
	}
	@Override
	public String toString() {
		return "ComuDto [comuCode=" + comuCode + ", Id=" + Id + ", Title=" + Title + ", Date=" + Date + ", Content=" + Content
				+  "]";
	}
}
