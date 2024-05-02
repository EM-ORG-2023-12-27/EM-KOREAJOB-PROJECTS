package Domain.Dao;

import java.util.ArrayList;
import java.util.List;

import Domain.Dto.ADDto;

public class ADDaoImpl extends CommonDao implements ADDao {

    // ADDaoImpl 생성자
    public ADDaoImpl() throws Exception {
        // JDBC 드라이버 로드 및 데이터베이스 연결
        System.out.println("[DAO] ADDaoImpl's INIT");
    }

    // INSERT 메서드: 광고 정보를 추가하는 메서드
    @Override
	public boolean Insert(ADDto adDto) throws Exception {
    	pstmt = conn.prepareStatement("INSERT ad (adnum, cominfo, salary, walfare, work) VALUES (?, ?, ?, ?, ?)");
    	pstmt.setInt(1, adDto.getADNum());
        pstmt.setString(2, adDto.getCominfo());
        pstmt.setString(3, adDto.getSalary());
        pstmt.setString(4, adDto.getWalfare());
        pstmt.setString(5, adDto.getWork());
        int result = pstmt.executeUpdate();

        // 결과 반환
        freeConnection(pstmt);
        return result > 0;
    }
  //UPDATE
  	@Override
	public boolean Update(ADDto adDto) throws Exception{
  		// id, title, comuCode가 일치하면 수정
  		pstmt = conn.prepareStatement("update ad set cominfo=?, salary=?, walfare=?, work=? where adnum=?");
  		pstmt.setString(1, adDto.getCominfo());
  		pstmt.setString(2, adDto.getSalary());
  		pstmt.setString(3, adDto.getWalfare());
  		pstmt.setString(4, adDto.getWork());
  		pstmt.setInt(5, adDto.getADNum());

  		int result = pstmt.executeUpdate();
  		
  		freeConnection(pstmt);
  		return result>0;
  	}
  	//DELETE
  	@Override
	public boolean Delete(ADDto adDto) throws Exception{
  		// id, title, comuCode가 일치하면 삭제
  		pstmt = conn.prepareStatement("delete from ad where adnum=?");
  		pstmt.setInt(1, adDto.getADNum());
  		int result = pstmt.executeUpdate();
  		
  		freeConnection(pstmt);
  		return result>0;
  	}
    
    // SELECTALL 메서드: 모든 광고 정보를 조회하는 메서드
    @Override
	public List<ADDto> SelectAll() throws Exception {
        pstmt = conn.prepareStatement("select * from ad");
        rs = pstmt.executeQuery();
        List<ADDto> list = new ArrayList<>();
        ADDto dto = null;
        if (rs != null) {
            while (rs.next()) {
                dto = new ADDto();
                dto.setADNum(rs.getInt("adnum"));
                dto.setCominfo(rs.getString("cominfo"));
                dto.setSalary(rs.getString("salary"));
                dto.setWalfare(rs.getString("walfare"));
                dto.setWork(rs.getString("work"));
                list.add(dto);
            }
        }
        
        freeConnection(pstmt, rs);
        return list;
    }

    // SELECTONE 메서드: 특정 광고 정보를 조회하는 메서드
    @Override
	public ADDto Select(String cominfo) throws Exception {
        pstmt = conn.prepareStatement("select * from ad where cominfo=?");
        pstmt.setString(1, cominfo);
        rs = pstmt.executeQuery();

        ADDto dto = null;
        if (rs != null) {
            rs.next();
            dto = new ADDto();
            dto.setADNum(rs.getInt("adnum"));
            dto.setCominfo(rs.getString("cominfo"));
            dto.setSalary(rs.getString("salary"));
            dto.setWalfare(rs.getString("walfare"));
            dto.setWork(rs.getString("work"));
        }
        
        freeConnection(pstmt, rs);
        return dto;
    }
}
