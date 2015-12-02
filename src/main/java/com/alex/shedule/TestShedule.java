package com.alex.shedule;

/**
 * Created by Alex on 30.10.2015.
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestShedule
{
    public static void main(String[] args)
    {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resut = "";
        try {
            Calendar calendar = new GregorianCalendar();
            long startWeek = calendar.getTimeInMillis() - (calendar.get(Calendar.DAY_OF_WEEK) - 2) * 24 * 60 * 60 * 1000L - (calendar.get(Calendar.HOUR_OF_DAY) - 3) * 60 * 60 * 1000L - calendar.get(Calendar.MINUTE) * 60 * 1000L - calendar.get(Calendar.SECOND) * 1000L;
            URL url = new URL("http://gu-unpk.ru/schedule/3094////" + String.valueOf(startWeek) + "/printschedule");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }
            resut = buffer.toString();
            resut = new String(resut.getBytes(), "UTF-8");
            System.out.println(resut);
        }

        catch(Exception e){
            System.out.println(e.toString());
        }

    }
}

