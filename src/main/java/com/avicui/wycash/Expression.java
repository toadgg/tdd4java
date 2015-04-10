package com.avicui.wycash;

/**
 * Created by zhangxiaoliang on 15/4/9.
 */
public interface Expression {

    Money reduce(Bank bank, String to);


    Expression plus(Expression tenFrancs);

    Expression times(int multiplier);
}
