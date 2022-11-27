package com.mkyong.java19.jep428;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JEP428 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        JEP428 obj = new JEP428();
        obj.handleUnStructureAPI();
    }

    Response handleUnStructureAPI() throws ExecutionException, InterruptedException {
        try (var executor = Executors.newFixedThreadPool(10)) {
            Future<String> user = executor.submit(this::findUser);
            Future<Integer> order = executor.submit(this::fetchOrder);
            String theUser = user.get();   // Join findUser
            int theOrder = order.get();  // Join fetchOrder
            return new Response(theUser, theOrder);
        }
    }

    /*Response handleStructureAPI() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> user = scope.fork(this::findUser);
            Future<Integer> order = scope.fork(this::fetchOrder);

            scope.join();           // Join both forks
            scope.throwIfFailed();  // ... and propagate errors

            // Here, both forks have succeeded, so compose their results
            return new Response(user.resultNow(), order.resultNow());
        }
    }*/

    private String findUser() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(1));
        return "mkyong";
    }

    private Integer fetchOrder() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(1));
        return 1;
    }

    record Response(String x, int y) {
    }
}
