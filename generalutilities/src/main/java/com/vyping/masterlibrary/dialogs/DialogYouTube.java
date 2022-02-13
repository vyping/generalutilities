package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL_ACCEPT;
import static java.lang.Boolean.FALSE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.vyping.masterlibrary.R;

public abstract class DialogYouTube extends CreateDialog  {

    private Context context;
    private Activity activity;
    private CreateDialog createDialog;

    private static final String codeVideo = "YDNRsYrprG4";
    private static String apiKey;


    /**
     * -------- SetUp Section
     */

    @SuppressLint("SetTextI18n")
    public DialogYouTube(@NonNull Context context, int parameters, String Text, String apiKey) {

        super(context, parameters);

        setParameters(context, Text, apiKey);
        setDialogViews();
        setModeButtons(BUTTONS_CANCEL_ACCEPT);
        setDialogListener(new CreateDialog.DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() { }

            @Override
            public void PositiveClick() {}
        });
    }

    private void setParameters(@NonNull Context context, String text, String ApiKey) {

        this.context = context;
        activity = (Activity) context;
        apiKey = ApiKey;
    }

    private void setDialogViews() {

        int style = R.style.DialogInputText;
        int attr = R.attr.dialogInputText;

        String urlVideo = "https://www.youtube.com/watch?v=tXmQ8WVHkOk";

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_help, getContainer(), true);
        int id = view.getId();

        YouTubePlayerFragment youTubePlayerFragment1 = (YouTubePlayerFragment) activity.getFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment1.initialize(apiKey, onInitializedListener);
        android.app.FragmentManager fragmentManager1 = activity.getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
        fragmentTransaction1.add(id, youTubePlayerFragment1);
        fragmentTransaction1.commit();

    }


    /**
     * ------  Interface Section Listeners
     */


    private final YouTubePlayer.OnInitializedListener onInitializedListener = new YouTubePlayer.OnInitializedListener() {

        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

            if (!b) {

                youTubePlayer.setShowFullscreenButton(FALSE);
                youTubePlayer.setFullscreen(FALSE);
                youTubePlayer.loadVideo(codeVideo);
                youTubePlayer.play();
            }
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) { }
    };


    /*----- Return -----*/

    protected abstract void ButtonConfirm(String InputText);

}


