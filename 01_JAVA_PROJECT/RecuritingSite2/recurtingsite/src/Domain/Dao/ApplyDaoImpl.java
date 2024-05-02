package Domain.Dao;

import java.util.ArrayList;
import java.util.List;

import Domain.Dto.ApplyDto;


public class ApplyDaoImpl extends CommonDao implements ApplyDao{

	public ApplyDaoImpl() throws Exception {
		System.out.println("[DAO] ApplyDaoImpl's INIT..");
	}

	// INSERT
	@Override
	public boolean Insert(ApplyDto dto) throws Exception {
		pstmt = conn.prepareStatement("insert into apply values(?,?)");
		pstmt.setInt(1, dto.getApplyCode());
		pstmt.setBoolean(2, dto.isApplyClassify());
		int result = pstmt.executeUpdate();

		freeConnection(pstmt);
		return result > 0;

	}

	// UPDATE
	@Override
	public boolean Update(ApplyDto dto) throws Exception {
		pstmt = conn.prepareStatement("update apply set applyClassify = ? where applyCode = ?");
		pstmt.setBoolean(1, dto.isApplyClassify());
		pstmt.setInt(2, dto.getApplyCode());
		int result = pstmt.executeUpdate();

		freeConnection(pstmt);
		return result > 0;
	}

	// DELETE
	@Override
	public boolean Delete(ApplyDto dto) throws Exception {
		pstmt = conn.prepareStatement("delete from apply where applyCode = ?");
		pstmt.setInt(1, dto.getApplyCode());
		int result = pstmt.executeUpdate();

		freeConnection(pstmt);
		return result > 0;
	}

	// SELECTALL
	@Override
	public List<ApplyDto> SelectAll() throws Exception {
		pstmt = conn.prepareStatement("select * from apply");
		rs = pstmt.executeQuery();
		List<ApplyDto> list = new ArrayList();
		ApplyDto dto = null;
		if (rs != null) {
			while (rs.next()) {
				dto = new ApplyDto();
				dto.setApplyCode(rs.getInt("applyCode"));
				dto.setApplyClassify(rs.getBoolean("applyClassify"));
				dto.setApplyClassify(false);

				list.add(dto);

			}
		}
		
		freeConnection(pstmt, rs);
		return list;

	}

	// SELECTONE
	@Override
	public ApplyDto Select(int applyCode) throws Exception {
		pstmt = conn.prepareStatement("select * from apply where applyCode=?");
		pstmt.setInt(1, applyCode);
		rs = pstmt.executeQuery();

		ApplyDto dto = null;
		if (rs != null) {
			while (rs.next()) {
				dto = new ApplyDto();
				dto.setApplyCode(rs.getInt("applyCode"));
				dto.setApplyClassify(rs.getBoolean("applyClassify"));
			}
		}
		
		freeConnection(pstmt, rs);
		return dto;

	}
}
