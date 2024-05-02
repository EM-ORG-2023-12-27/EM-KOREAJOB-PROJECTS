package Domain.Dao;

import java.util.List;

import Domain.Dto.ComuDto;

public interface ComuDao {

	//INSERT
	boolean Insert(ComuDto dto) throws Exception;

	//UPDATE
	boolean Update(ComuDto dto) throws Exception;

	//DELETE
	boolean Delete(ComuDto dto) throws Exception;

	//SELECTALL
	List<ComuDto> SelectAll() throws Exception;

	//SELECTONE
	ComuDto Select(String id) throws Exception;

}