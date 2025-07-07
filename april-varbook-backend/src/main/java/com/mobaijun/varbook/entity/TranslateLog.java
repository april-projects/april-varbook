package com.mobaijun.varbook.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Description: [翻译内容记录表实体对象]
 * Author: [mobaijun]
 * Date: [2025-06-27 13:54:59]
 * IntelliJ IDEA Version:[IntelliJ IDEA 2024.1]
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "translate_log")
@Schema(title = "翻译内容记录表", description = "翻译内容记录表实体对象")
public class TranslateLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID，自动生成。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Schema(title = "主键ID")
    private Long id;

    /**
     * 原始输入文本。
     */
    @Column(name = "text_raw")
    @Schema(title = "原始输入文本")
    private String textRaw;

    /**
     * 请求 IP。
     */
    @Column(name = "ip")
    @Schema(title = "请求 IP")
    private String ip;

    /**
     * User-Agent 设备信息。
     */
    @Column(name = "user_agent")
    @Schema(title = "User-Agent 设备信息")
    private String userAgent;

    /**
     * 翻译结果。
     */
    @Column(name = "translate")
    @Schema(title = "翻译结果")
    private String translate;

    /**
     * 响应结果
     */
    @Column(name = "response_data")
    @Schema(title = "接口响应内容")
    private String responseData;

    /**
     * 接口响应耗时（毫秒）。
     */
    @Column(name = "response_time")
    @Schema(title = "接口响应耗时(ms)")
    private Long responseTime;

    /**
     * 是否成功：1=成功，0=失败。
     */
    @Column(name = "success")
    @Schema(title = "是否成功：1=成功，0=失败")
    private Integer success;

    /**
     * 错误信息（失败时记录）。
     */
    @Column(name = "error_msg")
    @Schema(title = "错误信息（失败时记录）")
    private String errorMsg;
}