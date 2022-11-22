package com.vyping.masterlibrary.adapters.recyclerview.handler;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableArrayList;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.BR;
import com.vyping.masterlibrary.Common.MyString;

public class RecyclerHandler<T> extends BaseObservable {

    private final RecyclerHandlerInterfase<T> methodInterfase;

    @Bindable
    public ObservableArrayList<T> methodsDisplayed;
    public ObservableArrayList<T> methodsHidden;

    private final boolean searched;
    private String identifierSelected;


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

    public void resetMethods() {

        if (searched) {

           methodsHidden.clear();
        }

        methodsDisplayed.clear();
    }

    public void loadMethodHandler(@NonNull RecyclerHandler<T> recyclerHandler) {

        for (T method : recyclerHandler.methodsDisplayed) {

            loadMethod(method);
        }
    }

    public void loadMethod(@NonNull T method) {

        if (searched) {

            this.methodsHidden.add(methodsHidden.size(), method);
        }

        this.methodsDisplayed.add(methodsDisplayed.size(), method);
    }

    public void loadMethod(@NonNull DataSnapshot dataSnapshot) {

        T methods = methodInterfase.getMethod(dataSnapshot);

        if (searched) {

            this.methodsHidden.add(methodsHidden.size(), methods);
        }

        this.methodsDisplayed.add(methodsDisplayed.size(), methods);
    }

    public T loadMethodAndReturnMethod(@NonNull DataSnapshot dataSnapshot) {

        T methods = methodInterfase.getMethod(dataSnapshot);

        if (searched) {

            this.methodsHidden.add(methodsHidden.size(), methods);
        }

        this.methodsDisplayed.add(methodsDisplayed.size(), methods);

        return methods;
    }

    public void addMethodHandler(@NonNull RecyclerHandler<T> recyclerHandler) {

        for (T method : recyclerHandler.methodsDisplayed) {

            addMethod(method);
        }
    }

    public void addMethod(@NonNull T method) {

        String index = methodInterfase.getIndex(method);

        if (searched) {

            int positionHidden = setPositionHidden(index);
            int positionDisplayed = setPositionDisplayed(index);

            this.methodsHidden.add(positionHidden, method);
            this.methodsDisplayed.add(positionDisplayed, method);

        } else {

            int positionDisplayed = setPositionDisplayed(index);

            this.methodsDisplayed.add(positionDisplayed, method);
        }
    }

    public int addMethodAndReturnPosition(@NonNull T method) {

        String index = methodInterfase.getIndex(method);
        int positionDisplayed = -1;

        if (searched) {

            int positionHidden = setPositionHidden(index);
            positionDisplayed = setPositionDisplayed(index);

            this.methodsHidden.add(positionHidden, method);
            this.methodsDisplayed.add(positionDisplayed, method);

        } else {

            positionDisplayed = setPositionDisplayed(index);

            this.methodsDisplayed.add(positionDisplayed, method);
        }

        return positionDisplayed;
    }

    public void addMethod(@NonNull DataSnapshot dataSnapshot) {

       String index = methodInterfase.getIndex(dataSnapshot);
        T method = methodInterfase.getMethod(dataSnapshot);

        if (searched) {

            int positionHidden = setPositionHidden(index);
            int positionDisplayed = setPositionDisplayed(index);

            this.methodsHidden.add(positionHidden, method);
            this.methodsDisplayed.add(positionDisplayed, method);

        } else {

            int positionDisplayed = setPositionDisplayed(index);

            this.methodsDisplayed.add(positionDisplayed, method);
        }
    }

    public int addMethodAndReturnPosition(@NonNull DataSnapshot dataSnapshot) {

        String index = methodInterfase.getIndex(dataSnapshot);
        T method = methodInterfase.getMethod(dataSnapshot);
        int positionDisplayed = -1;

        if (searched) {

            int positionHidden = setPositionHidden(index);
            positionDisplayed = setPositionDisplayed(index);

            this.methodsHidden.add(positionHidden, method);
            this.methodsDisplayed.add(positionDisplayed, method);

        } else {

            positionDisplayed = setPositionDisplayed(index);

            this.methodsDisplayed.add(positionDisplayed, method);
        }

        return positionDisplayed;
    }

