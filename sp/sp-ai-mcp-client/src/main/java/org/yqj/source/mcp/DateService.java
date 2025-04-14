package org.yqj.source.mcp;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

/**
 * @author 10126730
 * Date: 2025/4/11 17:14
 * Description:
 */
@Service
public class DateService {

    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Tool(description = "获取当前时间，以yyyy-MM-dd HH:mm:ss的格式返回")
    public String getCurrentDate() {
        return DateTime.now().toString(formatter);
    }
}
