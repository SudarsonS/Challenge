package com.flightright.app.member_service.util;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DateUtilTest {

    @Test
    public void shouldReturnFalseWhenTheGivenDateIsInFuture() throws Exception {
        LocalDate pastDate = LocalDate.of(2016, 1,1);

        boolean isFutureDate = DateUtil.isFutureDate(pastDate);

        assertThat(isFutureDate).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenTheGivenDateIsInFuture() throws Exception {
        LocalDate futureDate = LocalDate.of(2020, 1,1);

        boolean isFutureDate = DateUtil.isFutureDate(futureDate);

        assertThat(isFutureDate).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheGivenDateIsNow() throws Exception {
        LocalDate todayDate = LocalDate.now();

        boolean isFutureDate = DateUtil.isFutureDate(todayDate);

        assertThat(isFutureDate).isFalse();
    }
}