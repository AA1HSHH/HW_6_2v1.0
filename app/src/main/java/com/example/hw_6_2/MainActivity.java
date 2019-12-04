package com.example.hw_6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface iMyAidlInterface;// 定义接口变量
private ServiceConnection connection;

int answer=0;

    TextView textView;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button button;



protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
      textView=findViewById(R.id.text);
      editText1=findViewById(R.id.ed1);
    editText2=findViewById(R.id.ed2);
    editText3=findViewById(R.id.ed3);
    button=findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bindRemoteService();
        }
    });




}

        private void bindRemoteService() {
Intent intentService = new Intent();
intentService.setClassName(this,"com.example.hw_6_2.RemoteService");

connection = new ServiceConnection() {

public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
// 从连接中获取Stub对象
iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
// 调用Remote Service提供的方法
try {
Log.d("MainActivity", "获取到消息：" +iMyAidlInterface.getMessage());
 answer=iMyAidlInterface.calculate(Integer.parseInt(editText1.getText().toString()),Integer.parseInt(editText3.getText().toString()),Integer.parseInt(editText2.getText().toString()));
    Log.d("MainActivity", "获取到消息：" +answer);
    String out=Integer.toString(answer);
    textView.setText("answer:"+out);


} catch (RemoteException e) {
 e.printStackTrace();
}
}


public void onServiceDisconnected(ComponentName componentName) {
// 断开连接
iMyAidlInterface = null;
}
};

bindService(intentService, connection, Context.BIND_AUTO_CREATE);
}


protected void onDestroy() {
super.onDestroy();
if (connection != null)
unbindService(connection);// 解除绑定
}
}
