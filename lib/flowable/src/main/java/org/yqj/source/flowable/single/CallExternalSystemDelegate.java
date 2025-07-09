package org.yqj.source.flowable.single;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author 10126730
 * Date: 2025/7/8 20:43
 * Description:
 */
public class CallExternalSystemDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("Calling the external system for employee "
                + execution.getVariable("employee"));
    }
}
