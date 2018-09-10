package com.app.githubapp.api;

import com.app.githubapp.model.CommitRes;
import com.app.githubapp.model.InfoRes;
import com.app.githubapp.model.IssuesRes;
import com.app.githubapp.model.SearchResult;
import com.app.githubapp.model.UserDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sagar Shimpi on 26/8/18.
 */

public interface ApiService<T> {

    @GET("/search/repositories")
    Call<SearchResult> GET_REPOSITORIES(@Query(value = "q", encoded = false) String query,
                                        @Query("sort") String sort,
                                        @Query("order") String order,
                                        @Query("per_page") String perPage,
                                        @Query("page") int page);

    @GET("/users/{user_name}")
    Call<UserDetails> GET_USER_DETAILS(@Path("user_name") String name);

    @GET("/repos/{login_name}/{repo_name}/commits")
    Call<List<CommitRes>> GET_COMMITS(@Path("login_name") String loginName, @Path("repo_name") String repoName);

    @GET("/repos/{login_name}/{repo_name}")
    Call<InfoRes> GET_INFO(@Path("login_name") String loginName, @Path("repo_name") String repoName);

    @GET("/repos/{login_name}/{repo_name}/issues")
    Call<List<IssuesRes>> GET_ISSUES(@Path("login_name") String loginName, @Path("repo_name") String repoName);

}
