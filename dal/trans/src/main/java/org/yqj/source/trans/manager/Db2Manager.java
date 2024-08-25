package org.yqj.source.trans.manager;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.yqj.source.trans.config.DB1Config;
import org.yqj.source.trans.config.DB2Config;
import org.yqj.source.trans.db2.PersonDB2Mapper;

/**
 * @author yaoqijun on 2018-03-12.
 */
@Component
@Slf4j
public class Db2Manager {

    @Resource
    private PersonDB2Mapper personDB2Mapper;

//    @Transactional(DB2Config.DB2_TRANSACTION)
    @Transactional(DB1Config.DB1_TRANSACTION)
    public void updateDiffDbCondition() {
        personDB2Mapper.updatePersonScore(1L, 99D);
        throw new IllegalStateException("illegal state exception");
    }

}
