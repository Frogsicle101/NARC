package seng202.group6;

import junit.framework.Test;

import static junit.framework.Assert.*;

public class CodyTest {

    Cody newTest = new Cody();
    
    public void testMain() {
        assertEquals(newTest.printString(), "Wow it worked");
    }
}