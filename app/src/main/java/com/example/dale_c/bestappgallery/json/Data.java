
package com.example.dale_c.bestappgallery.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {


    @SerializedName("result")
    @Expose
    private Result result;



    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
