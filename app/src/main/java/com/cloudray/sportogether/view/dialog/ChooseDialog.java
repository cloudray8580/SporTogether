package com.cloudray.sportogether.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.view.activity.CreateNewEventActivity;

/**
 * Created by Cloud on 2016/12/6.
 */

public class ChooseDialog extends Dialog {

    Context context;
    int theme;

    public ChooseDialog(Context context) {
        super(context);
        this.context = context;
    }

    public ChooseDialog(Context context, int theme){
        super(context, theme);
        this.context = context;
        this.theme = theme;
    }

    public class Builder{

        private ChooseDialog dialog;

        public ChooseDialog getDialog(){
            if(dialog == null){
                dialog = new ChooseDialog(context, theme);
            }
            return dialog;
        }

        public ChooseDialog create(){
            dialog = getDialog();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.dialog_choose, null);
            Window window = dialog.getWindow();
            WindowManager windowManager = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            findView(layout);

            dialog.setContentView(layout);
            DisplayMetrics dm = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(dm);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = (int)(dm.widthPixels * 0.7);
            lp.height = (int)(dm.heightPixels * 0.3);
            dialog.getWindow().setAttributes(lp);
            return dialog;
        }

        public void findView(View rootView){
            TextView fastjoin = (TextView)rootView.findViewById(R.id.dialog_choose_fast_participate);
            fastjoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // call to server
                    ConfirmPaticipateDialog dialog;
                    ConfirmPaticipateDialog.Builder builder = new ConfirmPaticipateDialog(context, R.style.dialog). new Builder(context);
                    dialog = builder.create();
                    dialog.show();
                    getDialog().cancel();
                }
            });
            TextView createnew = (TextView)rootView.findViewById(R.id.dialog_choose_create_new);
            createnew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CreateNewEventActivity.class);
                    context.startActivity(intent);
                    getDialog().cancel();
                }
            });
        }
    }
}
