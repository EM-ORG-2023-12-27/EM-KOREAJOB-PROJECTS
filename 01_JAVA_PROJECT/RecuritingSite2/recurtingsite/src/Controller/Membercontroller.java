package Controller;

import java.util.HashMap;
import java.util.Map;

import Domain.Dto.MemberDto;
import Domain.Service.MemberServiceImpl;

public class Membercontroller implements SubController{
	private MemberServiceImpl service;
	public Membercontroller() {
		try {
			service=new MemberServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> execute(int serviceNo, Map<String, Object> params) {
		// TODO Auto-generated method stub
//		파라미터 받기
		System.out.println("MemberController's execute");
		if(serviceNo==1) {
			//insert
			MemberDto dto=(MemberDto)params.get("MemberDto");
			System.out.println("[sc]MemberController's insert.."+dto);
			
//			2.유효성체크
			if( !isValid(dto) ) 
				return null;
//			3.서비스 실행
			boolean isREgistred=false;
			try {
				isREgistred=service.memberInsert(dto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			4.뷰로 전달 or 이동
			Map<String,Object> result=new HashMap();
			result.put("response", isREgistred);
		}

		else if(serviceNo==2) {
			//Update
			MemberDto dto=(MemberDto)params.get("MemberDto");
			System.out.println("[sc]MemberController's update.."+dto);
//			2.유효성체크
			if( !isValid(dto) ) 
				return null;
//			3.서비스 실행
			boolean isREgistred=false;
			try {
				isREgistred=service.memberUpdate(dto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			4.뷰로 전달 or 이동
			Map<String,Object> result=new HashMap();
			result.put("response", isREgistred);
			
		}else if(serviceNo==3) {
			//delete
			MemberDto dto=(MemberDto)params.get("MemberDto");
			System.out.println("[sc]MemberController's delete.."+dto);
//			2.유효성체크
			if( !isValid(dto) ) 
				return null;
//			3.서비스 실행
			boolean isREgistred=false;
			try {
				isREgistred=service.memberDelete(dto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			4.뷰로 전달 or 이동
			Map<String,Object> result=new HashMap();
			result.put("response", isREgistred);
			
		}else if(serviceNo==4) {
//			select all
//			MemberDto dto=(MemberDto)params.get("MemberDto");
//			System.out.println("[sc]MemberController's selectAll.."+dto);
////			2.유효성체크
//			if( !isValid(dto) ) 
//				return null;
////			3.서비스 실행
//			boolean isREgistred=false;
//			try {
//				isREgistred=service.getAllmebers();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////			4.뷰로 전달 or 이동
//			Map<String,Object> result=new HashMap();
//			result.put("response", isREgistred);
//			selectAll
		}else if(serviceNo==5) {
//			MemberDto dto=(MemberDto)params.get("MemberDto");
//			System.out.println("[sc]MemberController's update.."+dto);
////			2.유효성체크
//			if( !isValid(dto) ) 
//				return null;
////			3.서비스 실행
//			boolean isREgistred=false;
//			try {
//				isREgistred=service.getMember(dto);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////			4.뷰로 전달 or 이동
//			Map<String,Object> result=new HashMap();
//			result.put("response", isREgistred);
			//selectOne
		}
			
		
		return null;
	}
	private boolean isValid(MemberDto dto) {
		return true;
	}
}
