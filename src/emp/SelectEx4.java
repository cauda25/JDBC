package emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SelectEx4 {
    public static void main(String[] args) {
        // 2. 연결 문자열 작성
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "scott";
        String password = "TIGER";

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 job 입력 : ");
        String job = sc.nextLine();
        System.out.print("조회할 empno 입력 : ");
        int empno = sc.nextInt();

        // empno 동적 처리
        // String sql = "SELECT * FROM emp WHERE empno=" + empno;
        // String sql = "SELECT * FROM emp WHERE empno=?";
        String sql = "SELECT * FROM emp WHERE empno=? or job=?";
        // SelectEx == > try-with-resources 형태로 변경
        // fianlly 자원 닫기 자동으로 처리
        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            // 1. 드라이버 로드
            Class.forName("oracle.jdbc.OracleDriver");

            // ? 처리
            pstmt.setInt(1, empno);
            pstmt.setString(2, job);
            ResultSet rs = pstmt.executeQuery();

            // 6. 결과값이 담기 rs(행을 기준으로 담김)에서 하나씩 조회
            while (rs.next()) {
                // number => getInt() / varchar2 => getString() / date => getDate()
                // () 안에는 컬럼 번호 or 컬럼명 가능 (컬럼명을 선호)
                System.out.println("empno : " + rs.getInt(1));
                System.out.println("ename : " + rs.getString(2));
                System.out.println("job : " + rs.getString(3));
                System.out.println("mgr : " + rs.getInt(4));
                System.out.println("hiredate : " + rs.getDate(5));
                System.out.println("sal : " + rs.getInt(6));
                System.out.println("comm : " + rs.getInt(7));
                System.out.println("deptno : " + rs.getInt(8));
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
