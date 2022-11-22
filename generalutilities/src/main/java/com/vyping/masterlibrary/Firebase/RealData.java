package com.vyping.masterlibrary.Firebase;

import static com.vyping.masterlibrary.time.Definitions.FORMAT_DATE_01;
import static com.vyping.masterlibrary.time.Definitions.FORMAT_HOUR_01;
import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.models.images.Image;
import com.vyping.masterlibrary.time.MyTime;
import com.vyping.masterlibrary.Common.MyNumbers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class RealData {

    DataSnapshot dataSnapshot;


    // ----- SetUp ----- //

    public RealData(@NonNull DataSnapshot dataSnapshot) {

        this.dataSnapshot = dataSnapshot;
    }


    // ----- Key Readers ----- //

    public String getKeyString() {

        if (dataSnapshot.exists()) {

            return String.valueOf(dataSnapshot.getKey());

        } else {

            return "";
        }
    }

    public Long getKeyLong() {

        long number = 0L;

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getKey());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                number = Long.parseLong(value);
            }
        }

        return number;
    }

    public String getKeyTime(String format) {

        String number = getKeyString();
        long timestamp = new MyTime(format, number).getTimestamp();

        return String.valueOf(timestamp);
    }


    // ----- ChildCount Readers ----- //

    public long getChildrenCount() {

        if (dataSnapshot.exists()) {

            return dataSnapshot.getChildrenCount();

        } else {

            return 0L;
        }
    }


    // ----- Boolean Readers ----- //


    public boolean getBoolean() {

        return getBooleanOrDefault(FALSE);
    }

    public boolean getBoolean(String child) {

        return getBooleanOrDefault(child, FALSE);
    }

    public boolean getBooleanOrDefault(boolean boolDefault) {

        if (dataSnapshot.exists()) {

            try {

                boolDefault = (Boolean) Boolean.TRUE.equals(dataSnapshot.getValue(Boolean.class));

            }catch(Exception ignored){}
        }

        return boolDefault;
    }

    public boolean getBooleanOrDefault(String child, boolean boolDefault) {

        if (dataSnapshot.child(child).exists()) {

            try {

                boolDefault = (Boolean) Boolean.TRUE.equals(dataSnapshot.child(child).getValue(Boolean.class));

            }catch(Exception ignored){}
        }

        return boolDefault;
    }


    // ----- String Readers ----- //

    public String getString() {

        return getStringOrDefault("");
    }

    public String getString(String child) {

        return getStringOrDefault(child,"");
    }

    public String getStringOrDefault(String textDefault) {

        if (dataSnapshot.exists()) {

            textDefault = String.valueOf(dataSnapshot.getValue());
        }

        return textDefault;
    }

    public String getStringOrDefault(String child, String textDefault) {

        if (dataSnapshot.child(child).exists()) {

            textDefault = String.valueOf(dataSnapshot.child(child).getValue());
        }

        return textDefault;
    }


    // ----- Integer Readers ----- //

    public int getInteger() {

        return getIntegerOrDefault(0);
    }

    public int getInteger(String child) {

        return getIntegerOrDefault(child, 0);
    }

    public int getIntegerOrDefault(int numDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber) {

                numDefault = Integer.parseInt(value);
            }
        }

        return numDefault;
    }

    public int getIntegerOrDefault(String child, int numDefault) {

        if (dataSnapshot.child(child).exists()) {

            String value = String.valueOf(dataSnapshot.child(child).getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber) {

                numDefault = Integer.parseInt(value);
            }
        }

        return numDefault;
    }


    // ----- Long Readers ----- //

    public long getLong() {

        return getLongOrDefault(0L);
    }

    public long getLong(String child) {

        return getLongOrDefault(child,0L);
    }

    public long getLongOrDefault(long numDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());

            if (!value.equals("")) {

                boolean isNumber = new MyNumbers().isNumber(value);

                if (isNumber) {

                    numDefault = Long.parseLong(value);
                }
            }
        }

        return numDefault;
    }
    
    public long getLongOrDefault(String child, long numDefault) {

        if (dataSnapshot.child(child).exists()) {

            String value = String.valueOf(dataSnapshot.child(child).getValue());

            if (!value.equals("")) {

                boolean isNumber = new MyNumbers().isNumber(value);

                if (isNumber) {

                    numDefault = Long.parseLong(value);
                }
            }
        }

        return numDefault;
    }


    // ----- Float Readers ----- //

    public float getFloat() {

        return getFloatOrDefault(0f);
    }

    public float getFloat(String child) {

        return getFloatOrDefault(child,0f);
    }

    public float getFloatOrDefault(float numDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                numDefault = Float.parseFloat(value);
            }
        }

        return numDefault;
    }

    public float getFloatOrDefault(String child, float numDefault) {

        if (dataSnapshot.child(child).exists()) {

            String value = String.valueOf(dataSnapshot.child(child).getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                numDefault = Float.parseFloat(value);
            }
        }

        return numDefault;
    }


    // ----- Double Readers ----- //

    public double getDouble() {

        return getDoubleOrDefault(0.0);
    }

    public double getDouble(String child) {

        return getDoubleOrDefault(child,0.0);
    }

    public double getDoubleOrDefault(double numDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber) {

                numDefault = Double.parseDouble(value);
            }
        }

        return numDefault;
    }

    public double getDoubleOrDefault(String child, double numDefault) {

        if (dataSnapshot.child(child).exists()) {

            String value = String.valueOf(dataSnapshot.child(child).getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber) {

                numDefault = Double.parseDouble(value);
            }
        }

        return numDefault;
    }


    // ----- Hour Readers ----- //

    public String getHour() {

        return getHourOrDefault("00:00");
    }

    public String getHour(String child) {

        return getHourOrDefault(child,"00:00");
    }

    public String getHourOrDefault(String hourDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                return new MyTime(value).getTime(FORMAT_HOUR_01);
            }
        }

        return hourDefault;
    }

    public String getHourOrDefault(String child, String hourDefault) {

        if (dataSnapshot.child(child).exists()) {

            String value = String.valueOf(dataSnapshot.child(child).getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                return new MyTime(value).getTime(FORMAT_HOUR_01);
            }
        }

        return hourDefault;
    }


    // ----- Date Readers ----- //

    public String getDate() {

        return getDateOrDefault("00-00-00");
    }

    public String getDate(String child) {

        return getDateOrDefault(child, "00-00-00");
    }

    public String getDateOrDefault(String dateDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                dateDefault = new MyTime(value).getTime(FORMAT_DATE_01);
            }
        }

        return dateDefault;
    }

    public String getDateOrDefault(String child, String dateDefault) {

        if (dataSnapshot.child(child).exists()) {

            String value = String.valueOf(dataSnapshot.child(child).getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                dateDefault = new MyTime(value).getTime(FORMAT_DATE_01);
            }
        }

        return dateDefault;
    }


    // ----- Time Readers ----- //

    public String getTime(String format) {

        return getTimeOrDefault("-", format);
    }

    public String getTime(String child, String format) {

        return getTimeOrDefault(child,"-", format);
    }

    public String getTimeOrDefault(String timeDefault, String format) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                timeDefault = new MyTime(value).getTime(format);
            }
        }

        return timeDefault;
    }

    public String getTimeOrDefault(String child, String timeDefault, String format) {

        if (dataSnapshot.child(child).exists()) {

            String value = String.valueOf(dataSnapshot.child(child).getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                timeDefault = new MyTime(value).getTime(format);
            }
        }

        return timeDefault;
    }


    // ----- LatLng Readers ----- //




    // ----- Location Readers ----- //


    // ----- Image Readers ----- //

    public Image getImage() {

        return getImageOrDefault(new Image());
    }

    public Image getImage(String child) {

        return getImageOrDefault(child, new Image());
    }

    public Image getImageOrDefault(Image imageDefault) {

        if (dataSnapshot.exists()) {

            imageDefault = new Image(dataSnapshot);
        }

        return imageDefault;
    }

    public Image getImageOrDefault(String child, Image imageDefault) {

        if (dataSnapshot.child(child).exists()) {

            imageDefault = new Image(dataSnapshot.child(child));
        }

        return imageDefault;
    }


    // ----- List Readers ----- //

    public String getList() {

        String element = "";

        if (dataSnapshot.exists()) {

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                if (element.equals("")) {

                    element = snapshot.toString();

                } else {

                    element = MessageFormat.format("{0}\n\n{1}", element, snapshot.toString());
                }
            }
        }

        return element;
    }

    public String getList(String child) {

        String element = "";

        if (dataSnapshot.child(child).exists()) {

            for (DataSnapshot snapshot : dataSnapshot.child(child).getChildren()) {

                if (element.equals("")) {

                    element = snapshot.toString();

                } else {

                    element = MessageFormat.format("{0}\n\n{1}", element, snapshot.toString());
                }
            }
        }

        return element;
    }


    // ----- Array Readers ----- //

    public ArrayList<DataSnapshot> getArrayDataSnapshot() {

        ArrayList<DataSnapshot> arrayDataSnapshot = new ArrayList<>();

        for (DataSnapshot dataChild : dataSnapshot.getChildren()) {

            arrayDataSnapshot.add(dataChild);
        }

        return arrayDataSnapshot;
    }

    public ArrayList<DataSnapshot> getArrayDataSnapshot(String child) {

        ArrayList<DataSnapshot> arrayDataSnapshot = new ArrayList<>();

        for (DataSnapshot dataChild : dataSnapshot.child(child).getChildren()) {

            arrayDataSnapshot.add(dataChild);
        }

        return arrayDataSnapshot;
    }

    public ArrayList<String> getArrayString() {

        ArrayList<String> arrayList = new ArrayList<>();

        if (dataSnapshot.exists()) {

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                String value = snapshot.toString();
                arrayList.add(value);
            }
        }

        return arrayList;
    }

    public ArrayList<String> getArrayString(String child) {

        ArrayList<String> arrayList = new ArrayList<>();

        if (dataSnapshot.child(child).exists()) {

            for (DataSnapshot snapshot : dataSnapshot.child(child).getChildren()) {

                String value = snapshot.toString();
                arrayList.add(value);
            }
        }

        return arrayList;
    }


    // ----- HashMap Readers ----- //

    public HashMap<String, Object> getHashMap() {

        HashMap<String, Object> hashMap = new HashMap<>();

        if (dataSnapshot.exists()) {

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                String key = snapshot.getKey();
                String value = String.valueOf(snapshot.getValue());

                hashMap.put(key, value);
            }
        }

        return hashMap;
    }

    public HashMap<String, Object> getHashMap(String child) {

        HashMap<String, Object> hashMap = new HashMap<>();

        if (dataSnapshot.child(child).exists()) {

            for (DataSnapshot snapshot : dataSnapshot.child(child).getChildren()) {

                String key = snapshot.getKey();
                String value = String.valueOf(snapshot.getValue());

                hashMap.put(key, value);
            }
        }

        return hashMap;
    }

    public HashMap<String, String> getHashMapString() {

        HashMap<String, String> hashMap = new HashMap<>();

        if (dataSnapshot.exists()) {

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                String key = snapshot.getKey();
                String value = String.valueOf(snapshot.getValue());

                hashMap.put(key, value);
            }
        }

        return hashMap;
    }

    public HashMap<String, String> getHashMapString(String child) {

        HashMap<String, String> hashMap = new HashMap<>();

        if (dataSnapshot.child(child).exists()) {

            for (DataSnapshot snapshot : dataSnapshot.child(child).getChildren()) {

                String key = snapshot.getKey();
                String value = String.valueOf(snapshot.getValue());

                hashMap.put(key, value);
            }
        }

        return hashMap;
    }
}