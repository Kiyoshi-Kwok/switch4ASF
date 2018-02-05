package life.kiyoshi;

/**
 * @Author: Kiyoshi
 * @Description:
 */
public class Operate {
    private String host;
    private String username;
    private String password;

    public Operate(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public String check() {
        String cmd = "ps -A | grep ArchiSteamFarm";
        Shell shell = new Shell(host, username, password);
        try {
            shell.connect();
            String key = shell.execAndReadLine(cmd);
            shell.disconnect();
            if (null == key)
                return "ASF is not running on the host.";
            else
                return "ASF is running on the host.";
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    public String on() {
        String cmd1 = "ps -A | grep ArchiSteamFarm";
        String cmd2 = "cd";
        String cmd3 = "bash Scripts/ASFOn.sh";
        Shell shell = new Shell(host, username, password);
        try {
            shell.connect();
            String key = shell.execAndReadLine(cmd1);
            if (null != key)
                return "There is an ASF running on the host!";
            shell.execCmd(cmd2);
            shell.execCmd(cmd3);
            shell.disconnect();
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }

        return "Service was successfully turned on.";
    }

    public String off() {
        Shell shell = new Shell(host, username, password);
        String cmd1 = "ps -A | grep ArchiSteamFarm";
        StringBuilder cmd2 = new StringBuilder("kill ");
        String rawInput;
        try {
            shell.connect();
            rawInput = shell.execAndReadLine(cmd1);
            if (rawInput == null)
                return "There is no ASF running on the host!";
            int PID = Integer.parseInt(process(rawInput));
            cmd2.append(PID);
            shell.execCmd(cmd2.toString());
            shell.disconnect();
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }

        return "Service was shut down.";
    }

    public String process(String input) {
        char[] line = input.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < line.length; i ++) {
            if (Character.isSpaceChar(line[i]))
                continue;
            else if (Character.isDigit(line[i]))
                stringBuilder.append(line[i]);
            else break;
        }
        return stringBuilder.toString();
    }

}
