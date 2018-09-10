
package com.app.githubapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Sagar Shimpi on 8/9/18.
 */

@SuppressWarnings("unused")
public class IssuesRes {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("repository_url")
    @Expose
    public String repositoryUrl;
    @SerializedName("labels_url")
    @Expose
    public String labelsUrl;
    @SerializedName("comments_url")
    @Expose
    public String commentsUrl;
    @SerializedName("events_url")
    @Expose
    public String eventsUrl;
    @SerializedName("html_url")
    @Expose
    public String htmlUrl;
    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("node_id")
    @Expose
    public String nodeId;
    @SerializedName("number")
    @Expose
    public long number;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("labels")
    @Expose
    public List<Label> labels = null;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("locked")
    @Expose
    public boolean locked;
    @SerializedName("assignee")
    @Expose
    public Object assignee;
    @SerializedName("assignees")
    @Expose
    public List<Object> assignees = null;
    @SerializedName("milestone")
    @Expose
    public Object milestone;
    @SerializedName("comments")
    @Expose
    public long comments;
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("closed_at")
    @Expose
    public Object closedAt;
    @SerializedName("author_association")
    @Expose
    public String authorAssociation;
    @SerializedName("body")
    @Expose
    public String body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Object getAssignee() {
        return assignee;
    }

    public void setAssignee(Object assignee) {
        this.assignee = assignee;
    }

    public List<Object> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Object> assignees) {
        this.assignees = assignees;
    }

    public Object getMilestone() {
        return milestone;
    }

    public void setMilestone(Object milestone) {
        this.milestone = milestone;
    }

    public long getComments() {
        return comments;
    }

    public void setComments(long comments) {
        this.comments = comments;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    public String getAuthorAssociation() {
        return authorAssociation;
    }

    public void setAuthorAssociation(String authorAssociation) {
        this.authorAssociation = authorAssociation;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public class Label {

        @SerializedName("id")
        @Expose
        public long id;
        @SerializedName("node_id")
        @Expose
        public String nodeId;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("color")
        @Expose
        public String color;
        @SerializedName("default")
        @Expose
        public boolean _default;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNodeId() {
            return nodeId;
        }

        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public boolean is_default() {
            return _default;
        }

        public void set_default(boolean _default) {
            this._default = _default;
        }
    }

}
