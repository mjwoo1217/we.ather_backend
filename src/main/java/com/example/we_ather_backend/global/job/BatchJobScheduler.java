package com.example.we_ather_backend.global.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BatchJobScheduler {
    private final JobLauncher jobLauncher;
    private final Job weatherJob;
    private final Job locationJob;

    @Scheduled(fixedRate = 6000000)
    public void runJob() {
        try {
            JobParameters jobParameter = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(weatherJob, jobParameter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
