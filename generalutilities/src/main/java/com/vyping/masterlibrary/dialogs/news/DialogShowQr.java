package com.vyping.masterlibrary.dialogs.news;

import static com.vyping.masterlibrary.definitions.Instances.INSTANCE_ACCOUNTS;
import static com.vyping.masterlibrary.definitions.Instances.INSTANCE_MAIN;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ViewDataBinding;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Common.MyToast;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.GestureListeners.MyGesturesListener;
import com.vyping.masterlibrary.Images.MyColor;
import com.vyping.masterlibrary.adapters.recyclerview.interfase.RecyclerInterfase;
import com.vyping.masterlibrary.databinding.DialogShowQrBinding;
import com.vyping.masterlibrary.definitions.Buckets;
import com.vyping.masterlibrary.definitions.Instances;
import com.vyping.masterlibrary.models.accounts.Account;
import com.vyping.masterlibrary.time.MyDelay;

import net.glxn.qrgen.android.QRCode;

public class DialogShowQr {

    private Context context;

    @SuppressLint("StaticFieldLeak")
    public static DialogShowQrBinding dialogShowQrBinding;
    public static Dialog dialogCancelOrder;
    private MyRealtime myRealtime;

    private final Interfase registerInterfase;

    private String uidUser, title, instructons;


    // ----- SetUp ----- //

    public DialogShowQr(Context context, String uidUser, String title, String instructons, Interfase registerInterfase) {

        this.context = context;
        this.registerInterfase = registerInterfase;
        this.uidUser = uidUser;
        this.title = title;
        this.instructons = instructons;

        firebaseAccountExistence();
        DialogViews();
    }

    private void DialogViews() {

        new MyDelay(100).interfase(new MyDelay.Interfase() {

            @Override
            public void Execute() {

                if (dialogShowQrBinding != null && dialogCancelOrder != null) {

                    showQrCode();

                    dialogShowQrBinding.TvTitle.setText(title);
                    dialogShowQrBinding.TvInstructions.setText(instructons);

                    new MyGesturesListener(dialogShowQrBinding.BtnBack, new MyGesturesListener.Interfase() {

                        @Override
                        public void OnClick(@NonNull View view) {

                            myRealtime.removeListListener();
                            dialogCancelOrder.dismiss();
                        }

                        private void DummyVoid() {};
                    });

                } else {

                    DialogViews();
                }
            }

            private void DummyVoid() {};
        });
    }


    // ----- Firebase ----- //

    private void firebaseAccountExistence() {

        myRealtime = new MyRealtime(INSTANCE_MAIN);
        myRealtime.getListChanges(new MyRealtime.ListListener() {

            @Override
            public void ValueListen(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getKey() != null) {

                    if (dataSnapshot.getKey().equals(uidUser)) {

                        String restaurant = String.valueOf(dataSnapshot.getValue());

                        new Instances().setInstances(context, restaurant);
                        new Buckets().setBuckets(context, restaurant);

                        myRealtime.removeListListener();

                        firebaseAccountData();
                    }
                }
            }

            private void DummyVoid() {};
        });
    }

    private void firebaseAccountData() {

        myRealtime = new MyRealtime(INSTANCE_ACCOUNTS);
        myRealtime.getListChanges(new MyRealtime.ListListener() {

            @Override
            public void ValueListen(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getKey() != null) {

                    if (dataSnapshot.getKey().equals(uidUser)) {

                        Account account = new Account(dataSnapshot);

                        myRealtime.removeListListener();
                        registerInterfase.AccountCreated(account);
                        dialogCancelOrder.dismiss();

                        new MyToast(context, "Se activó su licencia con éxito!");
                    }
                }
            }

            private void DummyVoid() {};
        });
    }


    // ----- Methods ----- //

    public void showQrCode() {

        int colorMain = new MyColor().extractFromResources(context, com.vyping.masterlibrary.R.color.White);
        int colorSecondary = new MyColor().extractFromResources(context, com.vyping.masterlibrary.R.color.Black);

        Bitmap bitmap = QRCode.from(uidUser).withSize(1000, 1000).withColor(colorMain, colorSecondary).bitmap();
        dialogShowQrBinding.IvImageView.setImageBitmap(bitmap);
    }


    // ----- Interfase ----- //

    public interface Interfase extends RecyclerInterfase {

        default void AccountCreated(Account account) {};
    }


    // ----- Binding ----- //

    @BindingAdapter(value={"dialogShowQrBinding", "dialogShowQrDialog"}, requireAll=true)
    public static void setDialogShowQr(@NonNull View view, @NonNull ViewDataBinding viewDataBinding, Dialog dialog) {

        dialogShowQrBinding = (DialogShowQrBinding) viewDataBinding;
        dialogCancelOrder = dialog;
    }
}