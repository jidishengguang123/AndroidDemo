package com.jeck.demo.entities;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-20
 * Time: 16:53
 * Description:
 */
public class FirstEvent {
    private String message;
    public FirstEvent(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
