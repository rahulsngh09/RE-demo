package com.mysite.core.services;

import com.mysite.core.bean.BikeDetails;
import org.json.JSONArray;

import java.util.List;

public interface BikeDetailsService {
    List<BikeDetails> getBikeDetails();
}