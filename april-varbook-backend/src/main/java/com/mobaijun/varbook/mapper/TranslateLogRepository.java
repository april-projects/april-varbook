package com.mobaijun.varbook.mapper;

import com.mobaijun.varbook.entity.TranslateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 翻译日志的 JPA 仓库接口。
 *
 * @author lijing
 * @since 2024-06-29
 */
@Repository
public interface TranslateLogRepository extends JpaRepository<TranslateLog, Long> {

    /**
     * 统计非空的 textRaw 字符串数量。
     *
     * @return 非空 textRaw 字符串的总数
     */
    @Query("SELECT COUNT(t.textRaw) FROM TranslateLog t WHERE t.textRaw IS NOT NULL")
    long countNonNullTextRaw();

    /**
     * 统计所有非空 textRaw 字符串的总长度。
     *
     * @return 所有非空 textRaw 字符串的字符总数，如果没有非空 textRaw 则返回 0
     */
    @Query("SELECT COALESCE(SUM(LENGTH(t.textRaw)), 0) FROM TranslateLog t WHERE t.textRaw IS NOT NULL")
    long sumTextRawLength();
}