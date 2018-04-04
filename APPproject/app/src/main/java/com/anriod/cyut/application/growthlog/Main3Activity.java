package com.anriod.cyut.application.growthlog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class Main3Activity extends AppCompatActivity {


    private int i=0;
    private static final String[] mStrings={"芽菜箱1","芽菜箱2","芽菜箱3","芽菜箱4","芽菜箱5","芽菜箱6","芽菜箱7","芽菜箱8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final ListView list = (ListView)findViewById(R.id.listView1);
        list.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,mStrings));
        list.setTextFilterEnabled(true);

        //ListView點擊事件
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int i, long l){
                Intent intent = new Intent(Main3Activity.this, SecondActivity.class);
                intent.putExtra("CountrName",list.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

        //openDialog();
        // Notify();
    }

    private void openDialog(){
        //建立對話方塊片段
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);
        AlertDialog dialog = builder.create();
    }

    //通知功能 Notification
    public void Notify(){
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);  //
        builder.setSmallIcon(R.mipmap.house01)
                .setTicker("颱風特價")
                .setContentTitle("颱風限定")
                .setContentText("海砂屋任你住");

        Notification notification = builder.build();   //
        notificationManager.cancel(i);                 //移除ID值為0的通知
        notificationManager.notify(i,notification);    //發送ID值為0的通知
    }

    public void logout(View view){
        //SharedPreferences 是儲存相對較小的索引鍵值集合
        SharedPreferences result = getSharedPreferences("personal",0);
        result.edit().putBoolean("loggedin",false).commit();
        finish();
    }

}