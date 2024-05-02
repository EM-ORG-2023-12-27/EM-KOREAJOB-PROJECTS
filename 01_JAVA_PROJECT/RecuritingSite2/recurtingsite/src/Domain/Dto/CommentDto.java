package Domain.Dto;

public class CommentDto extends ComuDto{ // comuDto의 상속받은 MemberDto의 id와 cumuDto의 cumuCode를 받아옴
	private String Comment; // 댓글 내용을 저장할 변수

	//디폴트생성자/ 모든인자 생성자 추가하기
	public CommentDto() {};
	
	public CommentDto(int comuCode, String id,String comment) {
		super();
		this.comuCode = comuCode;
		Id = id;
		Comment = comment;
	}
	
	//getter and setter
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}

	//toString
	@Override
	public String toString() {
		return "CommentDto [Comment=" + Comment + ", comuCode=" + comuCode + ", Id=" + Id + "]";
	}
	
	
}
