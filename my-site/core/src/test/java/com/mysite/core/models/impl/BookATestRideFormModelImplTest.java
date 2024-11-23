package com.mysite.core.models.impl;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class BookATestRideFormModelImplTest {

    @InjectMocks
    private BookATestRideFormModelImpl bookATestRideFormModel;


    @BeforeEach
    void setUp() {
    }


    @Test
    void getNameLabel() {
        bookATestRideFormModel.getNameLabel();
    }

    @Test
    void getPhoneLabel() {
        bookATestRideFormModel.getPhoneLabel();
    }

    @Test
    void getEmailLabel() {
        bookATestRideFormModel.getEmailLabel();
    }

    @Test
    void getPincodeLabel() {
        bookATestRideFormModel.getPincodeLabel();
    }

    @Test
    void getSubmitButtonLabel() {
        bookATestRideFormModel.getSubmitButtonLabel();
    }

    @Test
    void getFormLabel() {
        bookATestRideFormModel.getFormLabel();
    }

    @Test
    void getNamePlaceholder() {
        bookATestRideFormModel.getNamePlaceholder();
    }

    @Test
    void getPhonePlaceholder() {
        bookATestRideFormModel.getPhonePlaceholder();
    }

    @Test
    void getEmailPlaceholder() {
        bookATestRideFormModel.getEmailPlaceholder();
    }

    @Test
    void getPincodePlaceholder() {
        bookATestRideFormModel.getPincodePlaceholder();
    }
}