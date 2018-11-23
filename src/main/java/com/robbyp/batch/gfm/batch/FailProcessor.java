package com.robbyp.batch.gfm.batch;

import com.robbyp.batch.gfm.model.EnrichedFail;
import com.robbyp.batch.gfm.model.Fail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Slf4j
public class FailProcessor implements ItemProcessor<Fail, EnrichedFail> {

    @Override
    public EnrichedFail process(final Fail fail) throws InterruptedException {
        final EnrichedFail enrichedFail = new EnrichedFail();
        BeanUtils.copyProperties(fail, enrichedFail);
        enrichedFail.setFailCost(fail.getConsideration().multiply(new BigDecimal(0.1)));

        // Time consuming task
        //Thread.sleep(100);

        //log.info("Converting (" + fail.getId() + ") into (" + enrichedFail + ")");

        return enrichedFail;
    }

}
