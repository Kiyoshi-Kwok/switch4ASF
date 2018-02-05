package life.kiyoshi;

import java.net.Socket;

/*
 * Create for myself to control rpi2
 */

public class Main {

    public static void main(String[] args) {
        //some essential variables
        String ipAdress = "192.168.0.102";
        String user = "pi";
        String path = "C:/.ssh/rpi2.key";

        //init the socket
        try {
            Socket socket = new Socket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
