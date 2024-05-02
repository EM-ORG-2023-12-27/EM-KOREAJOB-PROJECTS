package Domain.Dao;

import java.util.List;

import Domain.Dto.ADDto;

public interface ADDao {

	// INSERT 메서드: 광고 정보를 추가하는 메서드
	boolean Insert(ADDto adDto) throws Exception;

	//UPDATE
	boolean Update(ADDto adDto) throws Exception;

	//DELETE
	boolean Delete(ADDto adDto) throws Exception;

	// SELECTALL 메서드: 모든 광고 정보를 조회하는 메서드
	List<ADDto> SelectAll() throws Exception;

	// SELECTONE 메서드: 특정 광고 정보를 조회하는 메서드
	ADDto Select(String cominfo) throws Exception;

}