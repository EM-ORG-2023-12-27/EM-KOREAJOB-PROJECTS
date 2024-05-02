package Domain.Service;

import java.util.List;
import java.util.Map;

import Domain.Dto.MemberDto;

public interface MemberService {
	
	boolean memberJoin(MemberDto dto) throws Exception;

	boolean memberInsert(MemberDto dto) throws Exception;

	boolean memberUpdate(MemberDto dto) throws Exception;

	boolean memberDelete(MemberDto dto) throws Exception;

	
//	로그인
	Map<String,Object>login(int membercode,String pw,int sessionId) throws Exception;
	
	Map<String,Object>logout(int sessionId) throws Exception;
	
	List<MemberDto> getAllmembers() throws Exception;

	MemberDto getMember(int membercode) throws Exception;

	List<Integer> getSessionIdList();

}