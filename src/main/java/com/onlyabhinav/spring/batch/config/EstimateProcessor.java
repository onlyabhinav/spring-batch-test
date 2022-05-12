package com.onlyabhinav.spring.batch.config;

import com.onlyabhinav.spring.batch.entity.Estimate;
import org.springframework.batch.item.ItemProcessor;

public class EstimateProcessor implements ItemProcessor<Estimate, Estimate> {

    @Override
    public Estimate process(Estimate estimate) throws Exception {
        if(estimate.getFeItem().equals("EBITDA_ADJ")) {
            return estimate;
        }else{
            return null;
        }
    }
}
