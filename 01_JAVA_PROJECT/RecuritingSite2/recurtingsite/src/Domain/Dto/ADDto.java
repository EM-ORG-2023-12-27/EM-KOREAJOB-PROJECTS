package Domain.Dto;

public class ADDto {
	private int ADNum; 		// 광고 번호(구분자)
    private String Cominfo; // 회사 정보
    private String Walfare; // 복지
    private String Salary;  // 급여
    private String Work;    // 업무

    // 모든 인자를 받는 생성자
    public ADDto(int aDNum, String cominfo, String salary, String walfare, String work) {
		super();
		ADNum = aDNum;
		Cominfo = cominfo;
		Walfare = walfare;
		Salary = salary;
		Work = work;
	}

    // 디폴트 생성자
    public ADDto() {}

	// toString 메서드
    @Override
	public String toString() {
		return "ADDto [ADNum=" + ADNum + ", Cominfo=" + Cominfo + ", Walfare=" + Walfare + ", Salary=" + Salary
				+ ", Work=" + Work + "]";
	}

    // getter 및 setter 메서드
	public int getADNum() {
		return ADNum;
	}
	public void setADNum(int aDNum) {
		ADNum = aDNum;
	}
	public String getCominfo() {
		return Cominfo;
	}
	public void setCominfo(String cominfo) {
		Cominfo = cominfo;
	}
	public String getWalfare() {
		return Walfare;
	}
	public void setWalfare(String walfare) {
		Walfare = walfare;
	}
	public String getSalary() {
		return Salary;
	}
	public void setSalary(String salary) {
		Salary = salary;
	}
	public String getWork() {
		return Work;
	}
	public void setWork(String work) {
		Work = work;
	}
}

