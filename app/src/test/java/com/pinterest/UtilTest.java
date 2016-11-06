package com.pinterest;

import com.pinterest.common.Utils;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by harsh on 06/11/16.
 */
public class UtilTest {

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertThat(Utils.isValidEmail("harsh.signin@gmail.com"), is(true));
    }

    @Test
    public void stringNullChecker() {
        assertThat(Utils.isNullOrEmpty(""), is(true));
    }

}
