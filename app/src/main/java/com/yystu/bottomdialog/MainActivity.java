package com.yystu.bottomdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.zqz.bottomdialog.BottomListDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvString = findViewById(R.id.tv_string);
        TextView tvListString = findViewById(R.id.tv_listString);
        TextView tvListCustomString = findViewById(R.id.tv_listCustomString);
        TextView tvCancel = findViewById(R.id.tv_cancel);
        tvString.setOnClickListener(this);
        tvListString.setOnClickListener(this);
        tvListCustomString.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_string:
                showString();
                break;
            case R.id.tv_listString:
                showListString();
                break;
            case R.id.tv_listCustomString:
                showCustomListString();
                break;
            case R.id.tv_cancel:
                showCancel();
                break;
        }
    }

    private void showCancel() {
        new BottomListDialog.Builder(this)
                .addItem("拍照")
                .addItem("相册")
                .setOnItemClickListener((dialog, itemView, position) -> {
                    dialog.dismiss();

                }).build().show();
    }

    private void showCustomListString() {
        List<Person> arrayList =  new ArrayList<>();
        arrayList.add(new Person("ios",30));
        arrayList.add(new Person("android",24));
        arrayList.add(new Person("flutter",18));
        arrayList.add(new Person("kotlin",18));
        arrayList.add(new Person("go",18));
        arrayList.add(new Person("python",18));
        new BottomListDialog.Builder(this, true)
                .addList(arrayList)
                .setOnItemClickListener((dialog, itemView, position) -> {
                    Toast.makeText(MainActivity.this,arrayList.get(position).toString(),Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }).build().show();
    }

    private void showListString() {
        List<String> arrayList =  new ArrayList<>();
        arrayList.add("第一个");
        arrayList.add("第二个");
        arrayList.add("第三个");
        new BottomListDialog.Builder(this, true)
                .addList(arrayList)
                .setOnItemClickListener((dialog, itemView, position) -> {

                    Toast.makeText(MainActivity.this,arrayList.get(position),Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }).build().show();
    }

    private void showString() {
      new BottomListDialog.Builder(this, true)
                .addItem("拍照")
                .addItem("相册")
                .setOnItemClickListener((dialog, itemView, position) -> {
                    dialog.dismiss();

                }).setOnDismissListener(new DialogInterface.OnDismissListener() {
          @Override
          public void onDismiss(DialogInterface dialogInterface) {
              dialogInterface.dismiss();
              Toast.makeText(MainActivity.this,"弹窗消失",Toast.LENGTH_SHORT).show();
          }
      }).setOutSideCancelable(false).build().show();
    }
}
