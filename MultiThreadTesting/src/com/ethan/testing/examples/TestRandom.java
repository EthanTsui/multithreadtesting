/**
 * 
 */
package com.ethan.testing.examples;

import java.util.Random;

import com.ethan.testing.MultiThreadTestCase;
import com.ethan.testing.MultiThreadTestHandler;

/**
 * @author Ethan Tsui
 * created date: 2014/6/18
 */
public class TestRandom extends MultiThreadTestCase {

    /**
     * @param args
     */
    public static void main(String[] args) {
        MultiThreadTestHandler handler = new MultiThreadTestHandler();
        handler.setExecutionTimes(300);
        handler.setNumbersOfThreads(500);
        handler.setTestingClass(TestRandom.class);
        handler.setOutputPath("/data/temp/random/");
//        handler.setOutputCollision(true);
        handler.start();

    }

    /**
     * 
     */
    public TestRandom() {
        
    }

    /**
     * @see com.ethan.testing.MultiThreadTestCase#execute()
     */
    @Override
    public String execute() {
        return generateRandomIdLower36(6);
    }

    
    private Random random = new Random();
    
    private static final char[] CHARS36LOWER = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
        '9' };
    
    public String generateRandomIdLower36(int numberOfChar) {
        char[] output = new char[numberOfChar];
        for (int i = 0; i < numberOfChar; i++) {
            output[i] = CHARS36LOWER[random.nextInt(36)];
        }
        return new String(output);
    }
}
