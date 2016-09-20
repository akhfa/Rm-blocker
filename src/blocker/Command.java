/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
    public static void append (String text) throws IOException
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Blocker.dbdir);

            stmt = c.createStatement();
            String sql = "INSERT INTO IP " +
                         " VALUES ("+ text +")"; 
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
    }
    
    public static void delete (String text)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Blocker.dbdir);

            stmt = c.createStatement();
            String sql = "DELETE FROM IP " +
                         " WHERE IP = "+ text; 
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
    }
    
    public static void initDB()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Blocker.dbdir);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IP " +
                         "(ID INT PRIMARY KEY     NOT NULL," +
                         " IP           CHAR(15)    NOT NULL)"; 
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }
    
    /**
     * Menyamakan string pada file.
     * @param dir
     * @param patternString
     * @return 
     */
    public static String grep (String patternString)
    {
        Connection c = null;
        Statement stmt = null;
        String result = "";
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:" + Blocker.dbdir);
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM IP WHERE IP = " + patternString + ";" );
          while ( rs.next() ) {
             result = rs.getString("IP");
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("result = " + result);
        return result;
    }
}
