Thread safety testing framework
==================
This framework simulates multithreaded envirment for you and perform conccurent stress testing.

##How to use

1. Extend MultiThreadTestCase and implement method execute() (the returned String is used to be a key, each process should have unique key. After the execution, if there are duplicated keys, the test is failed, in term of, it is not a thread safe function.)

```
public class TestRandom extends MultiThreadTestCase {
    ...

    @Override
    public String execute() {
        return generateRandomIdLower36(6);
    }
    
    ... 

}
```

2. initial MultiThreadTestHandler, set parameters you want, and call start().
```
    MultiThreadTestHandler handler = new MultiThreadTestHandler();
    handler.setTestingClass(TestRandom.class);
    handler.setOutputPath("/data/temp/random/");
    handler.start();

```


##Example 1

```
public class TestRandom extends MultiThreadTestCase {

    public static void main(String[] args) {
        MultiThreadTestHandler handler = new MultiThreadTestHandler();
        handler.setExecutionTimes(300);
        handler.setNumbersOfThreads(500);
        handler.setTestingClass(TestRandom.class);
        handler.setOutputPath("/data/temp/random/");
//        handler.setOutputCollision(true);
        handler.start();

    }

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
```

ref: https://github.com/EthanTsui/multithreadtesting/blob/master/MultiThreadTesting/src/com/ethan/testing/examples/TestRandom.java


##Sample output

```
Sample output: 
execution finished...
execution time(seconds): 	1.107
single thread execution time (seconds): 	0.002
single process execution time (seconds): 	0.0
=== Result ===
total outputs: 		150000
izo5rp: 2
4e0egt: 2
7mw78w: 2
x51ubs: 2
# of confliction: 	4
confliction ratio: 	0.0026666666%
```

