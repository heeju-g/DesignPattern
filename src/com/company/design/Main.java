package com.company.design;

import com.company.design.adapter.*;
import com.company.design.singleton.AClazz;
import com.company.design.singleton.BClazz;
import com.company.design.singleton.SocketClient;

public class Main {

    public static void main(String[] args) {
        /* 싱글톤
        AClazz aClazz = new AClazz();
        BClazz bClazz = new BClazz();

        SocketClient aClient = aClazz.getSocketClient();
        SocketClient bClient = bClazz.getSocketClient();

        System.out.println("두 개의 객체가 동일한가?");
        System.out.println(aClient.equals(bClient));
        ---------------------------------------------------------------- */
        /* 어댑터
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);
        //220v짜리 청소기를 110v에 어떻게 꽂을 것인가 -> 어댑터 사용
        Cleaner cleaner = new Cleaner();
        Electronic110v adapter = new SocketAdapter(cleaner);
        connect(adapter);

        AirConditioner airConditioner = new AirConditioner();
        Electronic110v airAdapter = new SocketAdapter(airConditioner);
        connect(airAdapter);
        -------------------------------------------------------------------*/

    }
    //콘센트
    public static void connect(Electronic110v electronic110v){
        electronic110v.powerOn();
    }
}
