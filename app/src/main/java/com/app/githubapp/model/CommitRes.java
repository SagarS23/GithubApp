package com.app.githubapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Sagar Shimpi on 8/9/18.
 */

public class CommitRes {

    @SerializedName("sha")
    @Expose
    public String sha;
    @SerializedName("message")
    @Expose
    public String message = "";
    @SerializedName("node_id")
    @Expose
    public String nodeId;
    @SerializedName("commit")
    @Expose
    public Commit commit;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("html_url")
    @Expose
    public String htmlUrl;
    @SerializedName("comments_url")
    @Expose
    public String commentsUrl;
    @SerializedName("author")
    @Expose
    public Author_ author;
    @SerializedName("committer")
    @Expose
    public Committer_ committer;
    @SerializedName("parents")
    @Expose
    public List<Parent> parents = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public Author_ getAuthor() {
        return author;
    }

    public void setAuthor(Author_ author) {
        this.author = author;
    }

    public Committer_ getCommitter() {
        return committer;
    }

    public void setCommitter(Committer_ committer) {
        this.committer = committer;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public class Commit {

        @SerializedName("author")
        @Expose
        public Author author;
        @SerializedName("committer")
        @Expose
        public Committer committer;
        @SerializedName("message")
        @Expose
        public String message;
        @SerializedName("tree")
        @Expose
        public Tree tree;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("comment_count")
        @Expose
        public long commentCount;
        @SerializedName("verification")
        @Expose
        public Verification verification;

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Tree getTree() {
            return tree;
        }

        public void setTree(Tree tree) {
            this.tree = tree;
        }

        public Verification getVerification() {
            return verification;
        }

        public void setVerification(Verification verification) {
            this.verification = verification;
        }

        public Committer getCommitter() {
            return committer;
        }

        public void setCommitter(Committer committer) {
            this.committer = committer;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(long commentCount) {
            this.commentCount = commentCount;
        }
    }

    public class Committer {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("date")
        @Expose
        public String date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }


    public class Author {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("date")
        @Expose
        public Date date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    public class Committer_ {

        @SerializedName("login")
        @Expose
        public String login;

        @SerializedName("id")
        @Expose
        public long id;
        @SerializedName("node_id")
        @Expose
        public String nodeId;
        @SerializedName("avatar_url")
        @Expose
        public String avatarUrl;
        @SerializedName("gravatar_id")
        @Expose
        public String gravatarId;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("html_url")
        @Expose
        public String htmlUrl;
        @SerializedName("followers_url")
        @Expose
        public String followersUrl;
        @SerializedName("following_url")
        @Expose
        public String followingUrl;
        @SerializedName("gists_url")
        @Expose
        public String gistsUrl;
        @SerializedName("starred_url")
        @Expose
        public String starredUrl;
        @SerializedName("subscriptions_url")
        @Expose
        public String subscriptionsUrl;
        @SerializedName("organizations_url")
        @Expose
        public String organizationsUrl;
        @SerializedName("repos_url")
        @Expose
        public String reposUrl;
        @SerializedName("events_url")
        @Expose
        public String eventsUrl;
        @SerializedName("received_events_url")
        @Expose
        public String receivedEventsUrl;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("site_admin")
        @Expose
        public boolean siteAdmin;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
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

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getGravatarId() {
            return gravatarId;
        }

        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        public String getFollowersUrl() {
            return followersUrl;
        }

        public void setFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
        }

        public String getFollowingUrl() {
            return followingUrl;
        }

        public void setFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
        }

        public String getGistsUrl() {
            return gistsUrl;
        }

        public void setGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
        }

        public String getStarredUrl() {
            return starredUrl;
        }

        public void setStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
        }

        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        public void setSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
        }

        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        public String getReposUrl() {
            return reposUrl;
        }

        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        public String getEventsUrl() {
            return eventsUrl;
        }

        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSiteAdmin() {
            return siteAdmin;
        }

        public void setSiteAdmin(boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
        }
    }

    public class Parent {

        @SerializedName("sha")
        @Expose
        public String sha;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("html_url")
        @Expose
        public String htmlUrl;

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }
    }

    public class Tree {

        @SerializedName("sha")
        @Expose
        public String sha;
        @SerializedName("url")
        @Expose
        public String url;


        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Verification {

        @SerializedName("verified")
        @Expose
        public boolean verified;
        @SerializedName("reason")
        @Expose
        public String reason;
        @SerializedName("signature")
        @Expose
        public Object signature;
        @SerializedName("payload")
        @Expose
        public Object payload;

        public boolean isVerified() {
            return verified;
        }

        public void setVerified(boolean verified) {
            this.verified = verified;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Object getSignature() {
            return signature;
        }

        public void setSignature(Object signature) {
            this.signature = signature;
        }

        public Object getPayload() {
            return payload;
        }

        public void setPayload(Object payload) {
            this.payload = payload;
        }
    }

    public class Author_ {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("date")
        @Expose
        public String date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @SerializedName("login")
        @Expose
        public String login;
        @SerializedName("id")
        @Expose
        public long id;
        @SerializedName("node_id")
        @Expose
        public String nodeId;
        @SerializedName("avatar_url")
        @Expose
        public String avatarUrl;
        @SerializedName("gravatar_id")
        @Expose
        public String gravatarId;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("html_url")
        @Expose
        public String htmlUrl;
        @SerializedName("followers_url")
        @Expose
        public String followersUrl;
        @SerializedName("following_url")
        @Expose
        public String followingUrl;
        @SerializedName("gists_url")
        @Expose
        public String gistsUrl;
        @SerializedName("starred_url")
        @Expose
        public String starredUrl;
        @SerializedName("subscriptions_url")
        @Expose
        public String subscriptionsUrl;
        @SerializedName("organizations_url")
        @Expose
        public String organizationsUrl;
        @SerializedName("repos_url")
        @Expose
        public String reposUrl;
        @SerializedName("events_url")
        @Expose
        public String eventsUrl;
        @SerializedName("received_events_url")
        @Expose
        public String receivedEventsUrl;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("site_admin")
        @Expose
        public boolean siteAdmin;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
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

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getGravatarId() {
            return gravatarId;
        }

        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        public String getFollowersUrl() {
            return followersUrl;
        }

        public void setFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
        }

        public String getFollowingUrl() {
            return followingUrl;
        }

        public void setFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
        }

        public String getGistsUrl() {
            return gistsUrl;
        }

        public void setGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
        }

        public String getStarredUrl() {
            return starredUrl;
        }

        public void setStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
        }

        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        public void setSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
        }

        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        public String getReposUrl() {
            return reposUrl;
        }

        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        public String getEventsUrl() {
            return eventsUrl;
        }

        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSiteAdmin() {
            return siteAdmin;
        }

        public void setSiteAdmin(boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
        }
    }

}
