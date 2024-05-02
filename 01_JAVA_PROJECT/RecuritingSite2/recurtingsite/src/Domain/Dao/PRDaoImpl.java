package Domain.Dao;

import java.util.ArrayList;
import java.util.List;

import Domain.Dto.PRDto;

public class PRDaoImpl extends CommonDao implements PRDao{
	

	
	public PRDaoImpl() throws Exception{
		System.out.println("[DAO] PRDaoImpl's INIT.."+conn);
		
	}
	
	//INSERT
	@Override
	public boolean Insert(PRDto dto) throws Exception{
		pstmt = conn.prepareStatement("insert into PR values(?,?,?,?)");  
		pstmt.setInt(1, dto.getPRNum());
		pstmt.setString(2, dto.getInfo());
		pstmt.setString(3, dto.getEdu());
		pstmt.setString(4, dto.getCareer());
		
		int result = pstmt.executeUpdate();
		freeConnection(pstmt);
		return result>0;
	}
	//UPDATE
	public boolean Update(PRDto dto) throws Exception {
	    pstmt = conn.prepareStatement("update PR set info=?, edu=?, carrer=? where PRnum=?");
	    pstmt.setString(1, dto.getInfo());
	    pstmt.setString(2, dto.getEdu());
	    pstmt.setString(3, dto.getCareer());
	    pstmt.setInt(4, dto.getPRNum());
	    int result = pstmt.executeUpdate();
	    
	    freeConnection(pstmt);
	    return result > 0;
	}
	//DELETE
	@Override
	public boolean delete(int PRNum) throws Exception{
		pstmt = conn.prepareStatement("delete from PR where PRnum=?");
		pstmt.setInt(1, PRNum);
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		return result>0;
	}
	//SELECTALL
	@Override
	public List<PRDto> SelectAll() throws Exception{
		pstmt = conn.prepareStatement("select * from PR");
		rs = pstmt.executeQuery();
		List<PRDto> list = new ArrayList();
		PRDto dto = null;
		
		if(rs !=null )
		{
			while(rs.next()) {
				dto = new PRDto();
				dto.setPRNum(rs.getInt("PRNum"));
				dto.setInfo(rs.getString("PRinfo"));
				dto.setEdu(rs.getString("PRedu"));
				dto.setCareer(rs.getString("PRcarrer"));
				list.add(dto);
			}
		}
		freeConnection(pstmt,rs);
		return list;
	}
	
	// SELECTONE
	@Override
	public PRDto Select(int PRNum) throws Exception {
		pstmt = conn.prepareStatement("select * from PR where PRNum=?");
		pstmt.setInt(1, PRNum);
		rs = pstmt.executeQuery();

		PRDto dto = null;
		if (rs != null) {
			while (rs.next()) {
				dto = new PRDto();
				dto.setPRNum(rs.getInt("PRNum"));
				dto.setInfo(rs.getString("PRinfo"));
				dto.setEdu(rs.getString("PRedu"));
				dto.setCareer(rs.getString("PRcarrer"));
			}
		}
		freeConnection(pstmt,rs);
		return dto;

	}
	
}
