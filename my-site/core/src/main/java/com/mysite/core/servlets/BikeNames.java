package com.mysite.core.servlets;

import com.mysite.core.bean.BikeDetails;
import com.mysite.core.constants.CommonConstant;
import com.mysite.core.services.BikeDetailsService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Component(
        service = {Servlet.class},
        property = {
                "sling.servlet.methods=POST",
                "sling.servlet.paths=/bin/captureBikeNames"
        }
)
public class BikeNames extends SlingAllMethodsServlet {

    @Reference
    BikeDetailsService bikeDetailsService;

    @Override
    protected void doGet(SlingHttpServletRequest request,  SlingHttpServletResponse response) throws ServletException, IOException {
        List<String> bikeNames = new ArrayList<>();
        Logger logger = LoggerFactory.getLogger(BikeNames.class);
        response.setContentType("application/json");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramValue = request.getParameter(parameterNames.nextElement());
            bikeNames.add(paramValue);
        }

        List<BikeDetails> bikeDetails = bikeDetailsService.getBikeDetails(bikeNames);

        if (bikeDetails.isEmpty()) {
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Unable to fetch bike details\"}");
        } else {
            JSONArray bikeDetailsJsonArray = new JSONArray();
            for (BikeDetails bikeDetail : bikeDetails) {
                JSONObject bikeDetailJson = new JSONObject();
                try {
                    bikeDetailJson.put(CommonConstant.BIKE_NAME, bikeDetail.getBikeName());
                    bikeDetailJson.put(CommonConstant.BIKE_PRICE, bikeDetail.getBikePrice());
                    bikeDetailJson.put(CommonConstant.BIKE_HERO_IMAGE, bikeDetail.getBikeHeroImage());
                    bikeDetailJson.put("forwardIcon", bikeDetail.getForwardIcon());

                    JSONArray bikeFeaturesArray = new JSONArray(bikeDetail.getBikeFeatures());
                    bikeDetailJson.put(CommonConstant.BIKE_FEATURES, bikeFeaturesArray);
                    JSONArray bikeSmallImagesArray = new JSONArray(bikeDetail.getBikeSmallImages());
                    bikeDetailJson.put(CommonConstant.BIKE_SMALL_IMAGES, bikeSmallImagesArray);

                    bikeDetailsJsonArray.put(bikeDetailJson);
                } catch (JSONException e) {
                    logger.error("Data is in mismatch format {}",e.getMessage());
                    response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("{\"error\":\"Error converting bike details to JSON\"}");
                    return;
                }
            }
            response.getWriter().write(bikeDetailsJsonArray.toString());
        }
    }
}
