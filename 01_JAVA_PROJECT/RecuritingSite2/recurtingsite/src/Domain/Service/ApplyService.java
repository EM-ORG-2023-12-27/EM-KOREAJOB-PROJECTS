package Domain.Service;

import java.util.List;

import Domain.Dto.ApplyDto;



public interface ApplyService {

	// 광고 등록 메서드
		boolean ApplyInsert(ApplyDto dto) throws Exception;

		// 광고 수정 매서드
		boolean ApplyUpdate(ApplyDto dto) throws Exception;

		// 광고 삭제 매서드
		boolean ApplyDelete(ApplyDto dto) throws Exception;

		// 모든 광고 정보를 가져오는 메서드
		List<ApplyDto> getAllApplys() throws Exception;

		// 특정 회사의 광고 정보를 가져오는 메서드
		ApplyDto getApply(int ApplyCode) throws Exception;

}