package com.mysite.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.servlet.ServletException;
import java.io.IOException;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.Servlet;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Component(
        service = {Servlet.class},
        property = {
                ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/mycontentfragment",
                ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET
        }
)
public class GetContentFragment extends SlingSafeMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetContentFragment.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        String details = request.getParameter("postData");
        response.getWriter().write(details);

//        String requestData = IOUtils.toString(request.getReader());
//        LOGGER.info("Received POST data {}",requestData);

        response.setContentType("application/json");

        String url = "http://localhost:4502/api/assets/mysite/content-fragment/bike-details-cf.json";
        try {
//            JSONObject jsonObject = new JSONObject(requestData);
//            JSONArray imageIds = jsonObject.getJSONArray("imageIds");
//            String preferences = jsonObject.getString("prefrences");
//            String placeReferences = jsonObject.getString("placePreferences");
//            String checkBoxValues = jsonObject.getString("checkBoxValues");

//            response.getWriter().write(preferences);
//            response.getWriter().write(checkBoxValues);
            // Create URL object
            URL obj = new URL(url);

            // Create HttpURLConnection object to send GET request
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set request headers
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Basic " + encodeCredentials("admin", "admin"));

            // Check response code
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response body
                Scanner scanner = new Scanner(con.getInputStream());
                StringBuilder responseBody = new StringBuilder();
                while (scanner.hasNextLine()) {
                    responseBody.append(scanner.nextLine());
                }
                scanner.close();

                JSONObject jsonResponse = new JSONObject(responseBody.toString());

                JSONObject properties = jsonResponse.getJSONObject("properties");
                JSONObject elements = properties.getJSONObject("elements");

                String bikeNameWithModel = elements.getJSONObject("bikeNameWithModel").getString("value");
                String bikePrice = elements.getJSONObject("bikePrice").getString("value");
                String bikeHeroImage = elements.getJSONObject("bikeHeroImage").getString("value");

                String[] bikeSmallImages = null;
                if (elements.has("bikeSmallImages")) {
                    Object bikeSmallImagesObj = elements.get("bikeSmallImages");
                    if (bikeSmallImagesObj instanceof JSONArray) {
                        JSONArray bikeSmallImagesArray = (JSONArray) bikeSmallImagesObj;
                        bikeSmallImages = new String[bikeSmallImagesArray.length()];
                        for (int i = 0; i < bikeSmallImagesArray.length(); i++) {
                            bikeSmallImages[i] = bikeSmallImagesArray.getString(i);
                        }
                    } else if (bikeSmallImagesObj instanceof String) {
                        bikeSmallImages = new String[]{(String) bikeSmallImagesObj};
                    }
                }

                String[] bikeFeatures = null;
                if (elements.has("bikeFeatures")) {
                    Object bikeFeaturesObj = elements.get("bikeFeatures");
                    if (bikeFeaturesObj instanceof JSONArray) {
                        JSONArray bikeFeaturesArray = (JSONArray) bikeFeaturesObj;
                        bikeFeatures = new String[bikeFeaturesArray.length()];
                        for (int i = 0; i < bikeFeaturesArray.length(); i++) {
                            bikeFeatures[i] = bikeFeaturesArray.getString(i);
                        }
                    } else if (bikeFeaturesObj instanceof String) {
                        bikeFeatures = new String[]{(String) bikeFeaturesObj};
                    }
                }
                JSONObject contentFragmentData = new JSONObject();
                contentFragmentData.put("bikeNameWithModel", bikeNameWithModel);
                contentFragmentData.put("bikePrice", bikePrice);
                contentFragmentData.put("bikeHeroImage", bikeHeroImage);
                contentFragmentData.put("bikeSmallImages", bikeSmallImages);
                contentFragmentData.put("bikeFeatures", bikeFeatures);
               response.getWriter().write(contentFragmentData.toString());

            } else {
            }

        } catch (Exception e) {
            LOGGER.error("Error retrieving content fragment data", e);
        }
    }
    private String encodeCredentials(String username, String password) {
        String credentials = username + ":" + password;
        return java.util.Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}
