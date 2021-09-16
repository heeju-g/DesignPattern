package com.company.design;

import com.company.design.adapter.*;
import com.company.design.aop.AopBrowser;
import com.company.design.decorator.*;
import com.company.design.observer.Button;
import com.company.design.observer.IButtonListner;
import com.company.design.proxy.Browser;
import com.company.design.proxy.BrowserProxy;
import com.company.design.proxy.IBrowser;
import com.company.design.singleton.AClazz;
import com.company.design.singleton.BClazz;
import com.company.design.singleton.SocketClient;

import java.util.concurrent.atomic.AtomicLong;

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
       /* 프록시
        Browser browser = new Browser("www.naver.com");
        browser.show();
        browser.show();


        처음에만 로딩이 들어가고 그 후부터는 캐시를 사용하여(구현제 자체는 건드리지 않음) 내려받았음을 확인할 수 있다.
        IBrowser browser = new BrowserProxy("www.naver.com");
        browser.show();
        browser.show();
        --------------------------------------------------------------------*/
        /* AOP : 프록시 패턴을 활용해서, 특정한 메소드/기능의 앞 뒤로 원하는 기능이나 아규먼트를 조작할 수 있다.
                 흩어져있는 공통된 기능을 하나로 묶을 수도 있다.
        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();
        IBrowser aopBrowser = new AopBrowser("www.naver.com",
                //람다식 사용
                ()->{
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                ()->{
                    long now = System.currentTimeMillis();
                    end.set(now - start.get());
                }
                );
        처음 호출 할 땐 로딩시간 걸리지만 그 후부터 캐시사용해서 로딩시간 없다.
        aopBrowser.show();
        System.out.println("loading time : "+end.get());
        aopBrowser.show();
        System.out.println("loading time : "+end.get());
        ---------------------------------------------------------------------*/
        /* 데코레이터 : 기존 뼈대(클래스)는 유지하되, 이후 필요한 형태로 꾸밀 때 사용.
        ICar audi = new Audi(1000);
        audi.showPrice();
        //a3(등급별 가격매기기)
        ICar audi3 = new A3(audi,"A3");
        audi3.showPrice();
        //a4
        ICar audi4 = new A4(audi,"A4");
        audi4.showPrice();
        //a5
        ICar audi5 = new A5(audi,"A5");
        audi5.showPrice();
        ---------------------------------------------------------------------*/
        /* Observer 관찰자 : 변화가 생겼을 때, 미리 등록된 다른 클래스에 통보해주는 패턴. event listner
        Button button = new Button("버튼");
        button.addListner(new IButtonListner() {
            @Override
            public void clickEvent(String event) {
                System.out.println(event);
            }
        });
        button.click("메세지 전달 : click1");
        button.click("메세지 전달 : click2");
        button.click("메세지 전달 : click3");
        ------------------------------------------------------------------- */

    }
    //콘센트
    public static void connect(Electronic110v electronic110v){
        electronic110v.powerOn();
    }
}
