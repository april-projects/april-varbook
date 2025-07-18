package com.mobaijun.varbook.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: [常量]
 * Author: [mobaijun]
 * Date: [2025/7/16 19:08]
 * IntelliJ IDEA Version: [IntelliJ IDEA 2023.1.4]
 */
@Getter
@AllArgsConstructor
public enum CommonSuffixType {
    CONTROLLER("Controller"),
    SERVICE("Service"),
    SERVICE_IMPL("ServiceImpl"),
    REPOSITORY("Repository"),
    MAPPER("Mapper"),
    ENTITY("Entity"),
    VO("VO"),
    DTO("DTO"),
    QO("QO"),
    BO("BO"),
    PO("PO"),
    DO("DO"),
    ENUM("Enum"),
    UTIL("Util"),
    CONFIG("Config"),
    EXCEPTION("Exception"),
    ASPECT("Aspect"),
    JSON(".json"),
    TASK("Task"),
    CONSTANT("Constant"),
    API("Api"),
    HANDLER("Handler"),
    MANAGER("Manager"),
    FACTORY("Factory"),
    BUILDER("Builder"),
    CLIENT("Client"),
    SERVER("Server"),
    JOB("Job"),
    EVENT("Event"),
    LISTENER("Listener"),
    FILTER("Filter"),
    INTERCEPTOR("Interceptor"),
    SCHEDULER("Scheduler"),
    PROCESSOR("Processor"),
    PROVIDER("Provider"),
    CONSUMER("Consumer"),
    HELPER("Helper"),
    WRAPPER("Wrapper"),
    PROPERTIES("Properties"),
    CONFIGURATION("Configuration"),
    CONTEXT("Context"),
    CACHE("Cache"),
    QUEUE("Queue"),
    TASKLET("Tasklet"),
    MODULE("Module"),
    RESOURCE("Resource"),
    ROUTER("Router"),
    STORE("Store"),
    STATE("State"),
    ACTION("Action"),
    MUTATION("Mutation"),
    HOOK("Hook"),
    COMPONENT("Component"),
    LAYOUT("Layout"),
    PAGE("Page"),
    VIEW("View"),
    SERVICE_WORKER("ServiceWorker"),
    CONTEXT_PROVIDER("ContextProvider"),
    CONTEXT_CONSUMER("ContextConsumer"),
    SAGA("Saga"),
    EFFECT("Effect"),
    SELECTOR("Selector");

    private final String suffix;

    /**
     * 返回所有枚举后缀字符串数组
     */
    public static String[] getAllSuffixes() {
        return Arrays.stream(CommonSuffixType.values())
                .map(CommonSuffixType::getSuffix)
                .toArray(String[]::new);
    }
}
