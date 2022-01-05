package com.vyping.masterlibrary.Dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_BOTH;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.vyping.masterlibrary.R;

public abstract class DialogYouTube  {

    private Context context;
    private Activity activity;
    private CreateDialog createDialog;

    private static final String codeVideo = "YDNRsYrprG4";
    private static final String apiKey = "AIzaSyBtOl0rAhQvfDCBqtQzSskhGJDBYmkG7g0";


    /**
     * -------- SetUp Section
     */

    @SuppressLint("SetTextI18n")
    public DialogYouTube(@NonNull Context context, int parameters, String Text) {

        setParameters(context, Text);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(Dialog dialog) {

                setViewsOnLayout(dialog);


                dialog.show();
            }

            @Override
            protected void RefreshClick() { }

            @Override
            protected void PositiveClick() { }
        };

        setOptionButtons();
    }

    private void setOptionButtons() {

        createDialog.setModeButtons(BUTTONS_BOTH);
    }



    /**
     * ------  Interface Section Listeners
     */


    /*----- Tools -----*/

    private void setParameters(@NonNull Context context, String text) {

        this.context = context;
        activity = (Activity) context;
    }

    private void setViewsOnLayout(@NonNull Dialog dialog) {

        int style = R.style.DialogInputText;
        int attr = R.attr.dialogInputText;

        LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);
        layout.setOrientation(LinearLayout.VERTICAL);

        String urlVideo = "https://www.youtube.com/watch?v=tXmQ8WVHkOk";

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_help, layout, true);
        int id = view.getId();

        YouTubePlayerFragment youTubePlayerFragment1 = (YouTubePlayerFragment) activity.getFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment1.initialize(apiKey, onInitializedListener);
        android.app.FragmentManager fragmentManager1 = activity.getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
        fragmentTransaction1.add(id, youTubePlayerFragment1);
        fragmentTransaction1.commit();

    }

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


