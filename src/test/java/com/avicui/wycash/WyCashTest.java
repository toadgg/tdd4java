package com.avicui.wycash;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Hashtable;


/**
 * Created by zhangxiaoliang on 15/4/9.
 */
public class WyCashTest {

    //|ˌmʌltɪplɪˈkeɪʃn| 乘法运算
    @Test
    public void testMultiplication() {
        Money five = Money.dollar(5);
        assertThat(five.times(2), is(Money.dollar(10)));
        assertThat(five.times(3), is(Money.dollar(15)));
    }

    @Test
    public void testEquality() throws Exception {
        assertThat(Money.dollar(5), equalTo(Money.dollar(5)));
        assertThat(Money.dollar(5), not(equalTo(Money.dollar(6))));
        assertThat(Money.franc(5), not(equalTo(Money.dollar(5))));
    }

    @Test
    public void testCurrency() throws Exception {
        assertThat(Money.dollar(1).currency(), is("USD"));
        assertThat(Money.franc(1).currency(), is("CHF"));
    }

    @Test
    public void testSimpleAddition() throws Exception {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertThat(reduced, equalTo(Money.dollar(10)));
    }

    @Test
    public void testPlusReturnSum() throws Exception {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum)result;
        assertThat(five, equalTo(sum.augend));
        assertThat(five, equalTo(sum.addend));
    }

    @Test
    public void testReduceSum() throws Exception {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertThat(result, is(Money.dollar(7)));
    }

    @Test
    public void testReduceMoney() throws Exception {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertThat(result, is(Money.dollar(1)));
    }

    @Test
    public void testReduceMoneyDifferentCurrency() throws Exception {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertThat(result, equalTo(Money.dollar(1)));
    }

    @Test
    public void testIdentityRate() throws Exception {
        assertThat(new Bank().rate("USD", "USD"), is(1));
    }

    @Test
    public void testMixedAddition() throws Exception {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertThat(result, equalTo(Money.dollar(10)));
    }

    @Test
    public void testSumPlusMoney() throws Exception {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertThat(result, equalTo(Money.dollar(15)));
    }

    @Test
    public void testSumTimes() throws Exception {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrance = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrance).times(2);
        Money result = bank.reduce(sum, "USD");
        assertThat(result, equalTo(Money.dollar(20)));
    }
}
