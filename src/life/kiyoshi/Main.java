package life.kiyoshi;

import java.net.Socket;

/*
 * Create for myself to control rpi2
 */

public class Main {

    public static void main(String[] args) {

        //init the socket
        try {
            Socket socket = new Socket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
