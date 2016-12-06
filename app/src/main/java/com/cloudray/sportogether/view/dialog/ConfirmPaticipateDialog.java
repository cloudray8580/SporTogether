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
import android.widget.TextView;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.model.Event;

/**
 * Created by Cloud on 2016/11/26.
 */

public class ConfirmPaticipateDialog extends Dialog {

    Event event;
    TextView typeText, needText, spotText, timeText, hostText, callText, descriptionText, joinText;

    public ConfirmPaticipateDialog(Context context, int theme) {
        super(context, theme);
    }

    public ConfirmPaticipateDialog(Context context, int theme, Event event){
        super(context, theme);
        this.event = event;
    }

    public void setEvent(Event event){
        this.event = event;
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
//            screen = MyBlurEffect(screen);

            dialog = getDialog();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.dialog_event_confirm, null);

//            LinearLayout container = (LinearLayout)layout.findViewById(R.id.dialog_event_confirm_container);
//            container.setBackground(new BitmapDrawable(context.getResources(), screen));

            //findView(layout);
            //setMyText();

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

        public void findView(View rootView){
            typeText = (TextView)rootView.findViewById(R.id.dialog_event_confirm_sport_type);
            needText = (TextView)rootView.findViewById(R.id.dialog_event_confirm_required_people_number);
            spotText = (TextView)rootView.findViewById(R.id.dialog_event_confirm_location);
            timeText = (TextView)rootView.findViewById(R.id.dialog_event_confirm_time);
            hostText = (TextView)rootView.findViewById(R.id.dialog_event_confirm_creator);
            callText = (TextView)rootView.findViewById(R.id.dialog_event_confirm_phone);
            descriptionText = (TextView)rootView.findViewById(R.id.dialog_event_confirm_description);
            joinText = (TextView)rootView.findViewById(R.id.dialog_event_confirm_join);
            joinText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // call join method
                }
            });
        }

        public void setMyText(){
            switch (event.getType()){
                case 1:
                    typeText.setText("Basketball");
                    break;
                case 2:
                    typeText.setText("Football");
                    break;
                case 3:
                    typeText.setText("Running");
                    break;
                default:
                    break;
            }
            needText.setText(event.getRequiredPlayerNumber());
            spotText.setText(event.getLocation());
            timeText.setText(event.getTime().toString());
            hostText.setText(event.getUserName());
            callText.setText(event.getPhone());
            descriptionText.setText(event.getEventDescription());
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
