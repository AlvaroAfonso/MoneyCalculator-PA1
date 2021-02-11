package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MoneyCalculator {
    
    double amount;
    double exchangeRate;
    String currencyFrom;
    String currencyTo;

    public static void main(String[] args) throws IOException {
        MoneyCalculator mc = new MoneyCalculator();
        mc.execute();
    }
    
    private void execute() throws IOException{
        input();
        proccess();
        output();
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

    private void input() {
        System.out.print("Introduzca una cantidad: ");
        Scanner scanner = new Scanner(System.in);
        amount = scanner.nextDouble();
        
        System.out.print("Introduzca la divisa de origen: ");
        scanner = new Scanner(System.in);
        currencyFrom = scanner.next();
        
        System.out.print("Introduzca la divisa de destino: ");
        scanner = new Scanner(System.in);
        currencyTo = scanner.next();
    }

    private void proccess() throws IOException {
        exchangeRate = getExchangeRate(currencyFrom,currencyTo);
    }

    private void output() {
        System.out.println(amount + " " + currencyFrom + " quivalen a " + exchangeRate*amount + " " + currencyTo);
    }
    
}
