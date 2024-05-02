package Domain.Dto;

public class ApplyDto {
	private int ApplyCode;
	private boolean ApplyClassify;

	// 모든 인자를 받는 생성자
	public ApplyDto(int applyCode, boolean applyClassify) {
		super();
		ApplyCode = applyCode;
		ApplyClassify = applyClassify;
	}
	
	// 디폴트 생성자
	public ApplyDto() {}
	
	// toString 메서드
	@Override
	public String toString() {
		return "ApplyDto [ApplyCode=" + ApplyCode + ", ApplyClassify=" + ApplyClassify + "]";
	}
	public int getApplyCode() {
		return ApplyCode;
	}
	public void setApplyCode(int applyCode) {
		ApplyCode = applyCode;
	}
	public boolean isApplyClassify() {
		return ApplyClassify;
	}
	public void setApplyClassify(boolean applyClassify) {
		ApplyClassify = applyClassify;
	}
}
