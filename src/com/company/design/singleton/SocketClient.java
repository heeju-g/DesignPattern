package com.company.design.singleton;

//디자인 패턴 - 싱글톤 패턴. 객체가 한 개만 존재하도록.
public class SocketClient {
    //Singleton은 자기 자신을 객체로 가지고 있어야 한다.
    private static SocketClient socketClient = null;
    /*
     기본생성자를 private으로 막아야 한다.(자신을 통해서만 생성할 수 있도록)
     public으로 두고 다른 클래스에서 = new (); 이렇게 생성하면 메인에서 실행 시
     서로 다른 객체가 생겨서(각 클래스에서 직접 소켓객체를 만들기 때문에) false임을 확인할 수 있다.
     */
    private SocketClient(){

    }
    //static형태로 getInstance 메소드를 제공해야 한다. static 이기 때문에 외부 어떤 클래스에서도 접근이 가능하다.
    public static SocketClient getInstance(){
        //자신의 객체가 null이면 신규 생성해서 리턴하고, 아닌 경우는 기존 객체를 리턴.
        if(socketClient == null){
           socketClient = new SocketClient();
        }
        return socketClient;
    }

    public void connect(){
        System.out.println("connect");
    }

}
