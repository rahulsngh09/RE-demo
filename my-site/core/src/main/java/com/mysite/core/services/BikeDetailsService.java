package com.mysite.core.services;

import com.mysite.core.bean.BikeDetails;
import org.json.JSONArray;

import javax.jcr.RepositoryException;
import java.util.List;

public interface BikeDetailsService {
    List<BikeDetails> getBikeDetails(List<String> bikeNames);
//    List<String> getBikeNameAsResponse(List<String> bikeNames) throws RepositoryException;
}