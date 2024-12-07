package com.mysite.core.servlets;

import com.mysite.core.services.RecommendationService;
import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(
        service = {Servlet.class},
        property = {
                "sling.servlet.methods=GET",
                "sling.servlet.paths=/bin/getBikeName"
        }
)
public class ThirdApiServlet extends SlingAllMethodsServlet {

    @Reference
    private RecommendationService recommendationService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
//        String payload = request.getParameter("payload");
//        String apiResponse = recommendationService.getBikeNames(payload);
//        response.setContentType("application/json");
//        response.getWriter().write(apiResponse != null ? apiResponse : "{}");
//        response.getWriter().write(payload);
    }

    @Override
    protected void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
        String payload = IOUtils.toString(request.getReader());
        String apiResponse = recommendationService.getBikeNames(payload);
        response.setContentType("application/json");
        response.getWriter().write(apiResponse != null ? apiResponse : "{}");

    }
}
