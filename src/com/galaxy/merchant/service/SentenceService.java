package com.galaxy.merchant.service;

import com.galaxy.merchant.entity.Sentence;
import com.galaxy.merchant.enums.Error;
import com.galaxy.merchant.enums.SentenceType;
import com.galaxy.merchant.util.RomanNumberUtil;

import java.util.Map;
import java.util.Stack;


public class SentenceService {

    public SentenceService() {
    }

    /**
     * 根据sentenceTypes 调用不同的处理
     * 有异常会直接输出，解析错误会合并到答案里
     * todo 这块可以考虑使用策略模式+工厂来去switch
     *
     * @param sentence 句子对象
     * @return error Errorcodes
     * @see SentenceType
     */
    public String translate(Sentence sentence, Map<String, String> constantAssignments, Map<String, Float> mineralCredits) {

        String ret = "";

        switch (sentence.getSentenceType()) {
            case ASSIGNED:
                processAssignmentLine(sentence, constantAssignments);
                break;

            case CREDITS:
                processCreditsLine(sentence, constantAssignments, mineralCredits);
                break;

            case QUESTION_HOW_MUCH:
                ret = processHowMuchQuestion(sentence, constantAssignments);
                break;

            case QUESTION_HOW_MANY:
                ret = processHowManyCreditsQuestion(sentence, constantAssignments, mineralCredits);
                break;

            default:
                ret = Error.UNKNOWN.getErrorMessage();
                break;
        }

        return ret;
    }

    /**
     * 存储标识符(单词)与罗马字符的对照表
     *
     * @param sentence 一个句子
     * @throws ArrayIndexOutOfBoundsException
     */
    private void processAssignmentLine(Sentence sentence, Map<String, String> constantAssignments) {
        String[] splited = sentence.getContent().split("\\s+");

        try {
            //Add identifier and its value to the map
            constantAssignments.put(splited[0], splited[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            Error.INCORRECT_LINE_TYPE.printMessage();
            System.out.println(e.getMessage());
        }
    }

    /**
     * 存储单位矿石的价值映射表
     *
     * @param sentence String
     */

    private void processCreditsLine(Sentence sentence, Map<String, String> constantAssignments, Map<String, Float> mineralCredits) {
        try {
            //Remove the unwanted words like "is" and "credits"
            String formatted = sentence.getContent().replaceAll("(is\\s+)|([c|C]redits\\s*)", "").trim();

            //Split the remaining based on space
            String[] keys = formatted.split("\\s");

            //
            String mineral = keys[keys.length - 2];
            float value = Float.parseFloat(keys[keys.length - 1]);

            //连接罗马字符
            StringBuilder roman = new StringBuilder();
            for (int i = 0; i < keys.length - 2; i++) {
                roman.append(constantAssignments.get(keys[i]));
            }

            //转换成阿拉伯数字
            int romanNumber = RomanNumberUtil.romanToArabic(roman.toString());
            //计算单位价值
            float credit = value / romanNumber;
            mineralCredits.put(mineral, credit);
        } catch (Exception e) {
            Error.INCORRECT_LINE_TYPE.printMessage();
            System.out.println(e.getMessage());

        }
    }


    /**
     * 处理how much的问题
     *
     * @param sentence 句子
     * @throws Exception
     */
    private String processHowMuchQuestion(Sentence sentence, Map<String, String> constantAssignments) {
        try {
            //句子切割一下去掉how much
            String formatted = sentence.getContent().split("\\sis\\s")[1].trim();
            //去掉问号
            formatted = formatted.replace("?", "").trim();
            //按照空格切割成数组
            String keys[] = formatted.split("\\s+");

            StringBuilder romanResult = new StringBuilder();
            String completeResult = null;
            boolean hasError = false;

            for (String key : keys) {
                //For each identifier gets its value
                String romanValue = constantAssignments.get(key);
                if (romanValue == null) {
                    // This means that user has entered something thats not in the hash map
                    completeResult = Error.UNKNOWN.getErrorMessage();
                    hasError = true;
                    break;
                }
                romanResult.append(romanValue);
            }

            if (!hasError) {
                //System.out.println(romanResult.length()+"");
                romanResult = new StringBuilder(RomanNumberUtil.romanToArabic(romanResult.toString()) + "");
                completeResult = formatted + " is " + romanResult;
            }

            return completeResult;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Error.INCORRECT_LINE_TYPE.getErrorMessage();
        }

    }


    /**
     * 处理how many credits问题
     *
     * @param sentence
     */
    private String processHowManyCreditsQuestion(Sentence sentence, Map<String, String> constantAssignments, Map<String, Float> mineralCredits) {

        try {
            //句子切割一下去掉how much
            String formatted = sentence.getContent().split("\\sis\\s")[1].trim();
            //去掉问号
            formatted = formatted.replace("?", "").trim();
            //按照空格切割成数组
            String keys[] = formatted.split("\\s+");

            StringBuilder roman = new StringBuilder();
            String outputResult = null;
            Stack<Float> cvalues = new Stack<>();

            for (String key : keys) {
                String romanValue = constantAssignments.get(key);
                if (romanValue != null) {
                    roman.append(romanValue);
                } else {
                    Float computedValue = mineralCredits.get(key);
                    if (computedValue != null) {
                        cvalues.push(computedValue);
                    } else {
                        outputResult = Error.UNKNOWN.getErrorMessage();
                        break;
                    }
                }
            }

            if (outputResult == null) {
                float res = 1;
                for (Float cvalue : cvalues) res *= cvalue;

                int finalres = (int) res;
                if (roman.length() > 0) {
                    finalres = (int) (RomanNumberUtil.romanToArabic(roman.toString()) * res);
                }
                outputResult = formatted + " is " + finalres + " Credits";
            }
            return outputResult;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Error.INCORRECT_LINE_TYPE.getErrorMessage();
        }

    }

}
