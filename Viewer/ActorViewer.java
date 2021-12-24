package Viewer;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.ActorController;
import Model.ActorDTO;
import Model.UserDTO;
import util.ScannerUtil;
import util.ArrayUtil;

public class ActorViewer {
    private ArrayList<ActorDTO> actorListAddress;
    private ArrayList<UserDTO> userListAddress;
    private int nextUserId;
    private int nextActorId;
    private UserViewer userViewer;
    private ActorController actorController;
    private Scanner scanner;
    private UserDTO logIn;
    private int[] printId;

    public ActorViewer() {
        actorController = new ActorController();
        scanner = new Scanner(System.in);
        logIn = null;
        actorListAddress = actorController.actorAddress();
        userListAddress = actorController.userAddress();
        nextUserId = actorController.nextUserIdAddress();
        nextActorId = actorController.nextActorIdAddress();
        userViewer = new UserViewer(actorListAddress, userListAddress, nextUserId, nextActorId);
    }

    public void showIndex() {
        String message;
        while (true) {
            printId = new int[0];
            if (logIn == null) {
                message = new String("1. 전체목록출력 2. 검색후출력 3. 로그인 4. 종료");
            } else {
                message = new String("1. 전체목록출력 2. 검색후출력 3. 회원정보보기 4. 종료");
            }
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 4);
            if (userChoice == 1) {
                printId = actorController.search();
                printPage();
            } else if (userChoice == 2) {
                searchPage();
            } else if (userChoice == 3) {
                if (logIn != null) {
                    logIn = userViewer.userInfo(logIn);
                } else {
                    logIn = userViewer.logIn(logIn);
                }
            } else if (userChoice == 4) {
                System.out.println("사용해주셔서 감사합니다.\n");
                scanner.close();
                break;
            }

        }

    }

    private void printPage() {
        while (true) {

            System.out.println("============================================");
            if (ArrayUtil.isEmpty(printId)) {
                System.out.println("입력된 영화배우 정보가 없습니다.\n");
            } else {

                for (int i = 0; i < ArrayUtil.size(printId); i++) {
                    ActorDTO a = actorController.print(printId[i]);
                    System.out.printf("배우이름: %s\t%d년도데뷔 고유등록번호:%d\n", a.getName(), a.getYear(), a.getId());
                }
            }
            System.out.println("============================================");
            String message = new String("1. 입력 2. 상세보기 3. 뒤로가기");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if (userChoice == 1) {
                input();
            } else if (userChoice == 2) {
                message = new String("상세히 보실 영화배우의 고유등록번호를 입력해주세요.\n(돌아가시려면 0을 입력해주세요)");
                userChoice = ScannerUtil.nextInt(scanner, message);
                if (userChoice != 0) {
                    printDetail(userChoice);
                }
            } else if (userChoice == 3) {
                break;
            }
        }
    }

    private void searchPage() {
        while (true) {
            printId = new int[0];
            String message = new String("1. 이름검색 2. 데뷔년도검색 3. 뒤로가기");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if (userChoice == 1) {
                message = new String("검색하실 이름을 입력해주세요");
                String name = ScannerUtil.nextLine(scanner, message);
                printId = actorController.search(name);
                printPage();
            } else if (userChoice == 2) {
                message = new String("검색하실 년도를 입력해주세요.");
                int year = ScannerUtil.nextInt(scanner, message);
                printId = actorController.search(year);
                printPage();
            } else if (userChoice == 3) {
                break;
            }
        }
    }

    private void input() {
        if (logIn == null) {
            System.out.println("로그인 후 사용가능합니다.\n");
        } else {
            ActorDTO a = new ActorDTO();
            a.setWriter(logIn.getId());
            String message = new String("배우 이름을 입력해주세요.");
            a.setName(ScannerUtil.nextLine(scanner, message));
            message = new String("배우가 태어난 년도를 입력해주세요.");
            a.setBirthdayY(ScannerUtil.nextInt(scanner, message, 1, 9999));
            message = new String("배우가 태어난 월을 입력해주세요.");
            a.setBirthdayM(ScannerUtil.nextInt(scanner, message));
            message = new String("배우가 태어난 일을 입력해주세요.");
            switch (a.getBirthdayM()) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                a.setBirthdayD(ScannerUtil.nextInt(scanner, message, 1, 31));
                break;
            case 2:
                a.setBirthdayD(ScannerUtil.nextInt(scanner, message, 1, 28));
                break;
            default:
                a.setBirthdayD(ScannerUtil.nextInt(scanner, message, 1, 30));
                break;
            }
            message = new String("배우의 데뷔 년도를 입력해주세요.");
            a.setYear(ScannerUtil.nextInt(scanner, message, a.getBirthdayY(), 9999));
            message = new String("배우의 데뷔작품을 입력해주세요.");
            a.setFirstMov(ScannerUtil.nextLine(scanner, message));
            message = new String("배우의 가족관계를 입력해주세요.");
            a.setFamily(ScannerUtil.nextLine(scanner, message));
            if (actorController.same(a)) {
                System.out.println("이미 입력하려는 배우가 존재합니다.\n");
            } else {

                actorController.insert(a);
                printId = ArrayUtil.add(printId, actorController.search(a));

            }
        }
    }

    private void printDetail(int id) {
        if (actorController.print(id) == null) {
            System.out.println("등록번호와 일치하는 배우가 없습니다.\n");
        } else {
            ActorDTO a = actorController.print(id);
            System.out.printf("고유등록번호: %d [정보작성자 고유ID: %s]\n", a.getId(), a.getWriter());
            System.out.println("배우이름: " + a.getName());
            System.out.printf("배우생년월일: %d년 %d월 %d일     \n데뷔년도: %d\n", a.getBirthdayY(), a.getBirthdayM(),
                    a.getBirthdayD(), a.getYear());
            System.out.println("데뷔작: " + a.getFirstMov());
            System.out.println("가족관계: " + a.getFamily());

            String message = new String("1. 수정 2. 삭제 3. 뒤로가기");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if (userChoice == 1) {
                update(id);
            } else if (userChoice == 2) {
                delete(id);
            } else if (userChoice == 3) {

            }
        }

    }

    private void update(int id) {
        if (logIn == null) {
            System.out.println("로그인 후 사용가능합니다.\n");
        } else {

            ActorDTO a = actorController.print(id);
            if (a.getWriter() == logIn.getId()) {
                ActorDTO temp = new ActorDTO();
                temp.setWriter(a.getWriter());
                String message = new String("배우 이름을 입력해주세요.");
                temp.setName(ScannerUtil.nextLine(scanner, message));
                message = new String("배우가 태어난 년도를 입력해주세요.");
                temp.setBirthdayY(ScannerUtil.nextInt(scanner, message, 1, 9999));
                message = new String("배우가 태어난 월을 입력해주세요.");
                temp.setBirthdayM(ScannerUtil.nextInt(scanner, message, 1, 12));
                message = new String("배우가 태어난 일을 입력해주세요.");
                switch (temp.getBirthdayM()) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    temp.setBirthdayD(ScannerUtil.nextInt(scanner, message, 1, 31));
                    break;
                case 2:
                    temp.setBirthdayD(ScannerUtil.nextInt(scanner, message, 1, 28));
                    break;
                default:
                    temp.setBirthdayD(ScannerUtil.nextInt(scanner, message, 1, 30));
                    break;
                }
                message = new String("배우의 데뷔 년도를 입력해주세요.");
                temp.setYear(ScannerUtil.nextInt(scanner, message, temp.getBirthdayY(), 9999));
                message = new String("배우의 데뷔작품을 입력해주세요.");
                temp.setFirstMov(ScannerUtil.nextLine(scanner, message));
                message = new String("배우의 가족관계를 입력해주세요.");
                temp.setFamily(ScannerUtil.nextLine(scanner, message));
                if (actorController.same(a)) {
                    System.out.println("이미 입력하려는 배우가 존재합니다.\n");
                } else {
                    a = temp;
                    a.setId(id);
                    actorController.update(a);
                }
            } else {
                System.out.println("!!!글 작성자만 수정할 수 있습니다.!!!\n");
            }
        }
        printDetail(id);
    }

    private void delete(int id) {
        if (logIn == null) {
            System.out.println("로그인 후 사용가능합니다.\n");
            printDetail(id);
        } else {
            ActorDTO a = actorController.print(id);
            if (a.getWriter() == logIn.getId()) {
                String message = new String("정말 삭제하시겠습니까? Y/N");
                String yesNo = ScannerUtil.nextLine(scanner, message);
                if (yesNo.equalsIgnoreCase("y")) {
                    actorController.remove(id);
                    printId = ArrayUtil.removeByElement(printId, id);
                }
            } else {
                System.out.println("!!!글 작성자만 삭제할 수 있습니다.!!!\n");
                printDetail(id);
            }
        }
    }
}
