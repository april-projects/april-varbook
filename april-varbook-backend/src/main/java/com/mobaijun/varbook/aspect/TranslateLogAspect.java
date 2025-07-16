package com.mobaijun.varbook.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.mobaijun.common.result.R;
import com.mobaijun.varbook.entity.TranslateLog;
import com.mobaijun.varbook.mapper.TranslateLogRepository;
import com.mobaijun.varbook.vo.TranslateVO;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 翻译日志记录的 AOP 切面。
 * 拦截标注了 @LogTranslate 的方法，记录请求信息、执行时间和结果。
 *
 * @author lijing
 * @since 2025-06-27
 */
@Aspect
@Component
public class TranslateLogAspect {

    private final TranslateLogRepository translateLogRepository;

    public TranslateLogAspect(TranslateLogRepository translateLogRepository) {
        this.translateLogRepository = translateLogRepository;
    }

    /**
     * 环绕通知，拦截 @LogTranslate 注解的方法，记录日志。
     *
     * @param joinPoint 切点
     * @return 方法执行结果
     * @throws Throwable 如果方法执行抛出异常
     */
    @Around("@annotation(com.mobaijun.varbook.annotation.LogTranslate)")
    public Object logTranslate(ProceedingJoinPoint joinPoint) throws Throwable {
        TranslateLog log = new TranslateLog();
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            // 获取请求信息
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                log.setIp(getClientIp(request));
                log.setUserAgent(request.getHeader("User-Agent"));
            }
            // 设置输入文本
            Object[] args = joinPoint.getArgs();
            if (args.length > 0 && args[0] instanceof String textRaw) {
                log.setTextRaw(textRaw);
            }
            // 执行目标方法
            result = joinPoint.proceed();

            // 设置翻译结果
            if (result instanceof com.mobaijun.common.result.R<?> r && r.getData() instanceof com.mobaijun.varbook.vo.TranslateVO vo) {
                log.setTranslate(vo.getSmallHump());
                R<TranslateVO> full = R.ok(vo);
                log.setResponseData(JSONUtil.toJsonStr(full));

            } else {
                log.setTranslate("");
            }
            // 成功
            log.setSuccess(1);
        } catch (Throwable e) {
            // 失败
            log.setSuccess(0);
            // 截断错误信息
            log.setErrorMsg(StrUtil.sub(e.getMessage(), 0, 255));
            throw e;
        } finally {
            // 设置响应时间
            log.setResponseTime(System.currentTimeMillis() - startTime);
            // 保存日志
            translateLogRepository.save(log);
        }
        return result;
    }

    /**
     * 获取客户端 IP 地址。
     *
     * @param request HTTP 请求
     * @return 客户端 IP
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StrUtil.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 取第一个非 unknown 的 IP
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index).trim();
            }
            return ip;
        }
        ip = request.getHeader("X-Real-IP");
        if (StrUtil.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}