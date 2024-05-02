package Domain.Service;

import java.util.List;

import Domain.Dao.PRDao;
import Domain.Dao.PRDaoImpl;
import Domain.Dto.PRDto;

public class PRServiceImpl implements PRService{
	private PRDao dao;
	
	private static PRService instance;
	public static PRService getInstance() throws Exception{
		if(instance==null)
			instance=new PRServiceImpl();
		return instance;
	}
	
	
	public PRServiceImpl() throws Exception{
		dao = new PRDaoImpl();
	}
	
	@Override
	public boolean PRInsert(PRDto dto) throws Exception{
		return dao.Insert(dto);		
	}
	@Override
	public boolean PRUpdate(PRDto dto) throws Exception{
		return dao.Insert(dto);		
	}
	@Override
	public boolean PRDelete(PRDto dto) throws Exception{
		return dao.Insert(dto);		
	}
	@Override
	public List<PRDto> getAllPR() throws Exception{
		return dao.SelectAll();
	}
	@Override
	public PRDto getPR(int PRInfo) throws Exception{
		return dao.Select(PRInfo);
	}
	
	
	
	
	
}
