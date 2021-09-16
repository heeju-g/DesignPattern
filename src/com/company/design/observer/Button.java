package com.company.design.observer;

public class Button {

    private String name;
    private IButtonListner buttonListner;
    
    public Button(String name){
        this.name = name;
    }
    //메세지 넘겨주기
    public void click(String message){
        buttonListner.clickEvent(message);
    }

    public void addListner(IButtonListner buttonListner){
        this.buttonListner = buttonListner;
    }
}
