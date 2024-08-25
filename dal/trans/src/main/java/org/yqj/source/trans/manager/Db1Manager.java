package org.yqj.source.trans.manager;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.yqj.source.trans.config.DB1Config;
import org.yqj.source.trans.db1.PersonDB1Mapper;

/**
 * @author yaoqijun on 2018-03-12.
 */
@Component
@Slf4j
public class Db1Manager {

    @Resource
    private PersonDB1Mapper personDB1Mapper;

    @Transactional(DB1Config.DB1_TRANSACTION)
    public void updateDiffDbCondition() {
        personDB1Mapper.updatePersonScore(1L, 7D);
    }

    @Transactional(DB1Config.DB1_TRANSACTION)
    public void updateDiffDbConditionWithCallback() {
        log.info("****************** start db ");
        personDB1Mapper.updatePersonScore(1L, 7D);
        log.info("******************* end db");
        TransactionSynchronization transactionSynchronizationAdapter = new TransactionSynchronization() {
            @Override
            public void beforeCommit(boolean readOnly) {
                log.info("************* before transaction commit ******************");
            }

            @Override
            public void beforeCompletion() {
                log.info("************* before completion transaction **************");
            }

            @Override
            public void afterCommit() {
                log.info("************** after commit *******************");
            }

            @Override
            public void afterCompletion(int status) {
                log.info("*********** after complete ******************");
            }
        };
        TransactionSynchronizationManager.registerSynchronization(transactionSynchronizationAdapter);
    }


}
