package com.mobaijun.varbook.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Description: [翻译响应对象]
 * Author: [mobaijun]
 * Date: [2025/6/27 10:38]
 * IntelliJ IDEA Version: [IntelliJ IDEA 2023.1.4]
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "命名规范转换值对象，包含术语的各种命名形式")
public class TranslateVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 小驼峰命名法（lower camelCase）
     * 示例：userName
     */
    @Schema(
            description = "小驼峰命名法（lower camelCase）",
            example = "userName",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String smallHump;

    /**
     * 大驼峰命名法（Upper CamelCase/PascalCase）
     * 示例：UserName
     */
    @Schema(
            description = "大驼峰命名法（Upper CamelCase/PascalCase）",
            example = "UserName",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String bigHump;

    /**
     * 小蛇形命名法（lower snake_case）
     * 示例：user_name
     */
    @Schema(
            description = "小蛇形命名法（lower snake_case）",
            example = "user_name",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String smallSnake;

    /**
     * 大蛇形命名法（UPPER_SNAKE_CASE）
     * 示例：USER_NAME
     */
    @Schema(
            description = "大蛇形命名法（UPPER_SNAKE_CASE）",
            example = "USER_NAME",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String bigSnake;

    /**
     * 脊柱命名法（spinal-case/kebab-case）
     * 示例：user-name
     */
    @Schema(
            description = "脊柱命名法（spinal-case/kebab-case）",
            example = "user-name",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String spinal;

    /**
     * 术语描述说明
     */
    @Schema(
            description = "术语描述说明",
            example = "系统用户的唯一标识符",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String description;

    /**
     * 常见别名或变体名称
     */
    @Schema(
            description = "常见别名或变体名称",
            example = "[\"标识符\", \"ID\", \"名称\"]",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String[] commonNames;
}