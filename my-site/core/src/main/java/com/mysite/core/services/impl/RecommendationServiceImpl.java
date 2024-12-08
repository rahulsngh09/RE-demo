package com.mysite.core.services.impl;

import com.mysite.core.configurations.ApiConfigurations;
import com.mysite.core.services.RecommendationService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Component(service = {RecommendationService.class})
@Designate(ocd = ApiConfigurations.class)
public class RecommendationServiceImpl implements RecommendationService{

    private static final Logger logger = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    private String apiUrl;
    private String imageMappingURL;
    private String bikeNames;

    @Activate
    @Modified
    protected void activate(ApiConfigurations config){
        this.apiUrl = config.apiUrl();
        this.imageMappingURL = config.getImageMappingURL();
        this.bikeNames = config.getBikeNameFromApi();

    }

    @Override
    public String getRecommendations(String payload) {
        return callApi(apiUrl, payload, "POST");
    }

    @Override
    public String getImageMapping() {
        return callApi(imageMappingURL, null, "GET");
    }

    @Override
    public String getBikeNames(String payload) {
        return callApi(bikeNames, payload, "POST");
    }

    private String callApi(String url, String payload, String method) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpResponse response;
            if ("POST".equalsIgnoreCase(method)) {
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader("Content-Type", "application/json");
                if (payload != null) {
                    StringEntity entity = new StringEntity(payload);
                    httpPost.setEntity(entity);
                }
                response = httpClient.execute(httpPost);
            } else if ("GET".equalsIgnoreCase(method)) {
                HttpGet httpGet = new HttpGet(url);
                httpGet.setHeader("Content-Type", "application/json");
                response = httpClient.execute(httpGet);
            } else {
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
            }

            HttpEntity responseEntity = response.getEntity();
            return responseEntity != null ? EntityUtils.toString(responseEntity) : null;
        } catch (IOException e) {
            logger.error("Error occured while calling the API: {}",url,e);
            return null;
        }
    }
}
