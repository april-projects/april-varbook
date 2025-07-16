package com.mobaijun.varbook.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mobaijun.varbook.dto.XiaoNiuTranslateResponse;
import com.mobaijun.varbook.entity.TranslateLog;
import com.mobaijun.varbook.enums.CommonSuffixType;
import com.mobaijun.varbook.mapper.TranslateLogRepository;
import com.mobaijun.varbook.service.TranslateService;
import com.mobaijun.varbook.util.TranslateUtil;
import com.mobaijun.varbook.vo.TranslateVO;
import java.util.Arrays;
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
        TranslateLog latestLog = translateLogRepository.findLatestByTextRaw(word);
        if (latestLog != null && latestLog.getResponseData() != null) {
            try {
                final JSONObject jsonObject = new JSONObject(latestLog.getResponseData());
                Object dataObj = jsonObject.get("data");
                if (dataObj != null) {
                    String data = dataObj.toString();
                    if (JSONUtil.isTypeJSON(data)) {
                        return JSONUtil.toBean(data, TranslateVO.class);
                    }
                }
            } catch (Exception e) {
                log.error("解析翻译日志失败: {}", e.getMessage());
            }
        }

        XiaoNiuTranslateResponse translateResponse = TranslateUtil.translate(word);
        if (translateResponse == null || translateResponse.getTgt_text().isBlank()) {
            return new TranslateVO("", "", "", "", "", "", new String[0]);
        }

        String tgtText = translateResponse.getTgt_text();
        String bigHump = TranslateUtil.toUpperCamelCase(tgtText);
        // 获取常量
        String[] commonNames = Arrays.stream(CommonSuffixType.getAllSuffixes())
                .map(suffix -> bigHump + suffix)
                .toArray(String[]::new);

        return new TranslateVO(
                TranslateUtil.toCamelCase(tgtText),
                bigHump,
                TranslateUtil.toSnakeCase(tgtText),
                TranslateUtil.toUpperSnakeCase(tgtText),
                TranslateUtil.toKebabCase(tgtText),
                word,
                commonNames
        );
    }

    @Override
    public Long total() {
        return translateLogRepository.sumTextRawLength();
    }
}
