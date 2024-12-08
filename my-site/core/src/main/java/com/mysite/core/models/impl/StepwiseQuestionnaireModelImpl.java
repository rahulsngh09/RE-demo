package com.mysite.core.models.impl;

import com.mysite.core.bean.ImageEntity;
import com.mysite.core.bean.Index;
import com.mysite.core.models.StepwiseQuestionnaireModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = StepwiseQuestionnaireModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class StepwiseQuestionnaireModelImpl implements StepwiseQuestionnaireModel {

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
    @ValueMapValue
    private String question4;
    @ValueMapValue
    private String draggableIcon;

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String getQuestion2() {
        return question2;
    }

    @Override
    public String getQuestion3() {
        return question3;
    }
    @Override
    public String getDraggableIcon() {
        return draggableIcon;
    }

    @Override
    public List<Index> getAnswers() {
        List<Index> answers = new ArrayList<>();
        if(options != null && options.hasChildren()){
            int i=1;
            for(Resource resource : options.getChildren()){
                String answer = resource.getValueMap().get("answers", String.class);
                answers.add(new Index(i++,answer));
            }
        }
        return answers;
    }

    @Override
    public List<String> getScreen2Answers() {
        List<String> screen2Answers = new ArrayList<>();
        if(screen2 != null && screen2.hasChildren()){
            for(Resource resource : screen2.getChildren()){
                screen2Answers.add(resource.getValueMap().get("checkbox", String.class));
            }
        }
        return screen2Answers;
    }

    @Override
    public List<String> getRidingPositionImageAttributes() {
        List<String> ridingPositionImageAttributes = new ArrayList<>();
        ridingPositionImageAttributes.add("Cruiser");
        ridingPositionImageAttributes.add("Upright");
        ridingPositionImageAttributes.add("Off");
        ridingPositionImageAttributes.add("Agg");
        ridingPositionImageAttributes.add("Relax");
        ridingPositionImageAttributes.add("Cafe");
        return ridingPositionImageAttributes;
    }

    @Override
    public String getQuestion4(){
        return question4; }

    @Override
    public List<String> getRidingPositionImagesURL() {
        List<String> ridingPositionImagesURL = new ArrayList<>();
        if(screen4 != null && screen4.hasChildren()){
            for(Resource resource : screen4.getChildren()){
                ridingPositionImagesURL.add(resource.getValueMap().get("ridingPosition", String.class));
            }
        }
        return ridingPositionImagesURL;
    }

    @Override
    public List<ImageEntity> getScreen3Answers(){
        List<ImageEntity> optionWithImages = new ArrayList<>();
        if(screen3 != null && screen3.hasChildren()){
            Iterator<Resource> placeToRideIterator = screen3.getChildren().iterator();
            while( placeToRideIterator.hasNext()){
                Resource placeToRideResource = placeToRideIterator.next();
                String optionText = placeToRideResource.getValueMap().get("option3", String.class);
                String backgroundiMageURL = placeToRideResource.getValueMap().get("images",String.class);
                optionWithImages.add(new ImageEntity(optionText,backgroundiMageURL));
            }
        }
        return optionWithImages;
    }
}

