/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import java.util.Calendar;
import java.util.TimerTask;

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
        System.out.println(Command.executeCommand("ls -l"));
        Calendar calendar = Calendar.getInstance();
        System.out.println("[" + calendar.getTime() + "] IP " + this.IP + " unblocked.");
//        Command.executeCommand("sed -i '/"+ this.IP +"/d' " + Blocker.dir);
    }
}
