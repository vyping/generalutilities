package com.vyping.masterlibrary.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vyping.masterlibrary.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Dialogs extends FrameLayout {

    private final Context context;
    private OptionsClickListener interfase;

    private LinearLayout Ll_Container;
    private TextView Tv_IconTitle, Tv_TextTitle, Tv_Info;
    private Button Btn_Info, Btn_Negative, Btn_Positive;

    private static String IconTitle, TextTitle, IconInfo, IconBack, Information, Description, Positive, Negative;
    public static final int BUTTONS_NONE = 0, BUTTONS_SINGLE = 1, BUTTONS_BOTH = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({BUTTONS_NONE, BUTTONS_SINGLE, BUTTONS_BOTH})
    public @interface ModeButtons {
    }


    /*----- SetUp -----*/

    public Dialogs(@NonNull Context context) {

        super(context);

        this.context = context;

        setDalogViews();
    }

    public Dialogs(@NonNull Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        this.context = context;

        setDalogViews();
    }

    public Dialogs(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        this.context = context;

        setDalogViews();
    }

    private void setDalogViews() {

        inflate(context, R.layout.view_dialog, this);

        Tv_IconTitle = findViewById(R.id.Tv_Custom_IconTitle);
        Tv_TextTitle = findViewById(R.id.Tv_Custom_TextTitle);
        Btn_Info = findViewById(R.id.Btn_Custom_IconInfo);
        Tv_Info = findViewById(R.id.Tv_Custom_Information);
        Ll_Container = findViewById(R.id.Ll_Custom_MainContainer);
        Btn_Negative = findViewById(R.id.Btn_Custom_Negative);
        Btn_Positive = findViewById(R.id.Btn_Custom_Positive);
    }

    public void setLabels(int labels) {

        String[] ListParams = context.getResources().getStringArray(labels);
        IconTitle = ListParams[0];
        TextTitle = ListParams[1];
        Information = ListParams[2];
        Description = ListParams[3];
        Negative = ListParams[4];
        Positive = ListParams[5];

        IconInfo = context.getResources().getString(R.string.iconinfo);
        IconBack = context.getResources().getString(R.string.iconback);

        Tv_IconTitle.setText(IconTitle);
        Tv_TextTitle.setText(TextTitle);
        Btn_Info.setText(IconInfo);
        Tv_Info.setText(Information);
        Btn_Negative.setText(Negative);
        Btn_Positive.setText(Positive);

        Btn_Info.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                String icon = String.valueOf(Btn_Info.getText());

                if (icon.equals(IconInfo)) {

                    Btn_Info.setText(IconBack);
                    Tv_Info.setText(Description);
                    Ll_Container.setVisibility(GONE);

                } else {

                    Btn_Info.setText(IconInfo);
                    Tv_Info.setText(Information);
                    Ll_Container.setVisibility(VISIBLE);
                }
            }

            private void DummyVoid() {
            }
        });
    }

    public void setLabels(String icon, String title, String instructions, String description, String negative, String positive) {

        IconTitle = icon;
        TextTitle = title;
        Information = instructions;
        Description = description;
        Negative = negative;
        Positive = positive;

        IconInfo = context.getResources().getString(R.string.iconinfo);
        IconBack = context.getResources().getString(R.string.iconback);

        Tv_IconTitle.setText(icon);
        Tv_TextTitle.setText(title);
        Btn_Info.setText(instructions);
        Tv_Info.setText(description);
        Btn_Negative.setText(negative);
        Btn_Positive.setText(positive);
    }

    public void setModeButtons(@ModeButtons int mode) {

        if (mode == BUTTONS_NONE) {

            Btn_Negative.setVisibility(GONE);
            Btn_Positive.setVisibility(GONE);

        } else if (mode == BUTTONS_SINGLE) {

            Btn_Positive.setText(Negative);

            Btn_Negative.setVisibility(GONE);
            Btn_Positive.setVisibility(VISIBLE);

            Btn_Positive.setOnClickListener(NegativeClickListener);

        } else if (mode == BUTTONS_BOTH) {

            Btn_Negative.setText(Negative);
            Btn_Positive.setText(Positive);

            Btn_Negative.setVisibility(VISIBLE);
            Btn_Positive.setVisibility(VISIBLE);

            Btn_Negative.setOnClickListener(NegativeClickListener);
            Btn_Positive.setOnClickListener(PositiveClickListener);
        }
    }

    public void setOptionsClickListener(OptionsClickListener listener) {

        interfase = listener;
    }


    /*----- Listeners -----*/

    private final OnClickListener NegativeClickListener = new OnClickListener() {

        @Override
        public void onClick(View view) {

            interfase.ClickOnNegative();
        }
    };

    private final OnClickListener PositiveClickListener = new OnClickListener() {

        @Override
        public void onClick(View view) {

            interfase.ClickOnPositive();
        }
    };


    /*----- Interface -----*/

    public interface OptionsClickListener {

        void ClickOnNegative();

        void ClickOnPositive();
    }
}