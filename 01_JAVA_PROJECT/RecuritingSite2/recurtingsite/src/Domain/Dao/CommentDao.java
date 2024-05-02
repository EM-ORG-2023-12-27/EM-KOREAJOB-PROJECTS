package Domain.Dao;

import java.util.List;

import Domain.Dto.CommentDto;

public interface CommentDao {

	//INSERT
	boolean Insert(CommentDto dto) throws Exception;

	//UPDATE
	boolean Update(CommentDto dto) throws Exception;

	//DELETE
	boolean Delete(CommentDto dto) throws Exception;

	//SELECTALL
	List<CommentDto> SelectAll() throws Exception;

}