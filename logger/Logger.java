package logger;

import java.lang.*;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Logger {
    public static void log(String msg) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String caller = caller = new Exception().getStackTrace()[1].getClassName();
        System.err.println("[" + df.format(new Date()) + " " + caller +  "] " + msg);
    }
    public static void log(int msg) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String caller = caller = new Exception().getStackTrace()[1].getClassName();
        System.err.println("[" + df.format(new Date()) + " " + caller +  "] " + Integer.toString(msg));
    }
}
