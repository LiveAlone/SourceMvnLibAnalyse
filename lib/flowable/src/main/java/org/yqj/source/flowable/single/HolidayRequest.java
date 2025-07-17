package org.yqj.source.flowable.single;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 10126730
 * Date: 2025/6/26 20:25
 * Description: single demo instance
 */
@Slf4j
public class HolidayRequest {
    public static void main(String[] args) {
        log.info("HolidayRequest main method start...");
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();

        // 通过xml 导入Definition, 通过deptId 获取Definition
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")
                .deploy();
        log.info("deployment id: {}", deployment.getId());

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());

        // 1 通过定义开始执行流程
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", "姚启俊");
        variables.put("nrOfHolidays", 15);
        variables.put("description", "休假请求描述信息");
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("holidayRequest", variables);
        log.info("Started process instance with id: {} name:{}", processInstance.getId(), processInstance.getBusinessKey());

        // 2. 作为Manager 查询需要审批任务列表
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        System.out.println("You have " + tasks.size() + " tasks:");
        tasks.forEach(task -> {
            System.out.println(task.getId() + ": " + task.getName() + " ("
                    + task.getAssignee() + ")");
        });

        Task task = tasks.get(0);
        boolean approved;

//        // 3. 场景1 审批通过任务
        approved = true;
        variables = new HashMap<>();
        variables.put("approved", approved);
        taskService.complete(task.getId(), variables);
        System.out.println("step 3 finish 审批通过");

        // 4. 审批通过以后, 需要申请人确认
        tasks = taskService.createTaskQuery().taskAssignee("姚启俊").list();
        tasks.forEach(t -> {
            System.out.println("Task for employee: " + t.getName());
        });
        taskService.complete(tasks.get(0).getId(), Collections.emptyMap());
        System.out.println("step 4 finish 申请人确认");


        // 4.查询历史记录 展示列表
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> activities =
                historyService.createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstance.getId())
                        .finished()
                        .orderByHistoricActivityInstanceEndTime().asc()
                        .list();
        for (HistoricActivityInstance activity : activities) {
            System.out.println(activity.getActivityId() + " Name: " + activity.getActivityName() +  " took "
                    + activity.getDurationInMillis() + " milliseconds");
        }
    }
}
