
package com.hfzq.demoproperties.property.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * @description :从yaml里拿取配置,在容器初始化以前是拿不到值的,但也不会报错
 * @author Zephyr Lin
 * @created 2022/2/10 12:37

 * @return
 */
@Slf4j
public class YamlCfg {

    /**
     * 从配置文件中拿值,如果拿不到就返回null.
     *
     * @param code the code
     * @return the str
     * @author Norton Lai
     * @created 2020 -04-08 10:46:27
     */
    public static String getStr(String code) {
        ApplicationContext context = SpringContextHolder.get();
        if (context == null) {
            return null;
        }
        String value = context.getEnvironment().getProperty(code);
        return value;
    }

    /**
     * 从配置文件中拿值,如果拿不到就返回defaul.
     *
     * @param code   the code
     * @param defaul the defaul
     * @return the str
     * @author Norton Lai
     * @created 2020 -04-08 10:46:50
     */
    public static String getStr(String code, String defaul) {
        String str = getStr(code);
        if (EveUtil.isEmp(str)) {
            str = defaul;
        }
        return str;
    }

    /**
     * 从配置文件中拿值,如果拿不到返回null.
     *
     * @param code the code
     * @return the it
     * @author Norton Lai
     * @created 2020 -04-08 10:47:11
     */
    public static Integer getIt(String code) {
        return EveUtil.it(getStr(code));
    }

    /**
     * 从配置文件中拿值,如果拿不到就返回defaul.
     *
     * @param code   the code
     * @param defaul the defaul
     * @return the it
     * @author Norton Lai
     * @created 2020 -04-08 10:47:32
     */
    public static int getIt(String code, int defaul) {
        Integer it = getIt(code);
        if (EveUtil.isEmp(it)) {
            it = defaul;
        }
        return it;
    }

    /**
     * 从配置文件中拿值,如果拿不到返回null.
     *
     * @param code the code
     * @return the boo
     * @author Norton Lai
     * @created 2020 -04-08 10:49:14
     */
    public static Boolean getBoo(String code) {
        return EveUtil.boo(getStr(code));
    }


    /**
     * 从配置文件中拿值,如果拿不到就返回defaul.
     *
     * @param code   the code
     * @param defaul the defaul
     * @return the boo
     * @author Norton Lai
     * @created 2020 -04-08 10:49:17
     */
    public static boolean getBoo(String code, boolean defaul) {
        Boolean boo = getBoo(code);
        if (boo == null) {
            return defaul;
        }
        return boo;
    }

    /**
     * 从配置文件中拿值,如果拿不到返回null.
     *
     * @param code the code
     * @return the dou
     * @author Norton Lai
     * @created 2020 -04-08 10:52:57
     */
    public static Double getDou(String code) {
        return EveUtil.dou(getStr(code));
    }

    /**
     * 从配置文件中拿值,如果拿不到就返回defaul.
     *
     * @param code   the code
     * @param defaul the defaul
     * @return the dou
     * @author Norton Lai
     * @created 2020 -04-08 10:52:59
     */
    public static double getDou(String code, double defaul) {
        Double dou = getDou(code);
        if (dou == null) {
            return defaul;
        }
        return dou;
    }

    /**
     * 从配置文件中拿值,如果拿不到返回null.
     *
     * @param code the code
     * @return the dou
     * @author Norton Lai
     * @created 2020 -04-08 10:52:57
     */
    public static Long getLon(String code) {
        return EveUtil.lon(getStr(code));
    }

    /**
     * 从配置文件中拿值,如果拿不到就返回defaul.
     *
     * @param code   the code
     * @param defaul the defaul
     * @return the dou
     * @author Norton Lai
     * @created 2020 -04-08 10:52:59
     */
    public static long getLon(String code, long defaul) {
        Long lon = getLon(code);
        if (lon == null) {
            return defaul;
        }
        return lon;
    }
}
