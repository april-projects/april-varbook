package com.mobaijun.varbook.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 小牛翻译返回实体
 */
@Getter
@Setter
@ToString
public class XiaoNiuTranslateResponse {

    /**
     * 源语言代码。
     * 用于标识进行翻译的源语言。
     */
    private String from;

    /**
     * 目标语言代码。
     * 用于标识进行翻译的目标语言。
     */
    private String to;

    /**
     * 翻译结果。
     * 用于标识翻译结果。
     */
    private String tgt_text;

    /**
     * 待翻译的源文本。
     * 这是进行翻译的输入。
     */
    private String src_text;

    /**
     * 翻译过程中的错误代码。
     * 用于标识翻译过程中发生的特定错误。
     */
    private String error_code;

    /**
     * 翻译过程中的错误信息。
     * 对错误代码进行解释，提供关于翻译过程中发生问题的详细信息。
     */
    private String error_msg;
}
