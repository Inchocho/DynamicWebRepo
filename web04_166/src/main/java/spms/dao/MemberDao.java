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

	// Dao는 setConnection이 반드시 필요(로직이 실행되므로)
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
	} // selectList() End

	// 회원등록
	public int memberInsert(MemberDto memberDto) throws Exception {
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
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} // finally 종료

		return result;
	}

	public MemberDto selectOne(int no) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberDto memberDto = null;

		try {
			String email = "";
			String name = "";
			Date creDate = null;

			String sql = "";

			System.out.println("no의 값: " + no);
			sql = "SELECT MNO, MNAME, EMAIL, CRE_DATE" + " FROM MEMBERS" + " WHERE MNO = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				no = rs.getInt("MNO");
				name = rs.getString("MNAME");
				email = rs.getString("EMAIL");
				creDate = rs.getDate("CRE_DATE");

				memberDto = new MemberDto(no, name, email, creDate);
			} else {
				// readOnly 풀고 번호바꾸면 체크가능한 예외처리 에러
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();

			throw e; // 외부 에러페이지로 전달(위임) 그래서 updateServlet에 작성한 에러가 잡힌것
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
		return memberDto;

	}

	public void updateOne(String email, String name, int no) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "";

			sql += "UPDATE MEMBERS ";
			sql += "SET EMAIL = ?, MNAME = ?, MOD_DATE = SYSDATE ";
			sql += "WHERE MNO = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, name);
			pstmt.setInt(3, no);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

			throw e; // 외부 에러페이지로 전달(위임) 그래서 updateServlet에 작성한 에러가 잡힌것
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
	
	//updateOne 메소드 파라미터값을 MemberDto타입으로 받음
	public int updateOne(MemberDto memberDto) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int result = 0;
		try {

			String sql = "";

			sql += "UPDATE MEMBERS ";
			sql += "SET EMAIL = ?, MNAME = ?, MOD_DATE = SYSDATE ";
			sql += "WHERE MNO = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, memberDto.getEmail());
			pstmt.setString(2, memberDto.getName());
			pstmt.setInt(3, memberDto.getNo());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

			throw e; // 외부 에러페이지로 전달(위임) 그래서 updateServlet에 작성한 에러가 잡힌것
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
		
		return result;

	}	

	public void deleteOne(int no) throws Exception {
		PreparedStatement pstmt = null;

		try {
			String sql = "";

			sql = "DELETE FROM MEMBERS";
			sql += " WHERE MNO = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, no);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

			throw e; // 외부 에러페이지로 전달(위임) 그래서 updateServlet에 작성한 에러가 잡힌것
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public MemberDto loginChk(String email, String pwd) throws Exception {

		PreparedStatement pstmt = null;
		MemberDto memberDto = null;
		ResultSet rs = null;
		int colIndex = 1;

		String sql = "";

		try {

			String name = "";
			sql = "SELECT *";
			sql += " FROM MEMBERS";
			sql += " WHERE EMAIL = ?";
			sql += " AND PWD = ?";

			System.out.println("로그인 과정 잘타는지 확인용");
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(colIndex++, email);
			pstmt.setString(colIndex, pwd);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				email = rs.getString("email");
				name = rs.getString("mname");

				memberDto = new MemberDto();

				memberDto.setEmail(email);
				memberDto.setName(name);

			} else {
				throw new Exception("이메일 또는 비밀번호를 확인해주세요.");
			}

		} catch (Exception e) {
			e.printStackTrace();

			throw e; // 외부 에러페이지로 전달(위임) 그래서 updateServlet에 작성한 에러가 잡힌것
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

		return memberDto;
	}
	
	//로그인 로직(멤버존재여부) - 강사님이랑함, 기존 loginChk라는 메소드 존재(내가만듬)
	public MemberDto memberExist(String email, String pwd) throws Exception{
		MemberDto memberDto = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		int colIndex = 1;

		try {
			sql = "SELECT *";
			sql += " FROM MEMBERS";
			sql += " WHERE EMAIL = ?";
			sql += " AND PWD = ?";
			
			String name = "";
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(colIndex++, email);
			pstmt.setString(colIndex, pwd);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				email = rs.getString("email");
				name = rs.getString("mname");

				memberDto = new MemberDto();

				memberDto.setEmail(email);
				memberDto.setName(name);
				
				//온전한 정보가 있으면 memberDto를 반환한다
				return memberDto;
			}
			
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally {
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
		// 회원 조회 안된다면 즉 온전한 정보가 없으면 null 리턴
		return null;
	}
	
}