package com.example.alumnicellsystem;

import com.example.alumnicellsystem.Responses.LoginResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface UrlService {
   /* @GET("exercises/{user_id}")
    Call<ExerciseData> getJsonData(@Path(value = "user_id", encoded = true) int userId, @Query("format") String sort);

    @GET("feed/{parent_id}")
    Call<List<Feed>> getFeeds(@Path(value = "parent_id", encoded = true) int userId, @Query("format") String sort);

    @GET("channels/")
    Call<List<Channel>> getChannels(@Query("format") String sort);

    @GET("episodes/")
    Call<List<Feed>> getEpisodes(@Query("channel") int channelId, @Query("format") String sort);

    @GET("episodes/likes/{user_id}/")
    Call<List<Feed>> getLikedEpisodes(@Path(value = "user_id", encoded = true) int userId,@Query("format") String sort);

    @GET("kids/1")
    Call<List<ResponseBody>> getKids(@Query("format") String sort);

    @POST("episodes/action/")
    @FormUrlEncoded
    Call<ActionResponse> postLike(@Field("user") int userId,
                                  @Field("action") String action,
                                  @Field("episode") int episode);

    @Multipart
    @POST("kidaudios/{user_id}/")
    Call<UploadResponse> addRecord(@Path(value = "user_id", encoded = true) int id,
                                   @Query("audio_name") String fileName,
                                   @Query("kid_id") int userId,
                                   @Query("description") String desription,
                                   @Part MultipartBody.Part file);


    @POST("parents/")
    @FormUrlEncoded
    Call<ParentUser> createParent(@Field("parent_name") String name,
                                  @Field("user_token") String token);


    @POST("kids/add/")
    @FormUrlEncoded
    Call<KidUser> addKid(
            @Field("kid_name") String kidname,
            @Field("date_of_birth") String dob,
            @Field("gender") String gender,
            @Field("grade") String grade,
            @Field("school") String school,
            @Field("uid") Integer id);


    @POST("exercises/{user_id}")
    @FormUrlEncoded
    Call<Post> savePost(@Path(value = "user_id", encoded = true) int kidid,
                        @Field("exercise_id") long id,
                        @Field("userId") long userId,
                        @Field("response[]") ArrayList<String> response,
                        @Field("duration") long duration,
                        @Field("completed") boolean bool,
                        @Field("correct[]") ArrayList<Boolean> correct);


    @GET("parentkids/{parent_id}/")
    Call<List<KidUser>> getParentsKids(@Path(value = "parent_id", encoded = true) int userId,@Query("format") String sort);


    @POST("coupons/")
    @FormUrlEncoded
    Call<Coupon> giveUserFullAccessCall(@Field("kid_id") int user_id,
                                        @Field("code") String code);


    @GET("episodes/trending/")
    Call<List<TrendingEpisode>> getTrendingEpisodes(@Query("format") String format);*/

    @POST("adminlogin/")
    @FormUrlEncoded
    Call<LoginResponse> loginRequest(@Field("email") String email,
                                     @Field("password") String password);

}
