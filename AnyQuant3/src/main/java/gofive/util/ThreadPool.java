package gofive.util;

/**
 * Created by coral on 16-5-8.
 */
public class ThreadPool {
    Thread[] pool = null;

    public ThreadPool(int poolSize) {
        pool = new Thread[poolSize];
    }

    public void insertTask(Runnable task) {
        boolean run = false;
        while (!run) {
            for (int i = 0; i < pool.length; i++) {
                if (pool[i] == null || !pool[i].isAlive()) {
                    pool[i] = new Thread(task);
                    pool[i].start();
                    run = true;
                    break;
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
