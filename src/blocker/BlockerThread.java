/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import static blocker.Blocker.seconds;

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
        Command.executeCommand("bash /opt/blocker/blocker.sh " + this.IP + " " + seconds + "");
    }
}
