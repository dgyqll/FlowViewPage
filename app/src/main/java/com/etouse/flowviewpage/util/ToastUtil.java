package com.etouse.flowviewpage.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.etouse.flowviewpage.R;


public class ToastUtil {
	private static Toast toast;
	/**
	 * 强大的吐司，能够连续弹的吐司
	 * @param text
	 */
	public static void showToast(final Context context, final String text){
		Utils.runOnUIThread(new Runnable() {
			@Override
			public void run() {
				if(toast==null){
					toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER,0,0);
					LinearLayout toastView = (LinearLayout) toast.getView();
					toastView.setBackgroundResource(R.drawable.toast_backgroud);

				}else {
					toast.setText(text);//如果不为空，则直接改变当前toast的文本
				}
				toast.show();
			}
		});

	}
}
