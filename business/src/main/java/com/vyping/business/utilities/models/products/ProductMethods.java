package com.vyping.business.utilities.models.products;

import static com.vyping.business.utilities.definitions.Buckets.BUCKET_MENU;
import static com.vyping.masterlibrary.Common.MyFile.TYPE_PNG;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.business.R;
import com.vyping.masterlibrary.Common.MyDisplay;
import com.vyping.masterlibrary.Common.MyNumbers;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.Firebase.models.MyUrlStorage;
import com.vyping.masterlibrary.adapters.recyclerview.adapter.MyRecyclerAdapter;
import com.vyping.masterlibrary.views.MyImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ProductMethods {

    private final Product product;


    // ----- SetUp ----- //

    public ProductMethods(Product product) {

        this.product = product;

    }


    // ----- Basic ModelMethods ----- //

    public Product getProduct()
    {
        return product;
    }

    public String getId()
    {
        return product.Id;
    }

    public String getName()
    {
        return product.Name;
    }

    public String getDescription()
    {
        return product.Description;
    }

    public String getCategory()
    {
        return product.Category;
    }

    public HashMap<String, String> getSizes()
    {
        return product.Sizes;
    }


    // ----- Compounds ModelMethods ----- //

    public String getUrlImage() {

        String name = new MyString().changeToFileName(product.Name);

        return new MyUrlStorage(BUCKET_MENU).file(name, TYPE_PNG).token(product.Image);
    }

    @BindingAdapter("widthView")
    public static void setWidthView(@NonNull View view, @NonNull ProductMethods productMethods) {

        int width = new MyDisplay().displayWidth(view.getContext());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width/2, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(8, 0, 8, 0);

        view.setLayoutParams(layoutParams);

        //new MyView().setDimentionsFromPx(view, width/2, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @BindingAdapter(value={"imageFromAssets", "imageFromWeb"}, requireAll=true)
    public static void loadImage(@NonNull ImageView view, String asset, String url) {

        new MyImageView().putImageFromAssetsOrWeb(view, asset, url);
    }

    @BindingAdapter("setSpinner")
    public static void setSpinner(@NonNull Spinner spinner, @NonNull ProductMethods productMethods) {

        String[] spinnerTitles = new String[]{"Porción", "Junior", "Mediana", "Grande"};
        String[] spinnerPopulation = new String[]{"$4.900", "$16.900", "$28.900", "$38.900"};
        int[] spinnerImages = new int[]{R.drawable.portions_1, R.drawable.portions_4, R.drawable.portions_6, R.drawable.portions_8};

        SizesAdapter mCustomAdapter = new SizesAdapter(spinner.getContext(), spinnerTitles, spinnerImages, spinnerPopulation);
        spinner.setAdapter(mCustomAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    public final View.OnClickListener minusListener = new View.OnClickListener() {

        @Override
        public void onClick(@NonNull View view) {

            EditText editText = ((Activity) view.getContext()).findViewById(R.id.Et_Number);
            Button button = ((Activity) view.getContext()).findViewById(R.id.Bt_Agregate);

            String number = String.valueOf(editText.getText());
            int Number = new MyNumbers().stringToInteger(number);

            if (Number > 0) {

                int newNumber = Number - 1;
                String numberToShow = String.valueOf(newNumber);
                editText.setText(numberToShow);
            }

            if (Number > 1) {

                button.setEnabled(TRUE);

            } else {

                button.setEnabled(FALSE);
            }
        }

        public void DummyVoid() {}
    };

    public static RecyclerView getRecyclerView(@NonNull View view) {

        RecyclerView recyclerView = null;
        boolean hasPerent = TRUE;

        while (recyclerView == null && hasPerent == TRUE) {

            if (view instanceof RecyclerView) {

                recyclerView = (RecyclerView) view;

            } else {

                if(view.getParent() != null) {

                    view = (View) view.getParent();

                } else {

                    hasPerent = FALSE;
                }
            }
        }

        return recyclerView;
    }

    public MyRecyclerAdapter getAdapter(@NonNull RecyclerView recyclerView) {

        return (MyRecyclerAdapter) recyclerView.getAdapter();
    }

    public MyRecyclerAdapter.ViewHolder getHolder(@NonNull RecyclerView recyclerView, View view) {

        MyRecyclerAdapter.ViewHolder viewHolder = null;
        boolean hasPerent = TRUE;

        while (viewHolder == null && hasPerent == TRUE) {

           try  {

               viewHolder = (MyRecyclerAdapter.ViewHolder) recyclerView.getChildViewHolder(view);

           } catch (Exception e) {

               if(view.getParent() != null) {

                   view = (View) view.getParent();

               } else {

                   hasPerent = FALSE;
               }
           }
        }

        return viewHolder;
    }

    public final View.OnClickListener plusListener = new View.OnClickListener() {

        @Override
        public void onClick(@NonNull View view) {

            RecyclerView recyclerView = getRecyclerView(view);
            MyRecyclerAdapter.ViewHolder viewHolder = getHolder(recyclerView,view);

            ImageButton minusButton = viewHolder.itemView.findViewById(R.id.Ib_Minus);
            EditText editText = viewHolder.itemView.findViewById(R.id.Et_Number);
            Button button = viewHolder.itemView.findViewById(R.id.Bt_Agregate);

            minusButton.setEnabled(TRUE);

            String number = String.valueOf(editText.getText());
            int Number = new MyNumbers().stringToInteger(number);

             int newNumber = Number + 1;
             String numberToShow = String.valueOf(newNumber);
             editText.setText(numberToShow);

             button.setEnabled(TRUE);
        }

        public void DummyVoid() {}
    };


    // ----- Search ModelMethods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(product.Id, product.Image, product.Name, product.Description, product.Category));
    }
}