    public void modifyMethod(@NonNull DataSnapshot dataSnapshot) {

        try {

            String identifier = methodInterfase.getIdentifier(dataSnapshot);
            String index = methodInterfase.getIndex(dataSnapshot);
            T method = methodInterfase.getMethod(dataSnapshot);

            if (searched) {

                int hidenMovedFrom = itemHiddenWasMoved(dataSnapshot);
                int displayedMovedFrom = itemDisplayedWasMoved(dataSnapshot);

                if (hidenMovedFrom >= 0 && displayedMovedFrom >= 0) {

                    this.methodsHidden.remove(hidenMovedFrom);
                    this.methodsDisplayed.remove(displayedMovedFrom);

                    int addPositionHidden = setPositionHidden(index);
                    int addPositionDisplayed = setPositionDisplayed(index);

                    this.methodsHidden.add(addPositionHidden, method);
                    this.methodsDisplayed.add(addPositionDisplayed, method);

                } else {

                    int positionHidden = getPositionHidden(identifier);
                    int positionDisplayed = getPositionDisplayed(identifier);

                    this.methodsHidden.set(positionHidden, method);
                    this.methodsDisplayed.set(positionDisplayed, method);
                }

            } else {

                int displayedMovedFrom = itemDisplayedWasMoved(dataSnapshot);

                if (displayedMovedFrom >= 0) {

                    this.methodsDisplayed.remove(displayedMovedFrom);

                    int addPositionDisplayed = setPositionDisplayed(index);

                    this.methodsDisplayed.add(addPositionDisplayed, method);

                } else {

                    int positionDisplayed = getPositionDisplayed(identifier);

                    this.methodsDisplayed.set(positionDisplayed, method);
                }
            }

        } catch (Exception ignored) {}
    }

    public void modifyMethod(@NonNull T method, InterfaseTest<T> interfase) {

        int param = interfase.modofiedParam(method);

       notifyPropertyChanged(param);
    }

    public int modifyMethod(@NonNull T method) {

        String identifier = methodInterfase.getIdentifier(method);
        String index = methodInterfase.getIndex(method);
        int position = -1;

        try {

            if (searched) {

                int hidenMovedFrom = itemHiddenWasMoved(method);
                int displayedMovedFrom = itemDisplayedWasMoved(method);

                if (hidenMovedFrom >= 0 && displayedMovedFrom >= 0) {

                    this.methodsHidden.remove(hidenMovedFrom);
                    this.methodsDisplayed.remove(displayedMovedFrom);

                    int addPositionHidden = setPositionHidden(index);
                    int addPositionDisplayed = setPositionDisplayed(index);

                    this.methodsHidden.add(addPositionHidden, method);
                    this.methodsDisplayed.add(addPositionDisplayed, method);

                    position = addPositionDisplayed;

                } else {

                    int positionHidden = getPositionHidden(identifier);
                    int positionDisplayed = getPositionDisplayed(identifier);

                    this.methodsHidden.set(positionHidden, method);
                    this.methodsDisplayed.set(positionDisplayed, method);
                    position = positionDisplayed;
                }

            } else {

                int displayedMovedFrom = itemDisplayedWasMoved(method);

                if (displayedMovedFrom >= 0) {

                    this.methodsDisplayed.remove(displayedMovedFrom);

                    int addPositionDisplayed = setPositionDisplayed(index);

                    this.methodsDisplayed.add(addPositionDisplayed, method);

                    position = addPositionDisplayed;

                } else {

                    int positionDisplayed = getPositionDisplayed(identifier);

                    this.methodsDisplayed.set(positionDisplayed, method);

                    position = positionDisplayed;
                }
            }

        } catch (Exception ignored) {}

        return position;
    }

    public void moveMethod(@NonNull DataSnapshot dataSnapshot) {

        String identifier = methodInterfase.getIdentifier(dataSnapshot);
        T method = methodInterfase.getMethod(dataSnapshot);

        try {

            if (searched) {

                int positionHidden = getPositionHidden(identifier);
                int positionDisplayed = getPositionDisplayed(identifier);

                this.methodsHidden.set(positionHidden, method);
                this.methodsDisplayed.set(positionDisplayed, method);

            } else {

                int positionDisplayed = getPositionDisplayed(identifier);

                this.methodsDisplayed.set(positionDisplayed, method);
            }

        } catch (Exception ignored) {}
    }

