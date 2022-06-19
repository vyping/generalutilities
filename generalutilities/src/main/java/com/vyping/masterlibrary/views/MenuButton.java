package com.vyping.masterlibrary.views;

import static android.graphics.Typeface.DEFAULT_BOLD;
import static android.graphics.Typeface.SANS_SERIF;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.Common.MyNumbers;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.Images.MyBitMap;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.R;

public class MenuButton extends androidx.appcompat.widget.AppCompatButton {

    private Context context;

    private int top, iconHeight, titleHeight, subtitleHeight, heightBottom;
    private Drawable backgroundDrawable, iconDrawable;
    private int backgroundColor, iconColor, numberColor, titleColor, subTitleColor;
    private String numberText, titleText, subTitleText;
    private Typeface titleFont, subtitleFont;

    //MenuButton menuButton = new MenuButton(context, R.style.MenuButton);
    // menuButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
    // menuButton.setIcon(com.vyping.masterlibrary.R.drawable.icon_car, getResources().getColor(com.vyping.masterlibrary.R.color.Green));
    // menuButton.setTitle("TITLE", getResources().getColor(com.vyping.masterlibrary.R.color.colorOptionOrange));
    // menuButton.setSubTitle("subtitle", getResources().getColor(com.vyping.masterlibrary.R.color.colorOptionRed));


    // ----- SetUp ----- //

    public MenuButton(Context context) {

        super(context, null, R.style.MenuButton);

        setMenuButton(context, null, R.styleable.menuButton, R.attr.menuButton, R.style.MenuButton);
    }

    public MenuButton(Context context, int attr) {

        super(context, null, R.style.MenuButton);

        setMenuButton(context, null, R.styleable.menuButton, attr, R.style.MenuButton);
    }

    public MenuButton(Context context, int attr, int style) {

        super(context, null);

        setMenuButton(context, null, R.styleable.menuButton, attr, style);
    }

    public MenuButton(Context context, AttributeSet attrsSet) {

        super(context, attrsSet, R.style.MenuButton);

        setMenuButton(context, attrsSet, R.styleable.menuButton, R.attr.menuButton, R.style.MenuButton);
    }

    public MenuButton(Context context, AttributeSet attrsSet, int attr) {

        super(context, attrsSet, R.style.MenuButton);

        setMenuButton(context, null, R.styleable.menuButton, attr, R.style.MenuButton);
    }

    public MenuButton(Context context, AttributeSet attrsSet, int attr, int style) {

        super(context, attrsSet);

        setMenuButton(context, attrsSet, R.styleable.menuButton, attr, style);
    }

    public void setMenuButton(@NonNull Context context, AttributeSet attrsSet, int[] styleable, int attr, int style) {

        this.context = context;
        numberText = "";

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrsSet, styleable, attr, style);

        backgroundDrawable = typedArray.getDrawable(R.styleable.menuButton_backgroundDrawable);
        backgroundColor = typedArray.getColor(R.styleable.menuButton_backgroundColor, getResources().getColor(R.color.Primary));
        iconDrawable = typedArray.getDrawable(R.styleable.menuButton_iconDrawable);
        iconColor = typedArray.getColor(R.styleable.menuButton_iconColor, getResources().getColor(R.color.colorBlanco));
        numberColor = typedArray.getColor(R.styleable.menuButton_numberColor, getResources().getColor(R.color.PrimaryLight10));
        titleText = String.valueOf(typedArray.getText(R.styleable.menuButton_titleText));
        titleColor = typedArray.getColor(R.styleable.menuButton_titleColor, getResources().getColor(R.color.colorBlanco));
        subTitleText = String.valueOf(typedArray.getText(R.styleable.menuButton_subtitleText));
        subTitleColor = typedArray.getColor(R.styleable.menuButton_subtitleColor, getResources().getColor(R.color.PrimaryDark10));

        int titleFontId = typedArray.getResourceId(R.styleable.menuButton_titleFont, R.font.solid);
        int subtitleFontId = typedArray.getResourceId(R.styleable.menuButton_subtitleFont, 0);

        if (titleFontId > 0) {

            titleFont = ResourcesCompat.getFont(context, titleFontId);

        } else {

            titleFont = SANS_SERIF;
        }

        if (subtitleFontId > 0) {

            subtitleFont = ResourcesCompat.getFont(context, subtitleFontId);

        } else {

            subtitleFont = DEFAULT_BOLD;
        }

