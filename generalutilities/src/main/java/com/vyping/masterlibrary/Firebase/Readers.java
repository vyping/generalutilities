package com.vyping.masterlibrary.Firebase;

import static java.lang.Boolean.FALSE;

import android.location.Location;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.MyNumbers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Readers {

    // ----- Key Readers ----- //

    public String getKeyString(@NonNull DataSnapshot dataSnapshot) {

        if (dataSnapshot.exists()) {

            return String.valueOf(dataSnapshot.getKey());

        } else {

            return "";
        }
    }

    public Long getKeyLong(@NonNull DataSnapshot dataSnapshot) {

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

    public String getKeyTime(@NonNull DataSnapshot dataSnapshot, String format) {

        long number = getKeyLong(dataSnapshot);

        return new DateTools().getTime(format, number);
    }


    // ----- ChildCount Readers ----- //

    public long getChildrenCount(@NonNull DataSnapshot dataSnapshot) {

        if (dataSnapshot.exists()) {

            return dataSnapshot.getChildrenCount();

        } else {

            return 0L;
        }
    }


    // ----- Boolean Readers ----- //


    public boolean getBoolean(@NonNull DataSnapshot dataSnapshot) {

        return getBooleanOrDefault(dataSnapshot, FALSE);
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


    public String getString(@NonNull DataSnapshot dataSnapshot) {

        return getStringOrDefault(dataSnapshot, "");
    }

    public String getStringOrDefault(@NonNull DataSnapshot dataSnapshot, String textDefault) {

        if (dataSnapshot.exists()) {

            textDefault = String.valueOf(dataSnapshot.getValue());
        }

        return textDefault;
    }


    // ----- Integer Readers ----- //

    public int getInteger(@NonNull DataSnapshot dataSnapshot) {

        return getIntegerOrDefault(dataSnapshot, 0);
    }

    public int getIntegerOrDefault(@NonNull DataSnapshot dataSnapshot, int numDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber) {

                numDefault = Integer.parseInt(value);
            }
        }

        return numDefault;
    }


    // ----- Long Readers ----- //

    public long getLong(@NonNull DataSnapshot dataSnapshot) {

        return getLongOrDefault(dataSnapshot, 0L);
    }


    public long getLongOrDefault(@NonNull DataSnapshot dataSnapshot, long numDefault) {

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


    // ----- Float Readers ----- //

    public float getFloat(@NonNull DataSnapshot dataSnapshot) {

        return getFloatOrDefault(dataSnapshot, 0f);
    }

    public float getFloatOrDefault(@NonNull DataSnapshot dataSnapshot, float numDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                numDefault = Float.parseFloat(value);

            }
        }

        return numDefault;
    }


    // ----- Double Readers ----- //

    public double getDouble(@NonNull DataSnapshot dataSnapshot) {

        return getDoubleOrDefault(dataSnapshot, 0.0);
    }

    public double getDoubleOrDefault(@NonNull DataSnapshot dataSnapshot, double numDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber) {

                numDefault = Double.parseDouble(value);
            }
        }

        return numDefault;
    }


    // ----- Hour Readers ----- //

    public String getHour(@NonNull DataSnapshot dataSnapshot) {

        return getHourOrDefault(dataSnapshot, "00:00");
    }

    public String getHourOrDefault(@NonNull DataSnapshot dataSnapshot, String hourDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                long number = Long.parseLong(value);

                hourDefault = new DateTools().getTime("HH:mm", number);
            }
        }

        return hourDefault;
    }


    // ----- Date Readers ----- //

    public String getDate(@NonNull DataSnapshot dataSnapshot) {

        return getDateOrDefault(dataSnapshot, "00-00-00");
    }

    public String getDateOrDefault(@NonNull DataSnapshot dataSnapshot, String dateDefault) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                long number = Long.parseLong(value);

                dateDefault = new DateTools().getTime("dd-MM-yy", number);
            }
        }

        return dateDefault;
    }


    // ----- Time Readers ----- //

    public String getTime(@NonNull DataSnapshot timeSnapshot, String format) {

        return getTimeOrDefault(timeSnapshot, "-", format);
    }

    public String getTimeOrDefault(@NonNull DataSnapshot dataSnapshot, String timeDefault, String format) {

        if (dataSnapshot.exists()) {

            String value = String.valueOf(dataSnapshot.getValue());
            boolean isNumber = new MyNumbers().isNumber(value);

            if (isNumber && !value.equals("0")) {

                long number = Long.parseLong(value);

                timeDefault = new DateTools().getTime(format, number);
            }
        }

        return timeDefault;
    }


    // ----- LatLng Readers ----- //

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

    public LatLng getLatLngOrDefault(@NonNull DataSnapshot dataSnapshot, double latDefault, double lngDefault) {

        return getLatLngOrDefault(dataSnapshot, new LatLng(latDefault, lngDefault));
    }

    public LatLng getLatLngOrDefault(@NonNull DataSnapshot latDataSnapshot, @NonNull DataSnapshot lngDataSnapshot, double latDefault, double lngDefault) {

        return getLatLngOrDefault(latDataSnapshot, lngDataSnapshot, new LatLng(latDefault, lngDefault));
    }


    // ----- Location Readers ----- //

    public Location getLocation(@NonNull DataSnapshot dataSnapshot) {

        return getLocationOrDefault(dataSnapshot, 0.0, 0.0);
    }

    public Location getLocation(@NonNull DataSnapshot latDataSnapshot, DataSnapshot lngDataSnapshot) {

        return getLocationOrDefault(latDataSnapshot, lngDataSnapshot, 0.0, 0.0);
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

    public Location getLocationOrDefault(@NonNull DataSnapshot dataSnapshot, double latDefault, double lngDefault) {

        if (dataSnapshot.exists()) {

            return getLocationOrDefault(dataSnapshot.child("latitude"), dataSnapshot.child("longitude"), latDefault, lngDefault);

        } else {

            Location location = new Location("");
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

    public HashMap<String, Object> getHashMap(@NonNull DataSnapshot dataSnapshot) {

        HashMap<String, Object> hashMap = new HashMap<>();

        if (dataSnapshot.exists()) {

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                String key = getKeyString(snapshot);
                Object value = snapshot.getValue();

                hashMap.put(key, value);
            }
        }

        return hashMap;
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