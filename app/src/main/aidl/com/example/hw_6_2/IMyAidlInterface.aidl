// IMyAidlInterface.aidl
package com.example.hw_6_2;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String getMessage();
    int calculate(int a,int b,int mode);
}
