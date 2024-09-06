package emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectEx2 {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; // select 구문시에만 사용

        try {
            // 1. 드라이버 로드
            Class.forName("oracle.jdbc.OracleDriver");
            // 2. 연결 문자열 작성
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String user = "scott";
            String password = "TIGER";

            // 3. conneection 얻기
            con = DriverManager.getConnection(url, user, password);

            // 4. connection 을 통해서 실행하고자 하는 sql 구문 전송
            String sql = "select * from emp";
            pstmt = con.prepareStatement(sql);

            // 5. 전송된 sql 구문 실행
            rs = pstmt.executeQuery();

            System.out.println("==================================================================");
            System.out.println("empno   ename   job     mgr     hiredate    sal     comm    deptno");
            System.out.println("==================================================================");

            // 6. 결과값이 담기 rs(행을 기준으로 담김)에서 하나씩 조회
            while (rs.next()) {
                // number => getInt() / varchar2 => getString() / date => getDate()
                // () 안에는 컬럼 번호 or 컬럼명 가능 (컬럼명을 선호)
                System.out.print(rs.getInt(1) + "\t");
                System.out.print(rs.getString(2) + "\t");
                System.out.print(rs.getString(3) + "\t");
                System.out.print(rs.getInt(4) + "\t");
                System.out.print(rs.getDate(5) + "\t");
                System.out.print(rs.getInt(6) + "\t");
                System.out.print(rs.getInt(7) + "\t");
                System.out.println(+rs.getInt(8));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
