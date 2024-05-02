package Domain.Service;

import java.util.List;

import Domain.Dto.PRDto;

public interface PRService {

	boolean PRInsert(PRDto dto) throws Exception;

	boolean PRUpdate(PRDto dto) throws Exception;

	boolean PRDelete(PRDto dto) throws Exception;

	List<PRDto> getAllPR() throws Exception;

	PRDto getPR(int PRInfo) throws Exception;

}