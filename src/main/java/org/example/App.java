package org.example;

import java.util.Scanner;

//importing java swing API

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;

//importing jsoup library

import java.io.*;//for website
import java.net.URL;//retrieve url
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {

    public static String getdata(String country) throws Exception {

        StringBuffer br = new StringBuffer();
        br.append("<html>" +
                "<body style='text-align:center;color:green;'>");
        String url = "https://www.worldometers.info/coronavirus/country/" + country + "/";
        Document doc = Jsoup.connect(url).get();

        // div name "#maincounter-wrap" data

        Elements elements = doc.select("#maincounter-wrap");
        elements.forEach((e) -> {
            String text = e.select("h1").text();
            String count = e.select(".maincounter-number>span").text();
            br.append(text).append(count).append("<br>");
        });
        br.append("</body>" + "</html>");
        return br.toString();
    }

    public static void main(String[] args) throws Exception {
        // System.out.println("Here's your information!");
        // Scanner scan = new Scanner(System.in);
        // System.out.println("Enter Country name : ");
        // String Country = scan.nextLine();
        // System.out.println(getdata(Country));

        // creating for graphical user interface using java swing

        JFrame frame = new JFrame("Coronavirus cases details : ");
        frame.setSize(400, 400);
        Font f = new Font("Poppins", Font.BOLD, 28);

        // input text area

        JTextField field = new JTextField();

        // label for data

        JLabel label = new JLabel();
        field.setFont(f);
        label.setFont(f);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // creating button

        JButton button = new JButton("Get Details");

        button.addActionListener(event -> {
            // adding functionality on click for getting result

            try {
                String maindata = field.getText();
                String result = getdata(maindata);
                label.setText(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        button.setFont(f);
        frame.setLayout(new BorderLayout());
        frame.add(field, BorderLayout.NORTH);
        frame.add(label, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.setVisible(true);

    }
}
