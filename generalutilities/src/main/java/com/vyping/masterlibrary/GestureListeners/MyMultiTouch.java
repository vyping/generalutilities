package com.vyping.masterlibrary.GestureListeners;

import android.content.Context;
import android.view.View;

import com.vyping.masterlibrary.Common.MyGeneralTools;
import com.vyping.masterlibrary.Common.MyToast;

import com.vyping.masterlibrary.time.MyDelay;

public class MyMultiTouch {

    private Context context;
    private View view;
    private int countTouch;


    // ----- SetUp ----- //

    public MyMultiTouch(View view) {

        this.context = view.getContext();
        this.view = view;
    }


    // ----- Methods ----- //

    public void twoClicks(Interfase interfase) {

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                countTouch = countTouch + 1;

                if (countTouch == 1) {

                    new MyToast(context).showShort("¡Pulsa 1 vez mas para confirmar!");

                } else if (countTouch == 2) {

                    countTouch = 0;

                    new MyGeneralTools().startVibration(v.getContext(), 100);
                    interfase.ConfirmClick();
                }

                new MyDelay(3000).interfase(new MyDelay.Interfase() {

                    @Override
                    public void Execute() {

                        countTouch = 0;
                    }
                });
            }

            private void DummyVoid() {}
        });
    }

    public void threeClicks(Interfase interfase) {

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                countTouch = countTouch + 1;

                if (countTouch == 1) {

                    new MyToast(context).showShort("¡Pulsa 2 veces mas para confirmar!");

                } else if (countTouch == 2) {

                    new MyToast(context).showShort("¡Pulsa 1 vez mas para confirmar!");

                } else if (countTouch == 3) {

                    countTouch = 0;

                    new MyGeneralTools().startVibration(v.getContext(), 100);
                    interfase.ConfirmClick();
                }

                new MyDelay(3000).interfase(new MyDelay.Interfase() {

                    @Override
                    public void Execute() {

                        countTouch = 0;
                    }
                });
            }

            private void DummyVoid() {}
        });
    }


    // ----- Interfase ----- //

    public interface Interfase  {

        void ConfirmClick();
    }
}
