/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        System.out.println(Command.executeCommand("ls -l"));
        Calendar calendar = Calendar.getInstance();
        System.out.println(Command.executeCommand("iptables -D INPUT -s " + this.IP + " -j DROP"));
        Command.delete(this.IP);
        System.out.println("[" + calendar.getTime() + "] IP " + this.IP + " unblocked.");
        
//        System.out.println(Command.executeCommand("sed -i \"/"+ this.IP +"/d\" " + Blocker.dir));
    }
}
