package com.vyping.masterlibrary.views.customs;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.ImageView.ScaleType.FIT_CENTER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;

import com.vyping.masterlibrary.Common.MyDisplay;
import com.vyping.masterlibrary.Common.MyKeyboard;
import com.vyping.masterlibrary.Common.MyNumbers;
import com.vyping.masterlibrary.Common.MyToast;
import com.vyping.masterlibrary.GestureListeners.MyGesturesListener;
import com.vyping.masterlibrary.Images.MyColor;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.R;

public class MyModifyCounter extends LinearLayout {

    private Interfase interfase;

    private ImageButton Ibtn_Quit, Ibtn_Add;
    private EditText Et_Quantity;

    private String quantityString;
    private int eraseSize, erasePadding, quitSize, quitPadding, quantityInteger, quantityWight, quantityMargin, quantityColor, addSize, addPadding;
    private float quantitySize;
    private Drawable drawableErase, backgroundErase, drawableQuit, backgroundQuit, drawableAdd, backgroundAdd;
    private boolean quantityEnabled, erase, disccount;


    // ----- SetUp ----- //

    public MyModifyCounter(Context context) {

        super(context);
    }

    public MyModifyCounter(Context context, AttributeSet attrs) {

        super(context, attrs);

        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ModifyCounter, 0, 0);

        try {

            int backgroundResource = typeArray.getResourceId(R.styleable.ModifyCounter_background_drawable, R.drawable.icon_circle);

            addSize = typeArray.getDimensionPixelSize(R.styleable.ModifyCounter_add_size, 84);
            addPadding = typeArray.getDimensionPixelSize(R.styleable.ModifyCounter_add_padding, 12);
            int addResource = typeArray.getResourceId(R.styleable.ModifyCounter_add_drawable, R.drawable.icon_add);
            int addColor = typeArray.getColor(R.styleable.ModifyCounter_add_color, new MyColor().extractFromResources(getContext(), R.color.Black));

            quantityString = typeArray.getString(R.styleable.ModifyCounter_quantity_string);
            quantityInteger = typeArray.getInt(R.styleable.ModifyCounter_quantity_integer, 0);
            quantityWight = typeArray.getLayoutDimension(R.styleable.ModifyCounter_quantity_width, WRAP_CONTENT);
            quantitySize = typeArray.getDimension(R.styleable.ModifyCounter_quantity_size, 18);
            quantityMargin = typeArray.getDimensionPixelSize(R.styleable.ModifyCounter_quantity_margin, 12);
            quantityColor = typeArray.getColor(R.styleable.ModifyCounter_quantity_color, addColor);
            quantityEnabled = typeArray.getBoolean(R.styleable.ModifyCounter_quantity_enabled, TRUE);

            quitSize = typeArray.getDimensionPixelSize(R.styleable.ModifyCounter_quit_size, addSize);
            quitPadding = typeArray.getDimensionPixelSize(R.styleable.ModifyCounter_quit_padding, addPadding);
            int quitResource = typeArray.getResourceId(R.styleable.ModifyCounter_quit_drawable, R.drawable.icon_minus);
            int quitColor = typeArray.getColor(R.styleable.ModifyCounter_quit_color, addColor);

            eraseSize = typeArray.getDimensionPixelSize(R.styleable.ModifyCounter_erase_size, addSize);
            erasePadding = typeArray.getDimensionPixelSize(R.styleable.ModifyCounter_erase_padding, addPadding);
            int eraseResource = typeArray.getResourceId(R.styleable.ModifyCounter_erase_drawable, R.drawable.icon_trash);
            int eraseColor = typeArray.getColor(R.styleable.ModifyCounter_erase_color, addColor);

            erase = typeArray.getBoolean(R.styleable.ModifyCounter_erase, FALSE);
            disccount = typeArray.getBoolean(R.styleable.ModifyCounter_disccount, TRUE);

            drawableAdd = new MyDrawable().extractFromResources(getContext(), addResource);
            backgroundAdd = new MyDrawable().extractFromResources(getContext(), backgroundResource);
            drawableQuit = new MyDrawable().extractFromResources(getContext(), quitResource);
            backgroundQuit = new MyDrawable().extractFromResources(getContext(), backgroundResource);
            drawableErase = new MyDrawable().extractFromResources(getContext(), eraseResource);
            backgroundErase = new MyDrawable().extractFromResources(getContext(), backgroundResource);

            DrawableCompat.setTint(drawableAdd, addColor);
            DrawableCompat.setTint(backgroundAdd, addColor);
            DrawableCompat.setTint(drawableQuit, quitColor);
            DrawableCompat.setTint(backgroundQuit, quitColor);
            DrawableCompat.setTint(drawableErase, eraseColor);
            DrawableCompat.setTint(backgroundErase, eraseColor);

            if (quantityString == null) {

                quantityString = "";
            }

        } finally {

            typeArray.recycle();
        }

