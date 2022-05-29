package com.brycelowe.TestProject.health;

import com.codahale.metrics.health.HealthCheck;

public class TestProjectHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
