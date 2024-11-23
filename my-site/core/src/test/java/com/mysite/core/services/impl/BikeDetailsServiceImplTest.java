package com.mysite.core.services.impl;


import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.mysite.core.bean.BikeDetails;
import com.mysite.core.constants.CommonConstant;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import javax.jcr.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class BikeDetailsServiceImplTest {
    AemContext aemContext = new AemContext();

    @InjectMocks
    @Spy
    private BikeDetailsServiceImpl bikeDetailsService;
    @Mock
    private ResourceResolverFactory resourceResolverFactory;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private ResourceResolver resourceResolver;
    @Mock
    private Session session;
    @Mock
    private Query query;
    @Mock
    private SearchResult result;
    @Mock
    private Hit hit;
    @Mock
    private Node node;
    @Mock
    private Property bikeNameProperty;
    @Mock
    private Property bikePriceProperty;
    @Mock
    private Property bikeHeroImageProperty;
    @Mock
    private Property forwardIconProperty;
    @Mock
    private Property bikeFeatureProperty;
    @Mock
    private Property bikeSmallImagesProperty;

    @Mock
    private Value featureValue;
    @Mock
    private Value smallImageValue;
    @Mock
    private Node node1;

    private Logger logger;

    List<String> bikeName;
    private static final String bikeName1 = "Bike1";
    private static final String bikeName2 = "Bike2";
    private static final List<String> bikeNames = Arrays.asList(bikeName1, bikeName2);


    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(BikeDetailsServiceImpl.class);
         bikeName = new ArrayList<>();
    }

    @Test
    void getBikeDetails() throws LoginException, RepositoryException {

        bikeName.add("MountainBike");
        List<Hit> bikes = new ArrayList<>();
        bikes.add(hit);
        bikes.add(hit);
        Map<String,Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"demouser");
        when(resourceResolverFactory.getServiceResourceResolver(map)).thenReturn(resourceResolver);
        when(resourceResolver.adaptTo(Session.class)).thenReturn(session);

        when(queryBuilder.createQuery(any(),any())).thenReturn(query);
        when(query.getResult()).thenReturn(result);
        when(result.getHits()).thenReturn(bikes);
        when(hit.getNode()).thenReturn(node);
        when(node.getName()).thenReturn("BikeNode");
        when(session.getNode(CommonConstant.CONTENT_FRAGMENT_PARENT_PATH + "BikeNode" + CommonConstant.JCR_MASTER_PATH_OF_CF)).thenReturn(node1);
        node1.setProperty("bikeName","MountainBike");
        when(node1.hasProperty(CommonConstant.BIKE_NAME)).thenReturn(true);
        when(node1.getProperty("bikeName")).thenReturn(bikeNameProperty);
        when(bikeNameProperty.getString()).thenReturn("MountainBike");

        when(node1.hasProperty(CommonConstant.BIKE_PRICE)).thenReturn(true);
        when(node1.getProperty(CommonConstant.BIKE_PRICE)).thenReturn(bikePriceProperty);
        when(bikePriceProperty.getString()).thenReturn("1000");

        when(node1.hasProperty(CommonConstant.BIKE_HERO_IMAGE)).thenReturn(true);
        when(node1.getProperty(CommonConstant.BIKE_HERO_IMAGE)).thenReturn(bikeHeroImageProperty);
        when(bikeHeroImageProperty.getString()).thenReturn("heroImage.jpg");

        when(node1.hasProperty(CommonConstant.FOWWARD_ICON)).thenReturn(true);
        when(node1.getProperty(CommonConstant.FOWWARD_ICON)).thenReturn(forwardIconProperty);
        when(bikePriceProperty.getString()).thenReturn("forwardIcon.jpg");

        when(node1.hasProperty(CommonConstant.BIKE_FEATURES)).thenReturn(true);
        when(node1.getProperty(CommonConstant.BIKE_FEATURES)).thenReturn(bikeFeatureProperty);
        when(bikeFeatureProperty.getValues()).thenReturn(new Value[]{featureValue});
        when(featureValue.getString()).thenReturn("Feature1");

        when(node1.hasProperty(CommonConstant.BIKE_SMALL_IMAGES)).thenReturn(true);
        when(node1.getProperty(CommonConstant.BIKE_SMALL_IMAGES)).thenReturn(bikeSmallImagesProperty);
        when(bikeSmallImagesProperty.getValues()).thenReturn(new Value[]{smallImageValue});
        when(smallImageValue.getString()).thenReturn("SamllImage1");
        bikeDetailsService.getBikeDetails(bikeName);
    }

    @Test
    void getBikeDetailsSessionNull() throws LoginException {
        Map<String,Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"demouser");
        when(resourceResolverFactory.getServiceResourceResolver(map)).thenReturn(resourceResolver);
        when(resourceResolver.adaptTo(Session.class)).thenReturn(null);

        List<BikeDetails> result = bikeDetailsService.getBikeDetails(bikeNames);

        // Assert: Verify that the result is empty because session is null
        assertNotNull(result);
        assertTrue(result.isEmpty(), "The result should be empty when session is null.");
    }




//    @Test
//    void getResourceResolverException() throws LoginException {
//        Map<String,Object> map = new HashMap<>();
//        map.put(ResourceResolverFactory.SUBSERVICE,"demouser");
//        when(resourceResolverFactory.getServiceResourceResolver(map)).thenThrow(new LoginException("Login failed"));
//
//        // Call the method and assert that ResourceResolverException is thrown
//        ResourceResolverException thrownException = assertThrows(ResourceResolverException.class, () -> {
//            bikeDetailsService.getResourceResolver();
//        });
//
//        // Assert that the message of the thrown exception is correct
//        assertEquals("Error getting ResourceResolver", thrownException.getMessage());
//
//        // Verify that the cause of the ResourceResolverException is the LoginException
//        assertTrue(thrownException.getCause() instanceof LoginException);
//        assertEquals("Login failed", thrownException.getCause().getMessage());
//
//        // Verify that the logger was called with the correct error message
//        verify(logger).error(eq("Login Exception occured while getting ResourceResolver."), any(LoginException.class));
//
//    }
}
