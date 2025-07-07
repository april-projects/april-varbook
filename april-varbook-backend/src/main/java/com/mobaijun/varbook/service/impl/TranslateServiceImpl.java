package com.mobaijun.varbook.service.impl;

import com.mobaijun.varbook.dto.XiaoNiuTranslateResponse;
import com.mobaijun.varbook.mapper.TranslateLogRepository;
import com.mobaijun.varbook.service.TranslateService;
import com.mobaijun.varbook.util.TranslateUtil;
import com.mobaijun.varbook.vo.TranslateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description: [翻译服务]
 * Author: [mobaijun]
 * Date: [2025/6/27 13:52]
 * IntelliJ IDEA Version: [IntelliJ IDEA 2023.1.4]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TranslateServiceImpl implements TranslateService {

    private final TranslateLogRepository translateLogRepository;

    @Override
    public TranslateVO translate(String word) {
        XiaoNiuTranslateResponse translateResponse = TranslateUtil.translate(word);
        if (translateResponse.getTgt_text().isBlank()) {
            return new TranslateVO("", "", "", "", "", "");
        }
        String tgtText = translateResponse.getTgt_text();
        return new TranslateVO(TranslateUtil.toCamelCase(tgtText),
                TranslateUtil.toCamelCase(tgtText),
                TranslateUtil.toSnakeCase(tgtText),
                TranslateUtil.toUpperSnakeCase(tgtText),
                TranslateUtil.toKebabCase(tgtText), word);
    }

    @Override
    public Long total() {
        // 统计 TranslateLog 列表中所有非空 TextRaw 字符串的总长度。
        return translateLogRepository.sumTextRawLength();
    }
}
