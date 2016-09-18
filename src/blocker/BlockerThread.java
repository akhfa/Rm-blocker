/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import static blocker.Blocker.seconds;
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
        System.out.println(Command.executeCommand("ls -l"));
        System.out.println("[" + calendar.getTime() + "] IP " + this.IP + " blocked.");
        // Jalankan x detik kemudian
        calendar.add(Calendar.SECOND, seconds);
        Timer time = new Timer();
        BlockRemover task = new BlockRemover(IP);
        time.schedule(task, calendar.getTime());
    }
    
    
}
