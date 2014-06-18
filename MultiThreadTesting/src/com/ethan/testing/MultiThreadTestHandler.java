/**
 * 
 */
package com.ethan.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Ethan Tsui created date: 2014/6/17
 */
public class MultiThreadTestHandler {
    
    /** single thread execution times */
    private int executionTimes = 100;
    
    /** numbers of concurrent threads */
    private int numbersOfThreads = 10;
    
    /** output directory  */
    private String outputPath = "/data/temp/";
    
    /** test class */
    private Class testingClass = null;
    
    /** if true, output conflicted data  */
    private boolean outputCollision = false;

    public void start() {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(numbersOfThreads);
            File file = new File(outputPath);
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            
            Calendar start = Calendar.getInstance();
            
            for (int i = 0; i < numbersOfThreads; i++) {
                MultiThreadTestCase obj = (MultiThreadTestCase) testingClass.newInstance();
                obj.setMyName("id" + (i + 1));
                obj.setExecutionTimes(executionTimes);
                obj.setOutputPath(outputPath);
                executor.execute(obj);

            }

            executor.shutdown();
            executor.awaitTermination(1000, TimeUnit.HOURS);
            
            Calendar end = Calendar.getInstance();
            
            System.out.println("execution finished...");
            
            long time = end.getTimeInMillis()-start.getTimeInMillis();
            System.out.println("execution time(seconds): \t"+(time/1000D));
            System.out.println("single thread execution time (seconds): \t"+(time/numbersOfThreads/1000D));
            System.out.println("single process execution time (seconds): \t"+(time/numbersOfThreads/executionTimes/1000D));
            
            
            varify();

        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void varify() {
        try {
            HashMap<String, Integer> maps = new HashMap<String, Integer>();
            int datanum = 0;
            for (int i = 1; i <= numbersOfThreads; i++) {
                File f = new File(outputPath + "/id" + i);
                if (f.exists()) {

                    BufferedReader br = new BufferedReader(new FileReader(f));
                    String line = null;

                    while ((line = br.readLine()) != null) {
                        int counter = 1;
                        datanum++;
                        if (maps.containsKey(line)) {
                            counter = maps.get(line) + 1;
                        }
                        maps.put(line, counter);
                    }

                    br.close();
                }

            }

            System.out.println("=== Result ===");
            System.out.println("total outputs: \t\t"+datanum);

            int counter = 0;
            for (String key : maps.keySet()) {
                if (maps.get(key) >= 2) {
                    counter++;
                    if (isOutputCollision()) {
                        System.out.println(key + ": " + maps.get(key));
                    }
                }
            }

            System.out.println("# of confliction: \t" + counter);
            System.out.println("confliction ratio: \t"+((counter/(float)datanum)*100f)+"%");

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public int getExecutionTimes() {
        return executionTimes;
    }

    public MultiThreadTestHandler setExecutionTimes(int executionTimes) {
        this.executionTimes = executionTimes;
        return this;
    }

    public int getNumbersOfThreads() {
        return numbersOfThreads;
    }

    public MultiThreadTestHandler setNumbersOfThreads(int numbersOfThreads) {
        this.numbersOfThreads = numbersOfThreads;
        return this;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public MultiThreadTestHandler setOutputPath(String outputPath) {
        this.outputPath = outputPath;
        return this;
    }

    public Class getTestingClass() {
        return testingClass;
    }

    public MultiThreadTestHandler setTestingClass(Class testingClass) {
        this.testingClass = testingClass;
        return this;
    }

    public boolean isOutputCollision() {
        return outputCollision;
    }

    public void setOutputCollision(boolean outputCollision) {
        this.outputCollision = outputCollision;
    }

}
