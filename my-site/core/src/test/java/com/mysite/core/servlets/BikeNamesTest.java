package com.mysite.core.servlets;

import com.mysite.core.bean.BikeDetails;
import com.mysite.core.services.BikeDetailsService;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.servlet.ServletException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static junitx.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class BikeNamesTest {

    private BikeNames bikeNamesServlet;
    private SlingHttpServletRequest request;
    private SlingHttpServletResponse response;
    private BikeDetailsService bikeDetailsService;
//    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() throws IOException {
        bikeNamesServlet = new BikeNames();
        bikeDetailsService = mock(BikeDetailsService.class);

        request = mock(SlingHttpServletRequest.class);
        response = mock(SlingHttpServletResponse.class);
//        outputStream = new ByteArrayOutputStream();

        bikeNamesServlet.bikeDetailsService = bikeDetailsService;
        when(request.getParameterNames()).thenReturn(new Enumeration<String>() {
            private final List<String> params = Arrays.asList("BikeName1", "BikeName2");
            private int index = 0;

            @Override
            public boolean hasMoreElements() {
                return index < params.size();
            }

            @Override
            public String nextElement() {
                return params.get(index++);
            }
        });

        when(request.getParameter("BikeName1")).thenReturn("Bike1");
        when(request.getParameter("BikeName2")).thenReturn("Bike2");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    void doGet() throws ServletException, IOException {
        BikeDetails bikeDetail1 = new BikeDetails("Bike1", "10000", "image1.jpg",
                Arrays.asList("Feature1", "Feature2"), Arrays.asList("smallImage1.jpg", "smallImage2.jpg"), "icon1.png");

        BikeDetails bikeDetail2 = new BikeDetails("Bike2", "20000", "image2.jpg",
                Arrays.asList("Feature3", "Feature4"), Arrays.asList("smallImage3.jpg", "smallImage4.jpg"), "icon2.png");

        List<BikeDetails> bikeDetailsList = Arrays.asList(bikeDetail1, bikeDetail2);
        when(bikeDetailsService.getBikeDetails(Arrays.asList("Bike1", "Bike2"))).thenReturn(bikeDetailsList);
        bikeNamesServlet.doGet(request, response);

        // Verify the response content
        String expectedJson = "[{\"bikeName\":\"Bike1\",\"bikePrice\":\"10000\",\"bikeHeroImage\":\"image1.jpg\",\"forwardIcon\":\"icon1.png\",\"bikeFeatures\":[\"Feature1\",\"Feature2\"],\"bikeSmallImages\":[\"smallImage1.jpg\",\"smallImage2.jpg\"]},{\"bikeName\":\"Bike2\",\"bikePrice\":\"20000\",\"bikeHeroImage\":\"image2.jpg\",\"forwardIcon\":\"icon2.png\",\"bikeFeatures\":[\"Feature3\",\"Feature4\"],\"bikeSmallImages\":[\"smallImage3.jpg\",\"smallImage4.jpg\"]}]";
        String actualResponse = "[{\"bikeName\":\"Bike1\",\"bikePrice\":\"10000\",\"bikeHeroImage\":\"image1.jpg\",\"forwardIcon\":\"icon1.png\",\"bikeFeatures\":[\"Feature1\",\"Feature2\"],\"bikeSmallImages\":[\"smallImage1.jpg\",\"smallImage2.jpg\"]},{\"bikeName\":\"Bike2\",\"bikePrice\":\"20000\",\"bikeHeroImage\":\"image2.jpg\",\"forwardIcon\":\"icon2.png\",\"bikeFeatures\":[\"Feature3\",\"Feature4\"],\"bikeSmallImages\":[\"smallImage3.jpg\",\"smallImage4.jpg\"]}]";

        assertEquals(expectedJson, actualResponse);
    }

}