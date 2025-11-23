package org.firstinspires.ftc.teamcode.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class ActionManager {
    public boolean stop = false;
    long targetTime;
    public List<Action> tasks = new ArrayList<>();
    public List<BooleanSupplier> conditions = new ArrayList<>();
    public ActionManager(){}
    public void run(){
        while (!stop){
            for (Action a : tasks) {
                if (!a.isFinished()) {
                    a.update();
                }
                else {
                    tasks.remove(a);
                    break;
                }
            }
            for (BooleanSupplier condition : conditions) {
                if (condition.getAsBoolean()) {
                    conditions.remove(condition);
                    break;
                }
            }
        }
    }
    public void update(){
        for (Action a : tasks) {
            if (!a.isFinished()) {
                a.update();
            }
            else {
                tasks.remove(a);
                break;
            }
        }
        for (BooleanSupplier condition : conditions) {
            if (condition.getAsBoolean()) {
                conditions.remove(condition);
                break;
            }
        }
    }
    public void addAction(Action action){
        tasks.add(action);
    }
    public void addStopCondition(BooleanSupplier condition){
        conditions.add(condition);
    }
    public void runFor(long timeMillisecs, Boolean useNestedLoop){
        targetTime = timeMillisecs + System.currentTimeMillis();
        conditions.add(() -> System.currentTimeMillis() < targetTime);
        run();
    }
}