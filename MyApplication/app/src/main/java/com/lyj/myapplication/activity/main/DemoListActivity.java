package com.lyj.myapplication.activity.main;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lyj.myapplication.activity.LoginActivity;
import com.lyj.myapplication.activity.TestActivity;
import com.lyj.myapplication.activity.test.ClickTestActivity;

public class DemoListActivity extends ListActivity {
    ClassAndName[] mClassAndNames = {
            new ClassAndName(ClickTestActivity.class,"测试事件"),
            new ClassAndName(TestActivity.class,"测试数据库"),
            new ClassAndName(LoginActivity.class,"登录")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<ClassAndName>(this, android.R.layout.simple_list_item_1, mClassAndNames));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ClassAndName cn = (ClassAndName) l.getItemAtPosition(position);
        startActivity(new Intent(this, cn.mClass));
    }

    class ClassAndName {
        private Class<?> mClass;
        private String name;

        public ClassAndName(Class<?> aClass, String name) {
            mClass = aClass;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
