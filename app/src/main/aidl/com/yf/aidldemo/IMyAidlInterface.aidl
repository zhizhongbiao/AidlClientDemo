// IMyAidlInterface.aidl
package com.yf.aidldemo;

// Declare any non-default types here with import statements

import com.yf.aidldemo.bean.Person;
import com.yf.aidldemo.IOnNewPersonArrivedListener;

interface IMyAidlInterface {


   /**
     * 除了基本数据类型，其他所有自定义类型的参数
     * 都需要标上数据流方向的类型：in(客户端输入服务端)
     * , out(服务端输出客户端), inout(客户端和服务端双向输入输出)
     *
     * AIDL中的定向 tag 表示了在跨进程通信中数据的流向
     * ，其中 in 表示数据只能由客户端流向服务端
     * ， out 表示数据只能由服务端流向客户端
     * ，而 inout 则表示数据可在服务端与客户端之间双向流通。
     * 其中，数据流向是针对在客户端中的那个传入方法的对象而言的。
     * in 为定向 tag 的话表现为服务端将会接收到一个那个对象的完整数据
     * ，但是客户端的那个对象不会因为服务端对传参的修改而发生变动；
     * out 的话表现为服务端将会接收到那个对象的参数为空的对象，
     * 但是在服务端对接收到的空对象有任何修改之后客户端将会同步变动；
     * inout 为定向 tag 的情况下，服务端将会接收到客户端传来对象的完整信息，
     * 并且客户端将会同步服务端对该对象的任何变动。
     *
     */

   void addPersonIn(in Person person);

   void addPersonOut(out Person person);

   void addPersonInOut(inout Person person);

    List<Person> getPersons();

    int getPersonCount();

    void registerOnNewPersonArrivedListener(IOnNewPersonArrivedListener listener);

    void unregisterOnNewPersonArrivedListener(IOnNewPersonArrivedListener listener);


}
