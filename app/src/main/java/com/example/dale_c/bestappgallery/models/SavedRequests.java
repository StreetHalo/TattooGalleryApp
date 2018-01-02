package com.example.dale_c.bestappgallery.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dale_C on 30.12.2017.
 */

public class SavedRequests {
    Set<String> requests = new HashSet<>();


    public void addRequest(String reque){
        reque.toLowerCase();
        requests.add(reque);
    }

    public Set<String> getRequests(){
        return requests;
    }
}
