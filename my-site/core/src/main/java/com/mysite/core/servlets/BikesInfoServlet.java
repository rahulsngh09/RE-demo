//package com.mysite.core.servlets;
//
//import com.mysite.core.services.BikeDetailsService;
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.api.SlingHttpServletResponse;
//import org.apache.sling.api.servlets.HttpConstants;
//import org.apache.sling.api.servlets.ServletResolverConstants;
//import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
//import org.json.JSONArray;
//import org.osgi.framework.Constants;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Reference;
//
//import javax.servlet.Servlet;
//import javax.servlet.ServletException;
//import java.io.IOException;
//
//@Component(service = Servlet.class,
//        property = {
//                Constants.SERVICE_DESCRIPTION + "=Bike Content Fragment Servlet",
//                ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/bikesdetails",
//                ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET
//        })
//public class BikesInfoServlet  extends SlingSafeMethodsServlet {
//    @Reference
//    BikeDetailsService bikeDetailsService;
//    @Override
//    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//
//        JSONArray bikeDetails = bikeDetailsService.getBikeDetails();
//
//        if (bikeDetails.length() == 0) {
//            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("{\"error\":\"Unable to fetch bike details\"}");
//        } else {
//            response.getWriter().write(bikeDetails.toString());
//        }
//
//    }
//}
