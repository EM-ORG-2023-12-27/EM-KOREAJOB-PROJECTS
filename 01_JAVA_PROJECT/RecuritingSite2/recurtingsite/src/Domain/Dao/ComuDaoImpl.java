package Domain.Dao;

import java.util.ArrayList;
import java.util.List;

import Domain.Dto.ComuDto;

public class ComuDaoImpl extends CommonDao implements ComuDao{
	
	// 싱글톤 패턴 (private하게 만들어줌)
	// 싱글톤 패턴 시작 (객체 생성 단일화)
	private static ComuDao instance;
	public static ComuDao getInstance() throws Exception {
		if(instance==null)
			instance=new ComuDaoImpl();
		return instance;
	}
	//싱글톤 패턴 끝
	
	public ComuDaoImpl() throws Exception{ // 실행하는 위치에서 예외처리
		System.out.println("[DAO] comuDaoImpl's INIT");
	}
	
	
	// CRUD : insert, update, delete, selectall, selectone 사용
	
	//INSERT
	@Override
	public boolean Insert(ComuDto dto) throws Exception{
		// comuCode, Id, Title, Content, Date 순으로 입력받음
		pstmt = conn.prepareStatement("insert into comu values(?,?,?,?,?)");
//		pstmt.setInt(1, dto.getComuCode());
//		pstmt.setString(2, dto.getId());
//		pstmt.setString(3, dto.getTitle());
//		pstmt.setString(4, dto.getDate());
//		pstmt.setString(5, dto.getContent());
		pstmt.setInt(1, dto.getComuCode());
		pstmt.setString(2, dto.getId());
		pstmt.setString(3, dto.getTitle());
		pstmt.setString(4, dto.getDate());
		pstmt.setString(5, dto.getContent());
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		return result>0;
	}
	//UPDATE
	@Override
	public boolean Update(ComuDto dto) throws Exception{
		// id, title, comuCode가 일치하면 수정
		pstmt = conn.prepareStatement("update comu set id=?, title=?, date=?,content=? where comucode=?");
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getTitle());
		pstmt.setString(3, dto.getDate());
		pstmt.setString(4, dto.getContent());
		pstmt.setInt(5, dto.getComuCode());
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		return result>0;
	}
	//DELETE
	@Override
	public boolean Delete(ComuDto dto) throws Exception{
		// id, title, comuCode가 일치하면 삭제
		pstmt = conn.prepareStatement("delete from book where id=? and title=? and comuCode=?");
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getTitle());
		pstmt.setInt(3, dto.getComuCode());
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		return result>0;
	}
	//SELECTALL
	@Override
	public List<ComuDto> SelectAll() throws Exception{
		// select 기본 코드
		pstmt = conn.prepareStatement("select * from comu");
		rs = pstmt.executeQuery();
		List<ComuDto> list = new ArrayList();
		ComuDto dto = null;
		if(rs!=null) {
			while(rs.next()) {
				dto = new ComuDto();
				dto.setId(rs.getString("id"));
				dto.setComuCode(rs.getInt("comuCode"));
				dto.setTitle(rs.getString("Title"));
				dto.setContent(rs.getString("Content"));
				dto.setDate(rs.getString("Date"));
				list.add(dto);
			}
		}
		
		freeConnection(pstmt, rs);
		return list;
	}
	//SELECTONE
	@Override
	public ComuDto Select(String id) throws Exception{
		
		// id를 기준으로 셀렉트함
		pstmt = conn.prepareStatement("select * from comu where id=?");
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery();
		ComuDto dto = null;
		if(rs!=null) {
			rs.next();
			dto = new ComuDto();
			dto.setComuCode(rs.getInt("comuCoe"));
			dto.setId(rs.getString("id"));
			dto.setTitle(rs.getString("Title"));
			dto.setDate(rs.getString("Date"));
			dto.setContent(rs.getString("Content"));
			
		}
		
		freeConnection(pstmt, rs);
		return dto;
	}
}
