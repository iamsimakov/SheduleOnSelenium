package com.alex.shedule;

/**
 * Created by Alex on 30.10.2015.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Table implements Serializable{
    public ArrayList<Row> val = new ArrayList<Row>();

    public Table(String str){
        //System.out.println(str);
        int i = str.indexOf("<tr>");
        int b = str.indexOf("</tr>");
        while ( i != -1 ){
            val.add(new Row(str.substring(i + 4, b)));  //val.add(new Row(str.substring(i+4 , b)));
            str = str.substring(b+5);                  // str = str.substring(b+5);
            i = str.indexOf("<tr>");
            b = str.indexOf("</tr>");
        }
    }

    public Table(){

    }

    @Override
    public String toString()
    {
        String result = "";
        for (Row stroka : val)
        {
            result+=stroka.toString();
            /*for ( Cell mycell : stroki.val ){
                result += mycell + " ";
            }
            result += "\n";*/
        }
        return result;
    }

    public String toStringWithCountCells()
    {
        String result = "";
        for (Row stroka : val) {
            result += stroka.val.size() + stroka.toString();
        }
        return result;
    }

    public static class Row
    {
        public ArrayList<Cell> val = new ArrayList<Cell>();

        public Row(String str)
        {
            //System.out.println(str);
            int i = str.indexOf("<th>");
            int b = str.indexOf("</th>");
            while ( i != -1 ){
                val.add(new Cell(str.substring(i + 4, b)));  // val.add(new Cell(str.substring(i + 4, b)));
                str = str.substring(b+5);                       // str = str.substring(b+6);
                i = str.indexOf("<th>");
                b = str.indexOf("</th>");
            }
            i = str.indexOf("<td>");
            b = str.indexOf("</td>");
            while ( i != -1 ){
                val.add(new Cell(str.substring(i + 4, b)));  //val.add(new Cell(str.substring(i + 4, b)));
                str = str.substring(b+5);                       //str = str.substring(b+6);
                i = str.indexOf("<td>");
                b = str.indexOf("</td>");
            }
        }

        public Row(Row row)
        {
            this.val = new ArrayList<Cell>(row.val);
            Collections.copy(this.val, row.val);
        }

        public Row(){

        }

        @Override
        public String toString()
        {
            String result = "";
            for (Cell mycell : val)
            {
                result += mycell.toString()+" ";
            }
            result+="\n";
            return result;
        }
    }

    public static class Cell
    {
        public String val = "<empty>";

        Cell(String str)
        {
            val = str;
        }

        @Override
        public String toString()
        {
            return val;
        }
    }
}
