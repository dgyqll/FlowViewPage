package com.etouse.flowviewpage.act;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.etouse.flowviewpage.R;
import com.etouse.flowviewpage.bean.PayTypeBean;
import com.etouse.flowviewpage.fragment.PayTypeFragment;
import com.etouse.flowviewpage.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private List<PayTypeBean> sourceList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,3000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                initData();
                initDb();
            }
        }).start();
    }

    private void initData() {
        sourceList.add(new PayTypeBean(R.drawable.bk_hufu,"护肤","1"));
        sourceList.add(new PayTypeBean(R.drawable.bk_jiaoyu,"教育","1"));
        sourceList.add(new PayTypeBean(R.drawable.bk_jiechu,"借出","1"));
        sourceList.add(new PayTypeBean(R.drawable.bk_jiehun,"结婚","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_jucan,"聚餐","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_licai,"理财","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_lvxing,"旅行","1"));
        sourceList.add(new PayTypeBean(R.drawable.bk_moren,"默认","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_movie,"电影","1"));
        sourceList.add(new PayTypeBean(R.drawable.bk_pet,"宠物","1"));
        sourceList.add(new PayTypeBean(R.drawable.bk_qiankuan,"欠款","1"));
        sourceList.add(new PayTypeBean(R.drawable.bk_qiche,"汽车","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_renqing,"人情","1"));
        sourceList.add(new PayTypeBean(R.drawable.bk_school,"学习","1"));
        sourceList.add(new PayTypeBean(R.drawable.bk_shenghuo,"生活","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_shengyi,"生意","1"));
        sourceList.add(new PayTypeBean(R.drawable.bk_shopping,"购物","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_shucai,"买菜","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_shui,"饮料","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_sport,"运动","1"));
        sourceList.add(new PayTypeBean(R.drawable.bk_taobao,"淘宝","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_weixiu,"维修","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_xiebao,"鞋包","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_yifu,"衣服","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_yiliao,"医疗","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_yinger,"育婴","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_yingye,"营业","0"));
        sourceList.add(new PayTypeBean(R.drawable.bk_tianjia,"添加","1"));
    }


    private void initDb() {
        List<PayTypeBean> pList = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(Uri.parse("content://com.etouse.flow.provider/t_pay_type"), new String[]{"_pid","_name","_used"}, null, null, null);
        while(cursor.moveToNext()){
            PayTypeBean payTypeBean = new PayTypeBean();
            payTypeBean.setId(cursor.getInt(0));
            payTypeBean.setName(cursor.getString(1));
            payTypeBean.setUsed(cursor.getString(2));
            pList.add(payTypeBean);
        }
        cursor.close();

        for (int i = 0; i < sourceList.size(); i++) {
            boolean exist = false;
            for (int i1 = 0; i1 < pList.size(); i1++) {
                if (sourceList.get(i).getId()== pList.get(i1).getId()) {
                    exist = true;
                }
            }
            if (exist == false) {
                ContentValues values = new ContentValues();
                values.put("_pid",sourceList.get(i).getId());
                values.put("_name", sourceList.get(i).getName());
                values.put("_used", sourceList.get(i).getUsed());

                Uri insert = contentResolver.insert(Uri.parse("content://com.etouse.flow.provider/t_pay_type"), values);
            }
        }

    }
}

