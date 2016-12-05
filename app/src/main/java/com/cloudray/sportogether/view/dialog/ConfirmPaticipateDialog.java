package com.cloudray.sportogether.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.DrawableContainer;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.cloudray.sportogether.R;

/**
 * Created by Cloud on 2016/11/26.
 */

public class ConfirmPaticipateDialog extends Dialog {
    public ConfirmPaticipateDialog(Context context, int theme) {
        super(context, theme);
    }

    public class Builder{
        private Context context;
        private ConfirmPaticipateDialog dialog;
        private View view;

        public Builder(Context context){
            this.context = context;
        }

        public Builder(Context context, View view){
            this.context = context;
            this.view = view;
        }

        public ConfirmPaticipateDialog getDialog(){
            if(dialog == null) {
                dialog = new ConfirmPaticipateDialog(context, R.style.dialog);
//                dialog.setOnCancelListener(new OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                    }
//                });
            }
            return dialog;
        }

        public ConfirmPaticipateDialog create(){

//            view.setDrawingCacheEnabled(true);
//            view.buildDrawingCache();
//            Bitmap screen = Bitmap.createBitmap(view.getDrawingCache());
//            view.setDrawingCacheEnabled(false);
//
//            screen = MyBlurEffect(screen);

            dialog = getDialog();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.dialog_event_confirm, null);

//            LinearLayout container = (LinearLayout)layout.findViewById(R.id.dialog_event_confirm_container);
//            container.setBackground(new BitmapDrawable(context.getResources(), screen));

            Window window = dialog.getWindow();
            WindowManager windowManager = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.setContentView(layout);//!!!!!!!!!!!!!!!!!!!!!!!!!!!

            DisplayMetrics dm = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(dm);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = (int)(dm.widthPixels * 0.8);
            lp.height = (int)(dm.heightPixels * 0.6);
            dialog.getWindow().setAttributes(lp);

            return dialog;
        }


        /*
        public Bitmap MyBlurEffect(Bitmap sentBitmap){
            Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

            final RenderScript rs = RenderScript.create(context);
            final Allocation input = Allocation.createFromBitmap(rs, sentBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT);
            final Allocation output = Allocation.createTyped(rs, input.getType());
            final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setRadius(3.f);
            script.setInput(input);
            script.forEach(output);
            output.copyTo(bitmap);
            return bitmap;
        }
        */
    }
}
