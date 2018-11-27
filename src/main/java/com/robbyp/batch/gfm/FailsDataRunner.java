package com.robbyp.batch.gfm;

import com.robbyp.batch.gfm.model.FailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FailsDataRunner implements CommandLineRunner {

    private final ApplicationContext ctx;

    private final FailsRepository repository;

    @Autowired
    public FailsDataRunner(ApplicationContext context, FailsRepository repository) {
        this.ctx = context;
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository.deleteAll();
    }

}