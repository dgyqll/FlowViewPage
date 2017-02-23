package com.etouse.flowviewpage.act;


import android.content.ContentResolver;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.etouse.flowviewpage.R;
import com.etouse.flowviewpage.bean.PayTypeBean;
import com.etouse.flowviewpage.fragment.PayTypeFragment;
import com.etouse.flowviewpage.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //已选择的数据集合
    List<PayTypeBean> payTypeBeanList = new ArrayList<>();
    List<Fragment> fragmentDatas = new ArrayList<>();

    private ViewPager vp;
    private int fragmentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                queryDb();
            }
        }).start();

    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
    }



    private void queryDb() {
        ContentResolver contentResolver = getContentResolver();
        String selection ="_used=?";
        String[] selectionArgs={"1"};//具体的条件,注意要对应条件字段
        Cursor cursor1 = contentResolver.query(Uri.parse("content://com.etouse.flow.provider/t_pay_type"), new String[]{"_pid","_name","_used"},selection, selectionArgs, null);
        while(cursor1.moveToNext()){
            PayTypeBean payTypeBean = new PayTypeBean();
            payTypeBean.setId(cursor1.getInt(0));
            payTypeBean.setName(cursor1.getString(1));
            payTypeBean.setUsed(cursor1.getString(2));
            payTypeBeanList.add(payTypeBean);
        }

        if (payTypeBeanList.size() <= 10) {
             fragmentCount = 1;
        } else if (payTypeBeanList.size() % 10 > 0) {
            fragmentCount = payTypeBeanList.size() / 10 + 1;
        } else {
            fragmentCount = payTypeBeanList.size() / 10;
        }

        for (int i = 0; i < fragmentCount; i++) {
            PayTypeFragment payTypeFragment = new PayTypeFragment();
            fragmentDatas.add(payTypeFragment);
        }

        Util.runOnInThread(new Runnable() {
            @Override
            public void run() {
                MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(),fragmentCount,payTypeBeanList);
                vp.setAdapter(myAdapter);
            }
        });

    }


    class MyAdapter extends FragmentPagerAdapter {
        private int fragmentCount;
        private List<PayTypeBean> payTypeBeanList;

        public MyAdapter(FragmentManager fm, int fragmentCount, List<PayTypeBean> payTypeBeanList) {
            super(fm);
            this.fragmentCount = fragmentCount;
            this.payTypeBeanList = payTypeBeanList;
        }

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            List<PayTypeBean> list = new ArrayList<>();
            if (fragmentCount == 1) {
                list.addAll(payTypeBeanList);
            } else {
                if (position < fragmentCount - 1) {
                    for (int i1 = 0; i1 < 10; i1++) {
                        list.add(payTypeBeanList.get(position * 10 + i1));
                    }
                } else {
                    for (int i1 = 0; i1 <=  payTypeBeanList.size() / 10; i1++) {
                        list.add(payTypeBeanList.get(position * 10 + i1));
                    }
                }
            }
            PayTypeFragment fragment = (PayTypeFragment) fragmentDatas.get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("list", (Serializable) list);

            bundle.putInt("position",position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return fragmentDatas.size();
        }
    }
}

