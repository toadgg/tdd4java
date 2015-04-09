package com.avicui.wycash;

/**
 * Created by zhangxiaoliang on 15/4/9.
 */
public class Dollar {

    public int amount;

    public Dollar(int amount) {
        this.amount = amount;
    }

    public void times(int multiplier) {
        amount *= multiplier;
    }
}
