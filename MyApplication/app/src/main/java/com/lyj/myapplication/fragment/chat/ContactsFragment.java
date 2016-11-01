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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lyj.myapplication.R;
import com.lyj.myapplication.activity.LoginActivity;
import com.lyj.myapplication.dbHelper.UserHelper;
import com.lyj.myapplication.provider.UserProvider;
import com.lyj.myapplication.service.IMService;
import com.lyj.myapplication.util.PinyinUtils;
import com.lyj.myapplication.util.ThreadUtils;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;

import java.util.Collection;


public class ContactsFragment extends Fragment {

    private ListView mListView;
    private Roster mRoster;

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
                mRoster = IMService.conn.getRoster();
                //得到所有的联系人
                final Collection<RosterEntry> entries = mRoster.getEntries();
                //打印
                for (RosterEntry entry : entries) {
                    System.out.println(entry.toString());
                    System.out.println(entry.getUser());
                    System.out.println(entry.getName());
                    System.out.println(entry.getGroups());
                    System.out.println(entry.getType());
                    System.out.println(entry.getStatus());
                }
                mRoster.addRosterListener(new MyRosterListener());
                for (RosterEntry entry : entries) {
                    saveOrUpdateEntry(entry);
                }
                final Cursor cur = getActivity().getContentResolver().query(UserProvider.URI_MATCH, null, null, null, null);
//                设置Adapter，然后显示数据
                ThreadUtils.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        CursorAdapter adapter = new CursorAdapter(getActivity(), cur) {
                            @Override
                            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                                View view = View.inflate(context, R.layout.item_contact, null);
                                return view;
                            }

                            @Override
                            public void bindView(View view, Context context, Cursor cursor) {
                                ImageView head = (ImageView) view.findViewById(R.id.head);
                                TextView tvAccount = (TextView) view.findViewById(R.id.account);
                                TextView tvNickName = (TextView) view.findViewById(R.id.nickname);
                                String account = cursor.getString(cur.getColumnIndex(UserProvider.UserTableMeta.USER_ACCOUNT));
                                String nickname = cursor.getString(cur.getColumnIndex(UserProvider.UserTableMeta.USER_NICKNAME));
                                tvAccount.setText(account);
                                tvNickName.setText(nickname);
                            }
                        };
                        mListView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

        });
    }

    public void saveOrUpdateEntry(RosterEntry entry) {
        ContentValues values = new ContentValues();
        String account = entry.getUser();
        account = account.substring(0, account.indexOf("@")) + "@" + LoginActivity.SERVICENAME;
        String nickname = entry.getName();
        if (nickname == null || "".equals(nickname)) {
            nickname = account.substring(0, account.indexOf("@"));//截取
        }
        values.put(UserProvider.UserTableMeta.USER_ACCOUNT, account);
        values.put(UserProvider.UserTableMeta.USER_NICKNAME, nickname);
        values.put(UserProvider.UserTableMeta.USER_AVATAR, "0");
        values.put(UserProvider.UserTableMeta.USER_PINYIN, PinyinUtils.getPinyin(account));
        //先update,后插入--》
        int updateCount = getActivity().getContentResolver().update(UserProvider.URI_MATCH, values, UserProvider.UserTableMeta.USER_ACCOUNT + "=?", new String[]{account});
        if (updateCount <= 0) {
            getActivity().getContentResolver().insert(UserProvider.URI_MATCH, values);
        }
    }

    private void initListener() {
    }

    class MyRosterListener implements RosterListener {

        @Override
        public void entriesAdded(Collection<String> addresses) {//添加
            System.out.println("-------------entriesAdded-----------------");
            //对应数据库更新
            for (String address : addresses) {
                RosterEntry entry = mRoster.getEntry(address);
                //要么更新，要么插入
                saveOrUpdateEntry(entry);
            }
        }

        @Override
        public void entriesUpdated(Collection<String> addresses) {//修改
            System.out.println("-------------entriesUpdated-----------------");
            for (String address : addresses) {
                RosterEntry entry = mRoster.getEntry(address);
                //要么更新，要么插入
                saveOrUpdateEntry(entry);
            }
        }

        @Override
        public void entriesDeleted(Collection<String> addresses) {//删除
            System.out.println("-------------entriesDeleted-----------------");
            for (String address : addresses) {
                RosterEntry entry = mRoster.getEntry(address);
                getActivity().getContentResolver().delete(UserProvider.URI_MATCH, UserProvider.UserTableMeta.USER_ACCOUNT + "=?", new String[]{entry.getUser()});
            }
        }

        @Override
        public void presenceChanged(Presence presence) {//状态改变
            System.out.println("-------------presenceChanged-----------------");
        }
    }
}
