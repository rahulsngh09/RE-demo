package com.mysite.core.models.impl;

import com.mysite.core.models.Book_A_Test_Ride_Form_Model;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = Book_A_Test_Ride_Form_Model.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BookATestRideFormModelImpl implements Book_A_Test_Ride_Form_Model{

    @ValueMapValue
    private String nameLabel;

    @ValueMapValue
    private String phoneLabel;

    @ValueMapValue
    private String emailLabel;

    @ValueMapValue
    private String pincodeLabel;

    @ValueMapValue
    private String submitButtonLabel;

    @ValueMapValue
    private String namePlaceholder;

    @ValueMapValue
    private String phonePlaceholder;

    @ValueMapValue
    private String emailPlaceholder;

    @ValueMapValue
    private String pincodePlaceholder;

    @ValueMapValue
    private String formLabel;

    @Override
    public String getNameLabel() {
        return nameLabel;
    }

    @Override
    public String getPhoneLabel() {
        return phoneLabel;
    }

    @Override
    public String getEmailLabel() {
        return emailLabel;
    }

    @Override
    public String getPincodeLabel() {
        return pincodeLabel;
    }

    @Override
    public String getSubmitButtonLabel() {
        return submitButtonLabel;
    }

    @Override
    public String getFormLabel() {
        return formLabel;
    }

    @Override
    public String getNamePlaceholder() {
        return namePlaceholder;
    }

    @Override
    public String getPhonePlaceholder() {
        return phonePlaceholder;
    }

    @Override
    public String getEmailPlaceholder() {
        return emailPlaceholder;
    }

    @Override
    public String getPincodePlaceholder() {
        return pincodePlaceholder;
    }




}
