package com.avicui.wycash;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangxiaoliang on 15/4/9.
 */
public class WyCashTest {

    //|ˌmʌltɪplɪˈkeɪʃn| 乘法运算
    @Test
    public void testMultiplication() {

        Dollar five = new Dollar(5);
        five.times(2);
        assertThat(five.amount, Is.is(10));

    }
}
