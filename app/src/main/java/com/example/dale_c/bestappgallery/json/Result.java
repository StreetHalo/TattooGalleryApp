
package com.example.dale_c.bestappgallery.json;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("last")
    @Expose
    private boolean last;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }



    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

}
