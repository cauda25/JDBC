package emp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
    // CRUD 작업을 위한 메소드 작성
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // static 블럭 : 클래스 로드되면 인스턴스 생성여부와 상관없이 실행
    static {
        // 1. 드라이버 로드
        try {
            Class.forName("oracle.jdbc.Oracle.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 중복 코드 병합
    public Connection getConnection() throws SQLException {

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "scott";
        String password = "TIGER";

        // con = DriverManager.getConnection(url, user, password);
        // return con;
        return DriverManager.getConnection(url, user, password);
    }

    public void close(Connection con, PreparedStatement pstmt) {
        try {
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CRUD 메소드 작성
    public boolean insert(EmpDTO dto) {
        boolean flag = false;
        try {
            con = getConnection();
            String sql = "INSERT INTO EXAM_EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) ";
            sql += " VALUES(?,?,?,?,?,?,?,?)";

            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, dto.getEmpno());
            pstmt.setString(2, dto.getEname());
            pstmt.setString(3, dto.getJod());
            pstmt.setInt(4, dto.getMgr());
            pstmt.setString(5, dto.getHiredate());
            pstmt.setInt(6, dto.getSal());
            pstmt.setInt(7, dto.getComm());
            pstmt.setInt(8, dto.getDeptno());

            int result = pstmt.executeUpdate();

            if (result > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return flag;
    }

    // 특정 사원 조회
    public EmpDTO getEmp(int empno) {
        EmpDTO dto = null;
        try {
            con = getConnection();

            String sql = "SELECT * FROM EXAM_EMP WHERE empno=?";
            pstmt = con.prepareStatement(sql);
            // ? 해결
            pstmt.setInt(1, empno);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto = new EmpDTO();
                dto.setEmpno(rs.getInt("empno"));
                dto.setEname(rs.getString("ename"));
                dto.setJod(rs.getString("job"));
                dto.setMgr(rs.getInt("mgr"));
                dto.setHiredate(rs.getString("hiredate"));
                dto.setSal(rs.getInt("sal"));
                dto.setComm(rs.getInt("comm"));
                dto.setDeptno(rs.getInt("deptno"));
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return dto;
    }

    // 전체 사원 조회
    public List<EmpDTO> getList() {
        List<EmpDTO> list = new ArrayList<>();
        try {
            con = getConnection();

            String sql = "select * from EXAM_EMP";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // 레코드 => dto 로 생성 후 => list에 추가
                EmpDTO dto = new EmpDTO();
                dto.setEmpno(rs.getInt("empno"));
                dto.setEname(rs.getString("ename"));
                dto.setJod(rs.getString("job"));
                dto.setMgr(rs.getInt("mgr"));
                dto.setHiredate(rs.getString("hiredate"));
                dto.setSal(rs.getInt("sal"));
                dto.setComm(rs.getInt("comm"));
                dto.setDeptno(rs.getInt("deptno"));

                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return list;
    }

    // 특정 사원 정보 수정
    public boolean empUpdate(EmpDTO dto) {
        boolean flag = false;
        try {
            con = getConnection();

            String sql = "UPDATE EXAM_EMP SET COMM = ?, SAL = ? WHERE empno=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, dto.getComm());
            pstmt.setInt(2, dto.getSal());
            pstmt.setInt(3, dto.getEmpno());

            int result = pstmt.executeUpdate();
            if (result > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return flag;
    }

    // 특정 사원 정보 삭제
    public boolean empDelete(int empno) {
        boolean flag = false;
        try {
            con = getConnection();
            String sql = "delete from EXAM_EMP where empno=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, empno);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return flag;
    }
}
