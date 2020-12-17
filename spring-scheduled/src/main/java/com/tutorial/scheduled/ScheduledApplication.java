package com.tutorial.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ScheduledApplication {
    private volatile static int num = 0;

    /**
     * 结论：无论哪个，调度任务都会等上一个执行完才进行下一次，无需单独控制
     * @throws InterruptedException
     */
    //@Scheduled(cron = "* * * ? * *")
    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    public void scheduled() throws InterruptedException {
        num++;
        long start = System.currentTimeMillis();
        System.out.println("run-" + num + ":" + start);
        Thread.sleep(11000);
        System.out.println("run-" + num + ":" +(System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        SpringApplication.run(ScheduledApplication.class, args);
    }
}
