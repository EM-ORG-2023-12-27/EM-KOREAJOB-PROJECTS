package Domain.Service;

import java.util.List;

import Domain.Dao.ComuDao;
import Domain.Dao.ComuDaoImpl;
import Domain.Dto.ComuDto;

public class ComuServiceImpl implements ComuService {

	private ComuDao dao;
	
	public ComuServiceImpl() throws Exception {
		dao = new ComuDaoImpl();
	}
	
	@Override
	public boolean comuInsert(ComuDto dto) throws Exception{
		return dao.Insert(dto);
	}
	@Override
	public boolean comuUpdate(ComuDto dto) throws Exception{
		return dao.Update(dto);
	}
	@Override
	public boolean comuDelete(ComuDto dto) throws Exception{
		return dao.Delete(dto);
	}
	@Override
	public List<ComuDto> getAllcomu() throws Exception{
		return dao.SelectAll();
	}
	@Override
	public ComuDto getcomu(String id) throws Exception{
		return dao.Select(id); 
	}
}
