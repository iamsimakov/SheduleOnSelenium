package com.alex.shedule;

/**
 * Created by Alex on 30.10.2015.
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class TestShedule
{
    public static void main(String[] args)
    {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resut = "";// получаем данные с внешнего ресурса
        try

        {
            URL url = new URL("http://gu-unpk.ru/schedule");
            urlConnection = (HttpURLConnection) url.openConnection(); //открываем соединение
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Host", "gu-unpk.ru");
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0");
            urlConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            urlConnection.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
//            urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            urlConnection.setRequestProperty("Referer", "http://gu-unpk.ru/");
            urlConnection.setRequestProperty("Cookie", "CurrentSchedule=lesson; _ga=GA1.2.2094998912.1444770053; CurrentSchedule=lesson; StudPrepAudit=1; divisionForStuds=12; kurs=5; group=3094; PHPSESSID=1l3f3cja1l76e1j8fqoajo7c41; blind-font-size=fontsize-normal; blind-colors=color1; blind-font=sans-serif; blind-spacing=spacing-small; blind-images=imagesoff; _ym_visorc_16652224=w; _gat=1");
            urlConnection.setRequestProperty("Connection", "keep-alive");
            urlConnection.setRequestProperty("Cache-Control", "max-age=0");

            urlConnection.connect(); //подключаемся
            InputStream inputStream = urlConnection.getInputStream();//поток ввода
            StringBuffer buffer = new StringBuffer(); //в эту переменную будем читать
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null)
            {  //читаем строку из потока
                buffer.append(line); // и пока она не пустая дописываем в буфер
            }
            resut = buffer.toString(); //буфер - это объект, переводим в строку
            System.out.println(resut); //возвращаем строку и передаем ее в onPostExecute
        }

        catch(
                Exception e
                )

        {
            System.out.println(e.toString());
        }


    }
}

