package jdk.jdk8.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Package: jdk.jdk8.time<br>
 * @ClassName: DateTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class DateTest {

  public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
  public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
  public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
  public static final String DEFAULT_UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss Z";
  public static final String DEFAULT_UTC_SSS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS Z";

  private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter
      .ofPattern(DEFAULT_DATETIME_PATTERN);
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
      .ofPattern(DEFAULT_DATE_PATTERN);
  private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter
      .ofPattern(DEFAULT_TIME_PATTERN);
  private static final DateTimeFormatter PARSER_YYYY_MM_DDTHH_MM_SSZ = DateTimeFormatter
      .ofPattern(DEFAULT_UTC_PATTERN);
  private static final DateTimeFormatter PARSER_YYYY_MM_DDTHH_MM_SS_SSSZ = DateTimeFormatter
      .ofPattern(DEFAULT_UTC_SSS_PATTERN);

  // 传入时间戳，应该根据当前时区，转换为当前的字符串

  /**
   * 格式化日期与时间 yyyy-MM-dd HH:mm:ss
   * 默认时区
   */
  public static String formatDatetime(long timestamp) {
    return formatDatetime(timestamp, ZoneId.systemDefault());
  }

  public static String formatDatetime(long timestamp, ZoneId zoneId) {
    return DATETIME_FORMAT
        .format(ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId));
  }
  public static LocalDateTime parseDateTime(String dateTimeStr) {
    return DATETIME_FORMAT.parse(dateTimeStr, LocalDateTime::from);
  }

  /**
   * 格式化日期 yyyy-MM-dd
   * 默认时区
   */
  public static String formatDate(long timestamp) {
    return formatDate(timestamp, ZoneId.systemDefault());
  }

  public static String formatDate(long timestamp, ZoneId zoneId) {
    return DATE_FORMAT
        .format(ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId));
  }

  /**
   * 格式化时间 HH:mm:ss
   * 默认时区
   */
  public static String formatTime(long timestamp) {
    return formatTime(timestamp, ZoneId.systemDefault());
  }

  public static String formatTime(long timestamp, ZoneId zoneId) {
    return TIME_FORMAT
        .format(ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId));
  }


  /**
   * 格式为UTC格式 yyyy-MM-dd'T'HH:mm:ss Z
   * @param timestamp 时间戳
   * @return 字符串
   */
  public static String formatYMDTHMSZ(long timestamp) {
    return formatYMDTHMSZ(timestamp, ZoneId.systemDefault());
  }

  public static String formatYMDTHMSZ(long timestamp, ZoneId zoneId) {
    return PARSER_YYYY_MM_DDTHH_MM_SSZ
        .format(ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId));
  }

  /**
   * 转换带时区的字符串为java对象
   * @param dateTimeStr yyyy-MM-dd'T'HH:mm:ss Z
   * @return
   */
  public static ZonedDateTime parseYMDTHMSZ(String dateTimeStr) {
    return PARSER_YYYY_MM_DDTHH_MM_SSZ.parse(dateTimeStr, ZonedDateTime::from);
  }



  /**
   * 格式为UTC 带纳秒格式 yyyy-MM-dd'T'HH:mm:ss:SSS Z
   * @param timestamp 时间戳
   * @return 字符串
   */
  public static String formatYMDTHMSSSZ(long timestamp) {
    return formatYMDTHMSSSZ(timestamp, ZoneId.systemDefault());
  }

  public static String formatYMDTHMSSSZ(long timestamp, ZoneId zoneId) {
    return PARSER_YYYY_MM_DDTHH_MM_SS_SSSZ
        .format(ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId));
  }

  /**
   * 转换带时区的字符串为java对象
   * @param dateTimeStr yyyy-MM-dd'T'HH:mm:ss:SSS Z
   * @return
   */
  public static ZonedDateTime parseYMDTHMSSSZ(String dateTimeStr) {
    return PARSER_YYYY_MM_DDTHH_MM_SS_SSSZ.parse(dateTimeStr, ZonedDateTime::from);
  }

  public static void main(String[] args) {
    System.out.println(DateTest.formatDatetime(0));
    System.out.println(DateTest.formatDatetime(0, ZoneId.of("GMT")));
    System.out.println(DateTest.formatYMDTHMSSSZ(0));
    System.out.println(DateTest.parseDateTime("2019-12-24 14:28:06"));
    System.out.println(DateTest.parseYMDTHMSSSZ("2019-12-24T14:28:06.001 +0900"));
  }



}
