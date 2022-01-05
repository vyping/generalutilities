package com.vyping.masterlibrary.Firebase.Realtime;

import static com.vyping.masterlibrary.Firebase.Realtime.DatabaseRealtime.DATASNAPSHOT;
import static java.lang.Boolean.FALSE;

import android.location.Location;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.Numbers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Readers {


    // ----- Key Readers ----- //

    public String getKeyString() {

        return getKeyString(DATASNAPSHOT);
    }

    public String getKeyString(@NonNull DataSnapshot dataSnapshot) {

        if (dataSnapshot.exists()) {

            return String.valueOf(dataSnapshot.getKey());

        } else {

            return "";
        }
    }

    public Long getKeyLong() {

        return getKeyLong(DATASNAPSHOT);
    }

    public Long getKeyLong(@NonNull DataSnapshot dataSnapshot) {

        long number = 0L;

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getKey());
            boolean isNumber = new Numbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                number = Long.parseLong(value);
            }
        }

        return number;
    }


    // ----- ChildCount Readers ----- //

    public long getChildrenCount() {

        return getChildrenCount(DATASNAPSHOT);
    }

    public long getChildrenCount(String child) {

        if (DATASNAPSHOT.exists()) {

            return getChildrenCount(DATASNAPSHOT.child(child));

        } else{

            return 0L;
        }
    }

    public long getChildrenCount(@NonNull DataSnapshot dataSnapshot) {

        if (dataSnapshot.exists()) {

            return dataSnapshot.getChildrenCount();

        } else{

            return 0L;
        }
    }


    // ----- Boolean Readers ----- //

    public boolean getBoolean() {

        return getBooleanOrDefault(DATASNAPSHOT, FALSE);
    }

    public boolean getBoolean(String child) {

        return getBooleanOrDefault(DATASNAPSHOT.child(child), FALSE);
    }

    public boolean getBoolean(@NonNull DataSnapshot dataSnapshot) {

        return getBooleanOrDefault(dataSnapshot, FALSE);
    }

    public boolean getBooleanOrDefault(boolean boolDefault) {

        return getBooleanOrDefault(DATASNAPSHOT, boolDefault);
    }

    public boolean getBooleanOrDefault(String child, boolean boolDefault) {

        if (DATASNAPSHOT.exists()) {

            return getBooleanOrDefault(DATASNAPSHOT.child(child), boolDefault);

        } else {

            return boolDefault;
        }
    }

    public boolean getBooleanOrDefault(@NonNull DataSnapshot dataSnapshot, boolean boolDefault) {

        if (dataSnapshot.exists()) {

            Boolean Bool = (Boolean) dataSnapshot.getValue();

            if (Bool != null) {

                boolDefault = Bool;
            }
        }

        return boolDefault;
    }


    // ----- String Readers ----- //

    public String getString() {

        return getStringOrDefault(DATASNAPSHOT, "");
    }

    public String getString(String child) {

        return getStringOrDefault(DATASNAPSHOT.child(child), "");
    }

    public String getString(@NonNull DataSnapshot dataSnapshot) {

        return getStringOrDefault(dataSnapshot, "");
    }

    public String getStringOrDefault(String textDefault) {

        return getStringOrDefault(DATASNAPSHOT, textDefault);
    }

    public String getStringOrDefault(String child, String textDefault) {

        if (DATASNAPSHOT.exists()) {

            return getStringOrDefault(DATASNAPSHOT.child(child), textDefault);

        } else {

            return textDefault;
        }
    }

    public String getStringOrDefault(@NonNull DataSnapshot dataSnapshot, String textDefault) {

        if (dataSnapshot.exists()) {

            textDefault = String.valueOf(dataSnapshot.getValue());
        }

        return textDefault;
    }


    // ----- Integer Readers ----- //

    public int getInteger() {

        return getIntegerOrDefault(DATASNAPSHOT, 0);
    }

    public int getInteger(String child) {

        return getIntegerOrDefault(DATASNAPSHOT.child(child), 0);
    }

    public int getInteger(@NonNull DataSnapshot dataSnapshot) {

        return getIntegerOrDefault(dataSnapshot, 0);
    }

    public int getIntegerOrDefault(int numDefault) {

        return getIntegerOrDefault(DATASNAPSHOT, numDefault);
    }

    public int getIntegerOrDefault(String child, int numDefault) {

        if (DATASNAPSHOT.exists()) {

            return getIntegerOrDefault(DATASNAPSHOT.child(child), numDefault);

        } else {

            return numDefault;
        }
    }

    public int getIntegerOrDefault(@NonNull DataSnapshot dataSnapshot, int numDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new Numbers().isNumber(value);

            if (isNumber) {

                numDefault = Integer.parseInt(value);
            }
        }

        return numDefault;
    }


    // ----- Long Readers ----- //

    public long getLong() {

        return getLongOrDefault(DATASNAPSHOT, 0L);
    }

    public long getLong(String child) {

        return getLongOrDefault(DATASNAPSHOT.child(child), 0L);
    }

    public long getLong(@NonNull DataSnapshot dataSnapshot) {

        return getLongOrDefault(dataSnapshot, 0L);
    }

    public long getLongOrDefault(long numDefault) {

        return getLongOrDefault(DATASNAPSHOT, numDefault);
    }

    public long getLongOrDefault(String child, long numDefault) {

        if (DATASNAPSHOT.exists()) {

            return getLongOrDefault(DATASNAPSHOT.child(child), numDefault);

        } else {

            return numDefault;
        }
    }

    public long getLongOrDefault(@NonNull DataSnapshot dataSnapshot, long numDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new Numbers().isNumber(value);

            if (isNumber) {

                numDefault = Long.parseLong(value);
            }
        }

        return numDefault;
    }


    // ----- Float Readers ----- //

    public float getFloat() {

        return getFloatOrDefault(DATASNAPSHOT, 0f);
    }

    public float getFloat(String child) {

        return getFloatOrDefault(DATASNAPSHOT.child(child), 0f);
    }

    public float getFloat(@NonNull DataSnapshot dataSnapshot) {

        return getFloatOrDefault(dataSnapshot, 0f);
    }

    public float getFloatOrDefault(float numDefault) {

        return getFloatOrDefault(DATASNAPSHOT, numDefault);
    }

    public float getFloatOrDefault(String child, float numDefault) {

        if (DATASNAPSHOT.exists()) {

            return getFloatOrDefault(DATASNAPSHOT.child(child), numDefault);

        } else {

            return numDefault;
        }
    }

    public float getFloatOrDefault(@NonNull DataSnapshot dataSnapshot, float numDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new Numbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                numDefault = Float.parseFloat(value);

            }
        }

        return numDefault;
    }


    // ----- Double Readers ----- //

    public double getDouble() {

        return getDoubleOrDefault(DATASNAPSHOT, 0.0);
    }

    public double getDouble(String child) {

        return getDoubleOrDefault(DATASNAPSHOT.child(child), 0.0);
    }

    public double getDouble(@NonNull DataSnapshot dataSnapshot) {

        return getDoubleOrDefault(dataSnapshot, 0.0);
    }

    public double getDoubleOrDefault(double numDefault) {

        return getDoubleOrDefault(DATASNAPSHOT, numDefault);
    }

    public double getDoubleOrDefault(String child, double numDefault) {

        if (DATASNAPSHOT.exists()) {

            return getDoubleOrDefault(DATASNAPSHOT.child(child), numDefault);

        } else {

            return numDefault;
        }
    }

    public double getDoubleOrDefault(@NonNull DataSnapshot dataSnapshot, double numDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new Numbers().isNumber(value);

            if (isNumber) {

                numDefault = Double.parseDouble(value);
            }
        }

        return numDefault;
    }


    // ----- Hour Readers ----- //

    public String getHour() {

        return getHourOrDefault(DATASNAPSHOT, "00:00");
    }

    public String getHour(String child) {

        return getHourOrDefault(DATASNAPSHOT.child(child), "00:00");
    }

    public String getHour(@NonNull DataSnapshot dataSnapshot) {

        return getHourOrDefault(dataSnapshot, "00:00");
    }

    public String getHourOrDefault(String hourDefault) {

        return getHourOrDefault(DATASNAPSHOT, hourDefault);
    }

    public String getHourOrDefault(String child, String hourDefault) {

        if (DATASNAPSHOT.exists()) {

             return getHourOrDefault(DATASNAPSHOT.child(child), hourDefault);

        } else {

            return hourDefault;
        }
    }

    public String getHourOrDefault(@NonNull DataSnapshot dataSnapshot, String hourDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new Numbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                long number = Long.parseLong(value);

                hourDefault = new DateTools().getTime("HH:mm", number);
            }
        }

        return hourDefault;
    }


    // ----- Date Readers ----- //

    public String getDate() {

        return getDateOrDefault(DATASNAPSHOT, "00-00-00");
    }

    public String getDate(String child) {

        return getDateOrDefault(DATASNAPSHOT.child(child), "00-00-00");
    }

    public String getDate(@NonNull DataSnapshot dataSnapshot) {

        return getDateOrDefault(dataSnapshot, "00-00-00");
    }

    public String getDateOrDefault(String dateDefault) {

        return getDateOrDefault(DATASNAPSHOT, dateDefault);
    }

    public String getDateOrDefault(String child, String dateDefault) {

        if (DATASNAPSHOT.exists()) {

            return getDateOrDefault(DATASNAPSHOT.child(child), dateDefault);

        } else {

            return dateDefault;
        }
    }

    public String getDateOrDefault(@NonNull DataSnapshot dataSnapshot, String dateDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new Numbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                long number = Long.parseLong(value);

                dateDefault = new DateTools().getTime("dd-MM-yy", number);
            }
        }

        return dateDefault;
    }


    // ----- Time Readers ----- //

    public String getTime(String format) {

        return getTimeOrDefault(DATASNAPSHOT, "-", format);
    }

    public String getTime(String child, String format) {

        return getTimeOrDefault(DATASNAPSHOT.child(child), "-", format);
    }

    public String getTime(@NonNull DataSnapshot timeSnapshot, String format) {

        return getTimeOrDefault(timeSnapshot, "-", format);
    }

    public String getTimeOrDefault(String timeDefault, String format) {

        return getTimeOrDefault(DATASNAPSHOT, timeDefault, format);
    }

    public String getTimeOrDefault(String child, String timeDefault, String format) {

        if (DATASNAPSHOT.exists()) {

            return getTimeOrDefault(DATASNAPSHOT.child(child), timeDefault, format);

        } else {

            return timeDefault;
        }
    }

    public String getTimeOrDefault(@NonNull DataSnapshot dataSnapshot, String timeDefault, String format) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new Numbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                long number = Long.parseLong(value);

                timeDefault = new DateTools().getTime(format, number);
            }
        }

        return timeDefault;
    }


    // ----- LatLng Readers ----- //

    public LatLng getLatLng() {

        double latitude = 0.0, longitude = 0.0;

        if (DATASNAPSHOT.exists()) {

            latitude = getDouble(DATASNAPSHOT.child("latitude"));
            longitude = getDouble(DATASNAPSHOT.child("longitude"));
        }

        return new LatLng(latitude, longitude);
    }

    public LatLng getLatLng(String child) {

        double latitude = 0.0, longitude = 0.0;

        if (DATASNAPSHOT.exists()) {

            latitude = getDouble(DATASNAPSHOT.child("latitude"));
            longitude = getDouble(DATASNAPSHOT.child("longitude"));
        }

        return new LatLng(latitude, longitude);
    }

    public LatLng getLatLng(@NonNull DataSnapshot dataSnapshot) {

        double latitude = 0.0, longitude = 0.0;

        if (dataSnapshot.exists()) {

            latitude = getDouble(dataSnapshot.child("latitude"));
            longitude = getDouble(dataSnapshot.child("longitude"));
        }

        return new LatLng(latitude, longitude);
    }

    public LatLng getLatLng(@NonNull DataSnapshot latDataSnapshot, @NonNull DataSnapshot lngDataSnapshot) {

        double latitude = 0.0, longitude = 0.0;

        if (latDataSnapshot.exists() && lngDataSnapshot.exists()) {

            latitude = getDouble(latDataSnapshot);
            longitude = getDouble(lngDataSnapshot);
        }

        return new LatLng(latitude, longitude);
    }

    public LatLng getLatLngOrDefault(LatLng latLngDefault) {

        return getLatLngOrDefault(DATASNAPSHOT, latLngDefault);
    }

    public LatLng getLatLngOrDefault(String child, LatLng latLngDefault) {

        return getLatLngOrDefault(DATASNAPSHOT.child(child), latLngDefault);
    }

    public LatLng getLatLngOrDefault(@NonNull DataSnapshot dataSnapshot, LatLng latLngDefault) {

        if (dataSnapshot.exists()) {

            double latitude = getDouble(dataSnapshot.child("latitude"));
            double longitude = getDouble(dataSnapshot.child("longitude"));
            latLngDefault = new LatLng(latitude, longitude);
        }

        return latLngDefault;
    }

    public LatLng getLatLngOrDefault(@NonNull DataSnapshot latDataSnapshot, @NonNull DataSnapshot lngDataSnapshot, LatLng latLngDefault) {

        if (latDataSnapshot.exists() && lngDataSnapshot.exists()) {

            double latitude = getDouble(latDataSnapshot);
            double longitude = getDouble(lngDataSnapshot);
            latLngDefault = new LatLng(latitude, longitude);
        }

        return latLngDefault;
    }

    public LatLng getLatLngOrDefault(double latDefault, double lngDefault) {

        return getLatLngOrDefault(DATASNAPSHOT, latDefault, lngDefault);
    }

    public LatLng getLatLngOrDefault(String child, double latDefault, double lngDefault) {

        if (DATASNAPSHOT.child(child).exists()) {

            return getLatLngOrDefault(DATASNAPSHOT.child(child), latDefault, lngDefault);

        } else {

            return new LatLng(latDefault, lngDefault);
        }
    }

    public LatLng getLatLngOrDefault(@NonNull DataSnapshot dataSnapshot, double latDefault, double lngDefault) {

        return getLatLngOrDefault(dataSnapshot, new LatLng(latDefault, lngDefault));
    }

    public LatLng getLatLngOrDefault(@NonNull DataSnapshot latDataSnapshot, @NonNull DataSnapshot lngDataSnapshot, double latDefault, double lngDefault) {

        return getLatLngOrDefault(latDataSnapshot, lngDataSnapshot, new LatLng(latDefault, lngDefault));
    }



    // ----- Location Readers ----- //

    public Location getLocation() {

        return getLocationOrDefault(DATASNAPSHOT, 0.0, 0.0);
    }

    public Location getLocation(String child) {

        return getLocationOrDefault(DATASNAPSHOT.child(child), 0.0, 0.0);
    }

    public Location getLocation(@NonNull DataSnapshot dataSnapshot) {

        return getLocationOrDefault(dataSnapshot, 0.0, 0.0);
    }

    public Location getLocation(@NonNull DataSnapshot latDataSnapshot, DataSnapshot lngDataSnapshot) {

        return getLocationOrDefault(latDataSnapshot, lngDataSnapshot, 0.0, 0.0);
    }

    public Location getLocationOrDefault(Location locDefault) {

        return getLocationOrDefault(DATASNAPSHOT, locDefault);
    }

    public Location getLocationOrDefault(String child, Location locDefault) {

        return getLocationOrDefault(DATASNAPSHOT.child(child), locDefault);
    }

    public Location getLocationOrDefault(@NonNull DataSnapshot dataSnapshot, Location locDefault) {

        if (dataSnapshot.exists()) {

            return getLocationOrDefault(dataSnapshot.child("latitude"), dataSnapshot.child("longitude"), locDefault);

        } else {

            return locDefault;
        }
    }

    public Location getLocationOrDefault(@NonNull DataSnapshot latDataSnapshot, DataSnapshot lngDataSnapshot, Location locDefault) {

        Location location = new Location("");

        if (latDataSnapshot.exists() && lngDataSnapshot.exists()) {

            double latitude = getDouble(latDataSnapshot);
            double longitude = getDouble(lngDataSnapshot);

            location.setLatitude(latitude);
            location.setLongitude(longitude);

        } else {

            location = locDefault;
        }

        return location;
    }

    public Location getLocationOrDefault(double latDefault, double lngDefault) {

        return getLocationOrDefault(DATASNAPSHOT, latDefault, lngDefault);
    }

    public Location getLocationOrDefault(String child, double latDefault, double lngDefault) {

        return getLocationOrDefault(DATASNAPSHOT.child(child), latDefault, lngDefault);
    }

    public Location getLocationOrDefault(@NonNull DataSnapshot dataSnapshot, double latDefault, double lngDefault) {

        if (dataSnapshot.exists()) {

            return getLocationOrDefault(dataSnapshot.child("latitude"), dataSnapshot.child("longitude"), latDefault, lngDefault);

        } else {

            Location  location = new Location("");
            location.setLatitude(latDefault);
            location.setLongitude(lngDefault);

            return location;
        }
    }

    public Location getLocationOrDefault(@NonNull DataSnapshot latDataSnapshot, DataSnapshot lngDataSnapshot, double latDefault, double lngDefault) {

        Location location = new Location("");

        if (latDataSnapshot.exists() && lngDataSnapshot.exists()) {

            double latitude = getDouble(latDataSnapshot);
            double longitude = getDouble(lngDataSnapshot);

            location.setLatitude(latitude);
            location.setLongitude(longitude);

        } else {

            location.setLatitude(latDefault);
            location.setLongitude(lngDefault);
        }

        return location;
    }



    // ----- List Readers ----- //

    public String getList() {

        return getList(DATASNAPSHOT);
    }

    public String getList(String child) {

        return getList(DATASNAPSHOT.child(child));
    }

    public String getList(@NonNull DataSnapshot dataSnapshot) {

        String element = "";

        if (dataSnapshot.exists()) {

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                if (element.equals("")) {

                    element = getString(snapshot);

                } else {

                    element = MessageFormat.format("{0}\n\n{1}", element, getString(snapshot));
                }
            }
        }

        return element;
    }


    // ----- Array Readers ----- //

    public ArrayList<String> getArrayString() {

        return getArrayString(DATASNAPSHOT);
    }

    public ArrayList<String> getArrayString(String child) {

        if (DATASNAPSHOT.exists()) {

            return getArrayString(DATASNAPSHOT.child(child));

        } else {

            return new ArrayList<>();
        }
    }

    public ArrayList<String> getArrayString(@NonNull DataSnapshot dataSnapshot) {

        ArrayList<String> arrayList = new ArrayList<>();

        if (dataSnapshot.exists()) {

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                arrayList.add(getString(snapshot));
            }
        }

        return arrayList;
    }


    // ----- HashMap Readers ----- //

    public HashMap<String, String> getHashMapString() {

        return getHashMapString(DATASNAPSHOT);
    }

    public HashMap<String, String> getHashMapString(String child) {

        if (DATASNAPSHOT.exists()) {

            return getHashMapString(DATASNAPSHOT.child(child));

        } else {

            return new HashMap<>();
        }
    }

    public HashMap<String, String> getHashMapString(@NonNull DataSnapshot dataSnapshot) {

        HashMap<String, String> hashMap = new HashMap<>();

        if (dataSnapshot.exists()) {

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                String key = getKeyString(snapshot);
                String value = getString(snapshot);

                hashMap.put(key, value);
            }
        }

        return hashMap;
    }
}