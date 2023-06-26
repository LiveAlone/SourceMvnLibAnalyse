package org.yqj.source.pattern.balking;

/**
 * Created by yaoqijun on 2017-06-09.
 */
public class WashingMachine {

    private WashingMachineState washingMachineState;

    public WashingMachine(){
        washingMachineState = WashingMachineState.ENABLED;
    }

    public WashingMachineState getWashingMachineState() {
        return washingMachineState;
    }

    public void wash(){
        synchronized (this) {
            System.out.println((Thread.currentThread().getName() + ": Actual machine state: " + getWashingMachineState()));
            if (washingMachineState == WashingMachineState.WASHING) {
                System.out.println(("ERROR: Cannot wash if the machine has been already washing!"));
                return;
            }
            washingMachineState = WashingMachineState.WASHING;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        endOfWashing();
    }

    public synchronized void endOfWashing() {
        washingMachineState = WashingMachineState.ENABLED;
        System.out.println(("Washing completed." + Thread.currentThread().getId()));
    }

}
