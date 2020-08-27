package com.leo.manger.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Leo.ZhangTJ
 * @time 2020/8/27
 * @function
 * @describe
 */
public class ThreadManger {

    static class IoThread {
        private static int aliveCore = Runtime.getRuntime().availableProcessors();

//        static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
//                .setNameFormat("demo-pool-%d").build();

        static final ExecutorService IO_EXECUTOR = new ThreadPoolExecutor(aliveCore, aliveCore,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(true));
    }

    public static ExecutorService getIoExecutor() {
        return IoThread.IO_EXECUTOR;
    }
}
