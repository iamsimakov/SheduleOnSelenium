package com.alex.shedule;

/**
 * Created by Alex on 30.10.2015.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Shedule {
    private static HtmlUnitDriver driver;

    public static String init() throws Exception {
        String result = "";
        driver =  new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);

        String address = "http://gu-unpk.ru/schedule";
        driver.get(address);

        WebElement radio = driver.findElement(By.id("radio1"));
        radio.click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//select[@id='divisionStuds']/option[@value='12']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//select[@id='kurs']/option[@value='5']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//select[@id='group']/option[@value='3094']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("nowweek")).click();
        Thread.sleep(5000);

        result = driver.getPageSource();

        return result;
    }

    public static void main(String[] args) throws  Exception{
        String str = init();
        //Web.save("Расписание.html", "Windows-1251", str);
        Document html = Jsoup.parse(str);
        String strTable = html.select("table.table.table-striped.table-bordered.table-hover").outerHtml();
        Document doc = Jsoup.parseBodyFragment(strTable);
        Elements el = doc.getAllElements();
        for (Element e : el) {
            Attributes at = e.attributes();
            for (Attribute a : at) {
                e.removeAttr(a.getKey());
            }
        }
        strTable = doc.select("table").outerHtml();
        //System.out.println(strTable);
        Table sheduleTab = new Table(strTable);
        System.out.println(sheduleTab);
    }

}
