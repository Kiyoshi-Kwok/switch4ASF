package life.kiyoshi;

import java.util.ArrayList;

public class Shell {
    //some essential variables
    private String ipAdress;
    private static final int DEFAULT_SSH_PORT = 22;
    private String user;
    private String password;
    private ArrayList<String> stdout;

    /**
     * init login info.
     * @param address IP Address
     * @param user Username
     * @param password Password of the user
     */
    public Shell(final String address, final String user, final String password) {
        this.ipAdress = address;
        this.user = user;
        this.password = password;
        stdout = new ArrayList<>();
    }
}
