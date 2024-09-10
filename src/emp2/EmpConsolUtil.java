package emp2;

import java.util.List;
import java.util.Scanner;

import oracle.net.aso.l;

public class EmpConsolUtil {

    public EmpDTO insertEmp(Scanner sc) {
        System.out.println("========== 데이터 입력 ==========");
        System.out.print("사번 : ");
        int empno = Integer.parseInt(sc.nextLine());
        System.out.print("이름 : ");
        String ename = sc.nextLine();
        System.out.print("직무 : ");
        String job = sc.nextLine();
        System.out.print("매니저 번호 : ");
        int mgr = Integer.parseInt(sc.nextLine());
        System.out.print("입사일 : ");
        String hiredate = sc.nextLine();
        System.out.print("급여 : ");
        int sal = Integer.parseInt(sc.nextLine());
        System.out.print("수당 : ");
        int comm = Integer.parseInt(sc.nextLine());
        System.out.print("부서번호 : ");
        int deptno = Integer.parseInt(sc.nextLine());

        return new EmpDTO(empno, ename, job, mgr, hiredate, sal, comm, deptno);
    }

    public int getEmpNo(Scanner sc) {
        // 사번 입력받은 후 리턴
        System.out.print("조회할 사번 입력 >> ");
        // int empno = sc.nextInt();
        // return empno;
        return Integer.parseInt(sc.nextLine());
    }

    public void printEmp(EmpDTO dto) {
        // dto 내용 출력
        // smith 사원
        System.out.println(dto.getEname() + " 사원");
        System.out.println("=====================");
        // ===============
        System.out.println("사번 : " + dto.getEmpno());// 사번 : 7788
        System.out.println("직무  : " + dto.getJod());// 직무 :
        System.out.println("입사일 : " + dto.getHiredate());// 입사일 :
        System.out.println("급여 : " + dto.getSal());// 급여 :
        System.out.println("수당 : " + dto.getComm());// 급여 :
        System.out.println("부서번호 : " + dto.getDeptno());// 부서번호 :
        System.out.println();

    }

    public void printListEmp(List<EmpDTO> list) {
        // list 에 들어있는 emp 출력
        System.out.println("==========================================================================");
        System.out.println("empno   ename       job     mgr     hiredate        sal     comm    deptno");
        System.out.println("==========================================================================");
        for (EmpDTO empDto : list) {
            System.out.printf("%d\t%s\tt%s\tt%d\t%s\t%d\t%d\t%d\n", empDto.getEmpno(), empDto.getEname(),
                    empDto.getJod(), empDto.getMgr(), empDto.getHiredate(), empDto.getSal(), empDto.getComm(),
                    empDto.getDeptno());
        }
    }

    public EmpDTO updateInfo(Scanner sc) {
        // 수정할 정보 입력받기 => 입력받은 EmpDTO 담은 후 리턴
        System.out.println("========== 사원정보 수정 ==========");
        System.out.print("사번 : ");
        int empno = Integer.parseInt(sc.nextLine());
        System.out.print("수당 : ");
        int comm = Integer.parseInt(sc.nextLine());
        System.out.print("급여 : ");
        int sal = Integer.parseInt(sc.nextLine());
        System.out.println();

        EmpDTO dto = new EmpDTO();
        dto.setEmpno(empno);
        dto.setComm(comm);
        dto.setSal(sal);
        return dto;
        // return new EmpDTO(comm,sal,empno);
    }

    public int deleteEmpNo(Scanner sc) {
        // 삭제할 empno 받은 후 리턴
        System.out.print("삭제할 사번 입력 >> ");
        return Integer.parseInt(sc.nextLine());
    }
}
