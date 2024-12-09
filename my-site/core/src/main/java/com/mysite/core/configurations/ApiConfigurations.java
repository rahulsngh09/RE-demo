package com.mysite.core.configurations;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "API Configurations")
public @interface  ApiConfigurations {
    @AttributeDefinition(name = "API URL-1",description = "The First API URL")
    String apiUrl() default "";
    @AttributeDefinition(name = "API URL-2",description = "The Second API URL")
    String getImageMappingURL() default "";
    @AttributeDefinition(name = "API URL-3",description = "The Third API URL")
    String getBikeNameFromApi() default "";
}
