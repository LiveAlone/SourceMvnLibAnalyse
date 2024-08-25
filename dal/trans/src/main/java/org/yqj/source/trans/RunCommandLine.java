package org.yqj.source.trans;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yqj.source.trans.manager.CommonManager;
import org.yqj.source.trans.manager.Db1Manager;
import org.yqj.source.trans.manager.Db2Manager;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/6
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class RunCommandLine implements CommandLineRunner {

    @Resource
    private CommonManager commonManager;

    @Resource
    private Db1Manager db1Manager;

    @Resource
    private Db2Manager db2Manager;

    @Override
    public void run(String... args) throws Exception {

//        db2Manager.updateDiffDbCondition();

//        commonManager.updateDiffDbCondition();

//        db1Manager.updateDiffDbConditionWithCallback();

        commonManager.printPersonContent();

        log.info(" command info run");
    }
}
