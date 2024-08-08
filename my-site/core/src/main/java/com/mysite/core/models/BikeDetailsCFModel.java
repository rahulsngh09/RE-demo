package com.mysite.core.models;

import com.mysite.core.bean.SuggestedBikeDeatilsEntity;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BikeDetailsCFModel {

    @SlingObject
    SlingHttpServletRequest request;

    public List<SuggestedBikeDeatilsEntity> getBikeDetailsList() {
        return bikeDetailsList;
    }

    private List<SuggestedBikeDeatilsEntity> bikeDetailsList = new ArrayList<>();

    @PostConstruct
    protected void init() {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource("/content/dam/mysite/content-fragment");
        if (resource != null) {
            for (Resource childResource : resource.getChildren()) {
                SuggestedBikeDeatilsEntity suggestedBikeDeatilsEntity = new SuggestedBikeDeatilsEntity();
                Resource masterResource = childResource.getChild("jcr:content/data/master");
                if (masterResource != null) {
                    ValueMap valueMap = masterResource.getValueMap();
                    suggestedBikeDeatilsEntity.setBikeName(valueMap.get("bikeNameWithModel", String.class));
                    suggestedBikeDeatilsEntity.setBikePrice(valueMap.get("bikePrice", String.class));
                    suggestedBikeDeatilsEntity.setBikeHeroImage(valueMap.get("bikeHeroImage", String.class));
                    suggestedBikeDeatilsEntity.setForwardIcon(valueMap.get("forwardicon", String.class));
                    bikeDetailsList.add(suggestedBikeDeatilsEntity);
                }

            }
        }
    }
}
