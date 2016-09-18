/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import static blocker.Blocker.seconds;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Timer;

/**
 *
 * @author akhfa
 */
public class BlockerThread implements Runnable{
    String IP;
    public BlockerThread(String _IP)
    {
        this.IP = _IP;
    }
    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        this.executeCommand("ls -l");
        System.out.println("[" + calendar.getTime() + "] IP " + this.IP + " blocked.");
        // Jalankan x detik kemudian
        calendar.add(Calendar.SECOND, seconds);
        Timer time = new Timer();
        BlockRemover task = new BlockRemover(IP);
        time.schedule(task, calendar.getTime());
    }
    
    private String executeCommand(String command) {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                    output.append(line + "\n");
                    System.err.println(line);
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
//		return output.toString();
        return "";
    }
}