    public void moveMethod(@NonNull T method) {

        String identifier = methodInterfase.getIdentifier(method);

        try {

            if (searched) {

                int positionHidden = getPositionHidden(identifier);
                int positionDisplayed = getPositionDisplayed(identifier);

                this.methodsHidden.set(positionHidden, method);
                this.methodsDisplayed.set(positionDisplayed, method);

            } else {

                int positionDisplayed = getPositionDisplayed(identifier);

                this.methodsDisplayed.set(positionDisplayed, method);
            }

        } catch (Exception ignored) {}
    }

    public void removeMethod(@NonNull DataSnapshot dataSnapshot) {

        String identifier = methodInterfase.getIdentifier(dataSnapshot);

        try {

            if (searched) {

                int positionHidden = getPositionHidden(identifier);
                int positionDisplayed = getPositionDisplayed(identifier);

                this.methodsHidden.remove(positionHidden);
                this.methodsDisplayed.remove(positionDisplayed);

            } else {

                int positionDisplayed = getPositionDisplayed(identifier);

                this.methodsDisplayed.remove(positionDisplayed);
            }

        } catch (Exception ignored) {}
    }

    public void removeMethod(@NonNull T method) {

        String identifier = methodInterfase.getIdentifier(method);

        try {

            if (searched) {

                int positionHidden = getPositionHidden(identifier);
                int positionDisplayed = getPositionDisplayed(identifier);

                this.methodsHidden.remove(positionHidden);
                this.methodsDisplayed.remove(positionDisplayed);

            } else {

                int positionDisplayed = getPositionDisplayed(identifier);

                this.methodsDisplayed.remove(positionDisplayed);
            }

        } catch (Exception ignored) {}
    }

    public void removeMethod(int position) {

        if (searched) {

        //    int positionHidden = getPositionHidden(identifier);
         //   int positionDisplayed = getPositionDisplayed(identifier);

          //  this.methodsHidden.remove(positionHidden);
          //  this.methodsDisplayed.remove(positionDisplayed);

        } else {

            this.methodsDisplayed.remove(position);
        }
    }


    // ----- ConditionalMethods ----- //

    public void conditionalAdded(@NonNull T method, T selected) {

        if (selected != null) {

            String identifier = methodInterfase.getIdentifier(method);
            String selectedIdentifier = methodInterfase.getIdentifier(selected);

            if (identifier.equals(selectedIdentifier)) {

                modifyMethod(method);

            } else {

                loadMethod(method);
            }

        } else {

            loadMethod(method);
        }

        notifyChange();
    }

    public void conditionalAdded(@NonNull DataSnapshot dataSnapshot, T selected) {

        boolean displayable = methodInterfase.isDisplayable(dataSnapshot);

        if (displayable) {

            if (selected != null) {

                String identifier = methodInterfase.getIdentifier(dataSnapshot);
                String selectedIdentifier = methodInterfase.getIdentifier(selected);

                if (identifier.equals(selectedIdentifier)) {

                    modifyMethod(dataSnapshot);

                } else {

                    loadMethod(dataSnapshot);
                }

            } else {

                loadMethod(dataSnapshot);
            }
        }
    }

    public T conditionalChangeMethod(@NonNull T method, T selected) {

        return conditionalChangeMethod(method, selected, null);
    }

    public T conditionalChangeMethod(@NonNull DataSnapshot dataSnapshot, T selected) {

        T method = methodInterfase.getMethod(dataSnapshot);

        return conditionalChangeMethod(method, selected, null);
    }

