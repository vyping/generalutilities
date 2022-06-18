package com.vyping.libraries.utilities.models.products;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableArrayList;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Common.MyStrings;

public class ProductsHandler extends BaseObservable {

    private String search;
    private ObservableArrayList<ProductMethods> base;

    @Bindable
    public ObservableArrayList<ProductMethods> products;


    // ----- SetUp ----- //
    public ProductsHandler() {

        this.search = "";
        this.base = new ObservableArrayList<>();
        this.products = new ObservableArrayList<>();
    }


    // ----- Methods ----- //

    public void addProduct(@NonNull DataSnapshot dataSnapshot) {

        Product product = new Product(dataSnapshot);
        ProductMethods productMethods = new ProductMethods(product);
        int position = setPosition(product);
        int positionOnFilter = setPositionOnFilter(product);

        this.base.add(position, productMethods);
        this.products.add(positionOnFilter, productMethods);
    }

    public void modifyProduct(@NonNull DataSnapshot dataSnapshot) {

        Product product = new Product(dataSnapshot);
        ProductMethods productMethods = new ProductMethods(product);
        int position = getPosition(product);
        int positionOnFilter = getPositionOnFilter(product);

        this.base.set(position, productMethods);
        this.products.set(positionOnFilter, productMethods);
    }

    public void removeProduct(@NonNull DataSnapshot dataSnapshot) {

        Product product = new Product(dataSnapshot);
        int position = getPosition(product);
        int positionOnFilter = getPositionOnFilter(product);

        this.base.remove(position);
        this.products.remove(positionOnFilter);
    }


    // ----- Tools ----- //

    private int getPosition(@NonNull Product product) {

        int position = -1, returnPosition = base.size();

        if (returnPosition != 0) {

            for (final ProductMethods compareExercise : base) {

                position = position + 1;

                if (product.BarCode.equals(compareExercise.getProduct().BarCode)) {

                    returnPosition = position;
                    break;
                }
            }
        }

        return returnPosition;
    }

    private int getPositionOnFilter(@NonNull Product product) {

        int position = -1, returnPosition = products.size();

        if (returnPosition != 0) {

            for (final ProductMethods compareExercise : products) {

                position = position + 1;

                if (product.BarCode.equals(compareExercise.getProduct().BarCode)) {

                    returnPosition = position;
                    break;
                }
            }
        }

        return returnPosition;
    }

    private int setPosition(@NonNull final Product product) {

        long arraySize = base.size();
        int returnPosition = 0;

        if (!base.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareExercise = String.valueOf(base.get(position).getProduct().BarCode);
                int compareMarge = product.BarCode.compareTo(compareExercise);

                if (compareMarge < 0) {

                    returnPosition = position;
                    break;

                } else if (compareMarge == 0) {

                    returnPosition = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        returnPosition = position + 1;

                    } else {

                        String nextExercise = String.valueOf(base.get(position).getProduct().BarCode);
                        int nextMarge = product.BarCode.compareTo(nextExercise);

                        if (nextMarge < 0) {

                            returnPosition = position + 1;
                            break;
                        }
                    }
                }
            }
        }

        return returnPosition;
    }

    private int setPositionOnFilter(@NonNull final Product product) {

        long arraySize = products.size();
        int returnPosition = 0;

        if (!products.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareExercise = String.valueOf(products.get(position).getProduct().BarCode);
                int compareMarge = product.BarCode.compareTo(compareExercise);

                if (compareMarge < 0) {

                    returnPosition = position;
                    break;

                } else if (compareMarge == 0) {

                    returnPosition = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        returnPosition = position + 1;

                    } else {

                        String nextExercise = String.valueOf(products.get(position).getProduct().BarCode);
                        int nextMarge = product.BarCode.compareTo(nextExercise);

                        if (nextMarge < 0) {

                            returnPosition = position + 1;
                            break;
                        }
                    }
                }
            }
        }

        return returnPosition;
    }


    // ----- Search ----- //

    public void setSearch(@NonNull String search) {

        this.search = new MyStrings().toLowerCaseAndRemoveAccentMark(search);

        for (final ProductMethods productMethods : base) {

            boolean contains = FALSE;

            for (Object object : productMethods.getSearchParameters()) {

                String Parameter = String.valueOf(object);
                String parameter = new MyStrings().toLowerCaseAndRemoveAccentMark(Parameter);

                if (parameter.contains(this.search)) {

                    contains = TRUE;
                    break;
                }
            }

            if (contains) {

                if (!this.products.contains(productMethods)) {

                    int position = setPositionOnFilter(productMethods.getProduct());
                    this.products.add(position, productMethods);
                }

            } else {

                this.products.remove(productMethods);
            }
        }
    }
}

