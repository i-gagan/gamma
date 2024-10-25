package companies.atlassian.rate_limiter.interview;

import java.util.HashMap;
import java.util.Map;

class Rule {
    int maxRequests;
    int windowTimeMillis;

    Rule(int maxRequests, int windowTimeMillis) {
        this.maxRequests = maxRequests;
        this.windowTimeMillis = windowTimeMillis;
    }
}

class Customer {
    String customerId;
    SlidingWindowRateLimiter customerLimiter;

    public Customer(String customerId, Rule rule) {
        this.customerId = customerId;
        this.customerLimiter = new SlidingWindowRateLimiter(rule);
    }
}

class SlidingWindowRateLimiter {
    Rule rule;
    private final long[] timestamps;
    private int start;
    private int end;
    private int size;

    public SlidingWindowRateLimiter(Rule rule) {
        this.rule = rule;
        this.timestamps = new long[rule.maxRequests];
        this.start = 0;
        this.end = 0;
        this.size = 0;
    }

    public synchronized boolean handleRequest() {
        long currentTime = System.currentTimeMillis();

        while (size > 0 && currentTime - timestamps[start] > rule.windowTimeMillis) {
            start = (start + 1) % rule.maxRequests;
            size--;
        }

        if (size < rule.maxRequests) {
            timestamps[end] = currentTime;
            end = (end + 1) % rule.maxRequests;
            size++;
            return true;
        } else {
            return false;
        }
    }
}

interface RateLimiter {
    boolean rateLimit(String customerId);
}

class RateLimiterEngine implements RateLimiter {
    Map<String, Customer> customerMap;

    RateLimiterEngine() {
        customerMap = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customerMap.put(customer.customerId, customer);
    }

    @Override
    public boolean rateLimit(String customerId) {
        if (customerMap.containsKey(customerId)) {
            Customer customer = customerMap.get(customerId);
            return customer.customerLimiter.handleRequest();
        }
        return false;
    }
}

public class RateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {
        RateLimiterEngine rateLimiterEngine = new RateLimiterEngine();

        String customerId = "customer1";
        Rule rule1 = new Rule(5, 10000);
        Customer customer1 = new Customer("customer1", rule1);

        rateLimiterEngine.addCustomer(customer1);

        simulateCustomer(rateLimiterEngine, customerId);
    }

    private static void simulateCustomer(RateLimiterEngine rateLimiterEngine, String customerId) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            if (rateLimiterEngine.rateLimit(customerId)) {
                System.out.println("Request " + i + " for " + customerId + " allowed");
            } else {
                System.out.println("Request " + i + " for " + customerId + " limited");
            }
            Thread.sleep(1000);
        }
    }
}

