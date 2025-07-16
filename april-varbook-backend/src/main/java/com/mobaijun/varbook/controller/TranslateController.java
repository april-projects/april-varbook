package com.mobaijun.varbook.controller;

import com.mobaijun.common.result.R;
import com.mobaijun.varbook.annotation.LogTranslate;
import com.mobaijun.varbook.service.TranslateService;
import com.mobaijun.varbook.vo.TranslateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: [翻译控制器]
 * Author: [mobaijun]
 * Date: [2025/6/27 14:00]
 * IntelliJ IDEA Version: [IntelliJ IDEA 2023.1.4]
 */
@Slf4j
@RestController
@RequestMapping("/translate")
@Tag(name = "翻译接口", description = "翻译接口")
@RequiredArgsConstructor
public class TranslateController {

    private final TranslateService translateService;

    @LogTranslate
    @Operation(summary = "翻译接口")
    @PostMapping("/translate")
    public R<TranslateVO> translate(@RequestParam("text") String text) {
        return R.ok(translateService.translate(text));
    }

    @Operation(summary = "统计接口")
    @GetMapping("/total")
    public R<Long> total() {
        return R.ok(translateService.total());
    }
}
