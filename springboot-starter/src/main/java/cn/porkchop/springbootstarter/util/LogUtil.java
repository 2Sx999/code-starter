package cn.porkchop.springbootstarter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 *
 * @author pkulm on 2017/7/18
 */
public class LogUtil {

    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void info(String s) {
        logger.info(s);
    }

    public static void error(String s) {
        logger.error(s);
    }

    public static void error(String s, Throwable t) {
        logger.error(s, t);
    }

    private static Logger color = LoggerFactory.getLogger("color");

    public static void colorInfo(String message) {
        color.info(message);
    }

    public static void colorError(String message, Throwable t) {
        color.error(message, t);
    }

}
