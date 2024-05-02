package Domain.Dao;

import java.util.List;

import Domain.Dto.ApplyDto;

public interface ApplyDao {

	// INSERT
	boolean Insert(ApplyDto dto) throws Exception;

	// UPDATE
	boolean Update(ApplyDto dto) throws Exception;

	// DELETE
	boolean Delete(ApplyDto dto) throws Exception;

	// SELECTALL
	List<ApplyDto> SelectAll() throws Exception;

	// SELECTONE
	ApplyDto Select(int applyCode) throws Exception;

}