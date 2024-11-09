package companies.oracle.thread_demo;//package thread_demo;
//
//import java.util.ArrayDeque;
//import java.util.Deque;
//import java.util.Queue;
//
////In a ThreadPool the main idea is to have a pool of threads which are running for ever,
////like the run() of these threads won't complete until you stop the whole thread-pool.
////So the idea is to take the tasks one by one inside the run(),
////execute it and if no tasks remain for execution, just wait for the next task,
////but never allow the run() to finish.
//
//class BlockingQueue<MyThread> {
//    int maxSize;
//    Queue<MyThread> queue;
//
//    BlockingQueue(Queue<MyThread> queue, int maxSize) {
//        this.queue = queue;
//        this.maxSize = maxSize;
//    }
//
//    public void run() {
//        while (true) {
//            synchronized (queue) {
//                while (queue.isEmpty()) {
//                    try {
//                        queue.wait();
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                MyThread task = queue.poll();
//                if (task instanceof MyThread) {
//                    task.run();
//                }
//
//                queue.notify();
//            }
//        }
//    }
//
//    public void add(MyThread task) {
//        while (true) {
//            synchronized (queue) {
//                while (queue.size() == maxSize) {
//                    try {
//                        queue.wait();
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                queue.add(task);
//                queue.notify();
//            }
//        }
//    }
//}
//
//
//class MyThread extends Thread {
//    Runnable runnableTask;
//
//    public MyThread() {
//
//    }
//
//    public MyThread(Runnable runnable) {
//        this.runnableTask = runnable;
//        this.runnableTask.run();
//    }
//}
//
//class MyThreadPool {
//    int threadPoolSize;
//
//
//    public MyThreadPool(int threadPoolSize) {
//        this.threadPoolSize = threadPoolSize;
//
//    }
//
//    public MyThread getThread() {
//        if (threadPool.isEmpty()) {
//            return null;
//        } else {
//            MyThread myThread = (MyThread) threadPool.pollFirst();
//            myThread = new MyThread();
//            return myThread;
//        }
//    }
//
//    public void moveBackToThreadPool(MyThread thread) {
//        threadPool.addLast(thread);
//    }
//
//    public static void main(String[] args) {
//        MyThreadPool myThreadPool = new MyThreadPool(5);
//
//        for (int i = 0; i < 6; i++) {
//            final int k = i + 1;
//            Runnable runnableTask = new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("Running Task I " + (k));
//                }
//            };
//            MyThread myThread = myThreadPool.getThread();
//            if (myThread != null) {
//                System.out.println("Thread is available from Thread Pool");
//                myThread.runnableTask =
//                myThread.start();
//                //myThreadPool.moveBackToThreadPool(myThread);
//            } else {
//                System.out.println("No Thread is available from Thread Pool");
//            }
//        }
//    }
//}
