package com.company.design.adapter;

//110짜리를 220에 연결하기 위한 어댑터
//adapter : 서로 비슷하지만 인터페이스가 맞지 않을 떄 가운데 어댑터 클래스를 두어서 연결시켜준다.
public class SocketAdapter implements Electronic110v {

    private Electronic220v electronic220v;

    public SocketAdapter(Electronic220v electronic220v){
        this.electronic220v = electronic220v;
    }
    @Override
    public void powerOn() {
        electronic220v.connect();
    }
}
