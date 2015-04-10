package com.avicui.wycash;

/**
 * Created by zhangxiaoliang on 15/4/9.
 */
public class Money implements Expression {

    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }



    @Override
    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount && currency() == money.currency();
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }


    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public String currency() {
        return currency;
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }

    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    public Expression times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }

}
