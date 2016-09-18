/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import static blocker.Blocker.seconds;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        if(Command.executeCommand("grep "+ this.IP + " " + Blocker.dir).compareTo(this.IP + "\n") != 0)
        {
            Calendar calendar = Calendar.getInstance();
            try {
                Command.append(Blocker.dir, this.IP);
            } catch (IOException ex) {
                Logger.getLogger(BlockerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(Command.executeCommand("ls -l"));
            System.out.println("[" + calendar.getTime() + "] IP " + this.IP + " blocked.");
            // Jalankan x detik kemudian
            calendar.add(Calendar.SECOND, seconds);
            Timer time = new Timer();
            BlockRemover task = new BlockRemover(IP);
            time.schedule(task, calendar.getTime());
        }
        else
        {
            System.out.println("tidak masuk if");
        }
    }
}
