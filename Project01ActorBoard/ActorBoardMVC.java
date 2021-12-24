package Project01ActorBoard;

import Viewer.ActorViewer;

public class ActorBoardMVC {

    public static void main(String[] args) {
        System.out.println("영화배우 정보 관리 프로그램");
        ActorViewer actorViewer = new ActorViewer();
        actorViewer.showIndex();
    }
}
