package org.yqj.source.trans.manager;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.yqj.source.trans.config.DB1Config;
import org.yqj.source.trans.db1.PersonDB1Mapper;
import org.yqj.source.trans.db2.PersonDB2Mapper;
import org.yqj.source.trans.domain.Person;

/**
 * @author yaoqijun on 2018-03-12.
 */
@Component
@Slf4j
public class CommonManager {

    @Resource
    private PersonDB1Mapper personDB1Mapper;

    @Resource
    private PersonDB2Mapper personDB2Mapper;

    public void printPersonContent() {
        Person person = personDB1Mapper.selectById(1L);
        System.out.println("current person is " + person.toString());
    }

    @Transactional(DB1Config.DB1_TRANSACTION)
    public void updateDiffDbCondition() {
        personDB1Mapper.updatePersonScore(1L, 9D);
        personDB2Mapper.updatePersonScore(1L, 9D);


        TransactionSynchronization beforeTxCommitAdapter = new TransactionSynchronization() {
            @Override
            public void beforeCommit(boolean readOnly) {
                log.info("*********************** before commit config ***********************");
            }
        };
        TransactionSynchronizationManager.registerSynchronization(beforeTxCommitAdapter);

        TransactionSynchronization afterTxCommitAdapter = new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                log.info("*********************** after commit config ***********************");
            }
        };
        TransactionSynchronizationManager.registerSynchronization(afterTxCommitAdapter);
//        throw new IllegalStateException("");
    }
}
