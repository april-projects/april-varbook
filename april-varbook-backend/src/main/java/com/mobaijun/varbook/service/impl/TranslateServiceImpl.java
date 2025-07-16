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
            return new TranslateVO("", "", "", "", "", "", new String[]{});
        }
        String tgtText = translateResponse.getTgt_text();
        String bigHump = TranslateUtil.toUpperCamelCase(tgtText);
        String[] commonNames = new String[]{
                // Java
                bigHump + "Controller",
                bigHump + "Service",
                bigHump + "ServiceImpl",
                bigHump + "Repository",
                bigHump + "Mapper",
                bigHump + "Entity",
                bigHump + "VO",
                bigHump + "DTO",
                bigHump + "QO",
                bigHump + "BO",
                bigHump + "PO",
                bigHump + "DO",
                bigHump + "Enum",
                bigHump + "Util",
                bigHump + "Config",
                bigHump + "Exception",
                bigHump + "Aspect",
                bigHump + "Mapper.xml",
                bigHump + ".json",
                bigHump + "Task",
                bigHump + "Constant"
        };
        return new TranslateVO(TranslateUtil.toCamelCase(tgtText),
                bigHump,
                TranslateUtil.toSnakeCase(tgtText),
                TranslateUtil.toUpperSnakeCase(tgtText),
                TranslateUtil.toKebabCase(tgtText), word, commonNames);
    }

    @Override
    public Long total() {
        // 统计 TranslateLog 列表中所有非空 TextRaw 字符串的总长度。
        return translateLogRepository.sumTextRawLength();
    }
}
