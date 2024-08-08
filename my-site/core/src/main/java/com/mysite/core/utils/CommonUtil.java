package com.mysite.core.utils;

import com.google.gson.Gson;
import com.mysite.core.bean.ClientResponse;
import com.mysite.core.constants.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.apache.oltu.oauth2.common.message.types.TokenType.BEARER;

public class CommonUtil {

    public static <T> ClientResponse getClientResponse(final String method, final String apiUrl, final T requestObject
            , final String token) throws Exception {

        ClientResponse clientResponse = new ClientResponse();
        if(StringUtils.isAllBlank(method,apiUrl)){
            return clientResponse;
        }
        if(method.equals(CommonConstant.GET)){
            final HttpGet httpGet = new HttpGet(apiUrl);
            httpGet.setHeader(CommonConstant.CONTENT_TYPE,CommonConstant.CONTENT_TYPE_JSON);
            if(token != null){
                httpGet.setHeader(HttpHeaders.AUTHORIZATION,BEARER + token);
            }
            clientResponse = executeRequest(httpGet);
        }
        if (method.equals(CommonConstant.POST)) {
            final HttpPost httpPost = new HttpPost(apiUrl);
            String requestBody = new Gson().toJson(requestObject);
            if (StringUtils.isNotBlank(requestBody)) {
                httpPost.addHeader(CommonConstant.CONTENT_TYPE,
                        CommonConstant.CONTENT_TYPE_JSON);
                try {
                    httpPost.setEntity(new StringEntity(requestBody));
                } catch (UnsupportedEncodingException e) {
                }
            }
            clientResponse = executeRequest(httpPost);
        }
        return clientResponse;
    }

    public static ClientResponse executeRequest(final HttpUriRequest request) throws Exception {
        String result = StringUtils.EMPTY;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(request)) {
            HttpEntity httpEntity = httpResponse.getEntity();
            ClientResponse clientResponse = new ClientResponse();
            clientResponse.setStatusCode(httpResponse
                    .getStatusLine().getStatusCode());
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity,
                        StandardCharsets.UTF_8);
                clientResponse.setData(result);
                EntityUtils.consume(httpEntity);
            }
            return clientResponse;
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }
        return null;
    }
}
