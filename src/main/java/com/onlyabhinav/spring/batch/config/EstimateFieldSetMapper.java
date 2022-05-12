package com.onlyabhinav.spring.batch.config;

import com.onlyabhinav.spring.batch.entity.Estimate;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class EstimateFieldSetMapper implements FieldSetMapper<Estimate> {

    @Override
    public Estimate mapFieldSet(FieldSet fieldSet) throws BindException {

        //lineTokenizer.setNames("fsymId", "feItem", "feFpEndDate", "consStartDate", "consEndDate", "currency",
        //        "fePerRel", "adjDate", "feMean", "feMedian", "feNumEst", "feLow", "feHigh", "feStdDev", "feUp",
        //        "feDown");

        return Estimate.builder()
                .fsymId(fieldSet.readString("fsymId"))
                .feItem(fieldSet.readString("feItem"))
                .feFpEndDate(fieldSet.readDate("feFpEndDate","yyyy-MM-dd"))
                .consStartDate(fieldSet.readDate("consStartDate", "yyyy-MM-dd"))
                .consEndDate(fieldSet.readDate("consEndDate", "yyyy-MM-dd"))
                .currency(fieldSet.readString("currency"))
                .fePerRel(fieldSet.readDouble("fePerRel"))
                .adjDate(fieldSet.readDate("adjDate", "yyyy-MM-dd"))
                .feMean(fieldSet.readDouble("feMean"))
                .feMedian(fieldSet.readDouble("feMedian"))
                .feNumEst(fieldSet.readDouble("feNumEst"))
                .feLow(fieldSet.readDouble("feLow"))
                .feHigh(fieldSet.readDouble("feHigh"))
                .feStdDev(fieldSet.readDouble("feStdDev"))
                .feUp(fieldSet.readDouble("feUp"))
                .feDown(fieldSet.readDouble("feDown"))
                .build();
    }
}