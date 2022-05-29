package com.brycelowe.TestProject;

import com.brycelowe.TestProject.health.TestProjectHealthCheck;
import com.brycelowe.TestProject.resources.TestProjectResource;
import com.brycelowe.TestProject.utils.FileReader;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.nio.file.Paths;

public class TestProjectApplication extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new TestProjectApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception{
        environment.healthChecks().register("TestCheck", new TestProjectHealthCheck());
        environment.jersey().register(new TestProjectResource(FileReader.getLines(Paths.get("./groupInfo.txt"))));
    }
}
