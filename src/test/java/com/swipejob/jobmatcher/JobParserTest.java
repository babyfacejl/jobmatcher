package com.swipejob.jobmatcher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.swipejob.jobmatcher.models.Job;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JobParserTest {

    @Test
    public void shouldParseJobJson() {
        try {
            ClassLoader loader = getClass().getClassLoader();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    loader.getResourceAsStream("job.json"), "UTF-8"
            ));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
            objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            Job job = objectMapper.readValue(in.readLine(), new TypeReference<Job>() {});

            assertThat(job.isDriverLicenseRequired(), is(false));
            assertThat(job.getCompany(), is("Frenex"));
            assertThat(job.getGuid(), is("562f66aa51a9a4d728a65f6a"));
            assertThat(job.getJobId(), is(0L));
            assertThat(job.getWorkersRequired(), is(1));
            assertThat(job.getJobTitle(), is("The Resinator"));
            assertThat(job.getBillRate(), is("$6.16"));
            assertThat(job.getLocation().getLatitude(), is(49.93359d));
            assertThat(job.getLocation().getLongitude(), is(13.864602d));
            assertThat(job.getAbout(), is("Enim excepteur ex dolore commodo incididunt. Mollit officia laborum sunt nostrud id duis non. Minim consectetur enim in dolore ipsum nulla. Occaecat irure voluptate esse ut do est nostrud esse fugiat."));
            assertThat(job.getRequiredCertificates().size(), is(3));
            assertThat(job.getRequiredCertificates().get(0), is("The Human Handbook"));
            assertThat(job.getRequiredCertificates().get(1), is("Outstanding Memory Award"));
            assertThat(job.getRequiredCertificates().get(2), is("Excellence in Organization"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
