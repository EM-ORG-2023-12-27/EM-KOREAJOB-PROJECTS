package Domain.Dao;

import java.util.ArrayList;
import java.util.List;

import Domain.Dto.CommentDto;


public class CommentDaoImpl extends CommonDao implements CommentDao {

	// 싱글톤 패턴 (private하게 만들어줌)
	// 싱글톤 패턴 시작 (객체 생성 단일화)
	private static CommentDao instance;
	public static CommentDao getInstance() throws Exception {
		if(instance==null)
			instance=new CommentDaoImpl();
		return instance;
	}
	//싱글톤 패턴 끝
	
	public CommentDaoImpl() throws Exception{ // 실행하는 위치에서 예외처리
		System.out.println("[DAO] CommentDaoImpl's INIT");
	}
	
	// CRUD : insert, update, delete, selectall 사용
	// 댓글(comment)는 모두 다 표시하므로 selectall 만 사용 
	
	//INSERT
	@Override
	public boolean Insert(CommentDto dto) throws Exception{
		pstmt = conn.prepareStatement("insert into comment values(?,?,?)");
		pstmt.setInt(1, dto.getComuCode());
		pstmt.setString(2, dto.getId());
		pstmt.setString(3, dto.getComment());
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		return result>0;
	}
	//UPDATE
	@Override
	// UPDATE
	public boolean Update(CommentDto dto) throws Exception {
	    pstmt = conn.prepareStatement("update comment set id=?, comment=? where comucode=?");
	    pstmt.setString(1, dto.getId());
	    pstmt.setString(2, dto.getComment());
	    pstmt.setInt(3, dto.getComuCode());
	    int result = pstmt.executeUpdate();
	    
	    freeConnection(pstmt);
	    return result > 0;
	}

	//DELETE
	@Override
	public boolean Delete(CommentDto dto) throws Exception{
		pstmt = conn.prepareStatement("delete from comment where id=? and title=?");
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getTitle());
	
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		return result>0;
	}
	//SELECTALL
	@Override
	public List<CommentDto> SelectAll() throws Exception{
		// select 기본 코드
		pstmt = conn.prepareStatement("select * from comment");
		rs = pstmt.executeQuery();
		List<CommentDto> list = new ArrayList();
		CommentDto dto = null;
		if(rs!=null) {
			while(rs.next()) {
				dto = new CommentDto();
				dto.setId(rs.getString("id"));
				dto.setComment(rs.getString("comment"));
				list.add(dto);
			}
		}
		
		freeConnection(pstmt, rs);
		return list;
	}

}
