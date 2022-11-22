package com.vyping.masterlibrary.binding;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.vyping.masterlibrary.time.Definitions.FORMAT_HOUR_01;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.Common.MyNumbers;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.GestureListeners.MyBinderHandler;
import com.vyping.masterlibrary.GestureListeners.MyGesturesListener;
import com.vyping.masterlibrary.GestureListeners.MyMoneyWatcher;
import com.vyping.masterlibrary.GestureListeners.MyMultiTouch;
import com.vyping.masterlibrary.GestureListeners.MySlideListener;
import com.vyping.masterlibrary.GestureListeners.MyTextWatcher;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandler;
import com.vyping.masterlibrary.models.images.ImageMethods;
import com.vyping.masterlibrary.time.MyTime;
import com.vyping.masterlibrary.views.MyView;
import com.vyping.masterlibrary.views.customs.MyModifyCounter;

import cheekiat.slideview.SlideView;

public class BindingMethods extends BaseObservable {


    // ----- Main ----- //

    @BindingAdapter(value={"setViewBinding", "setInterfase"}, requireAll=true)
    public static void setBindingParms(@NonNull View view, @NonNull ViewDataBinding viewDataBinding, MyBinderHandler.Interfase interfase) {

        new MyBinderHandler(viewDataBinding, interfase);
    }


    // ----- General ----- //

    @BindingAdapter("setVisibility")
    public static void setVisibility(@NonNull View view, boolean visible) {

        if (visible) {

            view.setVisibility(VISIBLE);

        } else {

            view.setVisibility(GONE);
        }
    }

    @BindingAdapter(value={"setEnabled"}, requireAll=true)
    public static void setEnabled(@NonNull View view, boolean enabled) {

        view.setClickable(enabled);
        view.setEnabled(enabled);
    }

    @BindingAdapter(value={"setVisibilityFromText"}, requireAll=true)
    public static void setVisibilityFromText(@NonNull View view, String text) {

        if (text != null) {

            if (text.equals("")) {

                view.setVisibility(GONE);

            } else {

                view.setVisibility(VISIBLE);
            }

        } else {

            view.setVisibility(GONE);
        }
    }

    @BindingAdapter(value={"setVisibilityFromInt"}, requireAll=true)
    public static void setVisibilityFromInt(@NonNull View view, int text) {

         if (text == 0) {

             view.setVisibility(GONE);

         } else {

             view.setVisibility(VISIBLE);
         }
    }


    // ----- TextViews ----- //

    @BindingAdapter(value={"setTextFromInteger"}, requireAll=true)
    public static void setTextFromInteger(@NonNull View view, int number) {

        TextView textView = (TextView) view;

        if (number != 0) {

            String text = new MyNumbers().integerToString(number);
            textView.setText(text);

        } else {

            textView.setText("");
        }
    }


    @BindingAdapter(value={"setTextScriptIfVoid"}, requireAll=true)
    public static void setTextScriptIfVoid(@NonNull TextView textView, String text)  {

        if (text != null) {

            if (text.equals("")) {

                textView.setText("-");

            } else {

                textView.setText(text);
            }

        } else {

            textView.setText(text);
        }
    }

    // ----- ImageViews ----- //

    @BindingAdapter(value={"setImage"}, requireAll=true)
    public static void loadImageFirstResourcesThanWeb(@NonNull ImageView imageView, @NonNull ImageMethods imageMethods) {

        imageMethods.putOnImageView(imageView);
    }

    // ----- ListViews ----- //

    @BindingAdapter(value={"listStrings", "listAdapter"}, requireAll=true)
    public static void setListView(@NonNull ListView listView, ArrayAdapter arrayAdapter, AdapterView.OnItemClickListener clickListener) {

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(clickListener);
    }


    // ----- CustomViews ----- //

    @BindingAdapter(value={"setQuantity", "clickChange"}, requireAll=true)
    public static void setMyModifyCounter(@NonNull MyModifyCounter myModifyCounter, @NonNull String quantity, @NonNull MyModifyCounter.Interfase counterInterfase) {

        myModifyCounter.setQuantity(quantity);
        myModifyCounter.setOnChangeInterfase(counterInterfase);
    }

