package com.mysite.core.services.Impl;

import com.mysite.core.bean.ClientResponse;
import com.mysite.core.bean.RidingPositionImagesEntity;
import com.mysite.core.configurations.EndpointOSGIConfig;
import com.mysite.core.constants.CommonConstant;
import com.mysite.core.services.RidingPositionService;
import com.mysite.core.utils.CommonUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import javax.servlet.http.HttpServletResponse;

@Component(service = {RidingPositionService.class})
public class RidingPositionServiceImpl implements RidingPositionService {
    String apiEndpoint;

    @Activate
    @Modified
    protected void activate(EndpointOSGIConfig config) {
        apiEndpoint = config.apiEndpoint();
    }

    @Override
    public RidingPositionImagesEntity getAllRidingPositionsImages() {
        RidingPositionImagesEntity getAllRidingPositionsImages = new RidingPositionImagesEntity();
        try{
            ClientResponse clientResponse = CommonUtil.getClientResponse(CommonConstant.GET,apiEndpoint,null,null);
            if(clientResponse != null && clientResponse.getStatusCode() == HttpServletResponse.SC_OK){
                JSONObject responseObj =new JSONObject(clientResponse.getData());
                getAllRidingPositionsImages.setImageURL(responseObj.get("image").toString());

            }
        }catch (Exception exception){
            throw new RuntimeException();
        }
        return getAllRidingPositionsImages;
    }
}
