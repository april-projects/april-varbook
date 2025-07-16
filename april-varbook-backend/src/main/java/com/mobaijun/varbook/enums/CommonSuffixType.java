package com.mobaijun.varbook.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: [常量]
 * Author: [mobaijun]
 * Date: [2025/7/16 19:08]
 * IntelliJ IDEA Version: [IntelliJ IDEA 2023.1.4]
 */
@Getter
@AllArgsConstructor
public enum CommonSuffixType {
    CONTROLLER("Controller"),
    SERVICE("Service"),
    SERVICE_IMPL("ServiceImpl"),
    REPOSITORY("Repository"),
    MAPPER("Mapper"),
    ENTITY("Entity"),
    VO("VO"),
    DTO("DTO"),
    QO("QO"),
    BO("BO"),
    PO("PO"),
    DO("DO"),
    ENUM("Enum"),
    UTIL("Util"),
    CONFIG("Config"),
    EXCEPTION("Exception"),
    ASPECT("Aspect"),
    MAPPER_XML("Mapper.xml"),
    JSON(".json"),
    TASK("Task"),
    CONSTANT("Constant");

    private final String suffix;

    /**
     * 返回所有枚举后缀字符串数组
     */
    public static String[] getAllSuffixes() {
        return Arrays.stream(CommonSuffixType.values())
                .map(CommonSuffixType::getSuffix)
                .toArray(String[]::new);
    }
}
