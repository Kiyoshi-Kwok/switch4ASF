package life.kiyoshi;

/**
 * @Author: Kiyoshi
 * @Description:
 */
public class Operate {

    public void on() {
        String cmd1 = "cd";
        String cmd2 = "bash Scripts/ASFOn.sh";
        Shell shell = new Shell("192.168.0.102", "pi", "wo94tyzxxm");
        try {
            shell.connect();
            shell.execCmd(cmd1);
            shell.execCmd(cmd2);
            shell.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
