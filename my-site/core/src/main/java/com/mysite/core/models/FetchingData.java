package com.mysite.core.models;

import com.mysite.core.bean.ImageEntity;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FetchingData {
    
    @ChildResource
    private Resource placeToRide;

    @ChildResource
    private Resource backgroundImages;

    private List<ImageEntity> optionWithImages = new ArrayList<>();

    List<String> allImages = new ArrayList<>();
    List<String> allBackGroundImages = new ArrayList<>();

    public List<String> getAllImages(){
        if(placeToRide != null && placeToRide.hasChildren()){
            for(Resource resource : placeToRide.getChildren()){
                allImages.add(resource.getValueMap().get("option3",String.class));
            }
        }
        return allImages;
    }

    public List<String> getAllBackGroundImages(){
        if(backgroundImages != null && backgroundImages.hasChildren()){
            for(Resource resource : backgroundImages.getChildren()){
                allBackGroundImages.add(resource.getValueMap().get("images",String.class));
            }
        }
        return allBackGroundImages;
    }

    public List<ImageEntity> getOptionWithImages(){
        if(backgroundImages != null && backgroundImages.hasChildren() && placeToRide != null && placeToRide.hasChildren()){
            Iterator<Resource> backgroundImageIterator = backgroundImages.getChildren().iterator();
            Iterator<Resource> placeToRideIterator = placeToRide.getChildren().iterator();

            while(backgroundImageIterator.hasNext() && placeToRideIterator.hasNext()){
                Resource backgroundIageResource = backgroundImageIterator.next();
                Resource placeToRideResource = placeToRideIterator.next();

                String backgroundiMageURL = backgroundIageResource.getValueMap().get("images",String.class);
                String optionText = placeToRideResource.getValueMap().get("option3", String.class);

                optionWithImages.add(new ImageEntity(optionText,backgroundiMageURL));
            }

        }

        return optionWithImages;
    }
}
