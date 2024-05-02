package Domain.Service;

import java.util.List;

import Domain.Dto.CommentDto;

public interface CommentService {

	boolean comuInsert(CommentDto dto) throws Exception;

	boolean comuUpdate(CommentDto dto) throws Exception;

	boolean comuDelete(CommentDto dto) throws Exception;

	List<CommentDto> getAllcomu() throws Exception;

}