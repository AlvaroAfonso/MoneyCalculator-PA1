package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MoneyCalculator {

    public static void main(String[] args) throws IOException {
        System.out.print("Introduzca una cantidad en dolares: ");
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();
        double exchangeRate = getExchangeRate("USD","EUR");
        System.out.println(amount + " dólares quivalen a " + exchangeRate*amount + " euros");
    }
    
    private static double getExchangeRate(String from, String to) throws IOException {
        URL url = new URL("https://api.exchangeratesapi.io/latest?base="+from);
        URLConnection connection = url.openConnection();
        
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = reader.readLine();
            int posTo = line.indexOf(to);
            String res = line.substring(posTo + 5, line.indexOf(",", posTo));
            return Double.parseDouble(res);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
}
