package life.kiyoshi;

/*
 * Create for myself to control rpi2
 */

import com.jcraft.jsch.JSch;

public class Main {

    public static void main(String[] args) {
        //some essential variables
        String ipAddress = "192.168.0.102";
        String user = "pi";
        String path = "C:/.ssh/rpi2.key";

        //use JSch frame to set SSH connection
        JSch jSch = new JSch();
    }
}
