
package com.hfzq.demoproperties.property.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 基础工具方法.
 *
 * @author
 * @created 2019 -1-29 上午9:59:35
 */
@SuppressWarnings(value = {"rawtypes", "unchecked"})
@Slf4j
public class EveUtil {


    /**
     * 自己
     *
     * @author 
     * @created 
     * @Fields clz
     */
    private static Class clz = EveUtil.class;

    /**
     * 将int的多态对象转换成int，或者将值为数值的字符串转换成int.<br>
     * 如果对象为null就返回null
     *
     * @param obj the obj
     * @return integer integer
     */
    public static Integer it(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            if (obj instanceof Integer) {
                return (int) obj;
            } else if (obj instanceof Short) {
                return (int) (short) obj;
            } else if (obj instanceof Byte) {
                return (int) (byte) obj;
            }
            if (obj instanceof BigDecimal) {
                return ((BigDecimal) obj).intValue();
            }
            String s = obj.toString().trim();
            if (s.contains(".")) {
                Double dou = dou(s);
                if (dou == null) {
                    return null;
                }
                return dou.intValue();
            }
            return Integer.parseInt(s);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    /**
     * 将对象转换成int
     *
     * @param obj the obj
     * @return int int
     */
    public static int itnn(Object obj) {
        Integer int1 = it(obj);
        return int1 == null ? 0 : int1;
    }

    /**
     * 将对象转换成double
     *
     * @param obj the obj
     * @return double double
     */
    public static Double dou(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            if (obj instanceof Double) {
                return (double) obj;
            } else if (obj instanceof Float) {
                return (double) (float) obj;
            } else if (obj instanceof Byte) {
                return (double) (byte) obj;
            } else if (obj instanceof Short) {
                return (double) (short) obj;
            } else if (obj instanceof Integer) {
                return (double) (int) obj;
            } else if (obj instanceof Long) {
                return (double) (long) obj;
            }
            return Double.parseDouble(obj.toString().trim());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    /**
     * 将对象转换成double
     *
     * @param obj the obj
     * @return double double
     */
    public static double dounn(Object obj) {
        Double dou = dou(obj);
        return dou == null ? 0 : dou;
    }

    /**
     * 将long的多态对象转换成long，或者将值为数值的字符串转换成long.<br>
     * 如果对象为null就返回null
     *
     * @param obj the obj
     * @return long long

     * @created 2018 -2-8 上午10:11:47
     */
    public static Long lon(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            if (obj instanceof Long) {
                return (long) obj;
            } else if (obj instanceof Integer) {
                return (long) (int) obj;
            } else if (obj instanceof Short) {
                return (long) (short) obj;
            } else if (obj instanceof Byte) {
                return (long) (byte) obj;
            }
            String s = obj.toString().trim();
            if (s.contains(".")) {
                Double dou = dou(s);
                if (dou == null) {
                    return null;
                }
                return dou.longValue();
            }
            return Long.parseLong(s);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    /**
     * 返回转换的long 如果null 返回0
     *
     * @param obj the obj
     * @return long long

     * @created 2018 -7-16 下午11:38:03
     */
    public static long lonn(Object obj) {
        Long long1 = lon(obj);
        return long1 == null ? 0 : long1;
    }

    /**
     * 将Str的多态对象转换成Str.<br>
     * 如果对象为null就返回null
     *
     * @param obj the obj
     * @return string string

     * @created 2018 -2-8 上午10:11:47
     */
    public static String str(Object obj) {
        return obj == null ? null : obj.toString();
    }

    /**
     * 返回字符串 不会返回null.
     *
     * @param obj the obj
     * @return string string

     * @created 2018 -7-16 下午11:36:19
     */
    public static String strnn(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 解析boolean 字符串 空 false 0 返回false true 1返回true 其他null
     *
     * @param o the o
     * @return boolean boolean

     * @created 2018 -12-14 下午4:16:10
     */
    public static Boolean boo(Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Boolean) {
            return (boolean) o;
        }
        String s = o.toString();
        if ("false".equalsIgnoreCase(s) || "0".equals(s) || s.length() == 0) {
            return false;
        }
        if ("true".equalsIgnoreCase(s) || "1".equals(s)) {
            return true;
        }
        return null;
    }

    /**
     * 解析boolean 字符串 空 false 0 返回false 其他都是true
     *
     * @param o the o
     * @return boolean boolean

     * @created 2018 -12-14 下午4:16:10
     */
    public static boolean boonn(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Boolean) {
            return (boolean) o;
        }
        if (o instanceof BigDecimal) {
            return ((BigDecimal) o).intValue() == 0 ? false : true;
        }
        String s = o.toString();
        if ("false".equalsIgnoreCase(s) || "0".equals(s) || s.length() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 解析字符串日期,不报错 异常返回null
     *
     * @param d      the d
     * @param format the format
     * @return date date

     * @created 2018 -11-27 下午4:30:35
     */
    public static Date date(String d, String format) {
        try {
            return new SimpleDateFormat(format).parse(d);
        } catch (ParseException e) {
            log.warn(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 解析对象到日期,不管什么类型的对象都去尝试解析
     *
     * @param o the o
     * @return date date

     * @created 2018 -11-27 下午4:08:10
     */
    public static Date date(Object o) {
        if (o == null || "".equals(o)) {
            return null;
        }
        if (o instanceof Date || o instanceof java.sql.Date) {
            return (Date) o;
        }
        if (o instanceof String) {
            // yyyy-MM-dd HH:mm:ss /
            String d = (String) o;
            StringBuilder format = new StringBuilder("yyyy");
            if (d.charAt(4) == '-') {
                format.append("-MM-dd");
            } else if (d.charAt(4) == '/') {
                format.append("/MM/dd");
            } else if (d.charAt(4) == '_') {
                format.append("_MM_dd");
            } else {
                format.append("MMdd");
            }
            if (d.length() < format.length()) {
                return null;
            } else if (d.length() == format.length()) {
                return date(d, format.toString());
            }
            if (d.charAt(format.length()) == ' ') {
                format.append(' ');
            }
            if (d.charAt(format.length() + 2) == ':') {
                format.append("HH:mm:ss");
            } else if (d.charAt(format.length() + 2) == '/') {
                format.append("HH/mm/ss");
            } else {
                format.append("HHmmss");
            }
            if (d.length() < format.length()) {
                return null;
            }
            if (d.length() == format.length()) {
                return date(d, format.toString());
            }
            if (d.charAt(format.length()) == '.' && d.length() == (format.length() + 4)) {
                format.append(".SSS");
            } else if (d.length() == (format.length() + 3)) {
                format.append("SSS");
            } else {
                d = d.substring(0, format.length());
            }
            return date(d, format.toString());
        }
        if (o instanceof Long) {
            long l = (long) o;
            if (l < 10000000000l) {
                return new Date(l * 1000);
            }
            return new Date(l);
        }
        if (o instanceof Integer) {
            long l = (int) o * 1000;
            return new Date(l);
        }
        return null;
    }

    /**
     * 返回字符串.
     *
     * @param <K> the type parameter
     * @param <T> the type parameter
     * @param m   the m
     * @param key the key
     * @return string string

     * @created 2018 -8-7 上午10:13:51
     */
    public static <K, T> String str(Map<K, T> m, K key) {
        return str(m.get(key));
    }


    /**
     * 从map里拿取list,如果拿不到就创建并放入map.
     *
     * @param <K> the type parameter
     * @param <T> the type parameter
     * @param m   the m
     * @param key the key
     * @return the list

     * @created 2020 -03-25 15:50:54
     */
    public static <K, T> List<T> listnn(Map<K, Object> m, K key) {
        if (!m.containsKey(key)) {
            List<T> list = new ArrayList<>();
            m.put(key, list);
        }
        return (List<T>) m.get(key);
    }

    /**
     * 返回字符串.
     *
     * @param <K> the type parameter
     * @param <T> the type parameter
     * @param m   the m
     * @param key the key
     * @return string string

     * @created 2018 -8-7 上午10:13:51
     */
    public static <K, T> String strnn(Map m, K key) {
        return strnn(m.get(key));
    }

    /**
     * 返回Integer
     *
     * @param <K> the type parameter
     * @param <T> the type parameter
     * @param m   the m
     * @param key the key
     * @return integer integer

     * @created 2018 -8-7 上午10:15:03
     */
    public static <K, T> Integer it(Map<K, T> m, K key) {
        return it(m.get(key));
    }

    /**
     * 返回int
     *
     * @param <K> the type parameter
     * @param <T> the type parameter
     * @param m   the m
     * @param key the key
     * @return int int

     * @created 2018 -8-7 上午10:15:45
     */
    public static <K, T> int itnn(Map<K, T> m, K key) {
        return itnn(m.get(key));
    }

    /**
     * 返回Double
     *
     * @param <K> the type parameter
     * @param <T> the type parameter
     * @param m   the m
     * @param key the key
     * @return double double

     * @created 2018 -8-7 上午10:17:02
     */
    public static <K, T> Double dou(Map<K, T> m, K key) {
        return dou(m.get(key));
    }

    /**
     * 返回 double
     *
     * @param <K> the type parameter
     * @param <T> the type parameter
     * @param m   the m
     * @param key the key
     * @return double double

     * @created 2018 -8-7 上午10:17:00
     */
    public static <K, T> double dounn(Map m, K key) {
        return dounn(m.get(key));
    }

    /**
     * Long
     *
     * @param <K> the type parameter
     * @param <T> the type parameter
     * @param m   the m
     * @param key the key
     * @return long long

     * @created 2018 -8-7 上午10:19:15
     */
    public static <K, T> Long lon(Map<K, T> m, K key) {
        return lon(m.get(key));
    }

    /**
     * 返回long
     *
     * @param <K> the type parameter
     * @param <T> the type parameter
     * @param m   the m
     * @param key the key
     * @return long long

     * @created 2018 -8-7 上午10:18:55
     */
    public static <K, T> long lonn(Map<K, T> m, K key) {
        return lonn(m.get(key));
    }

    /**
     * 解析出boolean类型,可能null
     *
     * @param <K> the type parameter
     * @param <T> the type parameter
     * @param m   the m
     * @param key the key
     * @return boolean boolean

     * @created 2019 -1-29 上午10:10:35
     */
    public static <K, T> Boolean boo(Map<K, T> m, K key) {
        return boo(m.get(key));
    }

    /**
     * 解析出boolean类型 不会null
     *
     * @param <K> the type parameter
     * @param <T> the type parameter
     * @param m   the m
     * @param key the key
     * @return boolean boolean

     * @created 2019 -1-29 上午10:11:11
     */
    public static <K, T> boolean boonn(Map<K, T> m, K key) {
        return boonn(m.get(key));
    }

    /**
     * 解析出date类型 可能为null
     *
     * @param <K> the type parameter
     * @param <T> the type parameter
     * @param m   the m
     * @param key the key
     * @return date date

     * @created 2019 -1-29 上午10:15:54
     */
    public static <K, T> Date date(Map<K, T> m, K key) {
        return date(m.get(key));
    }

    /**
     * 转换基础类型
     *
     * @param <T> the type parameter
     * @param clz the clz
     * @param msg the msg
     * @return object object

     * @created 2019 -1-6 下午3:54:33
     */
    public static <T> Object parseBaseType(Class<T> clz, Object msg) {
        if (clz.equals(Integer.class) || "int".equals(clz.getName())) {
            return it(msg);
        }
        if (clz.equals(String.class)) {
            return str(msg);
        }
        if (clz.equals(Long.class) || "long".equals(clz.getName())) {
            return lon(msg);
        }
        if (clz.equals(Double.class) || "double".equals(clz.getName())) {
            return dou(msg);
        }
        if (clz.equals(Date.class)) {
            return date(msg);
        }
        return null;
    }

    /**
     * 判定class是否是基础类型 如基本数据类型 和日期
     *
     * @param <T> the type parameter
     * @param clz the clz
     * @return boolean boolean

     * @created 2019 -1-7 上午10:43:40
     */
    public static <T> boolean isBaseType(Class<T> clz) {
        if (clz.equals(Integer.class)) {
            return true;
        }
        if (clz.equals(String.class)) {
            return true;
        }
        if (clz.equals(Long.class)) {
            return true;
        }
        if (clz.equals(Double.class)) {
            return true;
        }
        if (clz.equals(Date.class)) {
            return true;
        }
        if ("long".equals(clz.getName()) || "double".equals(clz.getName()) || "int".equals(clz.getName())) {
            return true;
        }
        return false;
    }

    /**
     * 判空
     *
     * @param str the str
     * @return boolean boolean

     * @created 2018 -2-7 上午9:45:43
     */
    public static boolean isEmp(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 保留两位小数.
     *
     * @param d the d
     * @return the string

     * @created 2020 -03-10 16:33:14
     */
    public static String douToStr(Double d) {
        if (d == null) {
            return "0.00";
        }
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        return decimalFormat.format(d);
    }


    /**
     * 是否为空.为null一定为空 为空不一定为null 比如长度等于0也是为空
     *
     * @param obj the obj
     * @return boolean boolean

     * @created 2018 -4-9 上午9:32:22
     */
    public static boolean isEmp(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String && ((String) obj).length() == 0) {
            return true;
        } else if (obj instanceof Collection && ((Collection) obj).size() == 0) {
            return true;
        } else if (obj instanceof Map && ((Map) obj).size() == 0) {
            return true;
        } else if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否不为空
     *
     * @param obj the obj
     * @return boolean boolean

     * @created 2018 -4-9 上午9:33:14
     */
    public static boolean isNotEmp(Object obj) {
        return !isEmp(obj);
    }

    /**
     * 描述
     *
     * @param str the str
     * @return boolean boolean

     * @created 2018 -2-7 上午9:46:15
     */
    public static boolean isNotEmp(String str) {
        return !isEmp(str);
    }

    /**
     * 所有的都不能为空，如果查找到为空的对象 抛出异常<br>
     * 如果是 String、List、Map同时还会判断长度 例：>> net.evecom.cwyun.pdk.vmware.Main.main() 第 111 行第4个参数 c 为空 <br>
     * 使用方式 先传入要判断的变量 CkUtil.AllNotEmp(null, a,b,c);<br>
     * 然后将第二个参数开始复制传入的变量到第一个参数成字符串 用于提示 CkUtil.AllNotEmp( "a,b,c", a,b,c);
     *
     * @param s       提示信息 一般将后面的参数复制成字符串传入
     * @param objects 可变个数的参数，这些参数将进行空判断

     * @created 2017 -12-28 上午11:39:42
     */
    public static void allNotEmpThrow(String s, Object... objects) {
        Check ret = allNotEmp(s, objects);
        if (!ret.noEmp) {
            throw new RuntimeException(ret.msg);
        }
    }

    /**
     * 所有的都不能为空<br>
     * 如果是 String、List、Map同时还会判断长度 例：>> net.evecom.cwyun.pdk.vmware.Main.main() 第 111 行第4个参数 c 为空 <br>
     * 使用方式 先传入要判断的变量 CkUtil.AllNotEmp(null, a,b,c);<br>
     * 然后将第二个参数开始复制传入的变量到第一个参数成字符串 用于提示 CkUtil.AllNotEmp( "a,b,c", a,b,c);
     *
     * @param s       提示信息 一般将后面的参数复制成字符串传入
     * @param objects 可变个数的参数，这些参数将进行空判断
     * @return Check.status =true 代表没有空

     * @created 2017 -12-28 上午11:39:42
     */
    public static Check allNotEmp(String s, Object... objects) {
        Check ret = Check.ret();
        ret.noEmp = true;
        if (objects == null) {
            return ret;
        }
        int errIndex = -1;
        for (int i = 0; i < objects.length; i++) {
            if (isEmp(objects[i])) {
                errIndex = i;
                break;
            }
        }
        if (errIndex > -1) {
            String param = "";
            if (isNotEmp(s)) {
                String[] arr = s.split(",");
                if (errIndex < arr.length) {
                    param = arr[errIndex];
                    param = getSetMethodName2PropertyName(param);
                }
            }
            ret.noEmp = false;
            ret.msg = getInvokInfo() + " 第 " + (errIndex + 1) + " 个参数 " + param + " 为空";
        }
        return ret;
    }

    /**
     * 返回此方法的调用者信息.<br>
     * 即谁调用此方法就返回调用位置的类名 方法名 行号 <br>
     * 例: >> net.evecom.cwyun.pdk.vmware.Main.main() 第 102 行 方法会从堆栈最上层往下找到第一个 非 Thread、CkUtil的调用者 ，如果没找到就取最底层的调用者信息
     *
     * @return invok info

     * @created 2017 -10-16 下午2:42:43
     */
    public static String getInvokInfo() {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String className = null;
        String methodName = null;
        int lineNumber = 0;
        for (int i = 0; i < ste.length; i++) {
            className = ste[i].getClassName();
            if ("java.lang.Thread".equals(className) || clz.getName().equals(className)) {
                if (i != (ste.length - 1)) {
                    continue;// 如果不是最后一个就跳过，如果是最后一个那就没办法了，返回这栈信息吧
                }
            }
            methodName = ste[i].getMethodName();
            lineNumber = ste[i].getLineNumber();
            break;
        }
        return ">> " + className + "." + methodName + "() 第  " + lineNumber + " 行";
    }

    /**
     * 根据属性获取其getset方法
     *
     * @param param the param
     * @param isGet the is get
     * @return string string

     * @created 2019 -6-4 下午4:25:58
     */
    public static String propertyName2GetSetName(String param, boolean isGet) {
        if (isEmp(param)) {
            return param;
        }
        if (param.length() > 1) {
            param = param.substring(0, 1).toUpperCase() + param.substring(1);
            return isGet ? ("get" + param) : ("set" + param);
        }
        return isGet ? ("get" + param.toUpperCase()) : ("set" + param.toUpperCase());
    }

    /**
     * 从getset方法名中取得属性名 .
     *
     * @param methodName the method name
     * @return set method name 2 property name

     * @created 2018 -3-15 下午6:29:29
     */
    public static String getSetMethodName2PropertyName(String methodName) {
        if (isEmp(methodName)) {
            return null;
        }
        int del = methodName.lastIndexOf('.');
        if (del != -1 && (del != methodName.length() - 1)) {
            methodName = methodName.substring(del + 1);
        }

        if (methodName.startsWith("set") || methodName.startsWith("get")) {
            methodName = methodName.substring(3);
        }
        if (methodName.length() == 0) {
            return methodName;
        }
        char[] charArray = methodName.toCharArray();
        char charAt = charArray[0];
        if (charAt >= 65 && charAt <= 90) {
            charAt += 32;
        }
        charArray[0] = charAt;
        int e = -1;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 40) {// (
                e = i;
            }
        }
        if (e == -1) {
            return new String(charArray, 0, charArray.length);
        }
        return new String(charArray, 0, e);
    }


    /**
     * 分割字符串, 默认使用,号分割
     *
     * @param s the s
     * @return the string [ ]
     * @author Chuck Tang
     * @created 2019 -09-23 17:35:11
     */
    public static String[] splitStr(String s) {
        return splitStr(s, ",");
    }

    /**
     * 检查结果
     *

     * @created 2017 -12-1 上午10:14:55
     */
    public static class Check {
        /**
         * 返回的状态
         */
        public boolean noEmp = true;
        /**
         * 失败信息
         */
        public String msg;

        /**
         * 返回check
         *
         * @return check check
    
         * @created 2017 -12-1 上午10:17:53
         */
        public static Check ret() {
            return new Check();
        }

    }

    /**
     * equest 其中一个不相同就false,可以用于判定包装类数字是否相等,字符串是否相同,而不用担心空指针 <br>
     * 下标0与1比较 2与3比较
     *
     * @param a the a
     * @return boolean boolean

     * @created 2019 -1-28 上午11:29:23
     */
    public static boolean equstsAnd(Object... a) {
        if (a == null || a.length == 0 || a.length % 2 == 1) {
            throw new RuntimeException("参数不合法,必须是大于0的偶数个");
        }
        for (int i = 0; i < a.length; i += 2) {
            if (!equst(a[i], a[i + 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * equest 其中一个相同就true,可以用于判定包装类数字是否相等,字符串是否相同,而不用担心空指针 <br>
     * 下标0与1比较 2与3比较
     *
     * @param a the a
     * @return boolean boolean

     * @created 2019 -5-20 下午2:32:25
     */
    public static boolean equstsOr(Object... a) {
        if (a == null || a.length == 0 || a.length % 2 == 1) {
            throw new RuntimeException("参数不合法,必须是大于0的偶数个");
        }
        for (int i = 0; i < a.length; i += 2) {
            if (equst(a[i], a[i + 1])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 两个是否相等
     *
     * @param a the a
     * @param b the b
     * @return boolean boolean

     * @created 2019 -1-28 上午11:23:31
     */
    public static boolean equst(Object a, Object b) {
        // 两个为空
        if (isEmp(a) && isEmp(b)) {
            return true;
        }
        // 一个为空
        if (isEmp(a) || isEmp(b)) {
            return false;
        }
        return a.equals(b);
    }

    /**
     * 两个是否不相等
     *
     * @param a the a
     * @param b the b
     * @return boolean boolean

     * @created 2019 -1-29 上午10:33:04
     */
    public static boolean nequst(Object a, Object b) {
        return !equst(a, b);
    }

    /**
     * 检查名称相关方法 系统内的名称如 用户名 工单名 等,长度,非法字符等规范应该是一致的
     *
     * @param source the source
     * @param len    the len
     * @return boolean boolean

     * @created 2019 -1-29 上午11:30:24
     */
    public boolean checkName(String source, int len) {
        if (source.length() > len) {
            return false;
        }
        return true;
    }

    /**
     * 检查名称相关方法 系统内的名称如 用户名 工单名 等,长度,非法字符等规范应该是一致的 直接在前端显示提示
     *
     * @param source the source
     * @param len    the len

     * @created 2019 -1-29 上午11:31:23
     */
    public void checkNameTip(String source, int len) {
        if (source.length() > len) {
            tip("字符串 : [" + source + "] 长度不合法");
        }
    }

    /**
     * 返回几个参数中不空的那个.按顺序判定
     *
     * @param <T> the type parameter
     * @param a   the a
     * @return t t

     * @created 2018 -10-8 下午4:29:18
     */
    public static <T> T retNotNull(T... a) {
        if (a == null) {
            return null;
        }
        for (T t : a) {
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    /**
     * 返回第一个不为空的对象.
     *
     * @param <T> the type parameter
     * @param a   the a
     * @return the t

     * @created 2020 -03-02 16:35:35
     */
    public static <T> T retNotEmp(T... a) {
        if (a == null) {
            return null;
        }
        for (T t : a) {
            if (isNotEmp(t)) {
                return t;
            }
        }
        return null;
    }

    /**
     * 返回list
     *
     * @param <T>      the type parameter
     * @param elements the elements
     * @return list list

     * @created 2019 -5-31 上午10:21:35
     */
    public static <T> List<T> retList(T... elements) {
        List<T> list = new ArrayList<>();
        if (isEmp(elements)) {
            return list;
        }
        for (T t : elements) {
            list.add(t);
        }
        return list;
    }

    /**
     * 返回数组
     *
     * @param <T>      the type parameter
     * @param elements the elements
     * @return t [ ]

     * @created 2019 -5-31 上午10:22:35
     */
    public static <T> T[] retArray(T... elements) {
        return elements;
    }

    /**
     * 返回map 冒充对象,因为对前面来说 map和对象没区别. <br>
     * 参数最好是偶数个 否则最后一个参数会被忽略,从0开始数 偶数位为key，奇数位为val<br>
     * key为null时 这组key和val 会被忽略 ，<br>
     * key必须是String，如果不是会强行调用toString方法<br>
     * 参数可以不填 会返回一个空的map<by>
     *
     * @param o the o
     * @return map map

     * @created 2018 -3-15 下午4:36:36
     */
    public static Map<String, Object> retMap(Object... o) {
        Map<String, Object> m = new HashMap<>();
        if (o == null) {
            return m;
        }
        for (int i = 1; i < o.length; i += 2) {
            Object key = o[i - 1];
            Object val = o[i];
            if (key == null) {
                continue;
            }
            m.put(str(key), val);
        }
        return m;
    }

    /**
     * 过滤掉null
     *
     * @param o the o
     * @return map map

     * @created 2019 -1-29 上午10:37:11
     */
    public static Map<String, Object> retMapFiliterNull(Object... o) {
        Map<String, Object> m = new HashMap<>();
        if (o == null) {
            return m;
        }
        for (int i = 1; i < o.length; i += 2) {
            Object key = o[i - 1];
            Object val = o[i];
            if (key == null) {
                continue;
            }
            if (val == null) {
                continue;
            }
            m.put(str(key), val);
        }
        return m;
    }

    /**
     * 将传入的参数以固定的符号拼接 符号可以不传
     *
     * @param symbol 符号
     * @param objs   任意参数 任意个参数
     * @return string string

     * @created 2018 -10-16 下午3:08:23
     */
    public static String join(String symbol, Object... objs) {
        if (objs == null) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        for (Object obj : objs) {
            if (obj == null) {
                continue;
            }
            if (obj instanceof Collection && ((Collection) obj).size() > 0) {
                for (Object o : (Collection) obj) {
                    if (o != null && isNotEmp(o.toString())) {
                        str.append(o.toString());
                        if (symbol != null && symbol.length() > 0) {
                            str.append(symbol);
                        }
                    }
                }
            } else if (obj.getClass().isArray() && Array.getLength(obj) > 0) {
                for (int i = 0; i < Array.getLength(obj); i++) {
                    Object o = Array.get(obj, i);
                    if (o != null && isNotEmp(o.toString())) {
                        str.append(o.toString());
                        if (symbol != null && symbol.length() > 0) {
                            str.append(symbol);
                        }
                    }
                }
            } else {
                if (obj != null && isNotEmp(obj.toString())) {
                    str.append(obj.toString());
                    if (symbol != null && symbol.length() > 0) {
                        str.append(symbol);
                    }
                }
            }
        }
        if (str.length() > 0 && symbol != null && symbol.length() > 0) {
            str.deleteCharAt(str.length() - symbol.length());
        }
        return str.toString();
    }

    /**
     * 对list截取,不会报异常,如果start或者limit不传 原样返回
     *
     * @param <T>   the type parameter
     * @param list  the list
     * @param start the start
     * @param limit the limit
     * @return list list

     * @created 2018 -11-5 下午3:54:31
     */
    public static <T> List<T> subList(List<T> list, Integer start, Integer limit) {
        if (start == null || limit == null) {
            return list;
        }
        int size = list.size();
        // start必须是脚标 最大角标是size-1
        if (start > (size - 1)) {
            return new ArrayList<T>();
        }
        // start+limit-1 是要取的最大角标数 size-1是实际最大角标 而取头不取尾subList第2参数传入的是要取的最大角标+1
        if ((start + limit) > size) {
            return new ArrayList<>(list.subList(start, size));
        }
        return new ArrayList<>(list.subList(start, start + limit));
    }

    /**
     * 将任意类型的list转换成int的list,如果转换失败就丢弃.
     *
     * @param <T>    the type parameter
     * @param source the source
     * @return list list

     * @created 2019 -1-29 上午10:45:37
     */
    public static <T> List<Integer> intList(List<T> source) {
        List<Integer> target = alist();
        if (isEmp(source)) {
            return target;
        }
        for (T t : source) {
            Integer it = it(t);
            if (isNotEmp(it)) {
                target.add(it);
            }
        }
        return target;
    }

    /**
     * 将任意类型的list转换成long的list,如果转换失败就丢弃.
     *
     * @param <T>    the type parameter
     * @param source the source
     * @return list list

     * @created 2019 -1-29 上午10:45:37
     */
    public static <T> List<Long> longList(List<T> source) {
        List<Long> target = alist();
        if (isEmp(source)) {
            return target;
        }
        for (T t : source) {
            Long it = lon(t);
            if (isNotEmp(it)) {
                target.add(it);
            }
        }
        return target;
    }

    /**
     * 将任意类型的list转换成str的list,如果转换失败就丢弃.
     *
     * @param <T>    the type parameter
     * @param source the source
     * @return list list

     * @created 2019 -1-29 上午10:45:37
     */
    public static <T> List<String> strList(List<T> source) {
        List<String> target = alist();
        if (isEmp(source)) {
            return target;
        }
        for (T t : source) {
            String it = str(t);
            if (isNotEmp(it)) {
                target.add(it);
            }
        }
        return target;
    }

    /**
     * 将任意类型的list转换成long的list,如果转换失败就丢弃.
     *
     * @param <T>    the type parameter
     * @param source the source
     * @return list list

     * @created 2019 -1-29 上午10:45:37
     */
    public static <T> List<Double> douList(List<T> source) {
        List<Double> target = alist();
        if (isEmp(source)) {
            return target;
        }
        for (T t : source) {
            Double it = dou(t);
            if (isNotEmp(it)) {
                target.add(it);
            }
        }
        return target;
    }

    /**
     * 将任意类型的list转换成Boolean的list,如果转换失败就丢弃.
     *
     * @param <T>    the type parameter
     * @param source the source
     * @return list list

     * @created 2019 -1-29 上午10:45:37
     */
    public static <T> List<Boolean> booList(List<T> source) {
        List<Boolean> target = alist();
        if (isEmp(source)) {
            return target;
        }
        for (T t : source) {
            Boolean it = boo(t);
            if (isNotEmp(it)) {
                target.add(it);
            }
        }
        return target;
    }

    /**
     * 将任意类型的list转换成int的list,如果转换失败就丢弃.
     *
     * @param <T>    the type parameter
     * @param <K>    the type parameter
     * @param source the source
     * @param key    the key
     * @return list list

     * @created 2019 -1-29 上午10:45:37
     */
    public static <T, K> List<Integer> intList(List<Map<K, T>> source, K key) {
        List<Integer> target = alist();
        if (isEmp(source)) {
            return target;
        }
        for (Map<K, T> t : source) {
            Integer it = it(t, key);
            if (isNotEmp(it)) {
                target.add(it);
            }
        }
        return target;
    }

    /**
     * 将任意类型的list转换成long的list,如果转换失败就丢弃.
     *
     * @param <T>    the type parameter
     * @param <K>    the type parameter
     * @param source the source
     * @param key    the key
     * @return list list

     * @created 2019 -1-29 上午10:45:37
     */
    public static <T, K> List<Long> longList(List<Map<K, T>> source, K key) {
        List<Long> target = alist();
        if (isEmp(source)) {
            return target;
        }
        for (Map<K, T> t : source) {
            Long it = lon(t, key);
            if (isNotEmp(it)) {
                target.add(it);
            }
        }
        return target;
    }

    /**
     * 将任意类型的list转换成str的list,如果转换失败就丢弃.
     *
     * @param <T>    the type parameter
     * @param <K>    the type parameter
     * @param source the source
     * @param key    the key
     * @return list list

     * @created 2019 -1-29 上午10:45:37
     */
    public static <T, K> List<String> strList(List<Map<K, T>> source, K key) {
        List<String> target = alist();
        if (isEmp(source)) {
            return target;
        }
        for (Map<K, T> t : source) {
            String it = str(t, key);
            if (isNotEmp(it)) {
                target.add(it);
            }
        }
        return target;
    }

    /**
     * 将任意类型的list转换成long的list,如果转换失败就丢弃.
     *
     * @param <T>    the type parameter
     * @param <K>    the type parameter
     * @param source the source
     * @param key    the key
     * @return list list

     * @created 2019 -1-29 上午10:45:37
     */
    public static <T, K> List<Double> douList(List<Map<K, T>> source, K key) {
        List<Double> target = alist();
        if (isEmp(source)) {
            return target;
        }
        for (Map<K, T> t : source) {
            Double it = dou(t, key);
            if (isNotEmp(it)) {
                target.add(it);
            }
        }
        return target;
    }

    /**
     * 将任意类型的list转换成Boolean的list,如果转换失败就丢弃.
     *
     * @param <T>    the type parameter
     * @param <K>    the type parameter
     * @param source the source
     * @param key    the key
     * @return list list

     * @created 2019 -1-29 上午10:45:37
     */
    public static <T, K> List<Boolean> booList(List<Map<K, T>> source, K key) {
        List<Boolean> target = alist();
        if (isEmp(source)) {
            return target;
        }
        for (Map<K, T> t : source) {
            Boolean it = boo(t, key);
            if (isNotEmp(it)) {
                target.add(it);
            }
        }
        return target;
    }

    /**
     * 构造arraylist
     *
     * @param <T> the type parameter
     * @return list list

     * @created 2018 -4-3 下午3:33:33
     */
    public static <T> List<T> alist() {
        return new ArrayList<T>();
    }

    /**
     * 构造linkedlist
     *
     * @param <T> the type parameter
     * @return list list

     * @created 2018 -4-3 下午3:33:33
     */
    public static <T> List<T> llist() {
        return new LinkedList<T>();
    }

    /**
     * 构造HashSet
     *
     * @param <T> the type parameter
     * @return set set

     * @created 2018 -4-3 下午3:33:33
     */
    public static <T> Set<T> hset() {
        return new HashSet<T>();
    }

    /**
     * 构造map
     *
     * @param <A> the type parameter
     * @param <B> the type parameter
     * @return map map

     * @created 2018 -4-3 下午3:36:05
     */
    public static <A, B> Map<A, B> hmap() {
        return new HashMap<A, B>();
    }

    /**
     * 在前端显示告警消息
     *
     * @param msg the msg

     * @created 2019 -1-29 上午10:40:31
     */
    public static void tip(String msg) {
        throw new RuntimeException(msg);
    }


    /**
     * 分割s, 如果s不包含"," 则返回空
     *
     * @param s the s
     * @return string [ ]

     * @created 2019 -3-28 下午3:15:51
     */
    public static String[] splitMulitStr(String s) {
        if (EveUtil.isEmp(s) || (!s.contains(","))) {
            return null;
        }
        return s.split(",");
    }

    /**
     * 用第二个参数分割第一个参数
     *
     * @param s     the s
     * @param split the split
     * @return the string [ ]
     * @author Chuck Tang
     * @created 2019 -09-19 20:07:00
     */
    public static String[] splitStr(String s, String split) {
        if (isNotEmp(s)) {
            return s.split(split);
        }
        return null;
    }

    /**
     * 如果条件是in
     *
     * @param s the s
     * @return list list

     * @created 2019 -3-28 下午3:15:51
     */
    public static List<Long> splitMulitLon(String s) {
        if (EveUtil.isEmp(s) || (!s.contains(","))) {
            return null;
        }
        String[] arr = s.split(",");
        return EveUtil.longList(Arrays.asList(arr));
    }

    /**
     * 描述 如果如果传入单个数字正常获取
     *
     * @param s the s
     * @return java.util.List<java.lang.Long>  list
     * @author Oli Hong
     * @created 2019 /4/9 16:54
     */
    public static List<Long> splitMulitLong(String s) {
        if (s == null) {
            return null;
        }
        if (EveUtil.isEmp(s) || (!s.contains(","))) {
            return EveUtil.longList(Arrays.asList(s));
        }
        String[] arr = s.split(",");
        return EveUtil.longList(Arrays.asList(arr));
    }

    /**
     * 描述 如果如果传入单个数字正常获取
     *
     * @param s the s
     * @return java.util.List<java.lang.Integer>  list
     * @author Oli Hong
     * @created 2019 /4/9 16:54
     */
    public static List<Integer> splitMulitInt(String s) {
        if (s == null) {
            return null;
        }
        if (EveUtil.isEmp(s) || (!s.contains(","))) {
            return EveUtil.intList(Arrays.asList(s));
        }
        String[] arr = s.split(",");
        return EveUtil.intList(Arrays.asList(arr));
    }

    /**
     * 描述 循环数字为字符，小于10的会加上前缀0
     *
     * @param number the number
     * @param type   type=0从0开始，type=1从1开始
     * @return java.util.List<java.lang.String>  str number
     * @author Oli Hong
     * @created 2019 /4/17 15:59
     */
    public static List<String> getStrNumber(Integer number, Integer type) {
        List<String> list = new ArrayList<>();
        int begin = 0;
        if (type == 1) {
            begin = 1;
        }
        for (int i = begin; i < number; i++) {
            String str = i + "";
            if (i < 10) {
                str = "0" + i;
            }
            list.add(str);
        }

        return list;
    }

    /**
     * 描述 输出年月（2018-01格式）当月的字符类型天数
     *
     * @param time the time
     * @return java.util.List<java.lang.String>  str day
     * @author Oli Hong
     * @created 2019 /4/17 15:19
     */
    public static List<String> getStrDay(String time) {
        List<String> list = new ArrayList<>();
        int year, month, days;
        year = Integer.parseInt(time.substring(0, 4));
        if ("0".equals(time.substring(5, 6))) {
            month = Integer.parseInt(time.substring(6, 7));
        } else {
            month = Integer.parseInt(time.substring(5, 7));
        }
        // 判断是否为闰年也恰好查询2月份天数
        if (year % 100 == 0 && year % 400 == 0 && month == 2) {
            days = 29;
        } else {
            switch (month) {
                case 2:
                    days = 28;
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    days = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    days = 30;
                    break;
                default:
                    days = 30;
                    break;
            }
        }
        for (int i = 1; i <= days; i++) {
            String str = i + "";
            if (i < 10) {
                str = "0" + i;
            }
            list.add(str);
        }

        return list;
    }

    /**
     * 描述 分钟转换为小时和分钟
     *
     * @param time the time
     * @return java.lang.String string
     * @author Oli Hong
     * @created 2019 /5/6 9:55
     */
    public static String changeMinute(Integer time) {
        int hours = (int) Math.floor(time / 60);
        int minute = time % 60;
        StringBuffer sb = new StringBuffer();
        if (hours < 10) {
            sb.append("0");
        }
        sb.append(hours + ":" + minute);
        if (minute == 0) {
            sb.append("0");
        }
        return sb.toString();
    }

    /**
     * 描述 计划模板传入的时间格式更改为所需的长字符串 000011110000001111110000011110000000000000000111
     * 转换为"02:00-04:00,07:00-10:00,12:30-14:30,22:30-00:00"
     *
     * @param times the times
     * @return java.lang.String string
     * @author Oli Hong
     * @created 2019 /5/6 14:12
     */
    public static String timeFormatStr(String times) {
        StringBuilder str = new StringBuilder("000000000000000000000000000000000000000000000000");
        if (isNotEmp(times)) {
            String[] timesSplit = times.split(",");
            for (String ts : timesSplit) {
                Integer benginHourCount = Integer.parseInt(ts.substring(0, 2)) * 2;
                Integer beginMinuteCount = Integer.parseInt(ts.substring(3, 5)) / 30;
                Integer beginCount = benginHourCount + beginMinuteCount;
                Integer endHourCount = Integer.parseInt(ts.substring(6, 8)) * 2;
                Integer endMinuteCount = Integer.parseInt(ts.substring(9, 11)) / 30;
                Integer endCount = endHourCount + endMinuteCount;
                if (endCount == 0) {
                    endCount = 48;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < (endCount - beginCount); i++) {
                    sb.append("1");
                }
                str.replace(beginCount, endCount, sb.toString());
            }
        }
        return str.toString();
    }

    /**
     * 描述 计划模板的长字符串更改为所需的时间格式
     *
     * @param strTime the str time
     * @return java.lang.String string
     * @author Oli Hong
     * @created 2019 /5/6 9:58
     */
    public static String strFormatTime(String strTime) {
        int initVal = 0;
        String initStr = "1";
        StringBuffer sb = new StringBuffer();
        while (initVal < strTime.length()) {
            initVal = strTime.indexOf(initStr, initVal);
            if (initVal == -1) {
                if ("0".equals(initStr)) {
                    sb.append("00:00");
                }
                break;
            }
            Integer time = initVal * 30;
            sb.append(changeMinute(time));
            if ("1".equals(initStr)) {
                sb.append("-");
                initStr = "0";
            } else {
                sb.append(",");
                initStr = "1";
            }
        }
        String result = sb.toString();
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * 描述 前后加逗号,去除区域的带有#号
     *
     * @param str the str
     * @return java.lang.String string
     * @author Oli Hong
     * @created 2019 /6/3 18:12
     */
    public static String formatString(String str) {
        if (isEmp(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder(",");
        String[] strSplit = str.split(",");
        for (String s : strSplit) {
            if (s.contains("#") || "".equals(s)) {
                continue;
            }
            sb.append(s + ",");

        }
        return sb.toString();
    }

    /**
     * 克隆集合.
     *
     * @param source
     * @return

     * @created 2018-5-12 下午8:54:37
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <A> List cloneList(List<A> source) {
        if (source == null) {
            return null;
        }
        try {
            List dest = source.getClass().newInstance();
            for (A a : source) {
                dest.add(a);
            }
            return dest;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 克隆map
     *
     * @param source
     * @return

     * @created 2018-5-12 下午8:56:34
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <K, V> Map cloneMap(Map<K, V> source) {
        if (source == null) {
            return null;
        }
        try {
            Map dest = source.getClass().newInstance();
            for (Entry<K, V> ent : source.entrySet()) {
                dest.put(ent.getKey(), ent.getValue());
            }
            return dest;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 描述      统计分析传入时间解析出开始时间和结束时间
     *
     * @param time the time
     * @return java.util.Map<java.lang.String, java.lang.String>  map
     * @author Oli Hong
     * @created 2019 /6/4 21:42
     */
//    public static Map<String,String> getTimeSection(String time){
//        if(EveUtil.isEmp(time)){
//            return null;
//        }
//        String startTime=null;
//        String endTime=null;
//        Map<String,String> map=new HashMap<>();
//            String [] strSplit=time.split("-| ");
//            DateTime startDateTime =new DateTime();
//            DateTime endDateTime=new DateTime();
//            switch (strSplit.length){
//                //年份
//                case 1:
//                    startDateTime = new DateTime().withYear(Integer.parseInt(strSplit[0]))
//                            .dayOfYear().withMinimumValue();
//                    endDateTime = new DateTime().withYear(Integer.parseInt(strSplit[0]))
//                            .dayOfYear().withMaximumValue();
//                    break;
//                //月份
//                case 2:
//                    startDateTime = new DateTime().withYear(Integer.parseInt(strSplit[0]))
//                            .withMonthOfYear(Integer.parseInt(strSplit[1])).dayOfMonth().withMinimumValue();
//                    endDateTime = new DateTime().withYear(Integer.parseInt(strSplit[0]))
//                            .withMonthOfYear(Integer.parseInt(strSplit[1])).dayOfMonth().withMaximumValue();
//                    break;
//                //日份
//                case 3:
//                    startDateTime = new DateTime().withYear(Integer.parseInt(strSplit[0]))
//                            .withMonthOfYear(Integer.parseInt(strSplit[1]))
//                            .withDayOfMonth(Integer.parseInt(strSplit[2]));
//                    endDateTime = new DateTime().withYear(Integer.parseInt(strSplit[0]))
//                            .withMonthOfYear(Integer.parseInt(strSplit[1]))
//                            .withDayOfMonth(Integer.parseInt(strSplit[2]));
//                    break;
//                //时份
//                case 4:
//                    startDateTime = new DateTime().withYear(Integer.parseInt(strSplit[0]))
//                            .withMonthOfYear(Integer.parseInt(strSplit[1]))
//                            .withDayOfMonth(Integer.parseInt(strSplit[2]))
//                            .withHourOfDay(Integer.parseInt(strSplit[3]));
//                    endDateTime = new DateTime().withYear(Integer.parseInt(strSplit[0]))
//                            .withMonthOfYear(Integer.parseInt(strSplit[1]))
//                            .withDayOfMonth(Integer.parseInt(strSplit[2]))
//                            .withHourOfDay(Integer.parseInt(strSplit[3]));
//                    break;
//                default:
//                    break;
//
//            }
//            if(strSplit.length==4){
//                startTime=startDateTime.toString("yyyy-MM-dd HH:00:00");
//                endTime=endDateTime.toString("yyyy-MM-dd HH:59:59");
//            }else{
//                startTime=startDateTime.toString("yyyy-MM-dd 00:00:00");
//                endTime=endDateTime.toString("yyyy-MM-dd 23:59:59");
//            }
//            map.put("startTime",startTime);
//            map.put("endTime",endTime);
//        return map;
//    }

    /**
     * 构建消息,填充变量
     *
     * @param vars  the vars
     * @param templ the templ
     * @return string string

     * @created 2019 -6-4 下午4:35:24
     */
    public static String buildMsg(Map<String, Object> vars, String templ) {
        for (Entry<String, Object> ent : vars.entrySet()) {
            templ = templ.replace(ent.getKey(), str(ent.getValue()));
        }
        return templ;
    }

    /**
     * 切割目标字符串
     *
     * @param target
     * @return

     * @created 2019-4-23 下午4:07:43
     */
    public static List<String> split(String target) {
        return split(target, ",", String.class);
    }

    /**
     * 切割字符串成集合空字符串丢弃
     *
     * @param target   目标字符串
     * @param separate 分隔符
     * @param clz      目标类型
     * @return

     * @created 2019-4-23 下午4:06:27
     */
    public static <T> List<T> split(String target, String separate, Class<T> clz) {
        List<T> list = new ArrayList<>();
        if (target == null) {
            return list;
        }
        if (separate == null || separate.length() == 0) {
            list.add((T) EveUtil.parseBaseType(clz, target));
            return list;
        }
        String[] split = target.trim().split(separate);
        for (String s : split) {
            s = s.trim();
            if (s.length() > 0) {
                list.add((T) EveUtil.parseBaseType(clz, s));
            }
        }
        return list;
    }

    /**
     * 获取集合元素不会角标越界.
     *
     * @param <T>   the type parameter
     * @param list  the list
     * @param index the index
     * @return the t

     * @created 2020 -02-12 20:28:53
     */
    public static <T> T get(List<T> list, int index) {
        if (list == null || (list.size() - 1) < index) {
            return null;
        }
        return list.get(index);
    }

    /**
     * 获取数组元素 不会脚本越界.
     *
     * @param <T>   the type parameter
     * @param arr   the arr
     * @param index the index
     * @return the t

     * @created 2020 -02-12 20:29:50
     */
    public static <T> T get(T[] arr, int index) {
        if (arr == null || (arr.length - 1) < index) {
            return null;
        }
        return arr[index];
    }


    /**
     * 解压gz文件 只支持压缩包包含单个文件的情况.
     *
     * @param gzFile the gz file
     * @return the file

     * @created 2020 -03-02 10:16:48
     */
    public static File unCompressGz(File gzFile) {
        if (!gzFile.exists() || gzFile.isDirectory()) {
            throw new RuntimeException("解压异常: 文件不存在或者目标不是文件");
        }
        String path = gzFile.getAbsolutePath();
        if (!(path.endsWith(".gz") || path.endsWith(".GZ"))) {
            throw new RuntimeException("解压异常: 文件后缀异常");
        }
        File dest = new File(path.substring(0, path.length() - 3));
        try (GZIPInputStream gzi = new GZIPInputStream(new FileInputStream(gzFile));
             FileOutputStream fos = new FileOutputStream(dest);
        ) {
            byte[] byt = new byte[1024];
            int idx = 0;

            while ((idx = gzi.read(byt)) > -1) {
                fos.write(byt, 0, idx);
            }
            return dest;
        } catch (Exception e) {
            throw new RuntimeException("解压异常: " + e.getMessage());
        }
    }

    /**
     * Compress gz file 只支持单个文件.
     *
     * @param file the file
     * @return the file

     * @created 2020 -03-02 10:21:43
     */
    public static File compressGz(File file) {
        if (!file.exists() || file.isDirectory()) {
            throw new RuntimeException("压缩异常: 文件不存在或者目标不是文件");
        }
        String path = file.getAbsolutePath();
        File dest = new File(path + ".gz");
        try (GZIPOutputStream gzo = new GZIPOutputStream(new FileOutputStream(dest));
             FileInputStream fis = new FileInputStream(file);
        ) {
            byte[] byt = new byte[1024];
            int idx = 0;

            while ((idx = fis.read(byt)) > -1) {
                gzo.write(byt, 0, idx);
            }
            return dest;
        } catch (Exception e) {
            throw new RuntimeException("压缩异常: " + e.getMessage());
        }
    }

    /**
     * 获取本机的所有可用ip.集合肯定不会为空 至少有一个元素
     *
     * @return the local ip

     * @created 2020 -05-21 10:20:25
     */
    public static List<String> getLocalIp() {
        List<String> ips = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface anInterface = interfaces.nextElement();
                Enumeration<InetAddress> addresses = anInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    String format = String.format("地址 %s  正常ip?%s 回环?%s 链路?%s", address.getHostAddress(), address.isSiteLocalAddress(),
                            address.isLoopbackAddress(), address.isLinkLocalAddress());

                    log.debug(format);
                    if (address.isSiteLocalAddress()) {
                        String ip = address.getHostAddress();
                        if (isIpv4(ip)) {
                            ips.add(ip);
                        }
                    }
                }
            }
        } catch (SocketException e) {
            log.error(e.getMessage(), e);
        }
        if (ips.isEmpty()) {
            try {
                String address = InetAddress.getLocalHost().getHostAddress();
                ips.add(address);
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        if (ips.isEmpty()) {
            ips.add("127.0.0.1");
        }
        return ips;
    }

    /**
     * Is ipv 4 boolean.
     *
     * @param ip the ip
     * @return the boolean

     * @created 2020 -05-21 10:19:12
     */
    public static boolean isIpv4(String ip) {
        return isEmp(ip) ? false : ip.matches("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)){3}");
    }

    /**
     * 获取class的反射路径.
     *
     * @param clz the clz
     * @return the clz url

     * @created 2020 -03-18 14:46:37
     */
    public static String getClzUrl(Class clz) {
        return clz.getPackage().getName() + "." + clz.getSimpleName();
    }

    /**
     * Convert jsonstr to map map.
     *
     * @param jsonstr the jsonstr
     * @return the map

     * @created 2021 -05-12 15:16:29
     */
    public static Map<String, Object> convertJsonstrToMap(String jsonstr) {
        try {
            return new ObjectMapper().readValue(jsonstr, Map.class);
        } catch (Exception e) {
            log.warn("json转换异常", e);
            return new HashMap<>();
        }
    }

    /**
     * Convert jsonstr to map map.
     *
     * @param jsonstr the jsonstr
     * @return the map

     * @created 2021 -05-12 15:16:33
     */
    public static List<Map<String, Object>> convertJsonstrToListMap(String jsonstr) {
        try {
            return new ObjectMapper().readValue(jsonstr, List.class);
        } catch (Exception e) {
            log.warn("json转换异常", e);
            return new ArrayList<>();
        }
    }

    /**
     * Convert jsonstr string.
     *
     * @param o the o
     * @return the string

     * @created 2021 -05-12 15:18:23
     */
    public static String convertJsonstr(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (Exception e) {
            log.warn("json转换异常", e);
            return "";
        }
    }

    /**
     * Trim string.
     *
     * @param s the s
     * @return the string

     * @created 2021 -05-12 15:34:31
     */
    public static String trim(String s) {
        return s == null ? null : s.trim();
    }
}
