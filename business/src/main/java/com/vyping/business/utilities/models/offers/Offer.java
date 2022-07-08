package com.vyping.business.utilities.models.offers;

import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Firebase.RealData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

public class Offer {

    public static final String TAG_MAIN = "Main", TAG_SOCIAL = "Social";
    public static final String TAG_URL = "Image", TAG_TITLE = "Title", TAG_SUBTITLE = "SubTitle", TAG_DESCRIPTION = "Description", TAG_AUTHOR = "Author";
    public static final String TAG_COMMENTS = "Comments", TAG_REACTIONS = "Reactions", TAG_SHARES = "Shares";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_MAIN, TAG_URL, TAG_TITLE, TAG_SUBTITLE, TAG_DESCRIPTION})
    public @interface Tags {}

    public String Id, Url, Title, SubTitle, Description, Author;
    public HashMap<String, String> Reactions, Shares;
    public HashMap<String, Object> Comments;

    /*----- Main Model -----*/

    public Offer(DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Url = realData.getString(TAG_MAIN + "/" + TAG_URL);
        this.Title = realData.getString(TAG_MAIN + "/" + TAG_TITLE);
        this.SubTitle = realData.getString(TAG_MAIN + "/" + TAG_SUBTITLE);
        this.Description =realData.getString(TAG_MAIN + "/" + TAG_DESCRIPTION);
        this.Author = realData.getString(TAG_MAIN + "/" + TAG_AUTHOR);

        this.Comments = realData.getHashMap(TAG_SOCIAL + "/" + TAG_COMMENTS);
        this.Reactions = realData.getHashMapString(TAG_SOCIAL + "/" + TAG_REACTIONS);
        this.Shares = realData.getHashMapString(TAG_SOCIAL + "/" + TAG_SHARES);
    }
}
