package Controller;

import java.util.HashMap;
import java.util.Map;

import Domain.Dto.ADDto;
import Domain.Service.ADService;
import Domain.Service.ADServiceImpl;

public class ADController implements SubController {

    private ADService service;

    // ADController 생성자
    public ADController() {
        try {
            // ADServiceImpl 객체 생성
            service = new ADServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SubController의 execute 메서드 구현
    @Override
    public Map<String, Object> execute(int serviceNo, Map<String, Object> params) {
        System.out.println("ADController's execute()");

        // 서비스 번호에 따라 각각의 작업을 수행
        if (serviceNo == 1) { // insert
            System.out.println("[SC]ADController's Insert");

            // params에서 ADDto 객체 추출
            ADDto dto = (ADDto) params.get("ADDto");
            System.out.println("[SC]ADController's Insert" + dto);

            // 유효성 검사
            if (isValid(dto)) {
                return null; // 유효성 검사 실패 시 처리
            }

            boolean isRegistered = false;
            try {
                // 광고 등록 서비스 호출
                isRegistered = service.ADInsert(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 결과 반환을 위한 Map 생성
            Map<String, Object> result = new HashMap();
            result.put("response", isRegistered);
            return result; // 결과 반환

        } else if (serviceNo == 2) { // update
            // update 작업 수행 코드 작성
        	
        	System.out.println("[SC]ADController's Update");

            // params에서 ADDto 객체 추출
            ADDto dto = (ADDto) params.get("ADDto");
            System.out.println("[SC]ADController's Update" + dto);

            // 유효성 검사
            if (isValid(dto)) {
                return null; // 유효성 검사 실패 시 처리
            }

            boolean isRegistered = false;
            try {
                // 광고 등록 서비스 호출
                isRegistered = service.ADUpdate(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 결과 반환을 위한 Map 생성
            Map<String, Object> result = new HashMap();
            result.put("response", isRegistered);
            return result; // 결과 반환
        } else if (serviceNo == 3) { // delete
            // delete 작업 수행 코드 작성
        	
        	System.out.println("[SC]ADController's Delete");

            // params에서 ADDto 객체 추출
            ADDto dto = (ADDto) params.get("ADDto");
            System.out.println("[SC]ADController's Delete" + dto);

            // 유효성 검사
            if (isValid(dto)) {
                return null; // 유효성 검사 실패 시 처리
            }

            boolean isRegistered = false;
            try {
                // 광고 등록 서비스 호출
                isRegistered = service.ADDelete(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 결과 반환을 위한 Map 생성
            Map<String, Object> result = new HashMap();
            result.put("response", isRegistered);
            return result; // 결과 반환
        } else if (serviceNo == 4) { // selectall
            // selectall 작업 수행 코드 작성
        } else if (serviceNo == 5) { // selectone
            // selectone 작업 수행 코드 작성
        } else {
            // 그 외의 서비스 번호에 대한 처리
        }

        return null; // 기본적으로 null 반환
    }

    // ADController 객체를 문자열로 표현하는 메서드
    @Override
    public String toString() {
        return "ADController [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

    // ADDto 객체의 유효성을 검사하는 메서드
    private boolean isValid(ADDto dto) {
        // null 체크나 trim 처리 등 유효성 검사 수행
        return true; // 일단은 항상 true 반환하도록 구현되어 있음
    }
}
