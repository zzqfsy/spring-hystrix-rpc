package com.zzqfsy.test;

import com.zzqfsy.hystrix.demo.CommandHelloWorld;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HystrixTest {

    @Test
    public void testSynchronous() {
        assertEquals("Hello World!", new CommandHelloWorld("World").execute());
        assertEquals("Hello Bob!", new CommandHelloWorld("Bob").execute());
    }
}
