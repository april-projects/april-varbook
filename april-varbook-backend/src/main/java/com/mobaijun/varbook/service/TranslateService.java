package com.mobaijun.varbook.service;

import com.mobaijun.varbook.vo.TranslateVO;

/**
 * Description: [翻译接口]
 * Author: [mobaijun]
 * Date: [2025/6/27 13:52]
 * IntelliJ IDEA Version: [IntelliJ IDEA 2023.1.4]
 */
public interface TranslateService {

    /**
     * 文本翻译
     * @param word 文本
     * @return 翻译结果
     */
    TranslateVO translate(String word);

    /**
     * 统计文本字符数
     * @return 总数
     */
    Long total();
}
