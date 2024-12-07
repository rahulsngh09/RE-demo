package com.mysite.core.configurations;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "API Configurations")
public @interface  ApiConfigurations {
    @AttributeDefinition(name = "API URL",description = "The First API URL")
    String apiUrl() default "http://18.141.24.29:8001/api/recommendations/get-images";
    String getImageMappingURL() default "http://18.141.24.29:8001/api/image_mappings/";
    String getBikeNameFromApi() default "http://18.141.24.29:8001/api/recommendations/v2";
}
