package com.mysite.core.servlets;

import com.mysite.core.services.RecommendationService;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.servlet.ServletException;
import java.io.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class GetImagesApiServletTest {

    @InjectMocks
    GetImagesApiServlet getImagesApiServlet;

    @Mock
    private RecommendationService recommendationService;

    @Mock
    private SlingHttpServletRequest request;

    @Mock
    private SlingHttpServletResponse response;

    @Mock
    private PrintWriter printWriter;

    private StringWriter stringWriter;

    @BeforeEach
    void setUp() throws IOException {
        stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        // Ensure getWriter() returns a valid PrintWriter
        when(response.getWriter()).thenReturn(writer);

    }

    @Test
    void doPost() throws IOException, ServletException {
        BufferedReader bufferedReader = new BufferedReader(new StringReader("{\"key\":\"value\"}"));
        when(request.getReader()).thenReturn(bufferedReader);
        String mockPayload = "{\"key\":\"value\"}";
        String mockApiResponse = "{\"recommendedImages\": [\"image1.jpg\", \"image2.jpg\"]}";
        when(recommendationService.getRecommendations(mockPayload)).thenReturn(mockApiResponse);
        getImagesApiServlet.doPost(request, response);
        verify(response).setContentType("application/json");

    }

    @Test
    void doGet() throws ServletException, IOException {
        String mockApiResponse = "{\"imageMappings\": [\"image1.jpg\", \"image2.jpg\"]}";

        when(recommendationService.getImageMapping()).thenReturn(mockApiResponse);
        getImagesApiServlet.doGet(request, response);
        verify(response).setStatus(200);
        verify(response).setContentType("application/json");
    }

    @Test
     void testDoGet_failure() throws Exception {
        when(recommendationService.getImageMapping()).thenReturn(null);
        getImagesApiServlet.doGet(request, response);
        verify(response).setStatus(500);  // HTTP error status
    }
}