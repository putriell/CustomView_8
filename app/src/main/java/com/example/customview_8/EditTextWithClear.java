package com.example.customview_8;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class EditTextWithClear extends AppCompatEditText {

    Drawable mClearButtonImage;
    private void init() {
        mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.clear_opaque, null);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showClearButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                //mengechek posisi dari komponen clearbutton
                if (getCompoundDrawablesRelative()[2] != null) {
                    float clearButtonStartPosition = (getWidth() - getPaddingEnd() - mClearButtonImage.getIntrinsicWidth());
                    boolean isButtonClicked = false;

                    //koordinat button yang dipencet
                    if (motionEvent.getX() > clearButtonStartPosition) {
                        isButtonClicked = true;
                    }

                    if (isButtonClicked) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            mClearButtonImage = ResourcesCompat.getDrawable
                                    (getResources(), R.drawable.clear, null);
                            showClearButton();
                        }
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(),
                                    R.drawable.clear_opaque,null);
                            getText().clear();
                            hideClearButton();
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
                return false;
                }
        });
    }

    public EditTextWithClear(@NonNull Context context) {
        super(context);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //membuat fungsi untuk show ddan hide clear button
    private void showClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds
                (null, null, mClearButtonImage, null);
    }

    private void hideClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }

    //tugas: menggunakan bahasa arab maka x button di sebelah kiri
}
