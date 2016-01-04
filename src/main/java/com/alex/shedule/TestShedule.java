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
        String result = "";
        try {
            Calendar calendar = new GregorianCalendar();
            long startWeek = calendar.getTimeInMillis() - (calendar.get(Calendar.DAY_OF_WEEK) - 2) * 24 * 60 * 60 * 1000L - (calendar.get(Calendar.HOUR_OF_DAY) - 3) * 60 * 60 * 1000L - calendar.get(Calendar.MINUTE) * 60 * 1000L - calendar.get(Calendar.SECOND) * 1000L;
            URL url = new URL("http://gu-unpk.ru/schedule/3094////" + String.valueOf(startWeek) + "/printschedule");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
            }
            result = builder.toString();
            result = new String(result.getBytes(), "UTF-8");
            System.out.println("Расписание занятий");
            System.out.println(result);


            System.out.println("Расписание экзаменов");
            url = new URL("http://gu-unpk.ru/schedule/3094////printexamschedule");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            urlConnection.setConnectTimeout(2000);
            inputStream = urlConnection.getInputStream();
            builder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ( (line = reader.readLine()) != null ){
                builder.append(line);
            }
            result = builder.toString();
            result = new String(result.getBytes(), "UTF-8");
            System.out.println(result);
        }

        catch(Exception e){
            System.out.println(e.toString());
        }

    }
}

