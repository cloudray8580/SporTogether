package com.cloudray.sportogether.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cloudray.sportogether.R;

/**
 * Created by Cloud on 2016/11/26.
 */

public class ConfirmPaticipateDialog extends Dialog {
    public ConfirmPaticipateDialog(Context context) {
        super(context);
    }

    public class Builder{
        private Context context;
        private ConfirmPaticipateDialog dialog;

        public Builder(Context context){
            this.context = context;
        }

        public ConfirmPaticipateDialog getDialog(){
            if(dialog == null)
                dialog = new ConfirmPaticipateDialog(context);
            return dialog;
        }

        public ConfirmPaticipateDialog create(){



            dialog = getDialog();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.dialog_event_confirm, null);

            Window window = dialog.getWindow();
            WindowManager windowManager = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            DisplayMetrics dm = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(dm);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = (int)(dm.widthPixels * 0.8);
            lp.height = (int)(dm.heightPixels * 0.8);
            dialog.getWindow().setAttributes(lp);

            return dialog;
        }
    }
}
