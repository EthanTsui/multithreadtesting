/**
 * 
 */
package com.ethan.testing.examples;

import com.ethan.testing.MultiThreadTestCase;
import com.ethan.testing.MultiThreadTestHandler;

/**
 * @author Ethan Tsui created date: 2014/6/20
 */
public class TestAccessStaticMember1 extends MultiThreadTestCase {
    static int value = 0;
    static Integer LOCK = new Integer(0);
    /**
     * @see com.ethan.testing.MultiThreadTestCase#execute()
     */
    @Override
    public String execute() {
        synchronized (LOCK) {
            value += 1;
            return Integer.toString(value);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        MultiThreadTestHandler handler = new MultiThreadTestHandler();
        handler.setExecutionTimes(500);
        handler.setNumbersOfThreads(1000);
        handler.setTestingClass(TestAccessStaticMember1.class);
        handler.setOutputPath("/data/temp/test1/");
        // handler.setOutputCollision(true);
        handler.start();

    }

}
