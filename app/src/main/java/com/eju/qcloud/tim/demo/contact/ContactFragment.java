package com.eju.qcloud.tim.demo.contact;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eju.qcloud.tim.uikit.base.BaseFragment;
import com.eju.qcloud.tim.uikit.modules.contact.ContactItemBean;
import com.eju.qcloud.tim.uikit.modules.contact.ContactLayout;
import com.eju.qcloud.tim.uikit.modules.contact.ContactListView;
import com.eju.qcloud.tim.uikit.utils.TUIKitConstants;
import com.eju.qcloud.tim.demo.DemoApplication;
import com.eju.qcloud.tim.demo.R;
import com.eju.qcloud.tim.demo.menu.Menu;
import com.eju.qcloud.tim.demo.utils.DemoLog;

public class ContactFragment extends BaseFragment {

    private static final String TAG = ContactFragment.class.getSimpleName();
    private ContactLayout mContactLayout;
    private Menu mMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.contact_fragment, container, false);
        initViews(baseView);

        return baseView;
    }

    private void initViews(View view) {
        // 从布局文件中获取通讯录面板
        mContactLayout = view.findViewById(R.id.contact_layout);
        mMenu = new Menu(getActivity(), mContactLayout.getTitleBar(), Menu.MENU_TYPE_CONTACT);
        mContactLayout.getTitleBar().setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMenu.isShowing()) {
                    mMenu.hide();
                } else {
                    mMenu.show();
                }
            }
        });
        mContactLayout.getContactListView().setOnItemClickListener(new ContactListView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ContactItemBean contact) {
                if (position == 0) {
                    Intent intent = new Intent(DemoApplication.instance(), NewFriendActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    DemoApplication.instance().startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(DemoApplication.instance(), GroupListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    DemoApplication.instance().startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(DemoApplication.instance(), BlackListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    DemoApplication.instance().startActivity(intent);
                } else {
                    Intent intent = new Intent(DemoApplication.instance(), FriendProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(TUIKitConstants.ProfileType.CONTENT, contact);
                    DemoApplication.instance().startActivity(intent);
                }
            }
        });
    }

    private void refreshData() {
        // 通讯录面板的默认UI和交互初始化
        mContactLayout.initDefault();
    }

    @Override
    public void onResume() {
        super.onResume();
        DemoLog.i(TAG, "onResume");
        refreshData();
    }
}
