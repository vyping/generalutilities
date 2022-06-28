package com.vyping.masterlibrary.Json;

import static com.vyping.masterlibrary.time.Definitions.FORMAT_DATE_01;
import static com.vyping.masterlibrary.time.Definitions.FORMAT_HOUR_01;

import android.content.Context;
import android.util.JsonReader;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.time.MyTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;

public class MyJsonReader {

    private String Tag;


    // ----- String Readers ----- //

    public String getJsonString(@NonNull JSONArray array, int index) {

        try {

            return array.getString(index);

        } catch (JSONException e) {

            return "";
        }
    }

    public String getJsonString(@NonNull JSONObject jsonObject, String key) {

        try {

            return jsonObject.getString(key);

        } catch (JSONException e) {

            return "";
        }
    }


    // ----- Long Readers ----- //

    public long getJsonLong(@NonNull JSONArray array, int index) {

        try {

            return array.getLong(index);

        } catch (JSONException e) {

            e.printStackTrace();

            return 0L;
        }
    }

    public long getJsonLong(@NonNull JSONObject jsonObject, String key) {

        try {

            return jsonObject.getLong(key);

        } catch (JSONException e) {

            e.printStackTrace();

            return 0L;
        }
    }


    // ----- Double Readers ----- //

    public double getJsonDouble(@NonNull JSONArray array, int index) {

        try {

            return array.getDouble(index);

        } catch (JSONException e) {

            e.printStackTrace();

            return 0.0;
        }
    }

    public double getJsonDouble(@NonNull JSONObject jsonObject, String key) {

        try {

            return jsonObject.getDouble(key);

        } catch (JSONException e) {

            e.printStackTrace();

            return 0.0;
        }
    }


    // ----- Integer Readers ----- //

    public int getJsonInt(@NonNull JSONArray array, int index) {

        try {

            return array.getInt(index);

        } catch (JSONException e) {

            e.printStackTrace();

            return 0;
        }
    }

    public int getJsonInt(@NonNull JSONObject jsonObject, String key) {

        try {

            return jsonObject.getInt(key);

        } catch (JSONException e) {

            e.printStackTrace();

            return 0;
        }
    }


    // ----- ArrayList Readers ----- //

    public ArrayList<String> getArrayList(@NonNull JSONArray jsonArray) {

        ArrayList<String> arrayList = new ArrayList<>();

        try {

            for (int i = 0; i < jsonArray.length(); i++) {

                arrayList.add(jsonArray.getString(i));
            }

            return arrayList;

        } catch (JSONException e) {

            e.printStackTrace();

            return arrayList;
        }
    }

    public ArrayList<String> getArrayList(@NonNull JSONObject jsonObject, String key) {

        ArrayList<String> arrayList = new ArrayList<>();

        try {

            JSONArray jsonArray = jsonObject.getJSONArray(key);

            for (int i = 0; i < jsonObject.length(); i++) {

                arrayList.add(getJsonString(jsonArray, i));
            }

            return arrayList;

        } catch (JSONException e) {

            e.printStackTrace();

            return arrayList;
        }
    }



    public String getJsonDate(@NonNull JSONArray array, int index) {

        long longDate = getJsonLong(array, index);

        if (longDate != 0L) {

            return new MyTime(longDate).getTime(FORMAT_DATE_01);

        } else {

            return "";
        }
    }

    public String getJsonHour(@NonNull JSONArray array, int index) {

        try {

            long hour = array.getLong(index);

            return new MyTime(hour).getTime(FORMAT_HOUR_01);

        } catch (JSONException e) {

            e.printStackTrace();

            return "";
        }
    }

    public long getArrayListSize(@NonNull JSONArray array) {

        return array.length();
    }

    public String getArrayListToString(@NonNull JSONArray array) {

        try {

            String Text = "";

            for (int i = 0; i < array.length(); ++i) {

                if (Text.equals("")) {

                    Text = array.getString(i);

                } else {

                    Text = MessageFormat.format("{0}\n\n{1}", Text, array.getString(i));
                }
            }

            return Text;

        } catch (JSONException e) {

            e.printStackTrace();

            return "";
        }

    }

    public String getArrayListToString(@NonNull JSONArray array, String censure) {

        try {

            String Text = "";

            for (int i = 0; i < array.length(); ++i) {

                if (Text.equals("")) {

                    Text = array.getString(i);

                } else {

                    String line = array.getString(i);

                    if (!line.contains(censure)) {

                        Text = MessageFormat.format("{0}\n\n{1}", Text, line);
                    }
                }
            }

            return Text;

        } catch (JSONException e) {

            e.printStackTrace();

            return "";
        }
    }



    public String selectPlaceOrAddress(@NonNull JSONArray place) {

        String Place = getJsonString(place, 0);
        String Address = getJsonString(place, 2);

        if (!Place.contains("Domicilio")) {

            return Place;

        } else {

            return Address;
        }
    }



    /**
     * -------- Tools Section
     */

    public ArrayList<String> toArrayList(@NonNull Context context, int JsonList, String tag) {

        Tag = tag;
        InputStream imputStream = context.getResources().openRawResource(JsonList);

        try {

            return readJsonStream(imputStream);

        } catch (Exception e) {
            return null;
        }
    }

    @NonNull
    private ArrayList<String> readJsonStream(InputStream in) throws IOException {

        try (JsonReader reader = new JsonReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {

            return readItemsArray(reader);
        }
    }

    @NonNull
    private ArrayList<String> readItemsArray(@NonNull JsonReader reader) throws IOException {

        ArrayList<String> Items = new ArrayList<>();

        reader.beginArray();

        while (reader.hasNext()) {

            Items.add(readItem(reader));
        }

        reader.endArray();

        return Items;
    }

    private String readItem(@NonNull JsonReader reader) throws IOException {

        String Item = null;

        reader.beginObject();

        while (reader.hasNext()) {

            String item = reader.nextName();

            if (item.equals(Tag)) {

                Item = reader.nextString();

            } else {

                reader.skipValue();
            }
        }

        reader.endObject();

        return Item;
    }
}

