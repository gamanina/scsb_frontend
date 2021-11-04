package com.src.scsb.util;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IdUtil {

    static final SecureRandom RANDOM = new SecureRandom();

    /**
     * @return
     */
    private static long getLongTime() {
        return System.currentTimeMillis() - 1325347200000L;
    }

    /**
     * @return
     */
    private static String getTime() {
        return Long.toString(System.currentTimeMillis() - 1325347200000L);
    }

    /**
     * @return
     */
    private static String getHexTime() {
        return Long.toHexString(System.currentTimeMillis() - 1325347200000L);
    }

    /**
     * @return
     */
    public static long longid() {
        return getLongTime() * 10000 + RANDOM.nextInt(10000);
    }

    /**
     * @return
     */
    public static String randomId() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String prefix = df.format(Calendar.getInstance().getTime());
        String random = getHexTime()
                + Integer.toHexString(RANDOM.nextInt(100000));
        return prefix + random;
    }

    /**
     * @return
     */
    public static String id20() {
        String toFill = "00000000000000000000";
        String systemTime = getHexTime();
        String random = Integer.toHexString(RANDOM.nextInt(500000));
        String id = systemTime
                + toFill.substring(0,
                20 - systemTime.length() - random.length()) + random;
        return id.substring(0, 20);
    }

    /**
     * @return
     */
    public static Long idLong() {
        String toFill = "000000000000000000";
        String systemTime = getTime();
        String random = Integer.toString(RANDOM.nextInt(500000));
        String id = systemTime
                + toFill.substring(0,
                18 - systemTime.length() - random.length()) + random;
        return Long.parseLong(id);
    }

    /**
     * @return
     */
    public static String idNumber20() {
        String toFill = "00000000000000000000";
        String systemTime = getTime();
        String random = Integer.toString(RANDOM.nextInt(500000));
        String id = systemTime
                + toFill.substring(0,
                20 - systemTime.length() - random.length()) + random;
        return id.substring(1, 20);
    }

    /**
     * @return
     */
    public static String id32() {
        String toFill = "00000000000000000000000000000000";
        String systemTime = getHexTime();
        String random = Long.toHexString(RANDOM.nextLong());
        String id = systemTime
                + toFill.substring(0,
                32 - systemTime.length() - random.length()) + random;
        return id.substring(0, 32);
    }

    /**
     * @return
     */
    public static String dateId18() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = df.format(Calendar.getInstance().getTime());
        String toFill = "000000000000000000";
        String random = Integer.toString(RANDOM.nextInt(10000));
        return time + toFill.substring(0, 18 - time.length() - random.length()) + random;
    }

	/**
	 * 取得小寫英數字亂數租合(踢除小寫l,小寫o,數字1,0)
	 * @return
	 */
	public static String getRandomString() {
        char seeds[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
                'z', '2', '3', '4', '5', '6', '7', '8', '9'};
        int strLen = 7;
        char randStr[] = new char[strLen];
        for (int i = 0; i < randStr.length; i++) {
            randStr[i] = seeds[RANDOM.nextInt(seeds.length - 1)];
        }
        String returnStr = new String(randStr);
        return returnStr;
    }

    /**
     * @return
     */
    public static String randomNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(RANDOM.nextInt(6));
        }
        return sb.toString();
    }

    /**
     * @param length
     * @return
     */
    public static String otp(int length) {
        char seeds[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        int strLen = length;
        char randStr[] = new char[strLen];
        for (int i = 0; i < strLen; i++) {
            randStr[i] = seeds[RANDOM.nextInt(seeds.length - 1)];
        }
        return new String(randStr);
    }

    /**
     * 取得訂單流水號
     * @return
     */
    public static String getRandomText() {
        DateFormat df = new SimpleDateFormat("yyyyMM");
        return df.format(Calendar.getInstance().getTime()) + otp(8);
    }
}
