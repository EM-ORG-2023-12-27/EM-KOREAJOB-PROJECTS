package Domain.Service;

import java.util.List;

import Domain.Dao.ApplyDao;
import Domain.Dao.ApplyDaoImpl;
import Domain.Dto.ApplyDto;



public class ApplyServiceImpl implements ApplyService {
	 private ApplyDao dao; // ADDaoImpl 객체를 사용하기 위한 멤버 변수

	    // ApplyServiceImpl 생성자
	    public ApplyServiceImpl() throws Exception {
	        dao = new ApplyDaoImpl(); // ADDaoImpl 객체 생성
	    }

	    // 지원 정보 등록 메서드
	    @Override
		public boolean ApplyInsert(ApplyDto dto) throws Exception {
	        return dao.Insert(dto); // ADDaoImpl의 Insert 메서드 호출하여 광고 등록
	    }
	    
	    // 지원 정보 수정 매서드
	    @Override
		public boolean ApplyUpdate(ApplyDto dto) throws Exception {
	        return dao.Update(dto); // ApplyDaoImpl의 Update 메서드 호출하여 지원 정보 수정
	    }
	    
	    // 지원 정보 삭제 매서드
	    @Override
		public boolean ApplyDelete(ApplyDto dto) throws Exception {
	        return dao.Insert(dto); // ApplyDaoImpl의 Insert 메서드 호출하여 지원 정보 삭제
	    }

	    // 모든 지원 정보를 가져오는 메서드
	    @Override
		public List<ApplyDto> getAllApplys() throws Exception {
	        return dao.SelectAll(); // ApplyDaoImpl의 SelectAll 메서드 호출하여 모든 지원 정보 조회
	    }

	    // 특정 회사의 지원 정보를 가져오는 메서드
	    @Override
		public ApplyDto getApply(int ApplyCode) throws Exception {
	        return dao.Select(ApplyCode); // ApplyDaoImpl의 Select 메서드 호출하여 지원 정보를 조회
	    }

}