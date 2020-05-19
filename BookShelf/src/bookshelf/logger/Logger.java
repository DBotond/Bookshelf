/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshelf.logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.function.Consumer;
import java.util.logging.FileHandler;
import java.util.logging.Level;

@FunctionalInterface
public interface Logger {
    public static final java.util.logging.Logger logr = java.util.logging.Logger.getLogger( java.util.logging.Logger.GLOBAL_LOGGER_NAME);   

    public enum LogLevel {
        INFO, DEBUG, WARNING, ERROR;

        public static LogLevel[] all() {
            return values();
        }
    }

    abstract void message(String msg, LogLevel severity);

    default Logger appendNext(Logger nextLogger) {
        return (msg, severity) -> {
            message(msg, severity);
            nextLogger.message(msg, severity);
        };
    }

    static Logger writeLogger(LogLevel[] levels, Consumer<String> stringConsumer) {
        EnumSet<LogLevel> set = EnumSet.copyOf(Arrays.asList(levels));
        return (msg, severity) -> {
            if (set.contains(severity)) {
                stringConsumer.accept(msg);
            }
        };
    }
          
    static Logger consoleLogger(LogLevel... levels) {
        return writeLogger(levels, msg -> System.err.println(msg));
    }    
    
    static Logger fileLogger(LogLevel... levels) {
        
        try{
            FileHandler fh = new FileHandler("LOG.log", true);
            fh.setLevel(Level.FINE);
            logr.addHandler(fh);
        }
        catch(java.io.IOException e){
            logr.log(Level.SEVERE, "File logger not working.", e);
        }    
        
        return writeLogger(levels, msg -> Logger.logr.info(msg));
    }
}