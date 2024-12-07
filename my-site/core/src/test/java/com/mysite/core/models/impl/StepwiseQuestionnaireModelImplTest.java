package com.mysite.core.models.impl;

import com.mysite.core.bean.ImageEntity;
import com.mysite.core.bean.Index;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class StepwiseQuestionnaireModelImplTest {
    AemContext aemContext = new AemContext();
    @Mock
    private Resource options;
    @Mock
    private Resource screen4;
    @Mock
    private Resource screen2;
    @Mock
    private ValueMap valueMap1;
    @Mock
    private ValueMap valueMap2;
    @Mock
    private Resource childResource1;
    @Mock
    private Resource childResource2;
    @Mock
    private Resource placeToRideResource;
    @Mock
    private Resource backgroundImageResource;
    @Mock
    private Resource backgroundImages;
    @Mock
    private Iterator<Resource> backgroundImageIterator;

    @Mock
    private Iterator<Resource> placeToRideIterator;
    @Mock
    private Resource backgroundImageResource1;
    @Mock
    private Resource backgroundImageResource2;
    @Mock
    private Resource screen3;
    @Mock
    private Resource placeToRideResource1;
    @Mock
    private Resource placeToRideResource2;
    @InjectMocks
    private StepwiseQuestionnaireModelImpl stepwiseQuestionnaireModel;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(StepwiseQuestionnaireModelImpl.class);
    }

    @Test
    void getRidingPositionImageAttributes() {
        List<String> result = stepwiseQuestionnaireModel.getRidingPositionImageAttributes();
        assertNotNull(result, "The result should not be null.");
        assertEquals(6, result.size(), "The size of the list should be 6.");
        assertTrue(result.contains("Cruiser"), "The list should contain 'Cruiser'.");
        assertTrue(result.contains("Upright"), "The list should contain 'Upright'.");
        assertTrue(result.contains("Off"), "The list should contain 'Off'.");
        assertTrue(result.contains("Agg"), "The list should contain 'Agg'.");
        assertTrue(result.contains("Relax"), "The list should contain 'Relax'.");
        assertTrue(result.contains("Cafe"), "The list should contain 'Cafe'.");
    }

    @Test
    void getQuestion() {
        stepwiseQuestionnaireModel.getQuestion();
    }

    @Test
    void getQuestion2() {
        stepwiseQuestionnaireModel.getQuestion2();
    }

    @Test
    void getQuestion3() {
        stepwiseQuestionnaireModel.getQuestion3();
    }

    @Test
    void getDraggableIcon() {
        stepwiseQuestionnaireModel.getDraggableIcon();
    }

    @Test
    void getAnswers() {
        when(options.hasChildren()).thenReturn(true);
        List<Resource> children = new ArrayList<>();
        children.add(childResource1);
        children.add(childResource2);
        when(options.getChildren()).thenReturn(children);

        when(childResource1.getValueMap()).thenReturn(valueMap1);
        when(valueMap1.get("answers", String.class)).thenReturn("Answer 1");

        when(childResource2.getValueMap()).thenReturn(valueMap2);
        when(valueMap2.get("answers", String.class)).thenReturn("Answer 2");

        List<Index> result = stepwiseQuestionnaireModel.getAnswers();

        // Assert: Verify the results
        assertNotNull(result, "The result list should not be null.");
        assertEquals(2, result.size(), "The list should contain 2 answers.");

        // Verify that the answers are correctly added
        assertEquals(1, result.get(0).getItemIndex(), "The index of the first answer should be 1.");
        assertEquals("Answer 1", result.get(0).getAnswer(), "The first answer should be 'Answer 1'.");

        assertEquals(2, result.get(1).getItemIndex(), "The index of the second answer should be 2.");
        assertEquals("Answer 2", result.get(1).getAnswer(), "The second answer should be 'Answer 2'.");

    }

    @Test
    void getAnswerWithNoChildren(){
        when(options.hasChildren()).thenReturn(false);
        List<Index> result = stepwiseQuestionnaireModel.getAnswers();

        assertNotNull(result,"the result list should not be null");
        assertTrue(result.isEmpty(),"The result list should be emmpty when there are no children");
    }

    @Test
    void getAnswerWithNullOptions(){
        stepwiseQuestionnaireModel = new StepwiseQuestionnaireModelImpl();
        List<Index> result = stepwiseQuestionnaireModel.getAnswers();

        // Assert: Verify the result is empty
        assertNotNull(result, "The result list should not be null.");
        assertTrue(result.isEmpty(), "The result list should be empty when options are null.");
    }

    @Test
    void getScreen2Answers() {
        when(screen2.hasChildren()).thenReturn(true);

        // Create a list of child resources
        List<Resource> children = new ArrayList<>();
        children.add(childResource1);
        children.add(childResource2);
        when(screen2.getChildren()).thenReturn(children);

        // Mock behavior of childResource1 and childResource2
        when(childResource1.getValueMap()).thenReturn(valueMap1);
        when(valueMap1.get("checkbox", String.class)).thenReturn("Option 1");

        when(childResource2.getValueMap()).thenReturn(valueMap2);
        when(valueMap2.get("checkbox", String.class)).thenReturn("Option 2");

        // Act: Call the method
        List<String> result = stepwiseQuestionnaireModel.getScreen2Answers();

        // Assert: Verify the results
        assertNotNull(result, "The result list should not be null.");
        assertEquals(2, result.size(), "The list should contain 2 answers.");

        assertEquals("Option 1", result.get(0), "The first checkbox answer should be 'Option 1'.");
        assertEquals("Option 2", result.get(1), "The second checkbox answer should be 'Option 2'.");
    }

    @Test
    void getScreen3Answers() {
        when(backgroundImages.hasChildren()).thenReturn(true);
        when(screen3.hasChildren()).thenReturn(true);

        // Mock iterables and iterators
        Iterable<Resource> backgroundImageIterable = mock(Iterable.class);
        Iterable<Resource> placeToRideIterable = mock(Iterable.class);
        when(backgroundImages.getChildren()).thenReturn(backgroundImageIterable);
        when(screen3.getChildren()).thenReturn(placeToRideIterable);

        // Create mock iterators for the resources
        Iterator<Resource> backgroundImageIterator = mock(Iterator.class);
        Iterator<Resource> placeToRideIterator = mock(Iterator.class);

        when(backgroundImageIterable.iterator()).thenReturn(backgroundImageIterator);
        when(placeToRideIterable.iterator()).thenReturn(placeToRideIterator);

        // Mock background image iterator to return one resource and then stop
        when(backgroundImageIterator.hasNext()).thenReturn(true, false);  // First time true, second time false
        Resource backgroundImageResource = mock(Resource.class);
        when(backgroundImageIterator.next()).thenReturn(backgroundImageResource);

        // Mock placeToRide iterator to return one resource and then stop
        when(placeToRideIterator.hasNext()).thenReturn(true, false);  // First time true, second time false
        Resource placeToRideResource = mock(Resource.class);
        when(placeToRideIterator.next()).thenReturn(placeToRideResource);

        // Mock valueMap for background image
        ValueMap valueMap1 = mock(ValueMap.class);
        when(backgroundImageResource.getValueMap()).thenReturn(valueMap1);
        when(valueMap1.get("images", String.class)).thenReturn("background-image-url.jpg");

        // Mock valueMap for placeToRide resource
        ValueMap valueMap2 = mock(ValueMap.class);
        when(placeToRideResource.getValueMap()).thenReturn(valueMap2);
        when(valueMap2.get("option3", String.class)).thenReturn("Mountain Biking");

        // Run the method
        List<ImageEntity> result = stepwiseQuestionnaireModel.getScreen3Answers();

        // Validate results
        assertNotNull(result, "The result should not be null");
        assertEquals(1, result.size(), "The list should contain one element");

        // Check if the ImageEntity in the list matches the expected values
        ImageEntity imageEntity = result.get(0);
        assertEquals("Mountain Biking", imageEntity.getText(), "The option text should match");
        assertEquals("background-image-url.jpg", imageEntity.getBackgroundImage(), "The image URL should match");

    }

    @Test
    void getQuestion4() {
        stepwiseQuestionnaireModel.getQuestion4();
    }

    @Test
    void getRidingPositionImagesURL() {
        when(screen4.hasChildren()).thenReturn(true);

        // Create a list of child resources
        List<Resource> children = new ArrayList<>();
        children.add(childResource1);
        children.add(childResource2);

        when(screen4.getChildren()).thenReturn(children);

        // Mock behavior of childResource1 and childResource2
        when(childResource1.getValueMap()).thenReturn(valueMap1);
        when(valueMap1.get("ridingPosition", String.class)).thenReturn("Image 1");

        when(childResource2.getValueMap()).thenReturn(valueMap2);
        when(valueMap2.get("ridingPosition", String.class)).thenReturn("Image 2");

        // Act: Call the method
        List<String> result = stepwiseQuestionnaireModel.getRidingPositionImagesURL();

        // Assert: Verify the results
        assertNotNull(result, "The result list should not be null.");
        assertEquals(2, result.size(), "The list should contain 2 images.");


    }


}