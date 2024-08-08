package com.mysite.core.models.Impl;

import com.mysite.core.models.AuthoredImagesModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = AuthoredImagesModel.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class AuthoredImagesModelImpl implements AuthoredImagesModel{


    @ChildResource
    private Resource screen3;

    List<String> allImagesURL = new ArrayList<>();

    public List<String> getAllImagesURL() {
        getAllAuthoredImages();
        return allImagesURL;
    }

    @Override
    public void getAllAuthoredImages() {
        if(screen3 != null && screen3.hasChildren()){
            for(Resource resource : screen3.getChildren()){
                allImagesURL.add(resource.getValueMap().get("option4", String.class));
            }
        }
    }


}
