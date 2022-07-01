package com.vyping.masterlibrary.models.notices;

import static com.vyping.masterlibrary.time.MyTime.FORMAT_TIME_07;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.time.MyTime;
import com.vyping.masterlibrary.views.MyImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NoticeMethods extends BaseObservable {

    private final Notice notice;


    // ----- SetUp ----- //

    public NoticeMethods(Notice notice)
    {
        this.notice = notice;
    }


    // ----- Basic Methods ----- //

    public Notice getNotice()
    {
        return notice;
    }

    public String getId()
    {
        return notice.Id;
    }

    public String getUrl()
    {
        return notice.Url;
    }

    public String getTitle()
    {
        return notice.Title;
    }

    public String getSubTitle()
    {
        return notice.SubTitle;
    }

    public String getDescription()
    {
        return notice.Description;
    }

    public String getAuthor()
    {
        return notice.Author;
    }

    public HashMap<String, String> getReactions()
    {
        return notice.Reactions;
    }

    public HashMap<String, String> getShares()
    {
        return notice.Shares;
    }

    public HashMap<String, Object> getComments()
    {
        return notice.Comments;
    }


    // ----- Compounds Methods ----- //

    public String getDate() {

        return new MyTime(notice.Id).getTime(FORMAT_TIME_07);
    }

    // ----- Binding Methods ----- //


    @BindingAdapter("iconButton")
    public static void setIconButton(@NonNull Button button, Drawable resDrawable) {

        Drawable drawable = new MyDrawable().changeDrawableColor(button.getContext(), resDrawable, R.color.Grey);
        Drawable drawable2 = new MyDrawable().changeSizeBounds(drawable, 52, 52);

        button.setCompoundDrawables(drawable2, null, null, null);
    }

    @BindingAdapter("reactions") @SuppressLint("DefaultLocale")
    public static void loadReactions(@NonNull TextView view, @NonNull HashMap<String, String> hashMap) {

        String reactions = "";
        int count = 0;

        for (HashMap.Entry<String, String> entry : hashMap.entrySet()) {

            count = count + 1;

            String user = entry.getKey();
            String reaction = entry.getValue();

            if (!reactions.contains(reaction)) {

                reactions = reactions + reaction;
            }
        }

        view.setText(String.format("%s %d", reactions, count));
    }

    @BindingAdapter("comments") @SuppressLint("DefaultLocale")
    public static void loadComments(@NonNull TextView view, @NonNull HashMap<String, Object> hashMap) {

        int count = 0;

        for (HashMap.Entry<String, Object> entry : hashMap.entrySet()) {

            count = count + 1;

            String user = entry.getKey();
            Object comment = entry.getValue();
        }

        view.setText(String.format("%d Comentarios", count));
    }

    @BindingAdapter("shares") @SuppressLint("DefaultLocale")
    public static void loadShares(@NonNull TextView view, @NonNull HashMap<String, String> hashMap) {

        int count = 0;

        for (HashMap.Entry<String, String> entry : hashMap.entrySet()) {

            String user = entry.getKey();
            int share = Integer.parseInt(entry.getValue());

            count = count + share;
        }

        view.setText(String.format("%d Veces Compartido", count));
    }

    @BindingAdapter("profileImage")
    public static void loadImage(@NonNull ImageView view, String imageUrl) {

        new MyImageView().putImageFromWeb(view, imageUrl);
    }


    // ----- Search Methods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(notice.Title, notice.SubTitle, notice.Description, notice.Author, notice.Description));
    }
}