    public T conditionalChangeMethod(@NonNull T method, T selected, Interfase<T> interfase) {

        boolean displayable = methodInterfase.isDisplayable(method);
        int position = getPositionDisplayed(method);

        if (displayable) {

            if (position != -1) {

                if (selected != null) {

                    String identifier = methodInterfase.getIdentifier(method);
                    identifierSelected = methodInterfase.getIdentifier(selected);

                    if (identifier.equals(identifierSelected)) {

                        method = methodInterfase.setSelection(method, TRUE);

                        if (interfase != null) {

                            interfase.MethodSelected(method);
                            interfase.MethodModified(method);
                        }

                        modifyMethod(method);

                    } else {

                        if (interfase != null) {

                            interfase.MethodModified(method);
                        }

                        modifyMethod(method);
                    }

                } else {

                    if (interfase != null) {

                        interfase.MethodModified(method);
                    }

                    modifyMethod(method);
                }

            } else {

                if (interfase != null) {

                    interfase.MethodAdded(method);
                }

                addMethod(method);
            }

        } else {

            if (position != -1) {

                if (selected != null) {

                    String identifier = methodInterfase.getIdentifier(method);
                    String selectedIdentifier = methodInterfase.getIdentifier(selected);

                    if (identifier.equals(selectedIdentifier)) {

                        method = methodInterfase.setSelection(method, TRUE);

                        if (interfase != null) {

                            interfase.MethodSelected(method);
                            interfase.MethodRemoved(method);
                        }

                        removeMethod(method);

                    } else {

                        if (interfase != null) {

                            interfase.MethodRemoved(method);
                        }

                        removeMethod(method);
                    }

                } else {

                    if (interfase != null) {

                        interfase.MethodRemoved(method);
                    }

                    removeMethod(method);
                }
            }
        }

       // notifyPropertyChanged(BR.lastName);
        return method;
    }

    public T conditionalChangeMethod(@NonNull DataSnapshot dataSnapshot, T selected, Interfase<T> interfase) {

        T method = methodInterfase.getMethod(dataSnapshot);

        return conditionalChangeMethod(method, selected, interfase);
    }

    public T conditionalRemoveMethod(@NonNull T method, T selected) {

        return conditionalRemoveMethod(method, selected, null);
    }

    public T conditionalRemoveMethod(@NonNull DataSnapshot dataSnapshot, T selected) {

        return conditionalRemoveMethod(dataSnapshot, selected, null);
    }

    public T conditionalRemoveMethod(@NonNull T method, T selected, Interfase<T> interfase) {

        int position = getPositionDisplayed(method);

        if (position != -1) {

            if (selected != null) {

                String identifier = methodInterfase.getIdentifier(method);
                String selectedIdentifier = methodInterfase.getIdentifier(selected);

                if (identifier.equals(selectedIdentifier)) {

                    method = methodInterfase.setSelection(method, TRUE);

                    if (interfase != null) {

                        interfase.MethodSelected(method);
                        interfase.MethodRemoved(method);
                    }

                    removeMethod(method);

                } else {

                    if (interfase != null) {

                        interfase.MethodRemoved(method);
                    }

                    removeMethod(method);
                }

            } else {

                if (interfase != null) {

                    interfase.MethodRemoved(method);
                }

                removeMethod(method);
            }
        }

        return method;
    }

    public T conditionalRemoveMethod(@NonNull DataSnapshot dataSnapshot, T selected, Interfase<T> interfase) {

        T method = methodInterfase.getMethod(dataSnapshot);

        return conditionalRemoveMethod(method, selected, interfase);
    }


    // ----- Tools ----- //

    public int itemHiddenWasMoved(DataSnapshot dataSnapshot) {

        String identifier = methodInterfase.getIdentifier(dataSnapshot);
        String index = methodInterfase.getIndex(dataSnapshot);
        int position =-1, movedFrom = -1;

        for (T compareMethod : methodsHidden) {

            position = position + 1;

            String compareIdentifier = methodInterfase.getIdentifier(compareMethod);

            if (identifier.equals(compareIdentifier)) {

                String compareIndex = methodInterfase.getIndex(compareMethod);

                if (!index.equals(compareIndex)) {

                    movedFrom = position;
                    break;
                }
            }
        }

        return movedFrom;
    }

    public int itemHiddenWasMoved(T method) {

        String identifier = methodInterfase.getIdentifier(method);
        String index = methodInterfase.getIndex(method);
        int position =-1, movedFrom = -1;

        for (T compareMethod : methodsHidden) {

            position = position + 1;

            String compareIdentifier = methodInterfase.getIdentifier(compareMethod);

            if (identifier.equals(compareIdentifier)) {

                String compareIndex = methodInterfase.getIndex(compareMethod);

                if (!index.equals(compareIndex)) {

                    movedFrom = position;
                    break;
                }
            }
        }

        return movedFrom;
    }

