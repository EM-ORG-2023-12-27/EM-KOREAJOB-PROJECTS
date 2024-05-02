package Domain.Service;

import java.util.List;

import Domain.Dto.ComuDto;

public interface ComuService {

	boolean comuInsert(ComuDto dto) throws Exception;

	boolean comuUpdate(ComuDto dto) throws Exception;

	boolean comuDelete(ComuDto dto) throws Exception;

	List<ComuDto> getAllcomu() throws Exception;

	ComuDto getcomu(String id) throws Exception;

}