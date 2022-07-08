package com.vyping.masterlibrary.adapters.recyclerview.handler;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableArrayList;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Common.MyString;

public class RecyclerHandler<T> extends BaseObservable {

    private final RecyclerHandlerInterfase<T> methodInterfase;

    @Bindable
    public ObservableArrayList<T> methodsDisplayed;
    private ObservableArrayList<T> methodsHidden;

    private final boolean searched;


    // ----- SetUp ----- //

    public RecyclerHandler(RecyclerHandlerInterfase<T> modelBinder, boolean searched) {

        this.methodInterfase = modelBinder;
        this.searched = searched;

        if (searched) {

            this.methodsHidden = new ObservableArrayList<>();
        }

        this.methodsDisplayed = new ObservableArrayList<>();
    }


    // ----- ModelMethods ----- //

    public void loadMethod(@NonNull DataSnapshot dataSnapshot) {

        T methods = methodInterfase.getMethod(dataSnapshot);

        if (searched) {

            this.methodsHidden.add(methodsHidden.size(), methods);
        }

        this.methodsDisplayed.add(methodsDisplayed.size(), methods);
    }

    public void addMethod(@NonNull DataSnapshot dataSnapshot) {
        
        String index = methodInterfase.getIndex(dataSnapshot);
        T method = methodInterfase.getMethod(dataSnapshot);

        if (searched) {

            int positionHidden = getPositionHidden(index);
            int positionDisplayed = setPositionDisplayed(index);

            this.methodsHidden.add(positionHidden, method);
            this.methodsDisplayed.add(positionDisplayed, method);

        } else {

            int positionDisplayed = setPositionDisplayed(index);

            this.methodsDisplayed.add(positionDisplayed, method);
        }
    }

    public void modifyMethod(@NonNull DataSnapshot dataSnapshot) {

        String index = methodInterfase.getIndex(dataSnapshot);
        T method = methodInterfase.getMethod(dataSnapshot);

        if (searched) {

            int positionHidden = getPositionHidden(index);
            int positionDisplayed = getPositionDisplayed(index);

            this.methodsHidden.set(positionHidden, method);
            this.methodsDisplayed.set(positionDisplayed, method);

        } else {

            int positionDisplayed = getPositionDisplayed(index);

            this.methodsDisplayed.set(positionDisplayed, method);
        }
    }

    public void removeMethod(@NonNull DataSnapshot dataSnapshot) {

        String index = methodInterfase.getIndex(dataSnapshot);

        if (searched) {

            int positionHidden = getPositionHidden(index);
            int positionDisplayed = getPositionDisplayed(index);

            this.methodsHidden.remove(positionHidden);
            this.methodsDisplayed.remove(positionDisplayed);

        } else {

            int positionDisplayed = getPositionDisplayed(index);

            this.methodsDisplayed.remove(positionDisplayed);
        }
    }


    // ----- Tools ----- //

    private int getPositionHidden(String index) {
        
        int position = -1, returnPosition = methodsHidden.size();

        if (returnPosition != 0) {

            for (final T compareMethod : methodsHidden) {

                position = position + 1;

                String indexCompare = methodInterfase.getIndex(compareMethod);

                if (index.equals(indexCompare)) {

                    returnPosition = position;
                    break;
                }
            }
        }

        return returnPosition;
    }

    private int getPositionDisplayed(String index) {

        int position = -1, returnPosition = methodsDisplayed.size();

        if (returnPosition != 0) {

            for (final T compareMethod : methodsDisplayed) {

                position = position + 1;
                String indexCompare = methodInterfase.getIndex(compareMethod);

                if (index.equals(indexCompare)) {

                    returnPosition = position;
                    break;
                }
            }
        }

        return returnPosition;
    }

    private int setPositionHidden(String index) {

        long arraySize = methodsHidden.size();
        int returnPosition = 0;

        if (!methodsHidden.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String indexCompare = methodInterfase.getIndex(methodsHidden.get(position));
                int compareMarge = index.compareTo(indexCompare);

                if (compareMarge < 0) {

                    returnPosition = position;
                    break;

                } else if (compareMarge == 0) {

                    returnPosition = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        returnPosition = position + 1;

                    } else {

                        String indexNext = methodInterfase.getIndex(methodsHidden.get(position));
                        int nextMarge = index.compareTo(indexNext);

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

    private int setPositionDisplayed(String index) {

        long arraySize = methodsDisplayed.size();
        int returnPosition = 0;

        if (!methodsDisplayed.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String indexCompare = methodInterfase.getIndex(methodsDisplayed.get(position));
                int compareMarge = index.compareTo(indexCompare);

                if (compareMarge < 0) {

                    returnPosition = position;
                    break;

                } else if (compareMarge == 0) {

                    returnPosition = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        returnPosition = position + 1;

                    } else {

                        String indexNext = methodInterfase.getIndex(methodsDisplayed.get(position));
                        int nextMarge = index.compareTo(indexNext);

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

        if (searched) {

            String Search = new MyString().toLowerCaseAndRemoveAccentMark(search);

            for (final T modelMethods : methodsHidden) {

                boolean contains = FALSE;

                for (Object object : methodInterfase.getSearchParameters(modelMethods)) {

                    String Parameter = String.valueOf(object);
                    String parameter = new MyString().toLowerCaseAndRemoveAccentMark(Parameter);

                    if (parameter.contains(Search)) {

                        contains = TRUE;
                        break;
                    }
                }

                if (contains) {

                    if (!this.methodsDisplayed.contains(modelMethods)) {

                        String index = methodInterfase.getIndex(modelMethods);
                        int position = setPositionDisplayed(index);
                        this.methodsDisplayed.add(position, modelMethods);
                    }

                } else {

                    this.methodsDisplayed.remove(modelMethods);
                }
            }
        }
    }
}

