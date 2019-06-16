package com.swipejob.jobmatcher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.swipejob.jobmatcher.models.Transportation;
import com.swipejob.jobmatcher.models.Worker;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class WorkerParserTest {

    @Test
    public void shouldParseWorkerJson() {
        try {
            ClassLoader loader = getClass().getClassLoader();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    loader.getResourceAsStream("worker.json"), "UTF-8"
            ));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
            objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            Worker worker = objectMapper.readValue(in.readLine(), new TypeReference<Worker>() {});

            assertThat(worker.getRating(), is(2));
            assertThat(worker.isActive(), is(true));
            assertThat(worker.getJobSearchAddress().getUnit(), is("km"));
            assertThat(worker.getJobSearchAddress().getMaxJobDistance(), is(30L));
            assertThat(worker.getJobSearchAddress().getLatitude(), is(49.782281d));
            assertThat(worker.getJobSearchAddress().getLongitude(), is(13.971284d));
            assertThat(worker.getTransportation(), is(Transportation.CAR));
            assertThat(worker.isHasDriversLicense(), is(false));
            assertThat(worker.getPhone(), is("+1 (847) 420-3272"));
            assertThat(worker.getEmail(), is("fowler.andrews@comcubine.io"));
            assertThat(worker.getAge(), is(30));
            assertThat(worker.getGuid(), is("562f6647410ecd6bf49146e9"));
            assertThat(worker.getUserId(), is(0L));
            assertThat(worker.getName().getLast(), is("Andrews"));
            assertThat(worker.getName().getFirst(), is("Fowler"));
            assertThat(worker.getSkills().size(), is(2));
            assertThat(worker.getSkills().get(0), is("Creator of opportunities"));
            assertThat(worker.getSkills().get(1), is("Arts and Crafts Designer"));
            assertThat(worker.getCertificates().size(), is(9));
            assertThat(worker.getCertificates().get(0), is("Outstanding Innovator"));
            assertThat(worker.getCertificates().get(1), is("The Behind the Scenes Wonder"));
            assertThat(worker.getCertificates().get(2), is("The Risk Taker"));
            assertThat(worker.getCertificates().get(3), is("Outside the Box Thinker"));
            assertThat(worker.getCertificates().get(4), is("Marvelous Multitasker"));
            assertThat(worker.getCertificates().get(5), is("The Asker of Good Questions"));
            assertThat(worker.getCertificates().get(6), is("Outstanding Memory Award"));
            assertThat(worker.getCertificates().get(7), is("Office Lunch Expert"));
            assertThat(worker.getCertificates().get(8), is("Excellence in Organization"));

            assertThat(worker.getAvailabilities().length, is(7));
            assertThat(worker.getAvailabilities()[0].getTitle(), is("Tuesday"));
            assertThat(worker.getAvailabilities()[0].getDayIndex(), is(2));
            assertThat(worker.getAvailabilities()[1].getTitle(), is("Monday"));
            assertThat(worker.getAvailabilities()[1].getDayIndex(), is(1));
            assertThat(worker.getAvailabilities()[2].getTitle(), is("Sunday"));
            assertThat(worker.getAvailabilities()[2].getDayIndex(), is(7));
            assertThat(worker.getAvailabilities()[3].getTitle(), is("Thursday"));
            assertThat(worker.getAvailabilities()[3].getDayIndex(), is(4));
            assertThat(worker.getAvailabilities()[4].getTitle(), is("Friday"));
            assertThat(worker.getAvailabilities()[4].getDayIndex(), is(5));
            assertThat(worker.getAvailabilities()[5].getTitle(), is("Wednesday"));
            assertThat(worker.getAvailabilities()[5].getDayIndex(), is(3));
            assertThat(worker.getAvailabilities()[6], nullValue());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
