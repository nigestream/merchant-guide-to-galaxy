package com.galaxy.merchant.util;

import com.galaxy.merchant.enums.Error;
import com.galaxy.merchant.enums.Roman;

public class RomanNumberUtil {

    //校验罗马字符的正则
    private static String romanNumberValidator = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    /**
     * 罗马字符转阿拉伯数字
     *
     * @param roman string
     * @return string
     */
    public static int romanToArabic(String roman) {
        if (!validateRomanNumber(roman)) {
            throw new RuntimeException(Error.getErrorMessage(Error.INVALID_ROMAN_STRING.getErrorCode()));
        }
        return convert(roman);
    }


    /**
     * 校验罗马字符
     *
     * @param roman String
     * @return boolean
     */
    public static boolean validateRomanNumber(String roman) {
        return roman.matches(romanNumberValidator);
    }


    private static int convert(String roman) {
        int totalDecimal = 0;
        int lastNumber = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int preNumber = Roman.getArabic(roman.charAt(i));
            totalDecimal = CheckRoman(preNumber, lastNumber, totalDecimal);
            lastNumber = preNumber;
        }

        return totalDecimal;
    }


    private static int CheckRoman(int preNumber, int postNumber, int totalDecimal) {
        if (postNumber > preNumber) {
            return totalDecimal - preNumber;
        } else {
            return totalDecimal + preNumber;
        }

    }
}
