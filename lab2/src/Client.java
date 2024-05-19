import java.net.*;
import java.io.*;
public class Client {
    Socket clientSocket;
    BufferedReader inFromServer;
    DataOutputStream outToServer;
    Client() {
        try {
            clientSocket = new Socket("10.234.107.36", 2525);
            inFromServer = new BufferedReader((new InputStreamReader(clientSocket.getInputStream())));
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public boolean checkID(String ID) {
        try {
            outToServer.writeBytes("HELO" + " " + ID + '\n');
            String response = String.valueOf(inFromServer.readLine());
            System.out.println(response);
            if (response.substring(0, 3).equals("401")) {
                System.out.println(1);
                return false;
            }
            if (response.substring(0, 3).equals("500")) {
                return true;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(2);
        return false;
    }
    public boolean checkPassword(String password) {
        try {
            outToServer.writeBytes("PASS" + " " + password + '\n');
            String response = String.valueOf(inFromServer.readLine());
            System.out.println(response);
            if (response.substring(0, 3).equals("401")) {
                return false;
            }
            if (response.substring(0, 3).equals("525")) {
                return true;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
    public int inquireBalance() {
        try {
            outToServer.writeBytes("BALA" + '\n');
            String response = String.valueOf(inFromServer.readLine());
            System.out.println(response);
            String substring = response.substring(0, 4);
            if (response.substring(0, 4).equals("AMNT")) {
                return Integer.parseInt(response.substring(5, response.length()));
            }
            if (response.substring(0, 3).equals("401")) {
                return -1;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return -1;
    }
    public boolean checkWithdraw(String amount) {
        try {
            outToServer.writeBytes("WDRA" + " " + amount +'\n');
            String response = String.valueOf(inFromServer.readLine());
            System.out.println(response);
            if (response.substring(0, 3).equals("401")) {
                return false;
            }
            if (response.substring(0, 3).equals("525")) {
                return true;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean returnCard() {
        try {
            outToServer.writeBytes("BYE" + '\n');
            String response = String.valueOf(inFromServer.readLine());
            System.out.println(response);
            if (response.equals("BYE")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
}