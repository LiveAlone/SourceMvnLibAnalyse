package org.yqj.source.orm;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 10126730
 * Date: 2024/6/11 16:06
 * Description:
 */
@RestController
public class DruidController {
    @GetMapping("/monitor/druid/stat")
    @Deprecated
    public Object druidStat(){
        // `DruidStatManagerFacade#getDataSourceStatDataList()` 方法，可以获取所有数据源的监控数据。
        // 除此之外，DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}
