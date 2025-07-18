package org.yqj.source.flowable;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        List<ProcessDefinition> definitions =
                repositoryService.createProcessDefinitionQuery().list();
        definitions.forEach(definition -> {
            log.info("Process Definition ID: {}, Name: {}, Key: {}",
                    definition.getId(), definition.getName(), definition.getKey());
        });
    }
}
