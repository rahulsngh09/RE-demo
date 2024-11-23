package com.mysite.core.services.impl;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.mysite.core.bean.BikeDetails;
import com.mysite.core.constants.CommonConstant;
import com.mysite.core.exception.BikeDetailsException;
import com.mysite.core.exception.ResourceResolverException;
import com.mysite.core.services.BikeDetailsService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component(service = {BikeDetailsService.class})
public class BikeDetailsServiceImpl implements BikeDetailsService {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Reference
    private QueryBuilder queryBuilder;

    private static final Logger logger = LoggerFactory.getLogger(BikeDetailsServiceImpl.class);


    @Override
    public List<BikeDetails> getBikeDetails(List<String> bikeNames) {
        List<BikeDetails> bikeDetailsList = new ArrayList<>();
        ResourceResolver resourceResolver = getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);

        if (session == null) {
            logger.error("Failed to get JCR session from ResourceResolver.");
            return bikeDetailsList;
        }

        try {
            // Step 1: Execute query and fetch results
            SearchResult result = executeQueryAndFetchResults(session);

            // Step 2: Process the hits and populate BikeDetails
            bikeDetailsList = processHitsAndPopulateBikeDetails(result, bikeNames, session);
        } catch (RepositoryException e) {
            logger.error("Repository Exception occurred while fetching bike details." + bikeNames, e);
            throw new BikeDetailsException("Error fetching bike details" + bikeNames, e);
        } finally {
            resourceResolver.close();
        }

        return bikeDetailsList;
    }

    private SearchResult executeQueryAndFetchResults(Session session)  {
        Map<String, String> map = createQueryMap();
        Query query = queryBuilder.createQuery(PredicateGroup.create(map), session);
        return query.getResult();
    }

    private List<BikeDetails> processHitsAndPopulateBikeDetails(SearchResult result, List<String> bikeNames, Session session) throws RepositoryException {
        List<BikeDetails> bikeDetailsList = new ArrayList<>();
        int count = 0;

        for (Hit hit : result.getHits()) {
            Node node = hit.getNode();
            if (node == null) continue;

            if (count >= 3) {
                break;
            }

            String currentNodeName = node.getName();
            try {
                Node node1 = session.getNode(CommonConstant.CONTENT_FRAGMENT_PARENT_PATH + currentNodeName + CommonConstant.JCR_MASTER_PATH_OF_CF);
                String currentBikeName = getBikeProperty(node1, CommonConstant.BIKE_NAME);
                if (isBikeInNames(bikeNames, currentBikeName)) {
                    BikeDetails bikeDetails = createBikeDetails(node1);
                    bikeDetailsList.add(bikeDetails);
                    count++;
                }
            } catch (RepositoryException e) {
                logger.error("Error while processing node: " + node.getName(), e);
            }
        }

        return bikeDetailsList;
    }

    private String getBikeProperty(Node node, String propertyName) throws RepositoryException {
        return node.hasProperty(propertyName) ? node.getProperty(propertyName).getString() : null;
    }

    private boolean isBikeInNames(List<String> bikeNames, String currentBikeName) {
        return bikeNames != null && bikeNames.stream().anyMatch(str -> str.equalsIgnoreCase(currentBikeName));
    }

    private BikeDetails createBikeDetails(Node node1) throws RepositoryException {
        BikeDetails bikeDetails = new BikeDetails();

        bikeDetails.setBikeName(getBikeProperty(node1, CommonConstant.BIKE_NAME));
        bikeDetails.setBikePrice(getBikeProperty(node1, CommonConstant.BIKE_PRICE));
        bikeDetails.setBikeHeroImage(getBikeProperty(node1, CommonConstant.BIKE_HERO_IMAGE));
        bikeDetails.setForwardIcon(getBikeProperty(node1, CommonConstant.FOWWARD_ICON));

        List<String> bikeFeatures = extractPropertyValues(node1, CommonConstant.BIKE_FEATURES);
        bikeDetails.setBikeFeatures(bikeFeatures);

        List<String> bikeSmallImages = extractPropertyValues(node1, CommonConstant.BIKE_SMALL_IMAGES);
        bikeDetails.setBikeSmallImages(bikeSmallImages);

        return bikeDetails;
    }

    private List<String> extractPropertyValues(Node node, String propertyName) throws RepositoryException {
        List<String> values = new ArrayList<>();
        if (node.hasProperty(propertyName)) {
            for (Value feature : node.getProperty(propertyName).getValues()) {
                values.add(feature.getString());
            }
        }
        return values;
    }



    private Map<String, String> createQueryMap() {
        Map<String, String> map = new HashMap<>();
        map.put(CommonConstant.PATH, CommonConstant.CONTENT_FRAGMENT_PARENT_PATH);
        map.put(CommonConstant.TYPE, CommonConstant.DAM_ASSET);
        map.put(CommonConstant.P_LIMIT, "-1");
        return map;
    }

    public ResourceResolver getResourceResolver() {
        Map<String,Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,CommonConstant.USERNAME);
        logger.info("found the mentioned username");
        try {
            return resourceResolverFactory.getServiceResourceResolver(map);
        } catch (ResourceResolverException | LoginException e) {
            logger.error("Login Exception occured while getting ResourceResolver.",e);
            throw new ResourceResolverException("Error getting ResourceResolver" + CommonConstant.USERNAME ,e);
        }
    }
}

