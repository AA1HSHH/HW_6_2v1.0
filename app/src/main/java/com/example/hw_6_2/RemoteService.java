package com.example.hw_6_2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

// 远程服务示例
public class RemoteService extends Service {

       public RemoteService() {
}


public IBinder onBind(Intent intent) {
return stub;// 在客户端连接服务端时，Stub通过ServiceConnection传递到客户端
}

        // 实现接口中暴露给客户端的Stub--Stub继承自Binder，它实现了IBinder接口
       private IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub(){

        // 实现了AIDL文件中定义的方法

public String getMessage() throws RemoteException {
// 在这里我们只是用来模拟调用效果,因此随便反馈值给客户端
return "Remote Service方法调用成功";
}

            @Override
            public int calculate(int a, int b, int mode) throws RemoteException {
                int answer=0;
                switch (mode){
                    case 1:
                        answer=a+b;
                        break;
                    case 2:
                        answer=a-b;
                        break;
                    case 3:
                        answer=a*b;
                        break;
                    case 4:
                        answer=a/b;
                        break;
                }
                return answer;
            }
        };

}
























