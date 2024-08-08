package com.mysite.core.configurations;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Riding Position Service Configuration", description = "Configuration for Riding Position Service")
public @interface EndpointOSGIConfig {

    @AttributeDefinition(name = "API Endpoint URL", description = "URL of the API endpoint to fetch riding position images")
    String apiEndpoint() default "https://fakestoreapi.com/products/1";
}
