package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main( String[] args ){
        boolean isExit = false;
            while (!isExit) {
                try {
                    System.out.println("Для поиска по Википедии введите запрос:");
                    String request = scanner.nextLine();
                    String wikipediaUrl = "https://ru.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&utf8=1&explaintext=&titles="
                            + URLEncoder.encode(request, "UTF-8");

                    HttpURLConnection httpcon = (HttpURLConnection) new URL(wikipediaUrl).openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));

                    String responseSB = in.lines().collect(Collectors.joining());
                    in.close();
                    String result = new String(responseSB.split("extract\":\"")[1].getBytes(), StandardCharsets.UTF_8);
                    System.out.println(result);
                    isExit = true;
                } catch (Exception ex) {
                    System.out.println("Неправильный запрос или такой страницы на Википедии не существует");
                }
            }
    }
}