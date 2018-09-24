package com.edurabroj.donape.testRules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class FakeSchedulerRule implements TestRule {
    private Scheduler fake = new Scheduler() {
        @Override
        public Scheduler.Worker createWorker() {
            return new ExecutorScheduler.ExecutorWorker(command -> command.run());
        }
    };

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setInitIoSchedulerHandler(__ -> fake);
                RxJavaPlugins.setInitComputationSchedulerHandler(__ -> fake);
                RxJavaPlugins.setInitNewThreadSchedulerHandler(__ -> fake);
                RxJavaPlugins.setInitSingleSchedulerHandler(__ -> fake);
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> fake);
                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        };
    }
}
