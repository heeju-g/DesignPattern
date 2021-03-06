package com.company.design;

import com.company.design.adapter.*;
import com.company.design.aop.AopBrowser;
import com.company.design.decorator.*;
import com.company.design.facade.FTP;
import com.company.design.facade.Reader;
import com.company.design.facade.SftpClient;
import com.company.design.facade.Writer;
import com.company.design.observer.Button;
import com.company.design.observer.IButtonListner;
import com.company.design.proxy.Browser;
import com.company.design.proxy.BrowserProxy;
import com.company.design.proxy.IBrowser;
import com.company.design.singleton.AClazz;
import com.company.design.singleton.BClazz;
import com.company.design.singleton.SocketClient;
import com.company.design.strategy.*;

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
        /* Facade : 건물의 앞쪽 정면이란 뜻. 여러 개의 객체와 실제 사용하는 서브 객체 사이에 복잡한 의존관계가 있을 때,
                    중간에 facade라는 객체를 두고, 여기서 제공하는 interface만을 활용하여 기능을 사용하는 방식.
        facade를 사용하지 않을 경우 아래처럼 하나하나 연결해주고 끊어주고 해야 한다.
        FTP ftpClient = new FTP("www.foo.co.kr",22,"/home/etc");
        ftpClient.connect();
        ftpClient.moveDirectory();
        Writer writer = new Writer("text.tmp");
        writer.fileConnect();
        writer.write();
        Reader reader = new Reader("text.tmp");
        reader.fileConnect();
        reader.fileRead();

        reader.fileDisconnect();
        writer.fileDisconnect();
        ftpClient.disconnect();

        이처럼 각각의 객체에 의존하지 않고 facade 객체를 통해 기능을 제공하도록 할 수 있다.
        SftpClient sftpClient = new SftpClient("www.foo.co.kr",22,"/home/etc","text.tmp");
        sftpClient.connect();
        sftpClient.write();
        sftpClient.read();
        sftpClient.disconnect();
        --------------------------------------------------------------------- */
        /* Strategy : 유사한 행위들을 캡슐화하여, 객체의 행위를 바꾸고 싶은 경우 직접 변경이 아닌 전략만 변경하여,
                      유연하게 확장하는 패턴. 전략에 따라 결과가 변한다.


        //기본객체(encoder)와 전략(base64,normal)생성하고.
        Encoder encoder = new Encoder();
        EncodingStrategy base64 = new Base64Strategy();
        EncodingStrategy normal = new NormalStrategy();

        String message = "hello";
        //사용할 때마다 전략을 세팅한다.기본 encoder객체 자체는 변하지 않는다.
        encoder.setEncodingStrategy(base64);
        String base64Result = encoder.getMessage(message);
        System.out.println(base64Result);

        encoder.setEncodingStrategy(normal);
        String normalResult = encoder.getMessage(message);
        System.out.println(normalResult);

        encoder.setEncodingStrategy(new AppendStrategy());
        String appendResult = encoder.getMessage(message);
        System.out.println(appendResult);
        ----------------------------------------------------------------------- */

    }
    //콘센트
    public static void connect(Electronic110v electronic110v){
        electronic110v.powerOn();
    }
}
