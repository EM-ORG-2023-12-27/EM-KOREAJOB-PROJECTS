package Domain.Service;

import java.util.List;

import Domain.Dto.ADDto;

public interface ADService {

	// 광고 등록 메서드
	boolean ADInsert(ADDto dto) throws Exception;

	// 광고 수정 매서드
	boolean ADUpdate(ADDto dto) throws Exception;

	// 광고 삭제 매서드
	boolean ADDelete(ADDto dto) throws Exception;

	// 모든 광고 정보를 가져오는 메서드
	List<ADDto> getAllADs() throws Exception;

	// 특정 회사의 광고 정보를 가져오는 메서드
	ADDto getAD(String cominfo) throws Exception;

}