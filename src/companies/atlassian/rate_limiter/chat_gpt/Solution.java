package companies.atlassian.rate_limiter.chat_gpt;

import java.util.concurrent.*;
import java.util.*;

class CustomerRequest {
    private final Deque<Long> requestTimestamps;

    public CustomerRequest() {
        this.requestTimestamps = new LinkedList<>();
    }

    public synchronized void addRequest(long timestamp) {
        requestTimestamps.addLast(timestamp);
    }

    public synchronized long removeOldestRequest() {
        return requestTimestamps.removeFirst();
    }

    public synchronized long getOldestRequest() {
        return requestTimestamps.getFirst();
    }

    public synchronized int requestCount() {
        return requestTimestamps.size();
    }
}

class RateLimiter {
    private final int maxRequests;
    private final long timeWindowMillis;
    private final ConcurrentHashMap<Integer, CustomerRequest> customerRequests;

    // Scalable constructor to take request limits and time window
    public RateLimiter(int maxRequests, long timeWindowSeconds) {
        this.maxRequests = maxRequests;
        this.timeWindowMillis = timeWindowSeconds * 1000;
        this.customerRequests = new ConcurrentHashMap<>();
    }

    // Rate limit method - true if request allowed, false otherwise
    public boolean rateLimit(int customerId) {
        long currentTime = System.currentTimeMillis();
        CustomerRequest customerRequest = customerRequests.computeIfAbsent(customerId, id -> new CustomerRequest());

        synchronized (customerRequest) {
            if (customerRequest.requestCount() < maxRequests) {
                customerRequest.addRequest(currentTime);
                return true;
            } else {
                long oldestRequest = customerRequest.getOldestRequest();
                if (currentTime - oldestRequest < timeWindowMillis) {
                    return false; // too many requests within the time window
                } else {
                    // Remove the oldest and allow new request
                    customerRequest.removeOldestRequest();
                    customerRequest.addRequest(currentTime);
                    return true;
                }
            }
        }
    }

    // Remove old entries that aren't needed to prevent memory issues in a real implementation.
    public void cleanUp(int customerId) {
        long currentTime = System.currentTimeMillis();
        CustomerRequest customerRequest = customerRequests.get(customerId);
        if (customerRequest != null) {
            synchronized (customerRequest) {
                while (customerRequest.requestCount() > 0 && (currentTime - customerRequest.getOldestRequest()) > timeWindowMillis) {
                    customerRequest.removeOldestRequest();
                }
            }
        }
    }
}

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(5, 10); // 5 requests per 10 seconds

        int customerId = 123;

        // Simulating requests
        for (int i = 0; i < 10; i++) {
            boolean allowed = rateLimiter.rateLimit(customerId);
            System.out.println("Request " + (i + 1) + " allowed: " + allowed);
            Thread.sleep(1000); // Sleep 1 second between requests
        }
    }
}