        setOrientation(HORIZONTAL);
        setGravity(CENTER);

        setQuitButton();
        setQuantityText();
        setAddButton();
    }

    public MyModifyCounter(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }


    // ----- Internal Methods ----- //

    private void setQuitButton() {

        Ibtn_Quit = new ImageButton(getContext());
        Ibtn_Quit.setImageDrawable(drawableQuit);
        Ibtn_Quit.setBackground(backgroundQuit);
        Ibtn_Quit.setScaleType(FIT_CENTER);
        Ibtn_Quit.setVisibility(GONE);
        Ibtn_Quit.setPadding(quitPadding, quitPadding, quitPadding, quitPadding);

        LayoutParams param = new LayoutParams(quitSize, quitSize, 0f);
        Ibtn_Quit.setLayoutParams(param);

        new MyGesturesListener(Ibtn_Quit, new MyGesturesListener.Interfase() {

            @Override
            public void OnClick(@NonNull View view) {

                if (disccount) {

                    String number = String.valueOf(Et_Quantity.getText());
                    int Number = new MyNumbers().stringToInteger(number);

                    if (Number > 0) {

                        int newNumber = Number - 1;
                        quantityString = String.valueOf(newNumber);
                        Et_Quantity.setText(quantityString);

                        setButtonsAfterChange();

                        interfase.ClickOnQuitButton(newNumber);
                        interfase.ClickOnQuitButton(MyModifyCounter.this, newNumber);

                    } else {

                        interfase.ClickOnEraseButton();
                        interfase.ClickOnEraseButton(MyModifyCounter.this);
                    }

                } else {

                    new MyToast(getContext(), "¡No puedes hacer esto!");
                }
            }

            private void DummyVoid() {}
        });

        addView(Ibtn_Quit);
    }

    private void setQuantityText() {

        Et_Quantity = new EditText(getContext());
        Et_Quantity.setTextColor(quantityColor);
        Et_Quantity.setTextSize(new MyDisplay().pxsToDps(getContext(), quantitySize));
        Et_Quantity.setTypeface(Et_Quantity.getTypeface(), Typeface.BOLD);
        Et_Quantity.setInputType(InputType.TYPE_CLASS_NUMBER);
        Et_Quantity.setImeOptions(EditorInfo.IME_ACTION_DONE);
        Et_Quantity.setOnEditorActionListener(quantityListener);
        Et_Quantity.setEnabled(quantityEnabled);

        if (!quantityString.equals("") && quantityInteger == 0) {

            boolean isNumber = new MyNumbers().isNumber(quantityString);
            Et_Quantity.setText(isNumber ? quantityString : "0");

            setButtonsAfterChange();

        } else {

            quantityString = new MyNumbers().integerToString(quantityInteger);
            Et_Quantity.setText(quantityString);

            setButtonsAfterChange();
        }

        LayoutParams param = new LayoutParams(quantityWight, WRAP_CONTENT, 0f);
        param.setMarginStart(quantityMargin);
        param.setMarginEnd(quantityMargin);

        Et_Quantity.setLayoutParams(param);

        addView(Et_Quantity);
    }

    private void setAddButton() {

        Ibtn_Add = new ImageButton(getContext());
        Ibtn_Add.setImageDrawable(drawableAdd);
        Ibtn_Add.setBackground(backgroundAdd);
        Ibtn_Add.setScaleType(FIT_CENTER);
        Ibtn_Add.setPadding(addPadding, addPadding, addPadding, addPadding);

        LayoutParams param = new LayoutParams(addSize, addSize, 0f);
        Ibtn_Add.setLayoutParams(param);

        new MyGesturesListener(Ibtn_Add, new MyGesturesListener.Interfase() {

            @Override
            public void OnClick(@NonNull View view) {

                String number = String.valueOf(Et_Quantity.getText());
                int newNumber = new MyNumbers().stringToInteger(number) + 1;
                quantityString = String.valueOf(newNumber);

                Et_Quantity.setText(quantityString);

                setButtonsAfterChange();

                interfase.ClickOnAddButton(newNumber);
                interfase.ClickOnAddButton(MyModifyCounter.this, newNumber);
            }

            private void DummyVoid() {}
        });

        addView(Ibtn_Add);
    }

    private void setButtonsAfterChange() {

        if (quantityString.equals("0")) {

            if (erase) {

                Ibtn_Quit.setImageDrawable(drawableErase);
                Ibtn_Quit.setBackground(backgroundErase);
                Ibtn_Quit.setScaleType(FIT_CENTER);
                Ibtn_Quit.setVisibility(VISIBLE);
                Ibtn_Quit.setPadding(erasePadding, erasePadding, erasePadding, erasePadding);

                LayoutParams param = new LayoutParams(eraseSize, eraseSize, 0f);
                Ibtn_Quit.setLayoutParams(param);

            } else {

                Ibtn_Quit.setImageDrawable(null);
                Ibtn_Quit.setBackground(null);
                Ibtn_Quit.setScaleType(FIT_CENTER);
                Ibtn_Quit.setVisibility(GONE);
                Ibtn_Quit.setPadding(quitPadding, quitPadding, quitPadding, quitPadding);

                LayoutParams param = new LayoutParams(quitSize, quitSize, 0f);
                Ibtn_Quit.setLayoutParams(param);
            }

        } else {

            Ibtn_Quit.setImageDrawable(drawableQuit);
            Ibtn_Quit.setBackground(backgroundQuit);
            Ibtn_Quit.setScaleType(FIT_CENTER);
            Ibtn_Quit.setVisibility(VISIBLE);
            Ibtn_Quit.setPadding(quitPadding, quitPadding, quitPadding, quitPadding);

            LayoutParams param = new LayoutParams(quitSize, quitSize, 0f);
            Ibtn_Quit.setLayoutParams(param);
        }
    }


    // ----- Public Methods ----- //

    public void setDisccount(boolean disccount) {

        this.disccount = disccount;

        setButtonsAfterChange();
    }

    public void setQuantity(int quantity) {

        quantityString = new MyNumbers().integerToString(quantity);

        Et_Quantity.setText(quantityString);

        setButtonsAfterChange();
    }

    public void setQuantity(String quantity) {

        quantityString = quantity;

        Et_Quantity.setText(quantityString);

        setButtonsAfterChange();
    }

    public String getQuantityString() {

        return String.valueOf(Et_Quantity.getText());
    }

    public int getQuantityInteger() {

        String quantity = String.valueOf(Et_Quantity.getText());

        return new MyNumbers().stringToInteger(quantity);
    }

    public void setOnChangeInterfase(Interfase interfase) {

        this.interfase = interfase;
    }


    // ----- Listeners ----- //

    public final EditText.OnEditorActionListener quantityListener = new EditText.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView textView, int keyCode, KeyEvent keyEvent) {

            if ((keyCode == EditorInfo.IME_ACTION_DONE)) {

                if (disccount) {

                    quantityString = String.valueOf(textView.getText());
                    int intNewQuantity = new MyNumbers().stringToInteger(quantityString);

                    setButtonsAfterChange();

                    interfase.TextQuantityChanged(intNewQuantity);
                    interfase.TextQuantityChanged(MyModifyCounter.this, intNewQuantity);

                } else {

                    int tempQuantityString = new MyNumbers().stringToInteger(String.valueOf(textView.getText()));
                    int intNewQuantity = new MyNumbers().stringToInteger(quantityString);

                    if (tempQuantityString > intNewQuantity) {

                        quantityString = String.valueOf(textView.getText());

                        setButtonsAfterChange();

                        interfase.TextQuantityChanged(intNewQuantity);
                        interfase.TextQuantityChanged(MyModifyCounter.this, intNewQuantity);

                    } else {

                        textView.setText(quantityString);

                        new MyToast(getContext(), "¡No puedes hacer esto!");
                    }
                }

                new MyKeyboard().hide(textView);

                return true;

            } else {

                return false;
            }
        }
    };


    // ----- Interface ----- //

    public interface Interfase {

        default void ClickOnEraseButton() {};
        default void ClickOnQuitButton(int newQuantity) {};
        default void TextQuantityChanged(int newQuantity) {};
        default void ClickOnAddButton(int newQuantity) {};

        default void ClickOnEraseButton(View view) {};
        default void ClickOnQuitButton(View view, int newQuantity) {};
        default void TextQuantityChanged(View view, int newQuantity) {};
        default void ClickOnAddButton(View view, int newQuantity) {};
    }


    // ----- Inherents ----- //

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}