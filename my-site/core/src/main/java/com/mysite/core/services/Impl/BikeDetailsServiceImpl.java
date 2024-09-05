package com.mysite.core.services.Impl;

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
        Map<String, String> map = new HashMap<>();
        map.put(CommonConstant.PATH, CommonConstant.CONTENT_FRAGMENT_PARENT_PATH);
        map.put(CommonConstant.TYPE, CommonConstant.DAM_ASSET);
        map.put(CommonConstant.P_LIMIT, "-1");

        try {
            Query query = queryBuilder.createQuery(PredicateGroup.create(map), session);
            SearchResult result = query.getResult();
            int count = 0;
            for (Hit hit : result.getHits()) {
                if(count > 4){
                    break;
                }
                Node node = hit.getNode();
                if (node == null) continue;

                String currentNodeName = node.getName();
                Node node1 = session.getNode(CommonConstant.CONTENT_FRAGMENT_PARENT_PATH + currentNodeName + CommonConstant.JCR_MASTER_PATH_OF_CF);
                String currentBikeName = node1.hasProperty(CommonConstant.BIKE_NAME)?node1.getProperty(CommonConstant.BIKE_NAME).getString() : null;
                if(bikeNames != null && bikeNames.contains(currentBikeName)){
                    BikeDetails bikeDeatils = new BikeDetails();
                    bikeDeatils.setBikeName(node1.hasProperty(CommonConstant.BIKE_NAME) ? node1.getProperty(CommonConstant.BIKE_NAME).getString() : null);
                    bikeDeatils.setBikePrice(node1.hasProperty(CommonConstant.BIKE_PRICE) ? node1.getProperty(CommonConstant.BIKE_PRICE).getString() : null);
                    bikeDeatils.setBikeHeroImage(node1.hasProperty(CommonConstant.BIKE_HERO_IMAGE) ? node1.getProperty(CommonConstant.BIKE_HERO_IMAGE).getString() : null);
                    bikeDeatils.setForwardIcon(node1.hasProperty(CommonConstant.FOWWARD_ICON) ? node1.getProperty(CommonConstant.FOWWARD_ICON).getString() : null);

                    List<String> bikeFeatures = new ArrayList<>();
                    if (node1.hasProperty(CommonConstant.BIKE_FEATURES)) {
                        for (Value feature : node1.getProperty(CommonConstant.BIKE_FEATURES).getValues()) {
                            bikeFeatures.add(feature.getString());
                        }
                    }
                    bikeDeatils.setBikeFeatures(bikeFeatures);
                    List<String> bikeSmallImages = new ArrayList<>();
                    if (node1.hasProperty(CommonConstant.BIKE_SMALL_IMAGES)) {
                        for (Value feature : node1.getProperty(CommonConstant.BIKE_SMALL_IMAGES).getValues()) {
                            bikeSmallImages.add(feature.getString());
                        }
                    }
                    bikeDeatils.setBikeSmallImages(bikeSmallImages);
                    bikeDetailsList.add(bikeDeatils);
                    count++;
                }
                }
        } catch (RepositoryException e) {
            logger.error("Repository Exception occured while fetching bike details.",e);
            throw new BikeDetailsException("Error fetching bike details",e);

        }finally {
            resourceResolver.close();
        }
        return bikeDetailsList;
    }

    public ResourceResolver getResourceResolver() {
        Map<String,Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,CommonConstant.USERNAME);
        logger.info("found the mentioned username");
        try {
            return resourceResolverFactory.getServiceResourceResolver(map);
        } catch (LoginException e) {
            logger.error("Login Exception occured while getting ResourceResolver.",e);
            throw new ResourceResolverException("Error getting ResourceResolver",e);
        }
    }
}

