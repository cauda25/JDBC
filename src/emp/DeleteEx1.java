package emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteEx1 {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pstmt = null;
        Scanner sc = new Scanner(System.in);

        try {
            // 1. 드라이버 로드
            Class.forName("oracle.jdbc.OracleDriver");

            // 2. 연결 문자열 작성
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String user = "scott";
            String password = "TIGER";

            // 3. conneection 얻기
            con = DriverManager.getConnection(url, user, password);

            System.out.println("삭제할 사번 입력 >> ");
            int empno = sc.nextInt();

            // 4. delete 작업
            // 4-1. delete sql 구문 작성
            String sql = "delete from emp_temp where empno=?";

            // 4-2. sql 구문 전송
            pstmt = con.prepareStatement(sql);
            // ? 해결
            pstmt.setInt(1, empno);

            // 4-3. sql 구문 실행 후 결과 받기
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("삭제 성공");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
