package com.mysite.core.servlets;

import com.mysite.core.bean.SuggestedBikeDeatilsEntity;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {
        Constants.SERVICE_DESCRIPTION + "=Bike Content Fragment Servlet",
                ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/bikecontent",
                ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET
})
public class BikeDetailsServlet  extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource("/content/dam/mysite/content-fragment/bike-details-cf/jcr:content/data/master");
        if(resource != null){
            ValueMap valueMap = resource.getValueMap();
            SuggestedBikeDeatilsEntity suggestedBikeDeatilsEntity = new SuggestedBikeDeatilsEntity();
            suggestedBikeDeatilsEntity.setBikeName(valueMap.get("bikeNameWithModel", String.class));
            suggestedBikeDeatilsEntity.setBikePrice(valueMap.get("bikePrice", String.class));
            suggestedBikeDeatilsEntity.setBikeHeroImage(valueMap.get("bikeHeroImage", String.class));
            response.getWriter().write("bike naame = " + suggestedBikeDeatilsEntity.getBikeName());
            response.getWriter().write("Bike Name: " + suggestedBikeDeatilsEntity.getBikePrice());
        }

    }
}