    @BindingAdapter(value={"arrowsBinding", "arrowsHandler"}, requireAll=true)
    public static void setArrows(@NonNull RecyclerView recyclerView, ViewDataBinding viewDataBinding, @NonNull RecyclerHandler handler) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager lManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int intImageViewStart = new MyView().extractFromText(recyclerView.getContext(), "Iv_Start");
                int intImageViewEnd = new MyView().extractFromText(recyclerView.getContext(), "Iv_End");
                ImageView imageViewStart = (ImageView) new MyView().getViewFromParent(recyclerView.getParent(), intImageViewStart);
                ImageView imageViewEnd = (ImageView) new MyView().getViewFromParent(recyclerView.getParent(), intImageViewEnd);

                if (lManager != null && imageViewStart != null && imageViewEnd != null) {

                    int firstElementPosition = lManager.findFirstVisibleItemPosition();
                    int lastElementPosition = lManager.findLastVisibleItemPosition();

                    if (firstElementPosition == 0) {

                        imageViewStart.setVisibility(GONE);

                    } else {

                        imageViewStart.setVisibility(VISIBLE);
                    }

                    if (lastElementPosition == handler.methodsDisplayed.size()-1) {

                        imageViewEnd.setVisibility(GONE);

                    } else {

                        if (handler.methodsDisplayed.size() > 1) {

                            imageViewEnd.setVisibility(VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // Do something
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // Do something
                } else {
                    // Do something
                }
            }
        });
    }


    // ----- Format ----- //

    @BindingAdapter(value={"hourFormatted"}, requireAll=true)
    public static void setHourFormatted(@NonNull TextView textview, String hourUnFormatted) {

        if (hourUnFormatted != null) {

            String hourFormatted = new MyTime(hourUnFormatted).getTime(FORMAT_HOUR_01);
            textview.setText(hourFormatted);

        } else {

            textview.setText("00:00");
        }
    }

    @BindingAdapter(value={"moneyFormatted"}, requireAll=true)
    public static void setMoneyFormatted(@NonNull TextView textview, String moneyUnFormatted) {

        if (moneyUnFormatted != null) {

            if (!moneyUnFormatted.equals("")) {

                String moneyFormatted = new MyString().formatToMoney(moneyUnFormatted);
                textview.setText(moneyFormatted);
            }
        }
    }

    @BindingAdapter(value={"moneyFormatted"}, requireAll=true)
    public static void setMoneyFormatted(@NonNull TextView textview, int moneyUnFormatted) {

        String money = new MyNumbers().integerToString(moneyUnFormatted);

        if (money != null) {

            if (!money.equals("")) {

                String moneyFormatted = new MyString().formatToMoney(money);
                textview.setText(moneyFormatted);
            }
        }
    }

    @BindingAdapter(value={"moneyTotalFormatted"}, requireAll=true)
    public static void setMoneyTotalFormatted(@NonNull TextView textview, String moneyUnFormatted, String quantity) {

        if (moneyUnFormatted != null) {

            if (!moneyUnFormatted.equals("") && !quantity.equals("")) {

                int intQuantity = new MyNumbers().stringToInteger(moneyUnFormatted);
                int intMoney = new MyNumbers().stringToInteger(quantity);
                int intTotal = intQuantity * intMoney;
                String total = new MyString().formatToMoney(intTotal);

                textview.setText(total);
            }
        }
    }

    @BindingAdapter(value={"moneyUnitary", "quantityTotal"}, requireAll=true)
    public static void setMoneyTotalFormatted(@NonNull TextView textview, int moneyUnFormatted, int quantity) {

        int intTotal = moneyUnFormatted * quantity;
        String total = new MyString().formatToMoney(intTotal);

        textview.setText(total);
    }

    @BindingAdapter(value={"milesFormatted"}, requireAll=true)
    public static void setMilesFormatted(@NonNull TextView textview, String moneyUnFormatted) {

        if (moneyUnFormatted != null) {

            if (!moneyUnFormatted.equals("")) {

                String moneyFormatted = new MyString().formatMiles(moneyUnFormatted);
                textview.setText(moneyFormatted);
            }
        }
    }

    @BindingAdapter(value={"milesFormatted"}, requireAll=true)
    public static void setMilesFormatted(@NonNull TextView textview, int moneyUnFormatted) {

        String money = new MyNumbers().integerToString(moneyUnFormatted);

        if (money != null) {

            if (!money.equals("")) {

                String moneyFormatted = new MyString().formatMiles(money);
                textview.setText(moneyFormatted);
            }
        }
    }


    // ----- Selection ----- //

    @BindingAdapter("setIconDeployVertical")
    public static void setIconDeployVertical(@NonNull ImageView imageView, boolean deploy) {

        if (deploy) {

            Drawable upDrawable = new MyDrawable().extractFromResources(imageView, R.drawable.icon_angle_top_circle);
            imageView.setImageDrawable(upDrawable);

        } else {

            Drawable downDrawable = new MyDrawable().extractFromResources(imageView, R.drawable.icon_angle_bottom_circle);
            imageView.setImageDrawable(downDrawable);
        }
    }

    @BindingAdapter(value={"selectedHighlight"}, requireAll=true)
    public static void setHighlight(@NonNull View view, boolean selected) {

        if (view.getBackground() != null) {

            int color = view.getContext().getResources().getColor(selected ? R.color.colorAccentLight : R.color.White);
            view.getBackground().setTint(color);

        } else {

            int color = view.getContext().getResources().getColor(selected ? R.color.colorAccentLight : R.color.colorTransparente);
            view.setBackgroundColor(color);
        }
    }


    // ----- Listeners ----- //

    @BindingAdapter(value={"gestureListener"}, requireAll=true)
    public static void setGestureListener(@NonNull View view, @NonNull MyGesturesListener.Interfase gestureInterfase) {

        new MyGesturesListener(view, gestureInterfase);
    }

    @BindingAdapter(value={"dragListener"}, requireAll=true)
    public static void setDragListener(@NonNull ImageButton imageButton, @NonNull View.OnDragListener dragListener) {

        imageButton.setOnDragListener(dragListener);
    }

    @BindingAdapter(value={"editorListener"}, requireAll=true)
    public static void setEditorListener(@NonNull EditText editText, @NonNull EditText.OnEditorActionListener actionListener) {

        editText.setOnEditorActionListener(actionListener);
    }

    @BindingAdapter(value={"textWatcher"}, requireAll=true)
    public static void setTextWatcher(@NonNull EditText editText, @NonNull MyTextWatcher.Interface interfaseTextWatcher) {

        new MyTextWatcher(editText, interfaseTextWatcher);
    }

    @BindingAdapter(value={"moneyWatcher"}, requireAll=true)
    public static void setMoneyWatcher(@NonNull EditText editText, @NonNull MyMoneyWatcher.Interface interfaseTextWatcher) {

        new MyMoneyWatcher(editText, interfaseTextWatcher);
    }

    @BindingAdapter(value={"selectedListener"}, requireAll=true)
    public static void setSelectorListener(@NonNull Spinner spinner, @NonNull AdapterView.OnItemSelectedListener dragListener) {

        spinner.setOnItemSelectedListener(dragListener);
    }

    @BindingAdapter(value={"interfaceConfirm"}, requireAll=true)
    public static void setDialogConfirm(@NonNull SlideView slideView, MySlideListener.Interface interfase) {

        new MySlideListener(slideView, interfase);
    }

    @BindingAdapter(value={"confirmListener"}, requireAll=true)
    public static void setConfirmListener(@NonNull View view, @NonNull MyMultiTouch.Interfase interfase) {

        new MyMultiTouch(view).twoClicks(interfase);
    }
}
