package seng202.group6;

import org.junit.Test;

import static org.junit.Assert.*;

public class CodyTest {

    Cody newTest = new Cody();

    @Test
    public void testMain() {
        assertEquals(newTest.printString(), "Wow it worked");
    }
}