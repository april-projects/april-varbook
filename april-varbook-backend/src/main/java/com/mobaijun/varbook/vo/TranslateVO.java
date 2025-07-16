package com.mobaijun.varbook.vo;

/**
 * Description: [翻译响应对象]
 * Author: [mobaijun]
 * Date: [2025/6/27 10:38]
 * IntelliJ IDEA Version: [IntelliJ IDEA 2023.1.4]
 */
public record TranslateVO(String smallHump, String bigHump, String smallSnake, String bigSnake, String spinal,
                          String description, String[] commonNames) implements java.io.Serializable {
}
