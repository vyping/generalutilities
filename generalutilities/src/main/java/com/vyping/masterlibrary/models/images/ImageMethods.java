package com.vyping.masterlibrary.models.images;

import static com.vyping.masterlibrary.views.MyImageView.errorImage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.vyping.masterlibrary.Firebase.models.MyUrlStorage;
import com.vyping.masterlibrary.Images.MyDrawable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ImageMethods {

    public static final int PRIORITY_PHONE = 0, PRIORITY_WEB = 1;
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({PRIORITY_PHONE, PRIORITY_WEB})
    public @interface Priority {}

    private final Image image;

    private String urlStorage;
    private @Priority int priority;


    // ----- SetUp ----- //

    public ImageMethods() {

        this.image = new Image();
        this.urlStorage = "";
        this.priority = PRIORITY_PHONE;
    }

    public ImageMethods(Image image) {

        this.image = image;
        this.urlStorage = "";
        this.priority = PRIORITY_PHONE;
    }


    // ----- Getters ----- //

    public Image getImage()
    {
        return image;
    }

    public String getName()
    {
        return image.Name;
    }

    public String getType()
    {
        return image.Type;
    }

    public String getToken()
    {
        return image.Token;
    }

    public String getUrl()
    {
        return image.Url;
    }


    // ----- Setters ----- //

    public void setName(String name)
    {
        this.image.Name = name;
    }

    public void setType(String type)
    {
        image.Type = type;
    }

    public void setToken(String token)
    {
        image.Token = token;
    }

    public void setUrl(String url)
    {
        image.Url = url;
    }


    // ----- Compounds ----- //

    public ImageMethods priority(@Priority int priority) {

        this.priority = priority;

        return this;
    }

    public ImageMethods storage(String bucket) {

        if (image.Type.equals("")) {

            urlStorage = new MyUrlStorage(bucket).file(image.Name).token(image.Token);

        } else {

            urlStorage = new MyUrlStorage(bucket).file(image.Name, image.Type).token(image.Token);
        }

        return  this;
    }

    public Drawable getDrawable(Context context) {
        
        if (image.Type.equals("")) {

            return new MyDrawable().extractFromString(context, image.Name);

        } else {

            return new MyDrawable().extractFromAssets(context, image.Name);
        }
    }

    public Drawable downloadDrawable(Context context) {

        if (!image.Token.equals("")) {

            return new MyDrawable().extractFromUrl(context, urlStorage);

        } else if (!image.Url.equals("")) {

            return new MyDrawable().extractFromUrl(context, image.Url);

        } else {

            return new MyDrawable().extractFromResources(context, errorImage);
        }
    }

    public void putOnImageView(@NonNull ImageView imageView) {

        if (priority == PRIORITY_PHONE) {

            priorizePhone(imageView);

        } else {

            priorizeWeb(imageView);
        }
    }

    public void putOnButton(Button button) {

        if (priority == PRIORITY_PHONE) {

            Drawable drawable = getDrawable(button.getContext());
            drawable.setBounds(0, 0, 60, 60);
            button.setCompoundDrawables(drawable, null, null, null);

        } else {

            Drawable drawable = downloadDrawable(button.getContext());
            drawable.setBounds(0, 0, 60, 60);
            button.setCompoundDrawables(drawable, null, null, null);
        }
    }

    private void priorizePhone(@NonNull ImageView imageView) {

        Context context = imageView.getContext();

        Drawable drawable = getDrawable(context);
        Drawable error = new MyDrawable().extractFromResources(context, errorImage);

        if (drawable != error) {

            Glide.with(context).load(drawable).error(errorImage).into(imageView);

        } else {

            if (!image.Token.equals("")) {

                Glide.with(context).load(urlStorage).error(errorImage).into(imageView);

            } else if (!image.Url.equals("")) {

                Glide.with(context).load(image.Url).error(errorImage).into(imageView);
            }
        }
    }

    private void priorizeWeb(@NonNull ImageView imageView) {

        Context context = imageView.getContext();

        if (!image.Token.equals("")) {

            Glide.with(context).load(urlStorage).error(errorImage).into(imageView);

        } else if (!image.Url.equals("")) {

            Glide.with(context).load(image.Url).error(errorImage).into(imageView);

        } else {

            Drawable drawable = getDrawable(context);
            Glide.with(context).load(drawable).error(errorImage).into(imageView);
        }
    }
}
