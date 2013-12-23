/**************************************************************************************
 * [Project]：CustomDialog，自定义Dialog（alertDialog 与  ProgressDialog）
 * [Package]：com.example.customdialog
 * [Copyright]:Copyright 2013 LI Wentao All Rights Reserved.
 * [History]
 * [Reference]:ProgressDialog:http://www.open-open.com/lib/view/open1335576985577.html
 * 							  http://blog.csdn.net/qjlhlh/article/details/7979179
 * 			   AlertDialog:http://blog.csdn.net/huzgd/article/details/8155574
 *       Version          Date              Author                            Record
 *--------------------------------------------------------------------------------------
 *       1.0.0           2013-5-13         liwt (likebaboo@163.com)        Create
 **************************************************************************************/

package com.likebambo.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.likebamboo.customdialog.R;
import com.likebamboo.widget.CustomAlertDialog;
import com.likebamboo.widget.CustomProgressDialog;

/**
 * 主Activity
 * 
 * @author liwt
 */
public class MainActivity extends Activity {
    /** 异步任务 */
    private MainFrameTask mMainFrameTask = null;

    /** 自定义的ProgressDialog */
    private CustomProgressDialog progressDialog = null;

    /** 数据源 */
    private String[] items = {
            "Item-1", "Item-2", "Item-3", "Item-4", "Item-5"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置窗口无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置布局
        setContentView(R.layout.main);

    }

    @Override
    protected void onDestroy() {
        // 停止显示ProgressDialog
        stopProgressDialog();
        // 如果当前异步任务正在执行，打断之
        if (mMainFrameTask != null && !mMainFrameTask.isCancelled()) {
            mMainFrameTask.cancel(true);
        }
        super.onDestroy();
    }

    /**
     * 显示ProgressDialog
     */
    private void startProgressDialog() {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.createDialog(this);
            progressDialog.setMessage(R.string._loading);
        }
        progressDialog.show();
    }

    /**
     * 停止显示ProgressDialog
     */
    private void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * 异步任务
     * 
     * @author liwt
     */
    public class MainFrameTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected void onCancelled() {
            stopProgressDialog();
            super.onCancelled();
        }

        @Override
        protected String doInBackground(Integer... params) {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            // 执行前显示ProgressDialog
            startProgressDialog();
        }

        @Override
        protected void onPostExecute(String result) {
            // 执行完后停止显示ProgressDialog
            stopProgressDialog();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

    }

    /**
     * 显示ProgressDialog的回调函数
     * 
     * @param v 按钮
     */
    public void showProgressDialog(View v) {
        stopProgressDialog();
        if (mMainFrameTask != null && !mMainFrameTask.isCancelled()) {
            mMainFrameTask.cancel(false);
        }
        mMainFrameTask = new MainFrameTask();
        mMainFrameTask.execute();
    }

    /**
     * 显示一般AlertDialog的回调函数
     * 
     * @param v
     */
    public void showAlertDialogTips(View v) {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.setTitle(R.string.tips).setMessage(R.string.message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton("Drop dead", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 显示单选按钮的AlertDialog的回调函数
     * 
     * @param v
     */
    public void showAlertDialogRadio(View v) {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.setTitle(R.string.tips).setTitle(R.string.custom_alert_dialog_singChoice)
                .setSingleChoiceItems(items, 0, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton("Drop dead", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 显示多选按钮的Alertdialog的回调函数
     * 
     * @param v
     */
    public void showAlertDialogCheckBox(View v) {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.setTitle(R.string.tips).setTitle(R.string.custom_alert_dialog_multiChoice)
                .setMultiChoiceItems(items, null, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton("Drop dead", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 显示列表Alertdialog的回调函数
     * 
     * @param v
     */
    public void showAlertDialogList(View v) {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.setTitle(R.string.tips).setTitle(R.string.custom_alert_dialog_list)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "why me？--" + which,
                                Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton("Drop dead", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }

}
