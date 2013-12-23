
package com.likebamboo.widget;

/**************************************************************************************
 * [Project]：自定义Dialog（alertDialog 与  ProgressDialog）
 * [Package]：com.example.customdialog
 * [FileName]:CustomProgressDialog.java
 * [Copyright]:Copyright 2013 LI Wentao All Rights Reserved.
 * [History]
 *       Version          Date              Author                            Record
 *--------------------------------------------------------------------------------------
 *       1.0.0           2013-5-13         liwt (likebabooo@gmail.com)        Create
 **************************************************************************************/

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.likebamboo.customdialog.R;

/********************************************************************
 * [Summary] TODO 请在此处简要描述此类所实现的功能。因为这项注释主要是为了在IDE环境中生成tip帮助，务必简明扼要 [Remarks]
 * TODO 请在此处详细描述类的功能、调用方法、注意事项、以及与其它类的关系.
 *******************************************************************/

public class CustomProgressDialog extends Dialog {
    private Context context = null;

    private static CustomProgressDialog customProgressDialog = null;

    public CustomProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public static CustomProgressDialog createDialog(Context context) {
        customProgressDialog = new CustomProgressDialog(context, R.style.CustomProgressDialog);
        customProgressDialog.setContentView(R.layout.custom_progress_dialog);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

        return customProgressDialog;
    }

    public void onWindowFocusChanged(boolean hasFocus) {

        if (customProgressDialog == null) {
            return;
        }

        ImageView imageView = (ImageView)customProgressDialog.findViewById(R.id.progress_dialog_iv);
        // AnimationDrawable animationDrawable = (AnimationDrawable) imageView
        // .getBackground();
        // animationDrawable.start();
        if (context != null) {
            // 加载动画
            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.custom_progress_loading);
            // 使用ImageView显示动画
            imageView.startAnimation(animation);
        }
    }

    /**
     * [Summary] setTitile 标题
     * 
     * @param strTitle
     * @return
     */
    public CustomProgressDialog setTitile(String strTitle) {
        return customProgressDialog;
    }

    /**
     * [Summary] setMessage 提示内容
     * 
     * @param strMessage
     * @return
     */
    public CustomProgressDialog setMessage(String strMessage) {
        TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.progress_dialog_tv);

        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }

        return customProgressDialog;
    }

    /**
     * [Summary] setMessage 提示内容
     * 
     * @param strMessage
     * @return
     */
    public CustomProgressDialog setMessage(int resId) {
        TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.progress_dialog_tv);

        if (tvMsg != null) {
            tvMsg.setText(context.getString(resId));
        }

        return customProgressDialog;
    }
}
