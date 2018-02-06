package life.kiyoshi;

/**
 * @author : Kiyoshi
 * @version : 1.0
 */
public class Operate {
    private String host;
    private String username;
    private String password;

    /**
     *
     * @param host IP Address of the host.
     * @param username The username used to login.
     * @param password The password of the user.
     */
    Operate(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @param shell Shell that is currently running.
     * @return If ASF is running on remote host.
     */
    private boolean isRunning(Shell shell) {
        String cmd = "ps -A | grep ArchiSteamFarm";
        try {
            String key = shell.execAndReadLine(cmd);
            return (null != key);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * @return Nice sentences.
     */
    public String check() {
        Shell shell = new Shell(host, username, password);
        try {
            shell.connect();
            if (isRunning(shell)) {
                shell.disconnect();
                return "ASF is running on the host.";
            }
            shell.disconnect();
            return "ASF is not running on the host.";
        } catch (Exception e) {
            shell.disconnect();
            return e.getLocalizedMessage();
        }
    }

    /**
     *
     * @return Nice sentences.
     */
    public String on() {
        String cmd1 = "cd";
        String cmd2 = "bash Scripts/ASFOn.sh";
        Shell shell = new Shell(host, username, password);
        try {
            shell.connect();
            if (isRunning(shell)) return "There is an ASF running on the host!";
            shell.execCmd(cmd1);
            shell.execCmd(cmd2);
            shell.disconnect();
        } catch (Exception e) {
            shell.disconnect();
            return e.getLocalizedMessage();
        }

        return "Service has been successfully turned on.";
    }

    /**
     *
     * @return Nice sentences.
     */
    public String off() {
        Shell shell = new Shell(host, username, password);
        String cmd1 = "ps -A | grep ArchiSteamFarm";
        StringBuilder cmd2 = new StringBuilder("kill ");
        String rawInput;
        try {
            shell.connect();
            rawInput = shell.execAndReadLine(cmd1);
            if (null == rawInput) return "There is no ASF running on the host!";
            int PID = Integer.parseInt(process(rawInput));
            cmd2.append(PID);
            shell.execCmd(cmd2.toString());
            shell.disconnect();
        } catch (Exception e) {
            shell.disconnect();
            return e.getLocalizedMessage();
        }
        return "Service has been shut down.";
    }

    /**
     *
     * @param input The raw ps -A | grep ArchiSteamFarm output.
     * @return PID of ASF that is running on remote host.
     */
    private String process(String input) {
        char[] line = input.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (char i : line) {
            if (Character.isSpaceChar(i)) continue;
            else if (Character.isDigit(i)) stringBuilder.append(i);
            else break;
        }
        return stringBuilder.toString();
    }

}
