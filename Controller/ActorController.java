package Controller;

import java.util.ArrayList;
import util.ArrayUtil;

import Model.ActorDTO;
import Model.UserDTO;

public class ActorController {
//데이터베이스 인척 하는놈 설정
//    private Database database=new Database();
    private ArrayList<ActorDTO> actorList;
    private ArrayList<UserDTO> userList;
    private int nextActorId;
    private int nextUserId;

    public ActorController() {
        userList = new ArrayList<>();
        actorList = new ArrayList<>();
        nextUserId = 1;
        nextActorId = 1;

        // 진짜 정보 추가
        if (actorList.isEmpty()) {
            String[] name = { "류준열", "김고은", "강수연", "원빈", "강주은", "김래원", "강찬" };
            int[] birthdayY = { 1986, 1991, 1966, 1977, 2006, 1981, 1988 };
            int[] birthdayM = { 9, 7, 8, 11, 3, 3, 3 };
            int[] birthdayD = { 25, 2, 18, 10, 22, 19, 15 };
            String[] family = { "여동생 1명", "오빠 1명", "오빠2명, 여동생", "형 1명, 누나 3명", "모름", "여동생 1명", "모름" };
            String[] firstMov = { "영화 '소셜포비아'", "영화 '은교'", "영화 '핏줄'", "드라마 '프로포즈'", "드라마 '노숙자씨의 행방'", "드라마 '나'",
                    "뮤지컬 '화랑'" };
            int[] year = { 2015, 2012, 1966, 1997, 2012, 1997, 2012 };
            /*
             * for랑 a선언 및 초기화 자리바꾸면 강찬만 7번 들어감...읭
             */
            for (int i = 0; i < ArrayUtil.size(name); i++) {
                ActorDTO a = new ActorDTO();

                a.setId(nextActorId);
                a.setName(name[i]);
                a.setWriter(0);
                a.setBirthdayY(birthdayY[i]);
                a.setBirthdayM(birthdayM[i]);
                a.setBirthdayD(birthdayD[i]);
                a.setFamily(family[i]);
                a.setFirstMov(firstMov[i]);
                a.setYear(year[i]);

                insert(a);

            }
        }
    }

    public ArrayList<ActorDTO> actorAddress() {
        ArrayList<ActorDTO> temp = actorList;
        return temp;
    }

    public ArrayList<UserDTO> userAddress() {
        ArrayList<UserDTO> temp = userList;
        return temp;
    }

    public int nextUserIdAddress() {
        int temp = nextUserId;
        return temp;
    }

    public int nextActorIdAddress() {
        int temp = nextActorId;
        return temp;
    }

    // 정보받아서 데이터베이스에 추가 메소드
    public void insert(ActorDTO a) {
        a.setId(nextActorId++);

        actorList.add(a);
    }

    // 중복확인
    public boolean same(ActorDTO a) {
        if (actorList.contains(a)) {

            return true;
        }
        return false;
    }

    // id맞는거 출력
    public ActorDTO print(int id) {
        ActorDTO a = new ActorDTO();
        a.setId(id);
        if (actorList.contains(a)) {
            return actorList.get(actorList.indexOf(a));
        }
        return null;
    }

    // 맞는거 id출력
    public int search(ActorDTO a) {

        return a.getId();
    }

    // 이름들어오면 걔네 id들 배열로 출력
    public int[] search(String name) {
        int[] index = new int[0];
        for (int i = 0; i < actorList.size(); i++) {
            if (actorList.get(i).getName().equals(name)) {
                index = ArrayUtil.add(index, actorList.get(i).getId());
            }
        }
        return index;
    }

    // 데뷔년도들어오면 걔네 id들 배열로 출력
    public int[] search(int year) {
        int[] index = new int[0];
        for (int i = 0; i < actorList.size(); i++) {
            if (actorList.get(i).getYear() == year) {
                index = ArrayUtil.add(index, actorList.get(i).getId());
            }
        }
        return index;
    }

    // 가지고있는 아이디 다넘겨줘~~
    public int[] search() {
        int[] index = new int[0];
        for (int i = 0; i < actorList.size(); i++) {
            index = ArrayUtil.add(index, actorList.get(i).getId());
        }
        return index;
    }

    // 수정해야지... 클래스변수 바꿔치기~
    public void update(ActorDTO a) {
        actorList.set(actorList.indexOf(a), a);
    }

    public void remove(int id) {
        ActorDTO a = new ActorDTO();
        a.setId(id);

        actorList.remove(a);
    }

}
