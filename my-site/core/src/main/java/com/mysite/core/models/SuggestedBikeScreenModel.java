package com.mysite.core.models;

import com.mysite.core.bean.BikeDetails;
import com.mysite.core.services.BikeDetailsService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SuggestedBikeScreenModel {

    @OSGiService
    BikeDetailsService bikeDetailsService;

//    String url = "http://localhost:4502/api/assets/mysite/content-fragment-/suggested-bikes-details.json";

    public List<BikeDetails> getSuggestedBikeDeatilsEntityList() {
        return suggestedBikeDeatilsEntityList;
    }

    List<BikeDetails> suggestedBikeDeatilsEntityList = new ArrayList<>();


//    @PostConstruct
//    protected void init() {
//        suggestedBikeDeatilsEntityList = bikeDetailsService.getBikeDetailsContentFragment();
//    }
}
