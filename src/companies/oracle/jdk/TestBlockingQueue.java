package companies.oracle.jdk;

import java.util.LinkedList;
import java.util.Queue;

public class TestBlockingQueue {
    BlockingQueue<Integer> blockingQueue;

    TestBlockingQueue() {
        Queue<Integer> queue = new LinkedList<>();
        blockingQueue = new BlockingQueue<>(queue);
    }

    public static void main(String[] args) throws InterruptedException {
        TestBlockingQueue testBlockingQueue = new TestBlockingQueue();
        //testBlockingQueue.runTestCase1();
        testBlockingQueue.runTestCase2();
    }

    public void runTestCase1() throws InterruptedException {
        Thread threadProducer = new Thread(new Runnable() {
            int counter = 1;

            @Override
            public void run() {
                blockingQueue.push(counter);
                System.out.println("ProducerThread produced " + counter);
                counter++;
            }
        });

        Thread threadConsumer = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer val = blockingQueue.pull();
                System.out.println("ConsumerThread consumed " + val);
            }
        });

        threadConsumer.start();
        Thread.sleep(1000);
        threadProducer.start();
    }

    public void runTestCase2() throws InterruptedException {
        Thread threadProducer = new Thread(new Runnable() {
            int counter = 1;

            @Override
            public void run() {
                while (true) {
                    blockingQueue.push(counter);
                    System.out.println("ProducerThread produced " + counter);
                    counter++;
                }
            }
        });

        Thread threadConsumer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer val = blockingQueue.pull();
                System.out.println("ConsumerThread1 consumed " + val);
            }
        });

        Thread threadConsumer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Integer val = blockingQueue.pull();
                    System.out.println("ConsumerThread2 consumed " + val);
                }
            }
        });

        threadConsumer1.start();
        threadConsumer2.start();

        Thread.sleep(1000);
        threadProducer.start();
    }
}
