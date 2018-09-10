package com.app.githubapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Shimpi on 31/8/18.
 */

public class Repository {

    @Expose
    Integer id;

    @Expose
    String name;

    @SerializedName("full_name")
    @Expose
    String fullName;

    @SerializedName("forks")
    @Expose
    String forks;

    @SerializedName("html_url")
    @Expose
    String htmlUrl;

    @Expose
    String description;

    @SerializedName("created_at")
    @Expose
    String createdAt;

    @SerializedName("updated_at")
    @Expose
    String updatedAt;

    @SerializedName("url")
    @Expose
    String url;

    @Expose
    String homepage;

    @SerializedName("stargazers_count")
    @Expose
    Integer stargazersCount;

    @Expose
    String language;

    @Expose
    Integer watchers;

    @Expose
    Double score;

    @SerializedName("owner")
    @Expose
    public Owner _owner;

    public void set_owner(Owner _owner) {
        this._owner = _owner;
    }

    public Owner get_owner() {
        return _owner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getWatchers() {
        return watchers;
    }

    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public class Owner {

        @SerializedName("login")
        @Expose
        public String login;
        @SerializedName("id")
        @Expose
        public String id;
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
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("company")
        @Expose
        public Object company;
        @SerializedName("blog")
        @Expose
        public String blog;
        @SerializedName("location")
        @Expose
        public String location;
        @SerializedName("email")
        @Expose
        public Object email;
        @SerializedName("hireable")
        @Expose
        public Object hireable;
        @SerializedName("bio")
        @Expose
        public String bio;
        @SerializedName("public_repos")
        @Expose
        public String publicRepos;
        @SerializedName("public_gists")
        @Expose
        public String publicGists;
        @SerializedName("followers")
        @Expose
        public String followers;
        @SerializedName("following")
        @Expose
        public String following;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("updated_at")
        @Expose
        public String updatedAt;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public String getBlog() {
            return blog;
        }

        public void setBlog(String blog) {
            this.blog = blog;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getHireable() {
            return hireable;
        }

        public void setHireable(Object hireable) {
            this.hireable = hireable;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getPublicRepos() {
            return publicRepos;
        }

        public void setPublicRepos(String publicRepos) {
            this.publicRepos = publicRepos;
        }

        public String getPublicGists() {
            return publicGists;
        }

        public void setPublicGists(String publicGists) {
            this.publicGists = publicGists;
        }

        public String getFollowers() {
            return followers;
        }

        public void setFollowers(String followers) {
            this.followers = followers;
        }

        public String getFollowing() {
            return following;
        }

        public void setFollowing(String following) {
            this.following = following;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

}
