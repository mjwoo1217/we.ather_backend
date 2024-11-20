package com.example.we_ather_backend.global.job;


import com.example.we_ather_backend.domain.location.client.LocationClient;
import com.example.we_ather_backend.domain.location.dto.LocationDTO;
import com.example.we_ather_backend.domain.location.entity.Location;
import com.example.we_ather_backend.domain.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class LocationJobConfig {
    @Value("${chunkSize:1000}")
    private int chunkSize;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final LocationRepository locationRepository;
    private final LocationClient locationClient;


    @Bean
    public Job locationJob(JobRepository jobRepository) {
        return new JobBuilder("locationJob", jobRepository).start(locationStep()).build();
    }

    @Bean
    public Step locationStep() {
        return new StepBuilder("locationStep", jobRepository)
                .<LocationDTO, Location>chunk(chunkSize, transactionManager)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public ItemReader<LocationDTO> itemReader() {
        return new ListItemReader<>(Objects.requireNonNull(locationClient.getLocation().block()));
    }

    @Bean
    public ItemProcessor<LocationDTO, Location> itemProcessor() {
        return LocationDTO::of;
    }

    @Bean
    public ItemWriter<Location> itemWriter() {
        return locationRepository::saveAll;
    }
}