package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import spms.dto.MemberDto;

public class MemberDao {

	private Connection connection;

	//Dao는 setConnection이 반드시 필요(로직이 실행되므로)
	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	// 회원전체 조회
	public List<MemberDto> selectList() throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT MNO, MNAME, EMAIL, CRE_DATE" 
						+ " FROM MEMBERS" 
						+ " ORDER BY MNO ASC";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			ArrayList<MemberDto> memberList = new ArrayList<>();

			int no = 0;
			String name = "";
			String email = "";
			Date creDate = null;

			while (rs.next()) {
				no = rs.getInt("MNO");
				name = rs.getString("MNAME");
				email = rs.getString("EMAIL");
				creDate = rs.getDate("CRE_DATE");

				MemberDto memberDto = new MemberDto(no, name, email, creDate);

				memberList.add(memberDto);
			}

			return memberList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}	// selectList() End
	
	// 회원등록
	public int memberInsert(MemberDto memberDto) throws Exception{	
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String email = memberDto.getEmail();
			String pwd = memberDto.getPassword();
			String name = memberDto.getName();
			
			String sql = "";
			
			sql += "INSERT INTO MEMBERS";
			sql += " VALUE(MNO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)";
			sql += " VALUES(MEMBERS_MNO_SEQ.NEXTVAL, ?, ?";
			sql += ", ?, SYSDATE, SYSDATE)";
			
			pstmt = connection.prepareStatement(sql);
			System.out.println("3번");
			
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);						
			
			System.out.println("4번 쿼리가 실행됬니?");
			result = pstmt.executeUpdate();		
			
		} catch (Exception e) {
			throw e;
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}	// finally 종료

		return result;
	}
	
	public MemberDto selectOne(int no) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		MemberDto memberDto = null;
		
		try {
			String email = "";
			String name = "";
			Date creDate = null;
			
			System.out.println("no의 값: " + no);
			String sql = "SELECT MNO, MNAME, EMAIL, CRE_DATE" 
						+ " FROM MEMBERS" 
						+ " WHERE MNO = ?";

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				no = rs.getInt("MNO");
				name = rs.getString("MNAME");
				email = rs.getString("EMAIL");
				creDate = rs.getDate("CRE_DATE");
				
				memberDto = new MemberDto(no, name, email, creDate);
			}
			
			return memberDto;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}		
		
	}
	
}