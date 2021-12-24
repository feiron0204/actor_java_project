package Viewer;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.UserController;
import Model.ActorDTO;
import Model.UserDTO;
import util.ScannerUtil;

public class UserViewer {
    private UserController userController;
    private Scanner scanner;

    public UserViewer(ArrayList<ActorDTO> actorListAddress, ArrayList<UserDTO> userListAddress, int nextUserId,
            int nextActorId) {
        userController = new UserController(actorListAddress, userListAddress, nextUserId, nextActorId);
        scanner = new Scanner(System.in);
    }

    public UserDTO logIn(UserDTO logIn) {
        if (logIn == null) {

            while (true) {
                String message = new String("1. 로그인 2. 회원가입 3. 뒤로가기");
                int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
                if (userChoice == 1) {
                    message = new String("아이디를 입력해주세요.");
                    String username = ScannerUtil.nextLine(scanner, message);
                    message = new String("비밀번호를 입력해주세요.");
                    String password = ScannerUtil.nextLine(scanner, message);
                    logIn = userController.auth(username, password);
                    if (logIn == null) {
                        System.out.println("등록되지 않은 사용자입니다.\n");
                    } else {
                        return logIn;
                    }
                } else if (userChoice == 2) {
                    joinPage();

                } else if (userChoice == 3) {

                    return logIn;
                }

            }
        } else {
            return logIn;
        }
    }

    public void joinPage() {
        UserDTO u = new UserDTO();
        String message = new String("원하시는 아이디를 입력해주세요.");
        while (true) {
            String username = ScannerUtil.nextLine(scanner, message);
            u.setUsername(username);
            if (!userController.sameUsername(username)) {
                break;
            }
            System.out.println("이미 사용중인 아이디입니다.\n");
        }

        while (true) {
            message = new String("원하시는 비밀번호를 입력해주세요.");
            String pw1 = ScannerUtil.nextLine(scanner, message);

            message = new String("비밀번호를 한번 더 입력해주세요.");
            String pw2 = ScannerUtil.nextLine(scanner, message);

            if (pw1.equals(pw2)) {
                u.setPassword(pw2);
                break;
            }
            System.out.println("입력한 비밀번호가 서로 다릅니다.\n");
        }

        message = new String("원하시는 닉네임을 설정해주세요.");
        while (true) {
            String nickname = ScannerUtil.nextLine(scanner, message);
            u.setNickname(nickname);
            if (!userController.sameNickname(nickname)) {
                break;
            }
            System.out.println("이미 사용중인 닉네임입니다.\n");
        }
        userController.join(u);

    }

    public UserDTO userInfo(UserDTO logIn) {
        while (logIn != null) {

            String pwStar = "";
            for (int i = 0; i < logIn.getPassword().length(); i++) {
                pwStar += "*";
            }

            System.out.printf("회원고유ID: %d 아이디: %s 비밀번호: %s 닉네임: %s\n", logIn.getId(), logIn.getUsername(), pwStar,
                    logIn.getNickname());
            String message = new String("1. 회원정보 수정 2. 회원정보 삭제 3. 로그아웃 4. 뒤로가기");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 4);
            if (userChoice == 1) {
                update(logIn);
            } else if (userChoice == 2) {
                logIn = delete(logIn);
            } else if (userChoice == 3) {
                System.out.println("로그아웃되였습니다.\n");
                logIn = null;
            } else if (userChoice == 4) {
                System.out.println();
                break;
            }
        }
        return logIn;
    }

    private void update(UserDTO u) {
        if (u.getId() != 0) {

            String message = new String("비밀번호를 재입력해주세요.");
            String password = ScannerUtil.nextLine(scanner, message);
            if (u.getPassword().equals(password)) {
                while (true) {
                    message = new String("원하시는 비밀번호를 입력해주세요.");
                    String pw1 = ScannerUtil.nextLine(scanner, message);

                    message = new String("비밀번호를 한번 더 입력해주세요.");
                    String pw2 = ScannerUtil.nextLine(scanner, message);

                    if (pw1.equals(pw2)) {
                        u.setPassword(pw2);
                        break;
                    }
                    System.out.println("입력한 비밀번호가 서로 다릅니다.\n");
                }

                message = new String("원하시는 닉네임을 설정해주세요.");
                while (true) {
                    String nickname = ScannerUtil.nextLine(scanner, message);
                    u.setNickname(nickname);
                    if (!userController.sameNickname(nickname)) {
                        break;
                    }
                    System.out.println("이미 사용중인 닉네임입니다.\n");
                }
                userController.update(u);
            } else {
                System.out.println("비밀번호가 맞지 않습니다.\n");
            }
        } else {
            System.out.println("admin 아이디는 수정하실수 없습니다.\n");
        }
    }

    private UserDTO delete(UserDTO u) {
        if (u.getId() != 0) {

            String message = new String("비밀번호를 재입력해주세요.");
            String password = ScannerUtil.nextLine(scanner, message);
            if (u.getPassword().equals(password)) {

                message = new String("정말로 삭제하시겠습니까? Y/N");
                String yesNo = ScannerUtil.nextLine(scanner, message);
                if (yesNo.equalsIgnoreCase("y")) {
                    userController.remove(u);
                    return null;
                }
                return u;
            } else {
                System.out.println("비밀번호가 맞지 않습니다.\n");
                return u;
            }
        } else {
            System.out.println("admin 아이디는 삭제하실수 없습니다.\n");
            return u;
        }
    }
}