    public int itemDisplayedWasMoved(DataSnapshot dataSnapshot) {

        String identifier = methodInterfase.getIdentifier(dataSnapshot);
        String index = methodInterfase.getIndex(dataSnapshot);
        int position =-1, movedFrom = -1;

        for (T compareMethod : methodsDisplayed) {

            position = position + 1;

            String compareIdentifier = methodInterfase.getIdentifier(compareMethod);

            if (identifier.equals(compareIdentifier)) {

                String compareIndex = methodInterfase.getIndex(compareMethod);

                if (!index.equals(compareIndex)) {

                    movedFrom = position;
                    break;
                }
            }
        }

        return movedFrom;
    }

    public int itemDisplayedWasMoved(T method) {

        String identifier = methodInterfase.getIdentifier(method);
        String index = methodInterfase.getIndex(method);
        int position =-1, movedFrom = -1;

        for (T compareMethod : methodsDisplayed) {

            position = position + 1;

            String compareIdentifier = methodInterfase.getIdentifier(compareMethod);

            if (identifier.equals(compareIdentifier)) {

                String compareIndex = methodInterfase.getIndex(compareMethod);

                if (!index.equals(compareIndex)) {

                    movedFrom = position;
                    break;
                }
            }
        }

        return movedFrom;
    }

    private int getPositionHidden(String identifier) {

        int position = -1, returnPosition = -1;

        for (final T compareMethod : methodsHidden) {

            position = position + 1;

            String indexCompare = methodInterfase.getIdentifier(compareMethod);

            if (identifier.equals(indexCompare)) {

                returnPosition = position;
                break;
            }
        }

        //if (returnPosition == -1) {

          //  returnPosition = setPositionHidden(identifier);
        //}

        return returnPosition;
    }

    public int getPositionDisplayed(T method) {

        int position = -1, returnPosition = -1;

        for (final T compareMethod : methodsDisplayed) {

            position = position + 1;
            String index = methodInterfase.getIdentifier(method);
            String indexCompare = methodInterfase.getIdentifier(compareMethod);

            if (index.equals(indexCompare)) {

                returnPosition = position;
                break;
            }
        }

       // if (returnPosition == -1) {

         ///   returnPosition = setPositionDisplayed(identifier);
        //}

        return returnPosition;
    }

    public int getPositionDisplayed(DataSnapshot dataSnapshot) {

        int position = -1, returnPosition = -1;

        for (final T compareMethod : methodsDisplayed) {

            position = position + 1;
            String index = methodInterfase.getIdentifier(dataSnapshot);
            String indexCompare = methodInterfase.getIdentifier(compareMethod);

            if (index.equals(indexCompare)) {

                returnPosition = position;
                break;
            }
        }

        // if (returnPosition == -1) {

        ///   returnPosition = setPositionDisplayed(identifier);
        //}

        return returnPosition;
    }

    public int getPositionDisplayed(String identifier) {

        int position = -1, returnPosition = -1;

        for (final T compareMethod : methodsDisplayed) {

            position = position + 1;
            String indexCompare = methodInterfase.getIdentifier(compareMethod);

            if (identifier.equals(indexCompare)) {

                returnPosition = position;
                break;
            }
        }

        // if (returnPosition == -1) {

        ///   returnPosition = setPositionDisplayed(identifier);
        //}

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

    public int setPositionDisplayed(String index) {

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

    public boolean setSearch(@NonNull String search) {

        boolean globalResult = FALSE;

        if (searched) {

            String Search = new MyString().toLowerCaseAndRemoveAccentMark(search);

            for (final T modelMethods : methodsHidden) {

                boolean containsSearchOnChilds = methodInterfase.containsSearchOnChilds(modelMethods, Search);
                boolean contains = FALSE;

                if (containsSearchOnChilds) {

                    contains = TRUE;

                } else {

                    for (Object object : methodInterfase.getSearchParameters(modelMethods)) {

                        String Parameter = String.valueOf(object);
                        String parameter = new MyString().toLowerCaseAndRemoveAccentMark(Parameter);

                        if (parameter.contains(Search)) {

                            contains = TRUE;
                            break;
                        }
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

                if (globalResult == FALSE && contains == TRUE) {

                    globalResult = TRUE;
                }
            }
        }

        return globalResult;
    }


    // ----- Interfase ----- //

    public interface Interfase<T> {

        default public void MethodSelected(T method) {};
        default public void MethodAdded(T method) {};
        default public void MethodModified(T method) {};
        default public void MethodRemoved(T method) {};
    }

    public interface InterfaseTest<T> {

        default public int modofiedParam(T method) { return  0; };
    }
}

