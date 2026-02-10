package com.chitkara.bfhl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BfhlController {

    private static final String OFFICIAL_EMAIL = "aaryan1010.be23@chitkara.edu.in";

    @PostMapping("/bfhl")
    public ResponseEntity<Map<String, Object>> handleBfhl(
            @RequestBody(required = false) Map<String, Object> body) {

        Map<String, Object> response = new HashMap<>();
        response.put("official_email", OFFICIAL_EMAIL);

        
        if (body == null || body.isEmpty()) {
            response.put("is_success", false);
            response.put("error", "Request body is empty");
            return ResponseEntity.badRequest().body(response);
        }

        if (body.size() != 1) {
            response.put("is_success", false);
            response.put("error", "Exactly one key must be provided");
            return ResponseEntity.badRequest().body(response);
        }

        String key = body.keySet().iterator().next();
        Object value = body.get(key);

        switch (key) {

            case "fibonacci":
                if (!(value instanceof Integer)) return bad(response, "Fibonacci value must be an integer");
                int n = (Integer) value;
                if (n < 0) return bad(response, "Fibonacci value must be non-negative");

                response.put("is_success", true);
                response.put("fibonacci", generateFibonacci(n));
                return ResponseEntity.ok(response);

            case "prime":
                if (!(value instanceof List<?>)) return bad(response, "Prime value must be a list");

                List<Integer> primes = new ArrayList<>();
                for (Object o : (List<?>) value) {
                    if (o instanceof Integer && isPrime((Integer) o)) {
                        primes.add((Integer) o);
                    }
                }

                response.put("is_success", true);
                response.put("prime", primes);
                return ResponseEntity.ok(response);

            case "odd":
                if (!(value instanceof List<?>)) return bad(response, "Odd value must be a list");

                List<Integer> odds = new ArrayList<>();
                for (Object o : (List<?>) value) {
                    if (o instanceof Integer && ((Integer) o) % 2 != 0) {
                        odds.add((Integer) o);
                    }
                }

                response.put("is_success", true);
                response.put("odd", odds);
                return ResponseEntity.ok(response);

            case "even":
                if (!(value instanceof List<?>)) return bad(response, "Even value must be a list");

                List<Integer> evens = new ArrayList<>();
                for (Object o : (List<?>) value) {
                    if (o instanceof Integer && ((Integer) o) % 2 == 0) {
                        evens.add((Integer) o);
                    }
                }

                response.put("is_success", true);
                response.put("even", evens);
                return ResponseEntity.ok(response);

            case "alphabet":
                if (!(value instanceof List<?>)) return bad(response, "Alphabet value must be a list");

                List<String> alphabets = new ArrayList<>();
                for (Object o : (List<?>) value) {
                    if (o instanceof String && ((String) o).matches("[a-zA-Z]")) {
                        alphabets.add(((String) o).toUpperCase());
                    }
                }

                response.put("is_success", true);
                response.put("alphabet", alphabets);
                return ResponseEntity.ok(response);

            default:
                return bad(response, "Unsupported operation");
        }
    }

    
    private ResponseEntity<Map<String, Object>> bad(Map<String, Object> res, String msg) {
        res.put("is_success", false);
        res.put("error", msg);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    private int[] generateFibonacci(int n) {
        if (n == 0) return new int[]{};
        if (n == 1) return new int[]{0};

        int[] fib = new int[n];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i < n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
