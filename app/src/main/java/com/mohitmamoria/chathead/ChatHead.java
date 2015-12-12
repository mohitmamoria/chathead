package com.mohitmamoria.chathead;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatHead extends Service {

    private WindowManager windowManager;
    private ImageView chatHead;
    private View cardView;
    private ImageView chatHead2;

    public ChatHead() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Not User
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        // Layout Inflater
        LayoutInflater layoutInflater = (LayoutInflater) this.getApplicationContext().getSystemService(
                LAYOUT_INFLATER_SERVICE
        );

        cardView = layoutInflater.inflate(R.layout.card, null);

        TextView text = (TextView) cardView.findViewById(R.id.notification_text);
        text.setText("Blah Blah");

        EditText replyBox = (EditText) cardView.findViewById(R.id.notification_reply_box);
//        replyBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("FOCUS_1", ((TextView) v).hasFocus() + "");
//            }
//        });

        replyBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (hasFocus) {
                    //show keyboard
                    Log.i("FOCUS_show", "To show Keyboard");
//                    imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
                } else {
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                    //hide keyboard
                    Log.i("FOCUS_hide", "To hide Keyboard");
                }
            }
        });

//        chatHead = new ImageView(this);
//        chatHead.setImageResource(R.drawable.android_head);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
//                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
//                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//                        | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR,
                PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 50;
        params.y = 100;

        windowManager.addView(cardView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(chatHead != null) windowManager.removeView(chatHead);
    }
}
