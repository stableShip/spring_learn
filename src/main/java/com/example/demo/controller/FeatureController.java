package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@RestController
@EnableAutoConfiguration
@Slf4j
public class FeatureController {

    @GetMapping(value = "/mutliThreadCreadFile")
    private static String mutliThreadCreadFile() {
        log.info("mutliThreadCreadFile request in");
        long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        CompletableFuture[] futures = new CompletableFuture[list.size()];
        List futureList = new ArrayList();
        list.forEach(i -> {
            System.out.println("start forEach " + i);
            CompletableFuture runFuture = CompletableFuture.completedFuture(i);
            runFuture.thenApplyAsync(dealTarget -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("dealing " + dealTarget);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return dealTarget;
            });
            futureList.add(runFuture);
        });
        futureList.toArray(futures);
        CompletableFuture mainFuture = CompletableFuture.allOf(futures);
        mainFuture.join();
        mainFuture.whenComplete((result, exception) -> {
            System.out.println("result " + result);
        });
        long finished = System.currentTimeMillis();

        return MessageFormat.format("use time: {0} s", (finished - start) / 1000);
    }


}
