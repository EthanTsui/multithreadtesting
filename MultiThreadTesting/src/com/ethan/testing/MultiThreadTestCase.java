/**
 * 
 */
package com.ethan.testing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author Ethan Tsui created date: 2014/6/17
 */
public abstract class MultiThreadTestCase extends Thread {
    private String myName = null;
    private int executionTimes = 100;
    private BufferedWriter writer = null;
    private String outputPath = "/data/temp/";
    private DecimalFormat formater = new DecimalFormat("#.0");
    /**
     * 
     */
    public MultiThreadTestCase() {
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread-"+myName+" started ...");
            init();
            float nextLevel=0.25f;
            for (int i = 1; i <= executionTimes; i++) {
                String tmp = execute();
                writer.write(tmp);
                writer.write("\n");
                
                if(i/(float)executionTimes >=nextLevel) {
                    System.out.println("Thread-"+myName+"\t --> "+formater.format(100*(nextLevel))+"%");
                    nextLevel+=0.25f;
                }
            }
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        File file = new File(outputPath);

        writer = new BufferedWriter(new FileWriter(file.getAbsolutePath() + "/" + getMyName()));

    }

    
    public void close() {
        try {
            writer.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public abstract String execute();

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public int getExecutionTimes() {
        return executionTimes;
    }

    public void setExecutionTimes(int executionTimes) {
        this.executionTimes = executionTimes;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

}
