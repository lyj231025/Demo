package com.lyj.myapplication.fragment.chat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyj.myapplication.R;
import com.lyj.myapplication.service.IMService;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;

import java.util.Collection;


public class ContactsFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contactView = inflater.inflate(R.layout.fragment_contacts, container, false);
        initView(contactView);
        return contactView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
    }

    private void init() {
    }


    private void initView(View contactView) {

    }

    private void initData() {
        //得到花名册
        Roster roster = IMService.conn.getRoster();
        //得到所有的联系人
        Collection<RosterEntry> entries = roster.getEntries();
        //打印
        for (RosterEntry entry : entries) {
            System.out.println(entry.toString());
            System.out.println(entry.getUser());
            System.out.println(entry.getName());
            System.out.println(entry.getGroups());
            System.out.println(entry.getType());
            System.out.println(entry.getStatus());
        }
    }

    private void initListener() {
    }


}
