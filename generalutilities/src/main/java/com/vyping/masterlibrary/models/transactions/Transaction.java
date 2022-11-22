package com.vyping.masterlibrary.models.transactions;

import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Transaction {

    @Exclude
    public static final String TAG_DESCRIPTION = "Description", TAG_NUMBER = "Number", TAG_HOUR = "Hour", TAG_MOUNT = "Mount", TAG_RESPONSIBLE = "Responsible", TAG_TYPE = "Type", TAG_REFERENCE = "Reference", TAG_METHOD = "Method";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_DESCRIPTION, TAG_NUMBER, TAG_HOUR, TAG_MOUNT, TAG_RESPONSIBLE, TAG_TYPE, TAG_REFERENCE, TAG_METHOD})
    public @interface Tags {}

    @Exclude
    public String Id;
    public String Description, Number, Hour, Mount, Responsible, Type, Reference, Method;
    @Exclude
    public boolean Selected;


    /*----- Main Model -----*/

    public Transaction() {

        this.Id = "";
        this.Number = "";
        this.Description = "";
        this.Hour = "";
        this.Mount = "";
        this.Responsible = "";
        this.Type = "";
        this.Reference = "";
        this.Method = "";
        this.Selected = FALSE;
    }

    public Transaction(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Number = realData.getString(TAG_NUMBER);
        this.Description = realData.getString(TAG_DESCRIPTION);
        this.Hour = realData.getString(TAG_HOUR);
        this.Mount = realData.getString(TAG_MOUNT);
        this.Responsible = realData.getString(TAG_RESPONSIBLE);
        this.Type = realData.getString(TAG_TYPE);
        this.Reference = realData.getString(TAG_REFERENCE);
        this.Method = realData.getString(TAG_METHOD);
        this.Selected = FALSE;
    }
}
