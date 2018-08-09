package com.yf.aidldemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yf.aidldemo.bean.Person;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements ServiceConnection {

    private IMyAidlInterface remoteBinder;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bindService();
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.yf.aidldemo.action.SERVER");
        bindService(intent, this, BIND_AUTO_CREATE);
        LogUtils.e( "bindService");
    }

    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }

    @OnClick({R.id.btnIn, R.id.btnOut, R.id.btnInOut})
    public void onViewClicked(View view) {

        if (remoteBinder == null) {
            LogUtils.e("remoteBinder==null");
            return;
        }
        try {
            switch (view.getId()) {
                case R.id.btnIn:
                    remoteBinder.addPersonIn(new Person("in", "male", 10));
                    break;
                case R.id.btnOut:
                    remoteBinder.addPersonOut(new Person("out", "female", 20));
                    break;
                case R.id.btnInOut:
                    remoteBinder.addPersonInOut(new Person("inout", "gay", 30));
                    break;
            }
            LogUtils.e("Count =" + remoteBinder.getPersonCount());
            LogUtils.e("Persons =" + remoteBinder.getPersons());

        } catch (RemoteException e) {
            LogUtils.e("RemoteException  e=" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        LogUtils.e("onServiceConnected   name=" + name + "       service=" + service);
        try {
            remoteBinder = IMyAidlInterface.Stub.asInterface(service);
            remoteBinder.registerOnNewPersonArrivedListener(onNewPersonArrivedListener);
            if (remoteBinder == null&&count<0) {
                bindService();
                count++;

            }else
            {
                count=0;
            }
        } catch (RemoteException e) {
            LogUtils.e("onServiceConnected   RemoteException=" +e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        LogUtils.e("onServiceDisconnected");
    }


   private final IOnNewPersonArrivedListener onNewPersonArrivedListener = new IOnNewPersonArrivedListener.Stub()
    {
        @Override
        public void onNewPersonArrived(Person newPerson) throws RemoteException {
            LogUtils.e("newPerson 新的Person已经来了，newPerson="+newPerson);
        }
    };


}
