package com.etouse.flowviewpage.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.etouse.flowviewpage.R;
import com.etouse.flowviewpage.bean.PayTypeBean;
import com.etouse.flowviewpage.util.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */

public class PayTypeFragment extends Fragment {
    private List<PayTypeBean> mDatas = new ArrayList<>();
    private int position;
    private GridView gv;
    public MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        mDatas = (List<PayTypeBean>) arguments.get("list");
        position = arguments.getInt("position");

        View view = View.inflate(getActivity(), R.layout.fragment_paytype, null);
        gv = (GridView) view.findViewById(R.id.gv);
        myAdapter = new MyAdapter();
        gv.setAdapter(myAdapter);
        return view;

    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int i) {
            return mDatas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(getActivity());
            GridView.LayoutParams param = new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            imageView.setLayoutParams(param);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE );
            imageView.setPadding(8, 8, 8, 8);
            imageView.setImageResource(mDatas.get(i).getId());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showToast(getActivity(),mDatas.get(i).getName());
                }
            });
            return imageView;
        }
    }

}
