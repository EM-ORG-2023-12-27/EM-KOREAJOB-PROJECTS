package Domain.Dao;

import java.util.List;

import Domain.Dto.PRDto;

public interface PRDao {

	//INSERT
	boolean Insert(PRDto dto) throws Exception;

	//UPDATE
	boolean Update(PRDto dto) throws Exception;
	
	//DELETE
	boolean delete(int PRInfo) throws Exception;

	//SELECTALL
	List<PRDto> SelectAll() throws Exception;

	// SELECTONE
	PRDto Select(int PRNum) throws Exception;

}