package com.vyping.masterlibrary.Dialogs;

 import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
 import android.widget.LinearLayout;
 import android.widget.VideoView;

 import androidx.annotation.NonNull;

 import com.airbnb.paris.Paris;
 import com.vyping.masterlibrary.Common.Definitions;
 import com.vyping.masterlibrary.R;

public abstract class DialogVideoPlayer {

    private Context context;
    private final CreateDialog createDialog;

    private VideoView Vv_Player;

    /*----------- SetUp - Section ----------*/

    @SuppressLint("SetTextI18n")
    public DialogVideoPlayer(Context context, int parameters) {

        setParameters(context);

        String videoURL = "https://download1482.mediafire.com/f0lf0ntjozdg/wc7nwtthl3ed2na/VCam-20211207-1131.mp4";
        Uri uri= Uri.parse(videoURL);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(Dialog dialog) {

                setViewsOnLayout(dialog);

                Vv_Player.setVideoURI(uri);
                Vv_Player.start();

                dialog.show();
            }

            @Override
            protected void RefreshClick() {
                Vv_Player.stopPlayback();
                Vv_Player.start();
            }

            @Override
            protected void PositiveClick() {

                ButtonConfirm();
            }
        };

        setOptionButtons();
    }

    private void setOptionButtons() {

        createDialog.setModeButtons(Definitions.BUTTONS_THREE);
    }


    /*----- Tools -----*/

    private void setParameters(@NonNull Context context) {

        this.context = context;
    }

    private void setViewsOnLayout(@NonNull Dialog dialog) {

        int style = R.style.DialogVideoPlayer;
        int attr = R.attr.dialogVideoPlayer;

        LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);
        layout.setOrientation(LinearLayout.VERTICAL);

        Vv_Player = new VideoView(context, null, attr, style);

        Paris.style(Vv_Player).apply(style);

        layout.addView(Vv_Player);
    }


    /*----- Return -----*/

    protected abstract void ButtonConfirm();
}


