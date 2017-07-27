package com.base.sqliteopenhelperdemo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.base.sqliteopenhelperdemo.R;
import com.base.sqliteopenhelperdemo.dao.PersonDao;
import com.base.sqliteopenhelperdemo.domain.Person;

import java.util.List;

public class MainActivity extends Activity {

    private PersonDao dao;
    private ListView lv;
    private List<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkedPermission();
        //初始化我们的dao
        dao = new PersonDao(getApplicationContext());
        //找到我们的listview 用来显示 数据库查询的所有内容
        lv = (ListView) findViewById(R.id.lv);



    }
    private void checkedPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            Toast.makeText(this,"拥有权限请继续使用！",Toast.LENGTH_SHORT).show();
        }
    }
    // 往数据库里插入一条数据
    public void add(View v) {

        boolean result = dao.add("zhangsan", "18989900");
        if (result) {
            Toast.makeText(getApplicationContext(), "插入成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "插入失败", Toast.LENGTH_SHORT).show();

        }


    }

    // 从数据库里删除数据
    public void del(View v) {

        //影响了多少行
        int del = dao.del("zhangsan");
        Toast.makeText(getApplicationContext(), "删除了"+del+"行", Toast.LENGTH_SHORT).show();

    }

    // 根据名字进行更新
    public void update(View v) {

        int update = dao.update("zhangsan", "139999999");
        Toast.makeText(getApplicationContext(), "更新了"+update+"行", Toast.LENGTH_SHORT).show();
    }

    // 查询数据库里面所有的数据
    // 当我点击查询所有这个按钮的时候  把数据库里的数据 显示到listview上
    public void find(View v) {
        persons = dao.find();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                    save();
                }else {
                    Toast.makeText(this,"权限拒绝！",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

}
