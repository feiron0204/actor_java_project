package Controller;

import java.util.ArrayList;

import Model.ActorDTO;
import Model.UserDTO;

public class UserController {

    // 데이터베이스인척하는놈 설정
    private ArrayList<UserDTO> userList;
    private int nextUserId;

    public UserController(ArrayList<ActorDTO> actorListAddress, ArrayList<UserDTO> userListAddress, int nextUserId,
            int nextActorId) {
        userList = userListAddress;
        this.nextUserId = nextUserId;

        // 관리자계정 미리추가
        // 아이디 admin 비번 admin
        if (userList.isEmpty()) {
            UserDTO u = new UserDTO();

            u.setNickname("관리자");
            u.setUsername("admin");
            u.setPassword("admin");
            u.setId(0);
            userList.add(u);
        }
    }

    // 회원가입을 하시겟다구요? 넹
    public void join(UserDTO u) {
        u.setId(nextUserId++);
        userList.add(u);
    }

    // 유저네임 비번 받아 맞는 유저정보 출력
    public UserDTO auth(String username, String password) {
        for (UserDTO u : userList) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
                return new UserDTO(u);
            }
        }
        return null;
    }

    // 동일아이디가 있나요?
    public boolean sameUsername(String username) {
        for (UserDTO u : userList) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // 동일닉네임은요?
    public boolean sameNickname(String nickname) {
        for (UserDTO u : userList) {
            if (u.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    // 수정을 원하셧네요
    public void update(UserDTO u) {
        userList.set(userList.indexOf(u), u);
    }

    // 삭제하시게요?
    public void remove(UserDTO u) {

        userList.remove(u);
    }
}
