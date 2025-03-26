package org.yqj.source.ai.toolcalling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 10126730
 * Date: 2025/3/24 16:11
 * Description:
 */
@Slf4j
public class DateTimeTools {

    /**
     * 获取日期
     * @return
     */
    @Tool(description = "获取当前用户所在时区的日期时间")
    String getCurrentDateTime() {
        log.info("获取当前用户所在时区的日期时间");
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }

    /**
     * action 执行操作
     * @param time
     */
    @Tool(description = "给用户设置一个给定日期的闹钟, 参数日期以ISO-8601格式提供")
    void setAlarm(String time) {
        LocalDateTime alarmTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("用户准备设置闹钟" + alarmTime);
    }
}
