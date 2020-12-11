package com.barrett.util.thread;

/**
 * 超时任务
 * <p>
 *     超时任务是指在指定的时间范围内执行，如果超时后就退出
 *     超时机制由 Task.timeout 指定，TimeoutTask 负责任务的执行，尽管超时时间到了，也会等当前 handleUnit 执行完成后退出
 * </p>
 * @author fishcat
 * @date 2018/11/13 10:48 AM
 */
public abstract class TimeoutTask implements Runnable {

    /**
     * 处理单元任务
     */
    public abstract void handleUnit();

    @Override
    public void run() {
        while (true) {
            if (!Thread.currentThread().isInterrupted()) {
                // 中断后跳出
                handleUnit();
                break;
            }
        }
    }
}
