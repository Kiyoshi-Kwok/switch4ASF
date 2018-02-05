package life.kiyoshi;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;



public class Shell {
    private String host;
    private String username;
    private String password;
    private int port;
    private static final int DEFAULT_SSH_PORT = 22;
    private JSch jSch;
    private Session session;


    public Shell(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = DEFAULT_SSH_PORT;
    }

    public Shell(String host, String username, String password, int port) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.host = host;
    }

    public void connect() throws JSchException {
        jSch = new JSch();
        session = jSch.getSession(username, host, port);
        session.setPassword(password);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setTimeout(2000);
        session.connect();
    }

    public void disconnect() {
        session.disconnect();
    }

    public void execCmd(String cmd) throws JSchException {
        BufferedReader reader = null;
        Channel channel = null;
        try {
            if (cmd != null) {
                channel = session.openChannel("exec");
                ((ChannelExec)channel).setCommand(cmd);
                channel.connect();

                InputStream in = channel.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                String buffer;

                while ((buffer = reader.readLine()) != null) {
                    System.out.println(buffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            channel.disconnect();
        }
    }

    public static void main(String[] args) {

    }
}
