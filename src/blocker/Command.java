/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author akhfa
 */
public class Command {
    public static String executeCommand(String cmdString) {
        StringBuffer output = new StringBuffer();
        String [] cmd = {"bash", "-c", cmdString};
        Process p;
        try {
            p = Runtime.getRuntime().exec(cmdString);
            p.waitFor();
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                    output.append(line + "\n");
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        return output.toString();
    }
    public static void append (String dir, String text) throws IOException
    {
        File file =new File(dir);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        
        FileWriter fileWritter = new FileWriter(file.getAbsolutePath(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(text + "\n");
    	        bufferWritter.close();
    }
    
    /**
     * Menyamakan string pada file. Masih error.
     * @param dir
     * @param patternString
     * @return 
     */
    public static String grep (String dir, String patternString)
    {
        BufferedReader br = null;
        boolean found = false;
        String resultString = "";
        try {
            br = new BufferedReader(new FileReader(dir));
            while ((resultString = br.readLine()) != null && !found) {
                if(resultString.equals(patternString))
                {
                    found = true;
                }
            }
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
            try {
                    if (br != null)br.close();
            } catch (IOException ex) {
                    ex.printStackTrace();
            }
        }
        return resultString;
    }
}
