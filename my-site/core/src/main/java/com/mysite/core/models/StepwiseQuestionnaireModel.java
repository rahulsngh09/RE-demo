package com.mysite.core.models;

import com.mysite.core.bean.ImageEntity;
import com.mysite.core.bean.Index;

import java.util.List;

public interface StepwiseQuestionnaireModel {
    /**
     * Gets the first question for the questionnaire.
     *
     * @return the question text for the first screen.
     */
    String getQuestion();
    /**
     * Gets the second question for the questionnaire.
     *
     * @return the question text for the second screen.
     */
    String getQuestion2();
    /**
     * Gets the third question for the questionnaire.
     *
     * @return the question text for the second screen.
     */
    String getQuestion3();
    /**
     * Gets the fourth question for the questionnaire.
     *
     * @return the question text for the second screen.
     */
    String getQuestion4();
    /**
     * Gets the path or identifier for the draggable icon used in the questionnaire interface.
     *
     * @return the URL or identifier for the draggable icon.
     */
    String getDraggableIcon();
    /**
     * Gets a list of answer options for the first screen of the questionnaire.
     *
     * @return a list of possible answers for the first screen.
     */
    List<Index> getAnswers();
    /**
     * Gets a list of answer options for the second screen of the questionnaire.
     *
     * @return a list of possible answers for the second screen.
     */
    List<String> getScreen2Answers();


    /**
     * Gets all the images authored or added for the questionnaire.
     *
     * @return a list of image paths or identifiers.
     */
    List<String> getRidingPositionImagesURL();
    /**
     * Gets a list of answer options that include images associated with the answers.
     *
     * @return a list of ImageEntity objects that represent the answers with images.
     */
    List<ImageEntity> getScreen3Answers();
    /**
     * Gets a list of image attributes related to riding positions, used for answering or visual reference.
     *
     * @return a list of attributes related to riding position images.
     */
    List<String> getRidingPositionImageAttributes();




}
