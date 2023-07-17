package com.example.demo.controller;

import com.example.demo.manager.TestManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TestController.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestManager mockTestManager;

    @Test
    void testTestAsync() throws Exception {
        // Setup
        when(mockTestManager.testAsync()).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/demo/test/async")
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testTestSync() throws Exception {
        // Setup
        when(mockTestManager.testSync()).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/demo/test/sync")
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testHello() throws Exception {
        // Setup
        when(mockTestManager.sayHello()).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/demo/test/hello")
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testHello2() throws Exception {
        // Setup
        when(mockTestManager.sayHello()).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/demo/test/hello2")
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
