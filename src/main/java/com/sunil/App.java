package com.sunil;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class App
{
    public static String getData(String c) throws Exception
    {
        StringBuffer br=new StringBuffer();
        br.append("<html>");
        String url="https://www.worldometers.info/coronavirus/country/" + c +"/";
        Document document =Jsoup.connect(url).get();
        //#maincounter-wrap
        Elements elements =document.select("#maincounter-wrap");
        elements.forEach((e)->{
            String text=e.select("h1").text();
            String count=e.select(".maincounter-number>span").text();
               br.append(text).append(" : ").append(count).append("<br>");
        });
        br.append("</html>");
        return  br.toString();
    }

    public static void main( String[] args ) throws Exception
    {
//        System.out.println( "Hello World!" );
//        Scanner sc=new Scanner(System.in);
//        System.out.println("Enter Country: ");
//        String country=sc.nextLine();
//        System.out.println(getData(country));

        //creating GUI
        JFrame root=new JFrame("Details of Country");
        root.setSize(500,500);
        Font f=new Font("Poppins",Font.BOLD,30);

        //textfield
        JTextField field=new JTextField();
        //label
        JLabel dataL=new JLabel();
        field.setFont(f);
        dataL.setFont(f);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        dataL.setHorizontalAlignment(SwingConstants.CENTER);
        //button
        JButton btn=new JButton("Get");

        btn.addActionListener((event)->{
            //click:
            try {

                String mainData=field.getText();
                String result=getData(mainData);
                dataL.setText(result);


            }catch (Exception e){
                e.printStackTrace();
            }
        });


        btn.setFont(f);

        root.setLayout(new BorderLayout());
        root.add(field,BorderLayout.NORTH);
        root.add(dataL,BorderLayout.CENTER);
        root.add(btn,BorderLayout.SOUTH);
        root.setVisible(true);
    }
}
