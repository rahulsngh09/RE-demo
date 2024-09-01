//package com.mysite.core.models;
//
//import com.mysite.core.bean.BikeDetails;
//import com.mysite.core.services.BikeDetailsService;
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.models.annotations.DefaultInjectionStrategy;
//import org.apache.sling.models.annotations.Model;
//import org.apache.sling.models.annotations.injectorspecific.OSGiService;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
//@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
//public class BikeDetailsModel {
//
//    @OSGiService
//    BikeDetailsService bikeDetailsService;
//
//    List<BikeDetails> bikeDetailsList = new ArrayList<>();
//
//
//    public List<BikeDetails> getBikeDetailsList() {
//        bikeDetailsList = bikeDetailsService.getBikeDetails();
//        return bikeDetailsList;
//    }
//}