        setClickable(true);
    }


    // ----- Methods ----- //

    @Override
    protected void onDraw(@NonNull Canvas canvas) {

        top = (int) Math.round(canvas.getHeight() * 0.13);
        iconHeight = (int) Math.round(canvas.getHeight() * 0.4);
        titleHeight = (int) Math.round(canvas.getHeight() * 0.2);
        subtitleHeight = (int) Math.round(canvas.getHeight() * 0.12);
        heightBottom = (int) Math.round(canvas.getHeight() * 0.13);

        drawBackground(canvas);
        drawNumber(canvas);
        drawIcon(canvas);
        drawTitle(canvas);
        drawSubTitle(canvas);

    }

    private void drawBackground(@NonNull Canvas canvas) {

        backgroundDrawable = DrawableCompat.wrap(backgroundDrawable);
        DrawableCompat.setTint(backgroundDrawable, backgroundColor);

        setBackgroundDrawable(backgroundDrawable);

        //int offset = 0;
        //RectF rectF = new RectF(offset, offset, canvas.getWidth() - offset, canvas.getHeight() - offset);
        //int cornersRadius = (int) Math.round(canvas.getHeight()*0.35);

        //Paint paint = new Paint();
        //paint.setStyle(Paint.Style.FILL);
        //paint.setAntiAlias(false);

        //if (touchOn) {

        //    paint.setColor(subTitleColor);

        //} else {

        //    paint.setColor(backgroundColor);
        //}

        //canvas.drawRoundRect(rectF, cornersRadius, cornersRadius, paint);
    }

    private void drawIcon(@NonNull Canvas canvas) {

        int viewWidthHalf = canvas.getWidth() / 2 - (int) Math.round(iconHeight * 0.5);
        int viewHeightHalf = top;

        Paint paint = new Paint();
        paint.setColor(iconColor);
        paint.setAntiAlias(true);
        paint.setColorFilter(new PorterDuffColorFilter(titleColor, PorterDuff.Mode.SRC_ATOP));

        Bitmap bitmap = new MyBitMap().drawableToBitmap(iconDrawable);
        bitmap = new MyBitMap().getResizedBitmap(bitmap, iconHeight, iconHeight);
        bitmap = new MyBitMap().replaceColor(bitmap, Color.BLACK, iconColor);

        canvas.drawBitmap(bitmap, viewWidthHalf, viewHeightHalf, paint);
    }

    private void drawNumber(@NonNull Canvas canvas) {

        if (!numberText.equals("")) {

            int viewWidthHalf = canvas.getWidth() - 2 * top;
            int viewHeightHalf = 2 * top;
            int radius = top * 4 / 5;
            int textSize = (titleHeight + subtitleHeight) * 2 / 5;

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(numberColor);
            paint.setAntiAlias(true);

            canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, paint);

            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(textSize);
            paint.setTypeface(titleFont);
            paint.setColor(backgroundColor);

            canvas.drawText(numberText, viewWidthHalf, viewHeightHalf + radius - textSize / 2, paint);
        }
    }

    private void drawTitle(@NonNull Canvas canvas) {

        int viewWidthHalf = this.getMeasuredWidth() / 2;
        int viewHeightHalf = top + iconHeight + titleHeight;

        Paint paint = new Paint();
        paint.setColor(titleColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(titleHeight);
        paint.setTypeface(titleFont);

        canvas.drawText(titleText, viewWidthHalf, viewHeightHalf, paint);
    }

    private void drawSubTitle(@NonNull Canvas canvas) {

        int viewWidthHalf = this.getMeasuredWidth() / 2;
        int viewHeightHalf = canvas.getHeight() - heightBottom;

        Paint paint = new Paint();
        paint.setColor(subTitleColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(subtitleHeight);
        paint.setTypeface(subtitleFont);

        canvas.drawText(subTitleText, viewWidthHalf, viewHeightHalf, paint);
    }


    // ----- Listeners ----- //

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {

        super.onTouchEvent(event);

        int action = event.getActionMasked();

        if (action == MotionEvent.ACTION_DOWN) {

            new MyAnimation().ScaleAmpliate(this, 100, 0.9f, 1.05f, 0.9f, 1.05f);

        } else if (action == MotionEvent.ACTION_OUTSIDE) {

            new MyAnimation().ScaleAmpliate(this, 100, 1.05f, 1.0f, 1.05f, 1.0f);

        } else if (action == MotionEvent.ACTION_CANCEL) {

            new MyAnimation().ScaleAmpliate(this, 100, 1.05f, 1.0f, 1.05f, 1.0f);

        } else if (action == MotionEvent.ACTION_UP) {

            new MyAnimation().ScaleAmpliate(this, 100, 1.05f, 1.0f, 1.05f, 1.0f);
        }

        return true;
    }


    // ----- Get Methods ----- //

    public Drawable getBackgroundDrawable() {

        return backgroundDrawable;
    }

    public int getBackgroundColor() {

        return backgroundColor;
    }

    public Drawable getIconDrawable() {

        return iconDrawable;
    }

    public int geNumberColor() {

        return numberColor;
    }

    public String getNumberString() {

        return numberText;
    }

    public int getNumberInteger() {

        return new MyNumbers().stringToInteger(numberText);
    }

    public int getIconColor() {

        return iconColor;
    }

    public String getTitleText() {

        return titleText;
    }

    public int getTitleColor() {

        return titleColor;
    }

    public String getSubTitleText() {

        return subTitleText;
    }

    public int getSubTitleColor() {

        return subTitleColor;
    }

    public Typeface getFontTitle() {

        return titleFont;
    }

    public Typeface getFontSubTitle() {

        return subtitleFont;
    }


    // ----- Set Methods ----- //

    public void setBackgroundDrawables(int backgroundDrawable) {

        this.backgroundDrawable = new MyDrawable().extractFromResources(context, backgroundDrawable);

        invalidate();
    }

    public void setBackgroundDrawables(Drawable backgroundDrawable) {

        this.backgroundDrawable = backgroundDrawable;

        invalidate();
    }

    public void setBackgroundColor(int backgroundColor) {

        this.backgroundColor = backgroundColor;

        backgroundDrawable = DrawableCompat.wrap(backgroundDrawable);
        DrawableCompat.setTint(backgroundDrawable, backgroundColor);

        setBackgroundDrawable(backgroundDrawable);

        invalidate();
    }

    public void setIcon(int iconDrawable, int iconColor) {

        this.iconColor = iconColor;
        this.iconDrawable = new MyDrawable().extractFromResources(context, iconDrawable);


        invalidate();
    }

    public void setIcon(Drawable iconDrawable, int iconColor) {

        this.iconDrawable = iconDrawable;
        this.iconColor = iconColor;

        invalidate();
    }

    public void setIconDrawable(int iconDrawable) {

        this.iconDrawable = new MyDrawable().extractFromResources(context, iconDrawable);

        invalidate();
    }

    public void setIconDrawable(Drawable iconDrawable) {

        this.iconDrawable = iconDrawable;

        invalidate();
    }

    public void setIconColor(int iconColor) {

        this.iconColor = iconColor;

        invalidate();
    }

    public void setNumber(String numberText) {

        this.numberText = numberText;

        invalidate();
    }

    public void setNumber(int numberText) {

        this.numberText = String.valueOf(numberText);

        invalidate();
    }

    public void setNumberColor(int numberColor) {

        this.numberColor = numberColor;

        invalidate();
    }

    public void setTitle(int titleText, int titleColor) {

        this.titleText = new MyString().getStringResources(context, titleText);
        this.titleColor = titleColor;

        invalidate();
    }

    public void setTitle(String titleText, int titleColor) {

        this.titleText = titleText;
        this.titleColor = titleColor;

        invalidate();
    }

    public void setTitleText(int titleText) {

        this.titleText = new MyString().getStringResources(context, titleText);

        invalidate();
    }

    public void setTitleText(String titleText) {

        this.titleText = titleText;

        invalidate();
    }

    public void setTitleColor(int titleColor) {

        this.titleColor = titleColor;

        invalidate();
    }

    public void setSubTitle(String subTitleText, int subTitleColor) {

        this.subTitleText = subTitleText;
        this.subTitleColor = subTitleColor;

        invalidate();
    }

    public void setSubTitleText(String subTitleText) {

        this.subTitleText = subTitleText;

        invalidate();
    }

    public void setSubTitleColor(int subTitleColor) {

        this.subTitleColor = subTitleColor;

        invalidate();
    }

    public void setTitleFont(int titleFont) {

        this.titleFont = ResourcesCompat.getFont(getContext(), titleFont);

        invalidate();
    }

    public void setSubTitleFont(int subtitleFont) {

        this.subtitleFont = ResourcesCompat.getFont(getContext(), subtitleFont);

        invalidate();
    }
}
