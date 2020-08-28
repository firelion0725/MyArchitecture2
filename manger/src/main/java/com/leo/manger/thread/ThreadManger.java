package com.leo.manger.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Leo.ZhangTJ
 * @time 2020/8/27
 * @function
 * @describe
 */
public class ThreadManger {

    static class IoThread {
        private static int aliveCore = Runtime.getRuntime().availableProcessors();

        static ThreadFactory ioNamedThreadFactory = new DefaultThreadFactory("my io thread");
        static ThreadFactory cpuNamedThreadFactory = new DefaultThreadFactory("my cpu thread");

        static final ExecutorService IO_EXECUTOR = new ThreadPoolExecutor(2 * aliveCore, 2 * aliveCore,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(true), ioNamedThreadFactory);

        static final ExecutorService CPU_EXECUTOR = new ThreadPoolExecutor(aliveCore + 1, aliveCore + 1,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(true), cpuNamedThreadFactory);
    }

    public static ExecutorService getIoExecutor() {
        return IoThread.IO_EXECUTOR;
    }
    public static ExecutorService getCpuExecutor() {
        return IoThread.CPU_EXECUTOR;
    }

    /**
     * The default thread factory.
     */
    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = name + "-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
