package emp2;

import java.util.Scanner;

public class EmpMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean run = true;

        EmpConsolUtil util = new EmpConsolUtil();
        EmpService service = new EmpService(new EmpDAO());

        while (run) {
            System.out.println("=====================");
            System.out.println("1. 사원 입력");
            System.out.println("2. 특정 사원 조회");
            System.out.println("3. 전체 사원 조회");
            System.out.println("4. 사원 정보 수정");
            System.out.println("5. 사원 정보 삭제");
            System.out.println("6. 프로그램 종료");
            System.out.println("=====================");
            System.out.println();
            System.out.print("메뉴 번호 입력 >> ");
            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
                case 1:
                    EmpDTO dto = util.insertEmp(sc);
                    boolean result = service.addEmp(dto);
                    System.out.println(result ? "입력성공" : "입력실패");
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    run = false;
                    break;
                default:
                    System.out.println("메뉴 번호를 확인해 주세요");
                    break;
            }
        }
    }
}
