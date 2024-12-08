package com.mysite.core.servlets;


import com.mysite.core.services.RecommendationService;
import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(
        service = {Servlet.class},
        property = {
                "sling.servlet.methods=POST",
                "sling.servlet.paths=/bin/getImages"
        }
)
public class GetImagesApiServlet  extends SlingAllMethodsServlet {

    @Reference
    private transient RecommendationService recommendationService;


    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String payload = IOUtils.toString(request.getReader());

        String apiResponse = recommendationService.getRecommendations(payload);
        response.setContentType("application/json");
        response.getWriter().write(apiResponse != null ? apiResponse : "{}");
    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        String secondApiResponse = recommendationService.getImageMapping();

        if (secondApiResponse == null || secondApiResponse.trim().isEmpty()) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"Failed to get image mappings from second API. Empty response.\"}");
            return;
        }
        response.setStatus(200);  // HTTP OK
        response.setContentType("application/json");
        response.getWriter().write(secondApiResponse);
    }
}
