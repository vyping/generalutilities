package com.vyping.masterlibrary.dialogs;

 import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_REFRESH_ACCEPT;

 import android.annotation.SuppressLint;
 import android.content.Context;
import android.net.Uri;
 import android.widget.VideoView;

 import androidx.annotation.NonNull;

 import com.airbnb.paris.Paris;
 import com.vyping.masterlibrary.R;

public abstract class DialogVideoPlayer extends CreateDialog {

    private Context context;

    private VideoView Vv_Player;

    private Uri uri;


    /*----------- SetUp - Section ----------*/

    @SuppressLint("SetTextI18n")
    public DialogVideoPlayer(Context context, int parameters) {

        super(context, parameters);

        setParameters(context);
        setModeButtons(BUTTONS_REFRESH_ACCEPT);
        setDialogViews();
        setOptionButtons();
        setDialogListener(new DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() { }

            @Override
            public void PositiveClick() {

                Vv_Player.stopPlayback();
                Vv_Player.start();
            }
        });
    }

    private void setParameters(@NonNull Context context) {

        this.context = context;

        String videoURL = "https://download1482.mediafire.com/f0lf0ntjozdg/wc7nwtthl3ed2na/VCam-20211207-1131.mp4";
        uri= Uri.parse(videoURL);
    }

    private void setDialogViews() {

        int style = R.style.DialogVideoPlayer;
        int attr = R.attr.dialogVideoPlayer;

        Vv_Player = new VideoView(context, null, attr, style);
        Vv_Player.setVideoURI(uri);
        Vv_Player.start();
        Paris.style(Vv_Player).apply(style);

        addCustomView(Vv_Player);
    }

    private void setOptionButtons() {


    }

    /*----- Return -----*/

    protected abstract void ButtonConfirm();
}


