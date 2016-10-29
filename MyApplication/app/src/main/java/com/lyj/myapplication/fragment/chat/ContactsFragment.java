package com.lyj.myapplication.fragment.chat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lyj.myapplication.R;
import com.lyj.myapplication.activity.LoginActivity;
import com.lyj.myapplication.service.IMService;
import com.lyj.myapplication.util.PinyinUtils;
import com.lyj.myapplication.util.ThreadUtils;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;

import java.util.Collection;


public class ContactsFragment extends Fragment {

    private ListView mListView;

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
        mListView = (ListView) contactView.findViewById(R.id.list_view);
    }

    private void initData() {
        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
                //得到花名册
                Roster roster = IMService.conn.getRoster();
                //得到所有的联系人
                final Collection<RosterEntry> entries = roster.getEntries();
                //打印
                for (RosterEntry entry : entries) {
                    System.out.println(entry.toString());
                    System.out.println(entry.getUser());
                    System.out.println(entry.getName());
                    System.out.println(entry.getGroups());
                    System.out.println(entry.getType());
                    System.out.println(entry.getStatus());
                }
                for (RosterEntry entry : entries) {
//                    saveOrUpdateEntry(entry);
                }
//                final Cursor cur = getActivity().getContentResolver().query(ContactsProvider.URI_CONTACT, null, null, null, null);
                //设置Adapter，然后显示数据
//                ThreadUtils.runInUIThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        CursorAdapter adapter = new CursorAdapter(getActivity(), cur) {
//                            @Override
//                            public View newView(Context context, Cursor cursor, ViewGroup parent) {
//                                TextView tv = new TextView(getActivity());
//                                return tv;
//                            }
//
//                            @Override
//                            public void bindView(View view, Context context, Cursor cursor) {
//                                TextView tv = (TextView) view;
//                                String account = cursor.getString(cur.getColumnIndex(ContactOpenHelper.ContactTable.ACCOUNT));
//                                tv.setText(account);
//                            }
//                        };
//                        mListView.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
//                    }
//                });
            }

        });
    }
//
//    public void saveOrUpdateEntry(RosterEntry entry) {
//        ContentValues values = new ContentValues();
//        String account = entry.getUser();
//        account = account.substring(0, account.indexOf("@")) + "@" + LoginActivity.SERVICENAME;
//        String nickname = entry.getName();
//        if (nickname == null || "".equals(nickname)) {
//            nickname = account.substring(0, account.indexOf("@"));//截取
////        }
////        values.put(ContactOpenHelper.ContactTable.ACCOUNT, account);
////        values.put(ContactOpenHelper.ContactTable.NICKNAME, nickname);
////        values.put(ContactOpenHelper.ContactTable.AVATAR, "0");
////        values.put(ContactOpenHelper.ContactTable.PINYIN, PinyinUtils.getPinyin(account));
////        //先update,后插入--》
////        int updateCount = getActivity().getContentResolver().update(ContactsProvider.URI_CONTACT, values, ContactOpenHelper.ContactTable.ACCOUNT + "=?", new String[]{account});
////        if (updateCount <= 0) {
////            getActivity().getContentResolver().insert(ContactsProvider.URI_CONTACT, values);
////        }
//    }

    private void initListener() {
    }


}
