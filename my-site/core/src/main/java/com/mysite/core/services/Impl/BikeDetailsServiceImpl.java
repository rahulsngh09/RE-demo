package com.mysite.core.services.Impl;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;

import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.mysite.core.bean.BikeDetails;
import com.mysite.core.services.BikeDetailsService;

import org.apache.sling.api.resource.LoginException;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import java.util.*;

@Component(service = {BikeDetailsService.class})
public class BikeDetailsServiceImpl implements BikeDetailsService {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Reference
    private QueryBuilder queryBuilder;

    @Override
    public List<BikeDetails> getBikeDetails() {
        List<BikeDetails> bikeDetailsList = new ArrayList<>();
        ResourceResolver resourceResolver = getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        if (session == null) {
            return null;
        }

        Map<String, String> map = new HashMap<>();
        map.put("path", "/content/dam/mysite/content-fragment");
        map.put("type", "dam:Asset");
        map.put("p.limit", "-1");

        try {
            Query query = queryBuilder.createQuery(PredicateGroup.create(map), session);
            SearchResult result = query.getResult();

            for (Hit hit : result.getHits()) {
                Node node = hit.getNode();
                if (node == null) continue;

                String currentNodeName = node.getName();
                Node node1 = session.getNode("/content/dam/mysite/content-fragment/" + currentNodeName + "/jcr:content/data/master");

                BikeDetails bikeDeatils = new BikeDetails();
                bikeDeatils.setBikeName(node1.hasProperty("bikeName") ? node1.getProperty("bikeName").getString() : null);
                bikeDeatils.setBikePrice(node1.hasProperty("bikePrice") ? node1.getProperty("bikePrice").getString() : null);
                bikeDeatils.setBikeHeroImage(node1.hasProperty("bikeHeroImage") ? node1.getProperty("bikeHeroImage").getString() : null);
                bikeDeatils.setForwardIcon(node1.hasProperty("forwardicon") ? node1.getProperty("forwardicon").getString() : null);

                List<String> bikeFeatures = new ArrayList<>();
                if (node1.hasProperty("bikeFeatures")) {
                    for (Value feature : node1.getProperty("bikeFeatures").getValues()) {
                        bikeFeatures.add(feature.getString());
                    }
                }
                bikeDeatils.setBikeFeatures(bikeFeatures);

                List<String> bikeSmallImages = new ArrayList<>();
                if (node1.hasProperty("bikeSmallImages")) {
                    for (Value feature : node1.getProperty("bikeSmallImages").getValues()) {
                        bikeSmallImages.add(feature.getString());
                    }
                }
                bikeDeatils.setBikeSmallImages(bikeSmallImages);


                bikeDetailsList.add(bikeDeatils);
            }
        } catch (RepositoryException e) {
            // Handle exception
        }
        return bikeDetailsList;
    }

    public ResourceResolver getResourceResolver() {
        Map<String,Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"demouser");
        try {
            return resourceResolverFactory.getServiceResourceResolver(map);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }
}

