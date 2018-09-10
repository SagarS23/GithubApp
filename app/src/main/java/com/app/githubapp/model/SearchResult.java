package com.app.githubapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sagar Shimpi on 31/8/18.
 */

public class SearchResult {

    @SerializedName("total_count")
    @Expose
    Integer totalCount;


    @SerializedName("message")
    @Expose
    String message = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("incomplete_results")
    @Expose
    Boolean incompleteResults;

    @SerializedName("items")
    @Expose
    List<Repository> repositories = new ArrayList<Repository>();

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }
}
