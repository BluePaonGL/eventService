package fr.isep.eventService.application.controller;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class EventControllerTest {

    String baseURL = "http://localhost:3000";

    @Test
    public void givenEventExists_whenEventInfoIsRetrieved_then200IsReceived() throws IOException {
        String eventId = "4028b88180dd5f370180dd65f5ee0000";
        HttpUriRequest request = new HttpGet( this.baseURL + "/event/findEvent/" + eventId );

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void givenEventDoesNotExists_whenEventInfoIsRetrieved_then500IsReceived() throws IOException {
        String eventId = "4028b88180dd5f370180dd65f5ee0001";
        HttpUriRequest request = new HttpGet( this.baseURL + "/event/findEvent/" + eventId );

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_INTERNAL_SERVER_ERROR));
    }



}
