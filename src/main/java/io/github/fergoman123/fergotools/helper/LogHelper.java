package io.github.fergoman123.fergotools.helper;

import fergoman123.mods.fergoutil.logging.ILogLevel;
import io.github.fergoman123.fergotools.reference.ModInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper implements ILogLevel
{
    public static Logger logger = LogManager.getLogger(ModInfo.modid);

    public static void log(Level level, Object object)
    {
        logger.log(level, String.valueOf(object));
    }

    public static void all(Object object)
    {
        log(all, object);
    }

    public static void debug(Object object)
    {
        log(debug, object);
    }

    public static void error(Object object)
    {
        log(error, object);
    }

    public static void fatal(Object object)
    {
        log(fatal, object);
    }

    public static void info(Object object)
    {
        log(info, object);
    }

    public static void off(Object object)
    {
        log(off, object);
    }

    public static void trace(Object object)
    {
        log(trace, object);
    }

    public static void warn(Object object)
    {
        log(warn, object);
    }
}