package com.yf.aidldemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * FileName :  Person
 * Author   :  zhizhongbiao
 * Date     :  2018/8/8
 * Describe :
 */

public class Person implements Parcelable{
    public String name;
    public String gender;
    public int age;

    public Person() {
    }

    public Person(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    protected Person(Parcel in) {
        name = in.readString();
        gender = in.readString();
        age = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeInt(age);
    }


    /**
     *      但是请注意，这里有一个坑：默认生成的模板类的对象只支持为 in 的定向 Tag 。
     *      为什么呢？因为默认生成的类里面只有 writeToParcel() 方法，而如果要支持为 out 或者 inout 的定向 tag 的话
     *      ，还需要实现 readFromParcel() 方法——而这个方法其实并没有在 Parcelable 接口里面，所以需要我们从头写。
     *      当有了该方法之后，我们的Aidl接口文件中的方法的参数Tag就可以定义为out或者inout类型了。
     */

    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * @param dest
     */
    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        name = dest.readString();
        gender = dest.readString();
        age = dest.readInt();
    }

}
