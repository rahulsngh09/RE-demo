//package com.mysite.core.services.Impl;
//
//import com.adobe.cq.dam.cfm.ContentElement;
//import com.adobe.cq.dam.cfm.ContentFragment;
//import com.mysite.core.bean.ClientResponse;
//import com.mysite.core.bean.SuggestedBikeDeatilsEntity;
//import com.mysite.core.constants.CommonConstant;
//import com.mysite.core.services.BikeDetailsService;
//import com.mysite.core.services.GetResourceResolver;
//import com.mysite.core.utils.CommonUtil;
//import org.apache.sling.api.resource.Resource;
//import org.apache.sling.api.resource.ResourceResolver;
//import org.json.JSONObject;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Reference;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@Component(service = {BikeDetailsService.class})
//public class BikeDetailsServiceImpl implements BikeDetailsService {
//
//    @Reference
//    GetResourceResolver getResourceResolverService;
//
//    @Override
//    public List<SuggestedBikeDeatilsEntity> getBikeDetailsContentFragment() {
//        List<SuggestedBikeDeatilsEntity> allSuggestedBikesCF = new ArrayList<>();
//        List<String> bikeFeatures = new ArrayList<>();
//        List<String> bikeSmallImages = new ArrayList<>();
//        ResourceResolver resourceResolver = getResourceResolverService.getResourceResolver();
//        Resource resource = resourceResolver.getResource("/content/dam/mysite/content-fragment");
//        if(resource != null){
//            Iterator<Resource> allChildResource = resource.listChildren();
//            while(allChildResource.hasNext()){
//                Resource childResource = allChildResource.next();
//                if(childResource.isResourceType("dam:Asset")){
//                    ContentFragment contentFragment = childResource.adaptTo(ContentFragment.class);
//                    if(contentFragment == null){
//                        return null;
//                    }
//                    SuggestedBikeDeatilsEntity bikeDeatilsEntity = new SuggestedBikeDeatilsEntity();
//                    bikeDeatilsEntity.setBikePrice(contentFragment.getElement("bikePrice").getContent());
//                    bikeDeatilsEntity.setBikeHeroImage(contentFragment.getElement("heroBikeImage").getContent());
//                    bikeDeatilsEntity.setBikeName(contentFragment.getElement("bikeNameWithModel").getContent());
//
//                    allSuggestedBikesCF.add(bikeDeatilsEntity);
//                }
//            }
//        }
//        return allSuggestedBikesCF;
//    }
//
//
//
//
////    @Override
////    public SuggestedBikeDeatilsEntity getBikeDetailsContentFragment(String url) {
////        SuggestedBikeDeatilsEntity bikeDetailsEntity = new SuggestedBikeDeatilsEntity();
////        try{
////            ClientResponse clientResponse = CommonUtil.getClientResponse(CommonConstant.GET,url,null,null);
////            if(clientResponse != null && clientResponse.getStatusCode() == HttpServletResponse.SC_OK){
////                JSONObject responseObj = new JSONObject(clientResponse.getData());
////                bikeDetailsEntity.setBikeName(responseObj.get("bikeName").toString());
////                bikeDetailsEntity.setBikePrice(responseObj.get("bikePrice").toString());
////            }
////        }catch (Exception exception){
////            throw new RuntimeException(exception);
////        }
////
////        return bikeDetailsEntity;
////    }
//}
