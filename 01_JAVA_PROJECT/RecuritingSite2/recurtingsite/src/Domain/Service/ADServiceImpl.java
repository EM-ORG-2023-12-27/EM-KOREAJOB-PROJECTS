package Domain.Service;

import java.util.List;

import Domain.Dao.ADDao;
import Domain.Dao.ADDaoImpl;
import Domain.Dto.ADDto;

public class ADServiceImpl implements ADService  {
    private ADDao dao; // ADDaoImpl 객체를 사용하기 위한 멤버 변수

    // ADServiceImpl 생성자
    public ADServiceImpl() throws Exception {
        dao = new ADDaoImpl(); // ADDaoImpl 객체 생성
    }

    // 광고 등록 메서드
    @Override
	public boolean ADInsert(ADDto dto) throws Exception {
        return dao.Insert(dto); // ADDaoImpl의 Insert 메서드 호출하여 광고 등록
    }
    
    // 광고 수정 매서드
    @Override
	public boolean ADUpdate(ADDto dto) throws Exception {
        return dao.Update(dto); // ADDaoImpl의 Update 메서드 호출하여 광고 등록
    }
    
    // 광고 삭제 매서드
    @Override
	public boolean ADDelete(ADDto dto) throws Exception {
        return dao.Insert(dto); // ADDaoImpl의 Insert 메서드 호출하여 광고 등록
    }

    // 모든 광고 정보를 가져오는 메서드
    @Override
	public List<ADDto> getAllADs() throws Exception {
        return dao.SelectAll(); // ADDaoImpl의 SelectAll 메서드 호출하여 모든 광고 정보 조회
    }

    // 특정 회사의 광고 정보를 가져오는 메서드
    @Override
	public ADDto getAD(String cominfo) throws Exception {
        return dao.Select(cominfo); // ADDaoImpl의 Select 메서드 호출하여 특정 회사의 광고 정보 조회
    }
}
