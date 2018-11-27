package com.robbyp.batch.gfm.controller;

import com.robbyp.batch.gfm.model.Fail;
import com.robbyp.batch.gfm.model.FailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static com.robbyp.batch.gfm.model.Fail.createFail;

@RestController
@Slf4j
public class JobLauncherController {

    private final FailsRepository repository;
    private final JobLauncher jobLauncher;
    private final Job job;

    @Autowired
    public JobLauncherController(FailsRepository repository, JobLauncher jobLauncher, Job job) {
        this.repository = repository;
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping("/launchjob/{date}")
    public String handle(@PathVariable String date) { // TODO can this be a Date, not just a String
        JobExecution jobExecution = null;
        try {
            jobExecution = jobLauncher.run(job, createJobParams(date));
            return "jobExecution's info: Id = " + jobExecution.getId() + " ,status = " + jobExecution.getExitStatus();
        } catch (Exception e) {
            log.info(e.getMessage());
            return "jobExecution Failed: " + e.getMessage();
        }

    }

    private JobParameters createJobParams(String date) {
        return new JobParameters(Collections.singletonMap("runDate", new JobParameter(date)));
    }

    @GetMapping("/createFails")
    public String createFails() {
        int numberOfFails = 80;

        for (int i = 1; i <= numberOfFails; i += 1) {
            Fail f = createFail(i);
            repository.save(f);
        }

        return "Created " + numberOfFails + " fails.";
    }
}