package initializeUMLEditor;

import java.util.concurrent.*;
import java.util.Set;
import java.util.Collections;
import javax.swing.JFrame;

/**
 * Bill Pugh Singleton 併發測試
 *
 * 使用 CountDownLatch 確保所有執行緒在同一瞬間「起跑」，
 * 驗證多執行緒環境下 Init.getInstance() 是否始終回傳同一個實例。
 */
public class InitSingletonConcurrencyTest {

    /** 併發執行緒數量 */
    private static final int THREAD_COUNT = 100;

    public static void main(String[] args) throws Exception {
        System.out.println("=== Bill Pugh Singleton 併發安全性測試 ===");
        System.out.println("執行緒數量: " + THREAD_COUNT);
        System.out.println();

        // ── 測試 1：使用 ExecutorService + CountDownLatch ──
        testWithExecutorService();

        System.out.println();

        // ── 測試 2：使用 Thread + CountDownLatch ──
        testWithThreads();

        System.out.println();
        System.out.println("=== 所有測試完成 ===");

        // 確保程式結束（因為 JFrame 可能會讓 JVM 持續運行）
        System.exit(0);
    }

    // 測試 1：使用 ExecutorService + CountDownLatch

    private static void testWithExecutorService() throws Exception {
        System.out.println("── 測試 1: ExecutorService + CountDownLatch ──");

        // 用來存放所有執行緒取得的實例（使用 thread-safe Set）
        // HashSet is not thread-safe, so we use ConcurrentHashMap
        Set<JFrame> instances = Collections.newSetFromMap(new ConcurrentHashMap<>());

        // CountDownLatch：讓所有執行緒都準備好後，同時起跑
        CountDownLatch readyLatch = new CountDownLatch(THREAD_COUNT); // 每個執行緒準備好時 countDown
        CountDownLatch startLatch = new CountDownLatch(1);            // 主執行緒發出起跑信號
        CountDownLatch doneLatch  = new CountDownLatch(THREAD_COUNT); // 每個執行緒完成時 countDown

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadId = i;
            executor.submit(() -> {
                try {
                    // 通知主執行緒：我已準備好
                    readyLatch.countDown();
                    // 等待起跑信號
                    startLatch.await();

                    // 同時呼叫 getInstance()
                    JFrame instance = Init.getInstance();
                    instances.add(instance);

                    System.out.println("  [ExecutorService] Thread-" + threadId
                            + " 取得實例: " + System.identityHashCode(instance));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        // 等待所有執行緒準備就緒
        readyLatch.await();
        System.out.println("  所有執行緒已就緒，發出起跑信號！");

        // 記錄起跑時間
        long startTime = System.nanoTime();

        // 發出起跑信號！
        startLatch.countDown();

        // 等待所有執行緒完成
        doneLatch.await();

        long elapsed = System.nanoTime() - startTime;
        System.out.println("  耗時: " + (elapsed / 1_000_000.0) + " ms");

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        printResult("ExecutorService", instances);
    }

    // 測試 2：使用原生 Thread + CountDownLatch
    private static void testWithThreads() throws Exception {
        System.out.println("── 測試 2: Thread + CountDownLatch ──");

        Set<JFrame> instances = Collections.newSetFromMap(new ConcurrentHashMap<>());

        CountDownLatch readyLatch = new CountDownLatch(THREAD_COUNT);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch  = new CountDownLatch(THREAD_COUNT);

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                try {
                    readyLatch.countDown();
                    startLatch.await();

                    JFrame instance = Init.getInstance();
                    instances.add(instance);

                    System.out.println("  [Thread] Thread-" + threadId
                            + " 取得實例: " + System.identityHashCode(instance));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneLatch.countDown();
                }
            }, "SingletonTest-Thread-" + i);
            threads[i].start();
        }

        readyLatch.await();
        System.out.println("  所有執行緒已就緒，發出起跑信號！");

        long startTime = System.nanoTime();
        startLatch.countDown();
        doneLatch.await();
        long elapsed = System.nanoTime() - startTime;

        System.out.println("  耗時: " + (elapsed / 1_000_000.0) + " ms");

        // 等待所有執行緒結束
        for (Thread t : threads) {
            t.join();
        }

        printResult("Thread", instances);
    }

    // 印出測試結果
    private static void printResult(String testName, Set<JFrame> instances) {
        System.out.println();
        System.out.println("  ┌─────────────────────────────────────");
        System.out.println("  │ [" + testName + "] 測試結果");
        System.out.println("  │ 不同實例數量: " + instances.size());

        if (instances.size() == 1) {
            JFrame theInstance = instances.iterator().next();
            System.out.println("  │ ✅ 測試通過！所有執行緒取得同一個實例");
            System.out.println("  │    實例 hashCode: " + System.identityHashCode(theInstance));
        } else {
            System.out.println("  │ ❌ 測試失敗！產生了多個不同實例");
            for (JFrame inst : instances) {
                System.out.println("  │    實例 hashCode: " + System.identityHashCode(inst));
            }
        }
        System.out.println("  └─────────────────────────────────────");
    }
}
