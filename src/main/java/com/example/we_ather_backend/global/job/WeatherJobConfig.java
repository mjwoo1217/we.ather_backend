package com.example.we_ather_backend.global.job;

import com.example.we_ather_backend.domain.weather.client.WeatherClient;
import com.example.we_ather_backend.domain.weather.dto.WeatherDTO;
import com.example.we_ather_backend.domain.weather.entity.Weather;
import com.example.we_ather_backend.domain.weather.repository.WeatherRepository;
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
public class WeatherJobConfig {

    @Value("${chunkSize:1000}")
    private int chunkSize;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final WeatherRepository weatherRepository;
    private final WeatherClient weatherClient;

    @Bean
    public Job weatherJob(JobRepository jobRepository) {
        return new JobBuilder("weatherJob", jobRepository).start(weatherStep()).build();
    }

    @Bean
    public Step weatherStep() {
        return new StepBuilder("weatherStep", jobRepository)
                .<WeatherDTO, Weather>chunk(chunkSize, transactionManager)
                .reader(weatherItemReader())
                .processor(weatherItemProcessor())
                .writer(weatherItemWriter())
                .build();
    }

    @Bean
    public ItemReader<WeatherDTO> weatherItemReader() {
        return new ListItemReader<>(Objects.requireNonNull(weatherClient.getWeather().block()));
    }

    @Bean
    public ItemProcessor<WeatherDTO, Weather> weatherItemProcessor() {
        return WeatherDTO::of;
    }

    @Bean
    public ItemWriter<? super Weather> weatherItemWriter() {
        return weatherRepository::saveAll;
    }

}
