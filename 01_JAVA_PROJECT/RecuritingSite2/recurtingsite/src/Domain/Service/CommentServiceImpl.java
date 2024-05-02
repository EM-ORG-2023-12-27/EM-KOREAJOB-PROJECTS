package Domain.Service;

import java.util.List;

import Domain.Dao.CommentDao;
import Domain.Dao.CommentDaoImpl;
import Domain.Dto.CommentDto;

public class CommentServiceImpl implements CommentService {

	private CommentDao dao;
	
	public CommentServiceImpl() throws Exception {
		dao = new CommentDaoImpl();
	}
	@Override
	public boolean comuInsert(CommentDto dto) throws Exception{
		return dao.Insert(dto);
	}
	@Override
	public boolean comuUpdate(CommentDto dto) throws Exception{
		return dao.Update(dto);
	}
	@Override
	public boolean comuDelete(CommentDto dto) throws Exception{
		return dao.Delete(dto);
	}
	@Override
	public List<CommentDto> getAllcomu() throws Exception{
		return dao.SelectAll();
	}
}
