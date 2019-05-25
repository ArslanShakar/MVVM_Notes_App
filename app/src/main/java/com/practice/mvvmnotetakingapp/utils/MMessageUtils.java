package com.practice.mvvmnotetakingapp.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.practice.mvvmnotetakingapp.R;

public class MMessageUtils {

    /***************   Show TOAST Message      ***************/
    public static void toastShow(Context context, String message) {
        Toast.makeText(context, "\n" + message + "\n", Toast.LENGTH_SHORT).show();
    }

    public static void toastShow(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }


    /***************   Show Snack bar Message      ***************/
    public static void snackBar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setActionTextColor(view.getResources().getColor(R.color.colorPrimary));

        snackbar.show();
    }

    public static void snackBar(View view, int resIdMessage) {
        Snackbar snackbar = Snackbar.make(view, resIdMessage, Snackbar.LENGTH_LONG)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        snackbar.show();
    }


}
