package com.mysite.core.models;

import com.mysite.core.bean.ImageEntity;
import com.mysite.core.bean.BikeDetails;
import com.mysite.core.bean.Index;
import com.mysite.core.services.BikeDetailsService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.Resource;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class QuestionnaireOptions {

    @ChildResource
    private Resource options;
    @ChildResource
    private Resource screen2;
    @ChildResource
    private Resource screen3;
    @ValueMapValue
    private String question;
    @ValueMapValue
    private String question2;
    @ValueMapValue
    private String question3;
    @ChildResource
    private Resource screen4;
    @ChildResource
    private Resource personalInterest;
    @ChildResource
    private Resource backgroundImages;
    @OSGiService
    BikeDetailsService bikeDetailsService;
    @ValueMapValue
    private String question4;

    List<Index> answers = new ArrayList<>();
//    List<BikeDetails> bikeInfoList = new ArrayList<>();
//    public List<BikeDetails> getBikeInfoList() throws RepositoryException {
//        bikeInfoList = bikeDetailsService.getBikeDetails();
//        return bikeInfoList;
//    }

    List<String> ridingPositionImageAttributes = new ArrayList<>();
    List<String> screen2Answers = new ArrayList<>();
    List<String> screen3Answers = new ArrayList<>();
    List<String> allAuthoredImages = new ArrayList<>();
    List<String> allPersonalInterestImages = new ArrayList<>();
    private List<ImageEntity> optionWithImages = new ArrayList<>();


//    private void getBikesDetail(){
//        ResourceResolver resourceResolver = request.getResourceResolver();
//        Resource resource = resourceResolver.getResource("/content/dam/mysite/content-fragment");
//        if (resource != null) {
//            for (Resource childResource : resource.getChildren()) {
//                BikeDetails suggestedBikeDeatilsEntity = new BikeDetails();
//                Resource masterResource = childResource.getChild("jcr:content/data/master");
//                if (masterResource != null) {
//                    ValueMap valueMap = masterResource.getValueMap();
//                    suggestedBikeDeatilsEntity.setBikeName(valueMap.get("bikeNameWithModel", String.class));
//                    suggestedBikeDeatilsEntity.setBikePrice(valueMap.get("bikePrice", String.class));
//                    suggestedBikeDeatilsEntity.setBikeHeroImage(valueMap.get("bikeHeroImage", String.class));
//                    suggestedBikeDeatilsEntity.setForwardIcon(valueMap.get("forwardicon", String.class));
//                    bikeDetailsList.add(suggestedBikeDeatilsEntity);
//                }
//            }
//        }
//    }

    public List<String> getRidingPositionImageAttributes() {
        ridingPositionImageAttributes.add("Cruiser");
        ridingPositionImageAttributes.add("Upright");
        ridingPositionImageAttributes.add("Off");
        ridingPositionImageAttributes.add("Agg");
        ridingPositionImageAttributes.add("Relax");
        ridingPositionImageAttributes.add("Cafe");

        return ridingPositionImageAttributes;
    }

    public String getQuestion2() {
        return question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public String getQuestion() {
        return question;
    }

    public String getQuestion4(){
        return question4; }

    public List<String> getScreen3Answers(){
        if(screen3 != null && screen3.hasChildren()){
            for(Resource resource : screen3.getChildren()){
                screen3Answers.add(resource.getValueMap().get("option3", String.class));
            }
        }
        return screen3Answers;
    }

    public List<String> getScreen2Answers() {
        if(screen2 != null && screen2.hasChildren()){
            for(Resource resource : screen2.getChildren()){
                screen2Answers.add(resource.getValueMap().get("checkbox", String.class));
            }
        }
        return screen2Answers;
    }


    public List<Index> getAnswers() {
        if(options != null && options.hasChildren()){
            int i=1;
            for(Resource resource : options.getChildren()){
                String answer = resource.getValueMap().get("answers", String.class);
                answers.add(new Index(i++,answer));
            }
        }
        return answers;
    }



    public List<String> getAllAuthoredImages() {
        if(screen4 != null && screen4.hasChildren()){
            for(Resource resource : screen4.getChildren()){
                allAuthoredImages.add(resource.getValueMap().get("option4", String.class));
            }
        }
        return allAuthoredImages;
    }

    public List<String> getAllPersonalInterestImages(){
        if(personalInterest != null && personalInterest.hasChildren()){
            for(Resource resource : personalInterest.getChildren()){
                allPersonalInterestImages.add(resource.getValueMap().get("personalInterestImages", String.class));
            }
        }
        return allPersonalInterestImages;
    }

    public List<ImageEntity> getOptionWithImages(){
        if(backgroundImages != null && backgroundImages.hasChildren() && screen3 != null && screen3.hasChildren()){
            Iterator<Resource> backgroundImageIterator = backgroundImages.getChildren().iterator();
            Iterator<Resource> placeToRideIterator = screen3.getChildren().iterator();
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
