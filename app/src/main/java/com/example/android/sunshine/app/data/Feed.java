package com.example.android.sunshine.app.data;

import java.util.List;

/**
 * Created by neil on 5/25/16.
 */

public class Feed {
/*
    public Site getSite() {
        return site;
    }

    private Site site;
    */

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    //TODO: Make package for network and in the network model the network/json classes can go, then in data package contnet provider and pojos go.

}
