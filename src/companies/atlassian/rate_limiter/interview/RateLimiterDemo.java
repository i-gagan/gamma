package companies.atlassian.rate_limiter.interview;

import java.util.ArrayDeque;
import java.util.Deque;

class Rule {
    int noOfRequest;
    long timeWindowInMs;

    Rule(int noOfRequest, long timeWindowInSec) {
        this.noOfRequest = noOfRequest;
        this.timeWindowInMs = timeWindowInSec * 1000;
    }
}

class Customer implements RateLimiter {
    String customerId;
    Deque<Long> timeStampQueue;
    Rule rule;

    Customer(String customerId, Rule rule) {
        this.customerId = customerId;
        this.rule = rule;
        this.timeStampQueue = new ArrayDeque<>();
    }

    public boolean sendRequest() {
        return rateLimit(this.customerId);
    }

    @Override
    public boolean rateLimit(String customerId) {

        long currentTimeStamp = System.currentTimeMillis();

        while (!timeStampQueue.isEmpty() && timeStampQueue.getFirst() < currentTimeStamp - rule.timeWindowInMs) { // Delete Old Time Stamps - Out of Window
            timeStampQueue.pollFirst();
        }

        if (rule.noOfRequest == timeStampQueue.size()) {
            return false;
        } else {
            timeStampQueue.addLast(currentTimeStamp);
            return true;
        }
    }
}

interface RateLimiter {

    // Each customer can make X requests per Y seconds
    boolean rateLimit(String customerId);

}

public class RateLimiterDemo {
    public static void main(String[] args) {
        System.out.println("RateLimiterDemo");

        Rule rule1 = new Rule(3, 60);
        Customer customer1 = new Customer("Customer1", rule1);

        boolean isSuccess = customer1.sendRequest();

        System.out.println("Customer 1 Request " + isSuccess);
    }
}

//
//User -
//X , Y vary for each customer
//100 R/ M

//1sec
//10 re

//CurrentTimeStamp
//Scale - Not in Scope



//     1. Token Bucket
//     2. Leaky Bucket -
//     3. Sliding Window
//     3. TimeStamp Sliding Windo


// 3 R / 3 Min
// // User -
// // 12 Hr Window - timestamp
//     //Deque // TimeStampQueue  - 1, 2, 3, .    ....  1000.  1002 O (Y)


//     To Process a request
//     1. If it is falling in the window
//         //

//     2. if it is not
//         Discard - Note time stamp

//     1. I am not maininting for each minute -> each request
//     while ()

