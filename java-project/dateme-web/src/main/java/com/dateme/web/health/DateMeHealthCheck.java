package com.dateme.web.health;

import com.codahale.metrics.health.HealthCheck;

public class DateMeHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
