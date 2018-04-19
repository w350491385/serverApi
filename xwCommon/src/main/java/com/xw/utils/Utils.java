package com.xw.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xw.ErrorCode;
import com.xw.exception.CheckException;
import com.xw.exception.ModelException;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {
    public static final int CPU_NUM = Runtime.getRuntime().availableProcessors();
    /**
     * UTC转换CST时区，偏移量，毫秒
     */
    public static int UTCTimeZoneOffSet = 8 * 60 * 60 * 1000;

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳
     */
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp CSTNow() {
        return new Timestamp(System.currentTimeMillis() + UTCTimeZoneOffSet);
    }

    /**
     * 给指定日期时间增加天数
     *
     * @param timestamp 指定的时间戳
     * @param days      天数
     * @return 增加日期后的时间戳
     */
    public static Timestamp timestampAddDays(Timestamp timestamp, int days) {
        return new Timestamp(timestamp.getTime() + daysToMilliseconds(days));
    }

    /**
     * 返回当前日期
     *
     * @return 当前日期
     */
    public static Date today() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 返回当前日期
     *
     * @return 当前日期（sql 类型）
     */
    public static java.sql.Date todayForSQL() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    /**
     * 在日期上增加指定天数
     *
     * @param date 日期
     * @param days 天数
     * @return 增加了指定天数后的日期
     */
    public static Date dateAddDays(Date date, int days) {
        return new Date(date.getTime() + daysToMilliseconds(days));
    }

    /**
     * 在日期上增加指定天数
     *
     * @param date 日期（sql 类型）
     * @param days 天数
     * @return 增加了指定天数后的日期（sql 类型）
     */
    public static java.sql.Date dateAddDaysForSQL(java.sql.Date date, int days) {
        return new java.sql.Date(date.getTime() + daysToMilliseconds(days));
    }

    /**
     * 判断某个对象是否是数值型
     *
     * @param o      原对象
     * @param remark 错误对示
     */
    public static void checkIsNumber(Object o, String remark) {
        if (o == null) return;
        if (!(o instanceof Number)) {
            throw new ModelException(ErrorCode.ILLEGAL_PARAMETER, remark);
        }
    }


    /**
     * 天数转换为毫秒数
     *
     * @param days 天数
     * @return 毫秒数
     */
    public static long daysToMilliseconds(int days) {
        if (days < 0) {
            throw new IllegalArgumentException("天数不能为负数");
        }
        return (long) days * 24 * 60 * 60 * 1000;
    }

    /**
     * 天数转换为秒数
     *
     * @param days 天数
     * @return 秒数
     */
    public static long daysToSeconds(int days) {
        if (days < 0) {
            throw new IllegalArgumentException("天数不能为负数");
        }
        return (long) days * 24 * 60 * 60;
    }

    /**
     * 检查指定的对象是否为 NULL，如果为 NULL 则抛出指定异常
     *
     * @param o         指定对象
     * @param errorCode 指定错误
     */
    public static void checkNotNull(Object o, ErrorCode errorCode) {
        if (o == null) {
            throw new CheckException(errorCode);
        }
    }

    /**
     * 检查指定的对象是否为 NULL，如果为 NULL 则抛出指定异常
     *
     * @param o         指定对象
     * @param errorCode 指定错误
     * @param remark    指定提示
     */
    public static void checkNotNull(Object o, ErrorCode errorCode, String remark) {
        if (o == null) {
            throw new CheckException(errorCode, remark);
        }
    }

    public static void checkStringHasValue(Object o, ErrorCode errorCode, String remark) {
        if (o == null || ((String) o).trim().equals("")) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 检查指写的对象是否不为Null，如果不为NUll则抛出指定异常
     *
     * @param o         指定对象
     * @param errorCode 指定错误
     * @param remark    指定提示
     */
    public static void checkIsNull(Object o, ErrorCode errorCode, String remark) {
        if (o != null) {
            throw new CheckException(errorCode, remark);
        }
    }

    public static void checkIsNull(Object o, ErrorCode errorCode) {
        checkIsNull(o, errorCode, errorCode.getMessage());
    }

    /**
     * 检查指定的对象是否为 NULL，如果为 NULL 则抛出异常
     *
     * @param o 指定对象
     */
    public static void checkNotNull(Object o) {
        checkNotNull(o, ErrorCode.ILLEGAL_PARAMETER);
    }

    /**
     * 如果相等则抛出指定错误码和错误信息的异常
     *
     * @param expected 期望值
     * @param actual   实际值
     * @throws CheckException
     */
    public static void throwIfEquals(int expected, int actual, ErrorCode errorCode) {
        if (expected == actual) {
            throw new CheckException(errorCode);
        }
    }

    public static void throwIfEquals(int expected, int actual, ErrorCode errorCode, String remark) {
        if (expected == actual) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 如果不相等则抛出指定错误码和错误信息的异常
     *
     * @param expected 期望值
     * @param actual   实际值
     * @throws CheckException
     */
    public static void throwIfNotEquals(int expected, int actual, ErrorCode errorCode) {
        if (expected != actual) {
            throw new CheckException(errorCode);
        }
    }

    /**
     * 如果不相等则抛出指定错误码和错误信息的异常
     *
     * @param expected 期望值
     * @param actual   实际值
     * @throws CheckException
     */
    public static void checkNotEquals(int expected, int actual, ErrorCode errcode) {
        checkNotEquals(expected, actual, errcode, errcode.getMessage());
    }


    /**
     * 如果不相等则抛出指定错误码和错误信息的异常
     *
     * @param expected 期望值
     * @param actual   实际值
     * @throws CheckException
     */
    public static void checkNotEquals(int expected, int actual, ErrorCode apiErrorCode, String remark) {
        if (expected == actual) {
            throw new CheckException(apiErrorCode, remark);
        }
    }

    /**
     * 如果为真则抛出异常
     *
     * @param actual    检测值
     * @param errorCode 错误码
     */
    public static void throwIfTrue(boolean actual, ErrorCode errorCode) {
        if (actual) {
            throw new CheckException(errorCode);
        }
    }

    /**
     * 如果为真则抛出异常
     *
     * @param actual       值
     * @param apiErrorCode 错误码
     * @param remark       消息提示
     */
    public static void throwIfTrue(boolean actual, ErrorCode apiErrorCode, String remark) {
        if (actual) {
            throw new CheckException(apiErrorCode, remark);
        }
    }

    /**
     * 如果为假则抛出异常
     *
     * @param actual       检测值
     * @param apiErrorCode 错误码
     */
    public static void throwIfFalse(boolean actual, ErrorCode apiErrorCode) {
        if (!actual) {
            throw new CheckException(apiErrorCode);
        }
    }

    /**
     * 如果为假则抛出异常
     *
     * @param actual       检测值
     * @param apiErrorCode 错误码
     * @param remark       消息备注
     */
    public static void throwIfFalse(boolean actual, ErrorCode apiErrorCode, String remark) {
        if (!actual) {
            throw new CheckException(apiErrorCode, remark);
        }
    }

    public static void throwAlways(ErrorCode apiErrorCode, String remark) {
        throw new CheckException(apiErrorCode, remark);
    }

    public static void throwAlways(ErrorCode apiErrorCode) {
        throw new CheckException(apiErrorCode);
    }


    /**
     * 检测字符串是否相等，如果不等则抛出异常
     *
     * @param left      左值
     * @param right     右值
     * @param errorCode 错误码
     * @param remark    错误消息
     */
    public static void checkNotEquals(String left, String right, ErrorCode errorCode, String remark) {
        if (left.equals(right)) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 检测值是否在指定范围内（包含边界），如果不在范围内则抛出异常
     *
     * @param value     检测值
     * @param min       最小值
     * @param max       最大值
     * @param errorCode 错误码
     * @param remark    错误消息
     */
    public static void checkInRange(int value, int min, int max, ErrorCode errorCode, String remark) {
        if (!(value >= min && value <= max)) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 检测值是否在指定范围内（包含边界），如果在返回true
     *
     * @param value 检测值
     * @param min   最小值
     * @param max   最大值
     */
    public static boolean checkInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * 检测值是否在指定范围内（包含边界），如果不在范围内则抛出异常
     *
     * @param value     检测值
     * @param min       最小值
     * @param max       最大值
     * @param errorCode 错误码
     * @param remark    错误消息
     */
    public static void checkInRange(long value, long min, long max, ErrorCode errorCode, String remark) {
        if (!(value >= min && value <= max)) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 判断值是数组中的一员，否则抛出异常
     *
     * @param value     值
     * @param values    数组
     * @param errorCode 错误码
     */
    public static void checkIn(int value, int[] values, ErrorCode errorCode) {
        checkIn(value, values, errorCode, errorCode.getMessage());
    }

    /**
     * 判断值是数组中的一员，否则抛出异常
     *
     * @param value     值
     * @param values    数组
     * @param errorCode 错误码
     * @param remark    错误消息
     */
    public static void checkIn(int value, int[] values, ErrorCode errorCode, String remark) {
        for (int v : values) {
            if (value == v) return;
        }
        throw new CheckException(errorCode, remark);
    }


    public static void checkIn(byte value, byte[] values, ErrorCode errorCode, String remark) {
        for (byte v : values) {
            if (value == v) return;
        }
        throw new CheckException(errorCode, remark);
    }

    public static void checkIn(int value, Set<Integer> values, ErrorCode errorCode, String remark) {
        if (values.contains(value)) {
            return;
        }
        throw new CheckException(errorCode, remark);
    }

    /**
     * @param value
     * @param values
     * @param errorCode
     */
    public static void checkNotIn(int value, int[] values, ErrorCode errorCode) {
        checkNotIn(value, values, errorCode, errorCode.getMessage());
    }

    /**
     * 判断不在某范围内的值
     *
     * @param value     值
     * @param values    数组
     * @param errorCode 错误码
     * @param remark    错误信息
     */
    public static void checkNotIn(int value, int[] values, ErrorCode errorCode, String remark) {
        for (int v : values) {
            if (value == v) throw new CheckException(errorCode, remark);
        }
    }


    private static final Pattern MOBILE_PATTERN = Pattern.compile("^1[3456879]\\d{9}$");

    /**
     * 判断是否有效的手机
     *
     * @param mobile 手机号
     */
    public static void checkValidMobile(String mobile) {
        checkNotNull(mobile, ErrorCode.ILLEGAL_PARAMETER, "手机号码不能为空");
        checkEquals(mobile.length(), 11, ErrorCode.USER_MOBILE_INVALID);
        Matcher m = MOBILE_PATTERN.matcher(mobile);

        if (!m.matches()) {
            throw new CheckException(ErrorCode.USER_MOBILE_INVALID);
        }
    }

    public static boolean checkIsMobile(String mobile) {
        if (mobile != null && !"".equals(mobile)) {
            Matcher m = MOBILE_PATTERN.matcher(mobile);
            if (!m.matches()) return true;
        }
        return false;
    }

    /**
     * 检查两个值相等，如果不相等则抛出异常
     *
     * @param expect    期望值
     * @param actual    事实的值
     * @param errorCode 错误码
     */
    public static void checkEquals(int expect, int actual, ErrorCode errorCode) {
        if (expect != actual) {
            throw new CheckException(errorCode);
        }
    }

    /**
     * 检查两个值相等，如果不相等则抛出异常
     *
     * @param expect    期望值
     * @param actual    事实值
     * @param errorCode 错误码
     * @param remark    消息备注
     */
    public static void checkEquals(int expect, int actual, ErrorCode errorCode, String remark) {
        if (expect != actual) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 检测大于等于，如果不大于等于则抛出异常
     *
     * @param actual      检测值
     * @param lowerBounds 下限
     * @param errorCode   错误码
     */
    public static void checkGreaterOrEquals(int actual, int lowerBounds, ErrorCode errorCode, String remark) {
        if (!(actual >= lowerBounds)) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 检测大于等于，如果不大于等于则抛出异常
     *
     * @param actual      检测值
     * @param lowerBounds 下限
     * @param errorCode   错误码
     */
    public static void checkGreaterOrEquals(long actual, long lowerBounds, ErrorCode errorCode, String remark) {
        if (!(actual >= lowerBounds)) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 检测大于，如果不大于则抛出异常
     *
     * @param actual      检测值
     * @param lowerBounds 下限
     * @param errorCode   错误码
     */
    public static void checkGreaterThan(int actual, int lowerBounds, ErrorCode errorCode) {
        if (!(actual > lowerBounds)) {
            throw new CheckException(errorCode);
        }
    }

    /**
     * 检测大于，如果不大于则抛出异常
     * <p>
     * int数据
     *
     * @param actual    检测值
     * @param lowBounds 下限
     * @param errorCode 错误码
     * @param remark    错误消息
     */
    public static void checkGreaterThan(int actual, int lowBounds, ErrorCode errorCode, String remark) {
        if (!(actual > lowBounds)) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 检测大于，如果不大于则抛出异常
     * <p>
     * long 数据
     *
     * @param actual    检测值
     * @param lowBounds 下限
     * @param errorCode 错误码
     * @param remark    错误消息
     */
    public static void checkGreaterThan(long actual, long lowBounds, ErrorCode errorCode, String remark) {
        if (!(actual > lowBounds)) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 检测参数是否大于下界. 否则抛出参数错误异常.
     *
     * @param lowerBound       下界
     * @param parameter        参数
     * @param exceptionMessage 异常描述
     */
    public static void checkGt(int lowerBound, int parameter, String exceptionMessage) {
        if (!(parameter > lowerBound)) {
            throw new CheckException(ErrorCode.ILLEGAL_PARAMETER, exceptionMessage);
        }
    }

    public static void checkGt(long lowerBound, long parameter, ErrorCode errorCode, String remark) {
        if (!(parameter > lowerBound)) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 检测参数是否大于下界. 否则抛出参数错误异常.
     *
     * @param lowerBound       下界
     * @param parameter        参数
     * @param exceptionMessage 异常描述
     */
    public static void checkGt(long lowerBound, long parameter, String exceptionMessage) {
        if (!(parameter > lowerBound)) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }


    /**
     * 检测小于，如果不小于则抛出异常
     *
     * @param expect    检测值
     * @param actual    实际值
     * @param errorCode 错误码
     */
    public static void checkLessThan(int expect, int actual, ErrorCode errorCode) {
        if (!(expect < actual)) {
            throw new CheckException(errorCode);
        }
    }

    /**
     * 检测小于，如果不小于则抛出异常
     *
     * @param expect    检测值
     * @param actual    实际值
     * @param errorCode 错误码
     * @param remark    错误消息
     */
    public static void checkLessThan(int expect, int actual, ErrorCode errorCode, String remark) {
        if (!(expect < actual)) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 检测小于，如果不小于则抛出异常
     *
     * @param expect    检测值
     * @param actual    实际值
     * @param errorCode 错误码
     * @param remark    错误消息
     */
    public static void checkLessThanOrEquals(Number expect, Number actual, ErrorCode errorCode, String remark) {
        if (!(expect.longValue() <= actual.longValue())) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 对象转换为 JSON 字符串
     *
     * @param object 对象
     * @return json格式的字符串
     * @throws JsonProcessingException 转换异常
     */
    public static String objectToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    public static String objectToJsonWithoutException(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * JSON 字符串转换为 Pojo 对象
     * <code>
     * Hello hello = jsonToObject(jsonString, Hello.class);
     * <code/>
     *
     * @param json  json格式的字符串
     * @param clazz 对象(Pojo)的类
     * @return 转换后的对象实例
     * @throws IOException
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(json, clazz);
    }

    /**
     * JSON 字符串转换为Collection集合
     *
     * @param jsonStr         json格式的字符串
     * @param collectionClass 指定的Collection的实现类，如：ArrayList
     * @param elementClasses  自定义的pojo对象
     * @return 转换后的集合
     * @throws Exception
     */
    public static <T> T jsonToList(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        return mapper.readValue(jsonStr, javaType);
    }

    public static <T> T jsonToListWithoutException(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) {
        T result = null;
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            result = mapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Utils.checkNotNull(result, ErrorCode.ILLEGAL_PARAMETER);
        return result;
    }

    /**
     * List转换为 JSON 字符串
     *
     * @param list 集合对象
     * @return json格式的字符串
     * @throws JsonProcessingException 转换异常
     */
    public static String listToJson(List<?> list) {
        try {
            return new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Set转换为 JSON 字符串
     *
     * @param set 集合对象
     * @return json格式的字符串
     * @throws JsonProcessingException 转换异常
     */
    public static String setToJson(Set<?> set) {
        try {
            return new ObjectMapper().writeValueAsString(set);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Map转换为 JSON 字符串
     *
     * @param map 集合对象
     * @return json格式的字符串
     * @throws JsonProcessingException 转换异常
     */
    public static String mapToJson(Map<?, ?> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String listToString(List list) {
        StringBuffer sb = new StringBuffer();
        for (Object o : list) {
            sb.append(o.toString() + "、");
        }
        if (sb.length() > 0)
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static int[] listToPrimitive(List<Integer> list) {
        if (list == null) {
            return null;
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            result[i] = list.get(i).intValue();
        }
        return result;
    }

    public static String formatFingerPrintString(String src) {

        int srcLength = src.length();
        if (srcLength == 0) {
            src = "0000" + src;
        } else if (srcLength == 1) {
            src = "000" + src;
        } else if (srcLength == 2) {
            src = "00" + src;
        } else if (srcLength == 3) {
            src = "0" + src;
        } else if (srcLength >= 4) {
            src = src.substring(srcLength - 4, srcLength);
        }
        return src;
    }


    public static byte booleanToByte(boolean b) {
        return (byte) (b ? 1 : 0);
    }

    public static boolean byteToBoolean(byte b) {
        return b != 0;
    }

    /**
     * 获取最后时间，用于表示无限期限的情况
     * 最后日期是：2999-12-31T00:00:00
     *
     * @return Date
     */
    public static Date lastDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2999, Calendar.DECEMBER, 31, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取最后时间，用于表示无限期限的情况
     * 最后日期是：2999-12-31T00:00:00
     *
     * @return java.sql.Date
     */
    public static java.sql.Date lastDayForSql() {
        return new java.sql.Date(lastDay().getTime());
    }

    /**
     * 检查字符串长度
     *
     * @param value     被检查字符串
     * @param minValue  最小长度
     * @param maxValue  最大长度
     * @param errorCode 错误代码
     * @param remark    错误说明
     */
    public static void checkStringLength(String value, int minValue, int maxValue, ErrorCode errorCode, String remark) {
        if (value == null || value.length() < minValue || value.length() > maxValue) {
            throw new CheckException(errorCode, remark);
        }
    }

    /**
     * 检查字符串长度
     *
     * @param value     被检查字符串
     * @param minValue  最小长度
     * @param maxValue  最大长度
     * @param errorCode 错误代码
     * @param remark    错误说明
     */
    public static void checkTrimStringLength(String value, int minValue, int maxValue, ErrorCode errorCode, String remark) {
        if (value == null) {
            throw new CheckException(errorCode, remark);
        }
        checkStringLength(value.trim(), minValue, maxValue, errorCode, remark);
    }

    /**
     * @param m
     * @param key
     * @param isNumber
     */
    public static void checkMapIsContainer(Map<String, Object> m, String key, boolean isNumber, String remark) {
        checkNotNull(m, ErrorCode.ILLEGAL_PARAMETER, "参数集合不能为空");

        if (!m.containsKey(key)) {
            throw new ModelException(ErrorCode.ILLEGAL_PARAMETER, remark);
        }

        // 判断参数是否是一个数值类型
        if (isNumber) {
            Object o = m.get(key);
            if (!(o instanceof Number)) {
                throw new ModelException(ErrorCode.ILLEGAL_PARAMETER, key + "不是数值类型");
            }

        }
    }

    /**
     * 返回本月第一天的时间戳
     *
     * @return Timestamp
     */
    public static Timestamp firstTimestampOfThisMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        cal.set(year, month, 1, 0, 0, 0);

        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * 获取字符串是否为空
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        if (str.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取区域父级的代码
     *
     * @param code 输入代码
     * @return 区域父级代码
     */
    public static int districtParentCode(int code) {
        if (String.valueOf(code).length() < 6) return code;
        try {
            return Integer.parseInt(String.valueOf(code).substring(0, 4));
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取行业父级的代码
     *
     * @param code 输入代码
     * @return 区域父级代码
     */
    public static int industryParentCode(int code) {
        if (String.valueOf(code).length() < 4) return code;
        try {
            return Integer.parseInt(String.valueOf(code).substring(0, 2));
        } catch (Exception e) {
            return 0;
        }
    }

    public static long ifNullToZero(Long l) {
        return (l != null ? l : 0l);
    }

    public static int ifNullToZero(Integer i) {
        return i != null ? i : 0;
    }

    public static byte ifNullToZero(Byte b) {
        return b != null ? b : 0;
    }

    public static double ifNullToZero(Double d) {
        return d != null ? d : 0.0d;
    }

    public static String ifNullToTwo(String s) {
        return s != null ? s : "";
    }

    public static long ifNullToZero(Timestamp t) {
        return t != null ? t.getTime() : 0;
    }

    public static int toIntegerFromObject(Object o) {
        return o != null ? ((Number) o).intValue() : 0;
    }

    public static String replacePhone(String text) {
        Pattern patteern = Pattern.compile("1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}\\d");
        if (text != null) {
            Matcher matcher = patteern.matcher(text);
            while (matcher.find()) {
                text = text.replace(matcher.group(), matcher.group().substring(0, 7) + "****");
            }
            return text;
        }
        return text;
    }

    public static String replacePhone2(String text) {
        Pattern patteern = Pattern.compile("1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}\\d");
        if (text != null) {
            Matcher matcher = patteern.matcher(text);
            while (matcher.find()) {
                String group = matcher.group();
                if (group.length() < 11) {
                    continue;
                }
                text = text.replace(group, group.substring(0, 7) + "**" + group.substring(9));
            }
            return text;
        }
        return null;
    }

    public static String replaceMobileAndTel(String text) {
        Pattern telPattern = Pattern.compile("(0\\d{2,3}-\\d{7,8})|(0\\d{2,3}\\d{7,8})|(0\\d{2,3}/\\d{7,8})|((\\d{11}))");
        if (text != null) {
            Matcher matcher = telPattern.matcher(text);
            while (matcher.find()) {
                String group = matcher.group();

                text = text.replace(group, "*");
            }
            return text;
        }
        return text;
    }

    public static String replaceAddress(String address) {
        if (address == null || address.trim().length() == 0) {
            return address;
        }
        int length = address.length();

        String start = "";
        if (length > 4) {
            for (int i = 1; i <= length - 4; i++) {
                start += "*";
            }
            address = address.substring(0, 4) + start;
        }
        return address;
    }

    public static boolean isHavePattenPhone(String text) {
        Pattern telPattern = Pattern.compile("(0\\d{2,3}-\\d{7,8})|(0\\d{2,3}\\d{7,8})|(0\\d{2,3}/\\d{7,8})|((\\d{11}))");
        if (text != null) {
            Matcher matcher = telPattern.matcher(text);
            while (matcher.find()) {
                return true;
            }
        }
        return false;
    }

    public static int calcAge(int birthdayYear) {
        int nowYear = Calendar.getInstance().get(Calendar.YEAR);
        return nowYear - birthdayYear;
    }

    public static String hideMobile(String mobile) {
        if (mobile != null && mobile.length() >= 10) {
            return mobile.substring(0, 7) + "**" + mobile.substring(9);
        } else {
            return mobile;
        }
    }

    public static int cut(int s, int length) {
        String t = s + "";
        return t.length() >= length ? Integer.valueOf(t.substring(0, length)) : s;
    }

    public static boolean containsNotNullValue(Map<String, Object> map, String key) {
        return map.containsKey(key) && map.get(key) != null;
    }

    public static boolean containsNullValue(Map<String, Object> map, String key) {
        return map.containsKey(key) && map.get(key) == null;
    }

    public static boolean containsForceWriteString(Map<String, Object> map, String key, String error) {
        Object m = map.containsKey(key) && map.get(key) != null ? map.get(key) : null;
        if (m != null && m instanceof String && !"".equals((String) m)) {
            return true;
        }
        throw new ModelException(ErrorCode.ILLEGAL_PARAMETER, error);
    }

    public static boolean containsForceWriteNumber(Map<String, Object> map, String key, String error) {
        Object m = map.containsKey(key) && map.get(key) != null ? map.get(key) : null;
        if (m != null && m instanceof Number && ((Number) m).intValue() > 0) {
            return true;
        }
        throw new ModelException(ErrorCode.ILLEGAL_PARAMETER, error);
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static final TimeZone CSTTimeZone = TimeZone.getTimeZone("GMT");

    /**
     * 检查日期是否合规
     * <p>
     * 1.不超过今天
     * <p>
     * 2.不晚于去年同一天
     * <p>
     * 如果合规就不会抛异常，如果比今天早，就返回1，否则返回0
     */
    public static int checkDateInNowAndPast(int year, int month, int day) {
        String date = year + "" + fillZero(month) + "" + fillZero(day);
        Calendar cal = getCSTCalendar();
        int nowYear = cal.get(Calendar.YEAR);
        int nowMonth = cal.get(Calendar.MONTH) + 1; // 0代表1月，11代表12月
        int nowDay = cal.get(Calendar.DAY_OF_MONTH);
        String nowDate = nowYear + "" + fillZero(nowMonth) + "" + fillZero(nowDay);
        Utils.throwIfFalse(nowDate.compareTo(date) >= 0, ErrorCode.ILLEGAL_PARAMETER, "日期不能超过今天，当前日期=" + nowDate);
        String lastYearDate = (year - 1) + "" + month + "" + day;
        Utils.throwIfFalse(lastYearDate.compareTo(date) <= 0, ErrorCode.ILLEGAL_PARAMETER, "日期不能晚于去年的今天");
        return nowDate.compareTo(date) == 0 ? 0 : 1;
    }

    public static Calendar getCSTCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(CSTNow().getTime());
        return calendar;
    }

    /**
     * 检查日期是否合规
     * <p>
     * 1.不超过昨天
     * <p>
     * 2.不晚于去年同一天
     * <p>
     * 如果合规就不会抛异常，如果比昨天早，就返回1，否则返回0
     */
    public static int checkDateInYesterdayAndPast(int year, int month, int day) {
        String date = year + "" + fillZero(month) + "" + fillZero(day);
        Calendar cal = getCSTCalendar();
        cal.add(Calendar.DATE, -1);
        int yesterdayYear = cal.get(Calendar.YEAR);
        int yesterdayMonth = cal.get(Calendar.MONTH) + 1; // 0代表1月，11代表12月
        int yesterdayDay = cal.get(Calendar.DAY_OF_MONTH);
        String yesterdayDate = yesterdayYear + "" + fillZero(yesterdayMonth) + "" + fillZero(yesterdayDay);
        Utils.throwIfFalse(date.compareTo(yesterdayDate) <= 0, ErrorCode.ILLEGAL_PARAMETER, "日期不能超过昨天，昨天的日期=" + yesterdayDate + ",输入的日期=" + date);
        String lastYearDate = (year - 1) + "" + month + "" + day;
        Utils.throwIfFalse(lastYearDate.compareTo(date) <= 0, ErrorCode.ILLEGAL_PARAMETER, "日期不能晚于去年的昨天");
        return yesterdayDate.compareTo(date) == 0 ? 0 : 1;
    }

    /**
     * 检查日期是否合规
     * <p>
     * 1.不超过这个月
     * <p>
     * 2.不晚于去年1月
     * <p>
     * 如果合规就不会抛异常，如果比这个月早，就返回1，否则返回0
     */
    public static int checkDateInNowAndPast(int year, int month) {
        String date = year + "" + fillZero(month);
        Calendar cal = getCSTCalendar();
        int nowYear = cal.get(Calendar.YEAR);
        int nowMonth = cal.get(Calendar.MONTH) + 1; // 0代表1月，11代表12月
        String nowDate = nowYear + "" + fillZero(nowMonth);
        Utils.throwIfFalse(nowDate.compareTo(date) >= 0, ErrorCode.ILLEGAL_PARAMETER, "日期不能超过这个月，当前年月=" + nowDate);
        String lastYearDate = (year - 1) + "" + 1;
        Utils.throwIfFalse(lastYearDate.compareTo(date) <= 0, ErrorCode.ILLEGAL_PARAMETER, "日期不能晚于去年的同一月");
        return nowDate.compareTo(date) == 0 ? 0 : 1;
    }

    public static String fillZero(int v) {
        String r = v + "";
        if (r.length() >= 2) {
            return r;
        } else {
            return "0" + r;
        }
    }

    // 某年某月的最后一天
    public static int getLastDayOfMonth(int year, int month) {
        Calendar a = getCSTCalendar();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    // 某年某月的最后一天，不超过昨天
    public static int getLastDayOfMonthNotOverYesterday(int year, int month) {
        Calendar a = getCSTCalendar();
        int today = a.get(Calendar.DATE);
        if (today == 1) {
            return 0;
        }
        a.roll(Calendar.DATE, -1);
        int YesterdayYear = a.get(Calendar.YEAR);
        int YesterdayMonth = a.get(Calendar.MONTH) + 1; // 0代表1月，11代表12月
        if (year < YesterdayYear || (year < YesterdayYear && month < YesterdayMonth)) {
            return getLastDayOfMonth(year, month);
        }
        return a.get(Calendar.DATE);
    }

    public static Map<String, Object> stringToMap(String result) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        // convert JSON string to Map
        map = mapper.readValue(result, new TypeReference<Map<String, Object>>() {
        });
        return map;
    }

    public static void checkIsTelephone(String telephone, ErrorCode errorCode, String errormsg) {
        if (telephone == null || telephone.isEmpty()) {
            throw new CheckException(errorCode, errormsg);
        }

    }

    public static void putIfNotNull(String key, Object value, Map<String, Object> map) {
        if (key != null && value != null && map != null) {
            map.put(key, value);
        }
    }

    public static void checkIn(Collection<String> actual, Collection<String> expect, ErrorCode errorCode, String errormsg) {
        if (actual == null || !expect.containsAll(actual)) {
            throw new CheckException(errorCode, errormsg);
        }
    }

    public static long TimestampToLong(Timestamp t){
        return t != null ? t.getTime() : 0;
    }

    public static long DateToLong(Date d){
        return d != null ? d.getTime() : 0;
    }

    public static int BigDecimalToInt(BigDecimal bd){
        return bd != null ? bd.intValue() : 0;
    }
}
