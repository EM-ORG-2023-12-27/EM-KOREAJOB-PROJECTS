package Controller;

import java.util.HashMap;
import java.util.Map;

import Domain.Dto.ApplyDto;
import Domain.Service.ApplyService;
import Domain.Service.ApplyServiceImpl;



public class ApplyController implements SubController {

	private ApplyService service;

    // ApplyController 생성자
    public ApplyController() {
        try {
            // ApplyServiceImpl 객체 생성
            service = new ApplyServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	// 1 Insert , 2 Update , 3 Delete 4 SelectAll 5 Select

	@Override
	public Map<String, Object> execute(int serviceNo, Map<String, Object> params) {
		System.out.println("applyController's execute()");

		if (serviceNo == 1) { // Insert
			// params에서 ApplyDto 객체 추출
			ApplyDto dto = (ApplyDto) params.get("ApplyDto");
			System.out.println("[SC]ApplyController's Insert.." + dto);

			 // 유효성 검사
			if (!isValid(dto))
				return null;
			
			boolean isRegistered = false;
			try {
	              isRegistered = service.ApplyInsert(dto);
	         } catch (Exception e) {
	              e.printStackTrace();
	         }
			
			// 결과 반환을 위한 Map 생성
			Map<String, Object> result = new HashMap();
			result.put("response", isRegistered);
			return result; // 결과 반환

		} else if (serviceNo == 2) { // Update
			// params에서 ApplyDto 객체 추출
			ApplyDto dto = (ApplyDto) params.get("ApplyDto");
			System.out.println("[SC]ApplyController's Update.." + dto);

			 // 유효성 검사
			if (!isValid(dto))
				return null;
			
			boolean isRegistered = false;
			try {
	              isRegistered = service.ApplyUpdate(dto);
	         } catch (Exception e) {
	              e.printStackTrace();
	         }
			
			// 결과 반환을 위한 Map 생성
			Map<String, Object> result = new HashMap();
			result.put("response", isRegistered);
			return result; // 결과 반환
			
		} else if (serviceNo == 3) { // Delete
			// params에서 ApplyDto 객체 추출
			ApplyDto dto = (ApplyDto) params.get("ApplyDto");
			System.out.println("[SC]ApplyController's Delete.." + dto);

			 // 유효성 검사
			if (!isValid(dto))
				return null;
			
			boolean isRegistered = false;
			try {
	              isRegistered = service.ApplyDelete(dto);
	         } catch (Exception e) {
	              e.printStackTrace();
	         }
			
			// 결과 반환을 위한 Map 생성
			Map<String, Object> result = new HashMap();
			result.put("response", isRegistered);
			return result; // 결과 반환
		} else if (serviceNo == 4) { // SELECTALL

			System.out.println("");
		} else if (serviceNo == 5) // SELECTONE
		{
			System.out.println("");
		} else {
			System.out.println("");
		}

		return null;
	}

	private boolean isValid(ApplyDto dto) {
		// Null체크
		// trim제거

		return true;
	}

}
