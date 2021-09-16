package com.company.design.singleton;

public class AClazz {

    private SocketClient socketClient;
    //초기화
    public AClazz(){//디폴트 생성자를 막아놨기 때문에 this.socketClient = new SocketClient(); 로 할 수 없다.
       this.socketClient = SocketClient.getInstance();
       // this.socketClient = new SocketClient();
    }

    public SocketClient getSocketClient(){
        return this.socketClient;
    }
}
