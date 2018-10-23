package princesadaserra.java.util.config;

import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConfigLoader {
    public static List<Pair<String, String>> load(URL url){
        System.out.println("ConfigLoader.load");
        List<Pair<String,String>> configPairs = new ArrayList<>(10);
        Scanner scanner = null;
        if(url == null){

            System.out.println("Invalid URL");
            return configPairs;
        }
        try {
            scanner = new Scanner(url.openStream());

            while(scanner.hasNextLine())
                configPairs.add(getConfigPair(scanner.nextLine()));

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(scanner != null){
                scanner = null;
            }
        }

        return configPairs;
    }

    private static Pair<String, String> getConfigPair(String configLine){
        String[] pair = configLine.split(":");

        try{
            return new Pair<>(pair[0].trim(), pair[1].trim());

        }catch (ArrayIndexOutOfBoundsException exception){
            System.out.println("Error: invalid config line");
            return null;
        }
    }
}
