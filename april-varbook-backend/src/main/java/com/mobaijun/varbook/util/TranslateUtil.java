package com.mobaijun.varbook.util;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONUtil;
import com.mobaijun.varbook.dto.XiaoNiuTranslateResponse;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Description: [小牛翻译工具类]
 * Author: [mobaijun]
 * Date: [2025/6/27 14:06]
 * IntelliJ IDEA Version: [IntelliJ IDEA 2023.1.4]
 */
public class TranslateUtil {

    /**
     * 翻译 API 的 URL。
     */
    private static final String API_URL = "https://api.niutrans.com/NiuTransServer/translation";

    /**
     * 翻译 API 密钥，从环境变量加载以确保安全。
     */
    private static final String API_KEY = System.getenv("NIUTRANS_API_KEY");

    /**
     * 用于分割单词的正则表达式模式。
     */
    private static final Pattern WORD_SPLITTER = Pattern.compile("\\s+");

    private TranslateUtil() {
    }

    /**
     * 使用小牛翻译 API 将文本从中文翻译为英文。
     *
     * @param text 要翻译的文本，不能为空或 null
     * @return 翻译响应对象
     * @throws IllegalArgumentException 如果输入文本为空或 null
     * @throws IllegalStateException 如果 API 密钥未配置
     * @throws cn.hutool.http.HttpException 如果 API 请求失败
     * @throws cn.hutool.json.JSONException 如果响应解析失败
     */
    public static XiaoNiuTranslateResponse translate(String text) {
        if (text.isBlank()) {
            throw new IllegalArgumentException("要翻译的文本不能为空或 null");
        }
        if (API_KEY == null) {
            throw new IllegalStateException("API 密钥未配置，请设置 NIUTRANS_API_KEY 环境变量");
        }

        Map<String, Object> params = Map.of(
                "from", "zh",
                "to", "en",
                "apikey", API_KEY,
                "src_text", text,
                "dictNo", "",
                "memoryNo", ""
        );

        try {
            String response = HttpUtil.post(API_URL, params, 5000);
            return JSONUtil.toBean(response, XiaoNiuTranslateResponse.class);
        } catch (HttpException e) {
            throw new HttpException("翻译 API 请求失败: " + e.getMessage(), e);
        } catch (JSONException e) {
            throw new JSONException("解析翻译响应失败: " + e.getMessage(), e);
        }
    }

    /**
     * 将输入字符串转换为小驼峰命名（例如，"hello world" -> "helloWorld"）。
     *
     * @param input 输入字符串，不能为 null
     * @return 小驼峰命名的字符串
     * @throws IllegalArgumentException 如果输入为 null
     */
    public static String toCamelCase(String input) {
        validateInput(input);
        StringBuilder result = new StringBuilder();
        String[] parts = WORD_SPLITTER.split(input.trim());
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i].toLowerCase();
            if (i == 0) {
                result.append(part);
            } else {
                if (!part.isEmpty()) {
                    result.append(Character.toUpperCase(part.charAt(0)))
                            .append(part.substring(1));
                }
            }
        }
        return result.toString();
    }

    /**
     * 将输入字符串转换为大驼峰命名（例如，"hello world" -> "HelloWorld"）。
     *
     * @param input 输入字符串，不能为 null
     * @return 大驼峰命名的字符串
     * @throws IllegalArgumentException 如果输入为 null
     */
    public static String toUpperCamelCase(String input) {
        String camelCase = toCamelCase(input);
        return camelCase.isEmpty() ? "" : Character.toUpperCase(camelCase.charAt(0)) + camelCase.substring(1);
    }

    /**
     * 将输入字符串转换为小蛇形命名（例如，"Hello World" -> "hello_world"）。
     *
     * @param input 输入字符串，不能为 null
     * @return 小蛇形命名的字符串
     * @throws IllegalArgumentException 如果输入为 null
     */
    public static String toSnakeCase(String input) {
        validateInput(input);
        return WORD_SPLITTER.matcher(input.trim()).replaceAll("_").toLowerCase();
    }

    /**
     * 将输入字符串转换为大蛇形命名（例如，"Hello World" -> "HELLO_WORLD"）。
     *
     * @param input 输入字符串，不能为 null
     * @return 大蛇形命名的字符串
     * @throws IllegalArgumentException 如果输入为 null
     */
    public static String toUpperSnakeCase(String input) {
        return toSnakeCase(input).toUpperCase();
    }

    /**
     * 将输入字符串转换为脊柱命名（例如，"Hello World" -> "hello-world"）。
     *
     * @param input 输入字符串，不能为 null
     * @return 脊柱命名的字符串
     * @throws IllegalArgumentException 如果输入为 null
     */
    public static String toKebabCase(String input) {
        validateInput(input);
        return WORD_SPLITTER.matcher(input.trim()).replaceAll("-").toLowerCase();
    }

    /**
     * 验证输入字符串是否为 null。
     *
     * @param input 要验证的输入字符串
     * @throws IllegalArgumentException 如果输入为 null
     */
    private static void validateInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("输入字符串不能为 null");
        }
    }

    /**
     * 测试大小写转换方法的主方法。
     *
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        String inputData = "Small Hump Data";
        System.out.println("小驼峰命名: " + toCamelCase(inputData));
        System.out.println("大驼峰命名: " + toUpperCamelCase(inputData));
        System.out.println("小蛇形命名: " + toSnakeCase(inputData));
        System.out.println("大蛇形命名: " + toUpperSnakeCase(inputData));
        System.out.println("脊柱命名: " + toKebabCase(inputData));
    }
}
