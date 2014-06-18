Thread safety testing framework
==================
To ensure function is thread safe or not is not easy to do without "stress testing". 

This framework simulates multithreaded envirment for you and perform conccurent stress testing.

To use this framework is quite easy. 

1. Extend MultiThreadTestCase and implement method execute() (the returned String is used to be a key, each process should have unique key. After the execution, if there are duplicated keys, the test is failed, in term of, it is not a thread safe function.)

2. initial MultiThreadTestHandler, set parameters you want, and call start().

Examples:
1. https://github.com/EthanTsui/multithreadtesting/blob/master/MultiThreadTesting/src/com/ethan/testing/examples/TestRandom.java

2. 

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


