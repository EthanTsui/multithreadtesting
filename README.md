Thread safety testing framework
==================
To ensure function is thread safe or not is not easy to do without "stress testing". 

This framework simulates multithreaded envirment for you and perform conccurent stress testing.

To use this framework is quite easy. 

1. Extend MultiThreadTestCase and implement method execute() (the returned String is used to be a key, each process should have unique key. After the execution, if there are duplicated keys, the test is failed, in term of, it is not a thread safe function.)

2. initial MultiThreadTestHandler, set what parameters you want, and start()

Example:
1. Entend MultiThreadTestCase

