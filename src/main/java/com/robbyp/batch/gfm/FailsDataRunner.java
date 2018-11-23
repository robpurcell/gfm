package com.robbyp.batch.gfm;

import com.robbyp.batch.gfm.model.Fail;
import com.robbyp.batch.gfm.model.FailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

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
//        repository.deleteAll();
//
//        for (int i = 1; i < 50_000; i += 1) {
//            Fail f = createFail(i);
//            repository.save(f);
//        }
//
//        for (Fail fail : repository.findAll()) {
//            log.debug(fail.toString());
//        }
    }

    private Fail createFail(int id) {
        Fail f = new Fail();
        f.setTradeDate(LocalDate.now());
        f.setIntendedSettlementDate(LocalDate.now().plusDays(3));
        f.setCounterparty("Counterparty" + id);
        f.setConsideration(BigDecimal.valueOf(Math.random()).multiply(new BigDecimal(1000)));
        return f;
    }

}