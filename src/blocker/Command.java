/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author akhfa
 */
public class Command {
    public static String executeCommand(String cmdString) {
        System.out.println(cmdString);
        StringBuffer output = new StringBuffer();
//        String [] cmd = {"bash", "-c", cmdString};
        String [] cmd = cmdString.split(" ");
        Process p;
        try {
            p = Runtime.getRuntime().exec(cmdString);
            p.waitFor();
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader err =
                new BufferedReader(new InputStreamReader(p.getErrorStream()));
            
            String line = "";
            while ((line = err.readLine())!= null) {
                    output.append(line + "\n");
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        return output.toString();
    }
}
