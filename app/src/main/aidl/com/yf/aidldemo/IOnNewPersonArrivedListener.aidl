// IOnNewPersonArrivedListener.aidl
package com.yf.aidldemo;

// Declare any non-default types here with import statements
import com.yf.aidldemo.bean.Person;

interface IOnNewPersonArrivedListener {
    void onNewPersonArrived(inout Person newPerson);
}
