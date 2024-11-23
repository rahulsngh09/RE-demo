//package com.mysite.core.models;
//
//import com.mysite.core.bean.Index;
//import io.wcm.testing.mock.aem.junit5.AemContext;
//import io.wcm.testing.mock.aem.junit5.AemContextExtension;
//import org.apache.sling.api.resource.Resource;
//import org.apache.sling.api.resource.ValueMap;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith({AemContextExtension.class, MockitoExtension.class})
//class QuestionnaireOptionsTest {
//
//    AemContext aemContext = new AemContext();
//    @Mock
//    private Resource options;
//
//    @Mock
//    private Resource screen2;
//
//    @Mock
//    private Resource screen3;
//    @Mock
//    private Resource screen4;
//
//    @Mock
//    Resource childResource1;
//
//    @Mock
//    Resource childResource2;
//
//    @Mock
//    ValueMap valueMap1;
//    @Mock
//    ValueMap valueMap2;
//
//    @Mock
//    ValueMap valueMap;
//    @InjectMocks
//    private QuestionnaireOptions questionnaireOptions;
//
//    @BeforeEach
//    void setUp() {
//        aemContext.addModelsForClasses(QuestionnaireOptions.class);
//    }
//
//    @Test
//    void getRidingPositionImageAttributes() {
//    }
//
//    @Test
//    void getQuestion2() {
//        questionnaireOptions.getQuestion2();
//    }
//
//    @Test
//    void getQuestion3() {
//        questionnaireOptions.getQuestion3();
//    }
//
//    @Test
//    void getQuestion() {
//        questionnaireOptions.getQuestion();
//    }
//
//    @Test
//    void getDraggableIcon() {
//        questionnaireOptions.getDraggableIcon();
//    }
//
//    @Test
//    void getQuestion4() {
//        questionnaireOptions.getQuestion4();
//    }
//
//    @Test
//    void getScreen3Answers() {
//        List<Index> answers = new ArrayList<>();
//        when(screen3.hasChildren()).thenReturn(true);
//        when(screen3.getChildren()).thenReturn(Arrays.asList(childResource1,childResource2));
//        when(childResource1.getValueMap()).thenReturn(valueMap1);
//        when(childResource1.getValueMap().get("option3", String.class)).thenReturn("Answer 1");
//        when(childResource2.getValueMap()).thenReturn(valueMap1);
//        when(childResource2.getValueMap().get("option3", String.class)).thenReturn("Answer 2");
//        questionnaireOptions.getScreen3Answers();
//    }
//
//    @Test
//    void getScreen2Answers() {
//        List<Index> answers = new ArrayList<>();
//        when(screen2.hasChildren()).thenReturn(true);
//        when(screen2.getChildren()).thenReturn(Arrays.asList(childResource1,childResource2));
//        when(childResource1.getValueMap()).thenReturn(valueMap1);
//        when(childResource1.getValueMap().get("checkbox", String.class)).thenReturn("Answer 1");
//        when(childResource2.getValueMap()).thenReturn(valueMap1);
//        when(childResource2.getValueMap().get("checkbox", String.class)).thenReturn("Answer 2");
//        questionnaireOptions.getScreen2Answers();
//    }
//
//    @Test
//    void getAnswers() {
//        List<Index> answers = new ArrayList<>();
//        when(options.hasChildren()).thenReturn(true);
//        when(options.getChildren()).thenReturn(Arrays.asList(childResource1,childResource2));
//        when(childResource1.getValueMap()).thenReturn(valueMap1);
//        when(childResource1.getValueMap().get("answers", String.class)).thenReturn("Answer 1");
//        when(childResource2.getValueMap()).thenReturn(valueMap1);
//        when(childResource2.getValueMap().get("answers", String.class)).thenReturn("Answer 2");
//
//        questionnaireOptions.getAnswers();
//    }
//
//    @Test
//    void getAllAuthoredImages() {
//        List<Index> answers = new ArrayList<>();
//        when(screen4.hasChildren()).thenReturn(true);
//        when(screen4.getChildren()).thenReturn(Arrays.asList(childResource1,childResource2));
//        when(childResource1.getValueMap()).thenReturn(valueMap1);
//        when(childResource1.getValueMap().get("option4", String.class)).thenReturn("Answer 1");
//        when(childResource2.getValueMap()).thenReturn(valueMap1);
//        when(childResource2.getValueMap().get("option4", String.class)).thenReturn("Answer 2");
//
//        questionnaireOptions.getAllAuthoredImages();
//    }
//
//    @Test
//    void getOptionWithImages() {
//        questionnaireOptions.getOptionWithImages();
//    }
//}