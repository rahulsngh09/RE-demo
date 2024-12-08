package com.mysite.core.services;

import com.mysite.core.bean.BikeDetails;
import javax.jcr.RepositoryException;
import java.util.List;

public interface BikeDetailsService {
    /**
     * Fetches a list of bike details based on the provided bike names.
     *
     * This method is used to retrieve detailed information for a list of bike names.
     * The returned list contains objects of type `BikeDetails` that represent the details
     * of each corresponding bike.
     *
     * @param bikeNames A list of bike names for which the details are to be fetched.
     * @return A list of `BikeDetails` objects corresponding to the provided bike names.
     * @throws RepositoryException If there is an issue retrieving the bike details from the repository.
     */
    List<BikeDetails> getBikeDetails(List<String> bikeNames);
}