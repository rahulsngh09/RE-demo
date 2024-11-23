//package com.mysite.core.servlets;
//
//import com.mysite.core.bean.BikeDetails;
//import com.mysite.core.services.impl.BikeDetailsServiceImpl;
//import io.wcm.testing.mock.aem.junit5.AemContextExtension;
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.api.SlingHttpServletResponse;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import javax.servlet.ServletException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith({AemContextExtension.class, MockitoExtension.class})
//class BikeNamesTest {
//
//    @InjectMocks
//    private BikeNames bikeNamesServlet;
//
//    @InjectMocks
//    private BikeDetailsServiceImpl bikeDetailsService;
//
//    @Mock
//    private SlingHttpServletRequest request;
//
//    @Mock
//    private SlingHttpServletResponse response;
//
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void doGet() throws ServletException, IOException {
//
//        List<String> bikeNames = new ArrayList<>();
//        bikeNames.add("Bike1");
//
//        // Create mock bike details with an invalid format for JSON conversion
//        List<BikeDetails> bikeDetailsList = new ArrayList<>();
//        BikeDetails bikeDetails = new BikeDetails();
//        bikeDetails.setBikeName("Bike1");
//        bikeDetails.setBikePrice("1000");
//        bikeDetails.setBikeHeroImage("image1.jpg");
//        bikeDetails.setForwardIcon("icon1.png");
//        bikeDetails.setBikeFeatures(null);  // Simulate invalid data for JSON
//        bikeDetailsList.add(bikeDetails);
//        when(bikeDetailsService.getBikeDetails(bikeNames)).thenReturn(bikeDetailsList);
//        when(request.getParameterNames()).thenReturn(new Enumeration<String>() {
//            private final String[] params = {"Bike1"};
//            private int index = 0;
//
//            @Override
//            public boolean hasMoreElements() {
//                return index < params.length;
//            }
//
//            @Override
//            public String nextElement() {
//                return params[index++];
//            }
//        });
//
//
//        bikeNamesServlet.doGet(request,response);
//
//        // Mock service call
//
//    }
//}