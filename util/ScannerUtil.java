package util;
// 우리가 입력을 처리할 때,
// 도움이 되는 메소드를 모아둔 클래스
import java.util.Scanner;
public class ScannerUtil {
    // 1. 입력 시 메세지의 출력방법을 담당하는
    // printMessage(String)
    private static void printMessage(String message) {
        System.out.println(message);
        System.out.print("> ");
    }
    
    // 2. 정수 입력에 사용할
    //    nextInt(Scanner, String)
    public static int nextInt(Scanner scanner, String message) {
        printMessage(message);
        
        int temp=scanner.nextInt();
        return temp;
    }
    // 3. 범위의 정수 입력에 사용할
    //    nextInt(Scanner, String, int, int)
    public static int nextInt(Scanner scanner, String message,final int MIN, final int MAX) {
        int temp=nextInt(scanner,message);
        
        while(temp<MIN||temp>MAX) {
            System.out.println("잘못 입력하셨습니다.");
            temp=nextInt(scanner,message);
        }
        
        return temp;
    }
    // 4. 스트링 입력에 사용할
    //    nextLine(Scanner, String)
    public static String nextLine(Scanner scanner, String message) {
        printMessage(message);
        
        String temp=scanner.nextLine();
        
        if(temp.isEmpty()) {
            temp=scanner.nextLine();
        }
        return temp;
    }
    // 5. 점수 출력에 사용할
    //    printInfo(int id, String name, int korean, int english, int math)
    public static void printInfo(int id, String name, int korean, int english, int math,final int SUBJECT_SIZE) {
        int sum=korean+english+math;
        double average=(double)sum/SUBJECT_SIZE;
        
        System.out.printf("번호: %d번 이름:%s\n",id,name);
        System.out.printf("국어: %03d점 영어: %03d점 수학: %03d점\n", korean, english, math);
        System.out.printf("총점: %03d점 평균:%06.2f점\n",sum,average);
                
    }
    // 6. 점수 입력에 사용할
    //    inputInfo(int[] id,String[] name, int[] korean, int[] english, int[] math)
    public static void inputInfo(Scanner scanner,int[] inputIdx,int[] idArray,String[] nameArray, int[] koreanArray, int[] englishArray, int[] mathArray,final int STUDENT_SIZE,final int SCORE_MIN,final int SCORE_MAX) {
        
        
        if (inputIdx[0] < STUDENT_SIZE) {
            idArray[inputIdx[0]] = ScannerUtil.nextInt(scanner, "번호를 입력하세요");
            nameArray[inputIdx[0]] = ScannerUtil.nextLine(scanner, "이름을 입력하세요");
            koreanArray[inputIdx[0]] = ScannerUtil.nextInt(scanner, "국어 점수를 입력하세요", SCORE_MIN, SCORE_MAX);
            englishArray[inputIdx[0]] = ScannerUtil.nextInt(scanner, "영어 점수를 입력하세요", SCORE_MIN, SCORE_MAX);
            mathArray[inputIdx[0]] = ScannerUtil.nextInt(scanner, "수학 점수를 입력하세요", SCORE_MIN, SCORE_MAX);
            inputIdx[0]++;
        } else {
            System.out.println("더이상 입력하실수 없습니다.");
        }
    }
    
    
}
