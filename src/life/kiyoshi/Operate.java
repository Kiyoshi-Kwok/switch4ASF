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

    public String on() {
        String cmd1 = "cd";
        String cmd2 = "bash Scripts/ASFOn.sh";
        Shell shell = new Shell(host, username, password);
        try {
            shell.connect();
            shell.execCmd(cmd1);
            shell.execCmd(cmd2);
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
                return "There is no ASF running on host!";
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
