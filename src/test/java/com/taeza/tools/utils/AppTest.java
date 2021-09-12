package com.taeza.tools.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private String regex = "^[a-z][a-z0-9_]*(\\.[a-z0-9_]+)+[0-9a-z_]$";
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( "test.sss.daasda.adadsa.1.asda".matches(regex));
    }
}
