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
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akhfa
 */
public class BlockRemover extends TimerTask {
    String IP;
    public BlockRemover(String _IP)
    {
        this.IP = _IP;
    }
    
    @Override
    public void run() {
        this.executeCommand("ls -l");
        Calendar calendar = Calendar.getInstance();
        System.out.println("[" + calendar.getTime() + "] IP " + this.IP + " unblocked.");
//        try {
//            Thread.sleep((seconds + 1) * 1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(BlockRemover.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
