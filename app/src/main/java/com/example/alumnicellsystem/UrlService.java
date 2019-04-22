package com.example.alumnicellsystem;

import com.example.alumnicellsystem.Responses.AddFacultyResponse;
import com.example.alumnicellsystem.Responses.AlumniConfirmResponse;
import com.example.alumnicellsystem.Responses.AlumniLoginResponse;
import com.example.alumnicellsystem.Responses.AlumniSignupResponse;
import com.example.alumnicellsystem.Responses.AlumniUpdateResponse;
import com.example.alumnicellsystem.Responses.FacultyResponse;
import com.example.alumnicellsystem.Responses.LoginResponse;
import com.example.alumnicellsystem.Responses.SearchResponse;
import com.example.alumnicellsystem.Responses.SignupResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;



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

    @POST("login/")
    @FormUrlEncoded
    Call<LoginResponse> loginRequest(@Field("email") String email,
                                     @Field("password") String password);


    @POST("alumnilogin/")
    @FormUrlEncoded
    Call<AlumniLoginResponse> alumniLoginRequest(@Field("enrollment_no") String enrollmentNo,
                                                 @Field("password") String password);


    @POST("alumnisignup/")
    @FormUrlEncoded
    Call<AlumniSignupResponse> alumniSignUpRequest(@Field("enrollment_no") String enrollmentNo);


    @POST("alumniconfirm/")
    @FormUrlEncoded
    Call<AlumniConfirmResponse> alumniConfirmRequest(@Field("enrollment_no") String enrollmentNo,
                                                     @Field("otp") String otp,
                                                     @Field("password") String password);


    @POST("signup/")
    @FormUrlEncoded
    Call<SignupResponse> signupRequest(@Field("email") String email,
                                      @Field("password") String password,
                                       @Field("name")String name,
                                       @Field("designation") int designation,
                                       @Field("department") String department,
                                       @Field("phone_no") String phoneNo);


    @POST("confirm/")
    @FormUrlEncoded
    Call<SignupResponse> otpRequest(@Field("email") String email,
                                       @Field("otp") String otp);

    @POST("dashboard/custom-search")
    @FormUrlEncoded
    Call<SearchResponse> customSearchRequest(@FieldMap Map<String, String> options);

/*
    @PUT("dashboard/alumni/update")
    @FormUrlEncoded
    Call<UpdateAlumniResponse> updateAlumniRequest(@FieldMap Map<String, String> options);*/

    @POST("admindashboard/add")
    @FormUrlEncoded
    Call<AddFacultyResponse> addFacultyRequest(@Field("email")String email,
                                               @Field("role")int role,
                                               @Field("active")int active);

    @Multipart
    @POST("dashboard/upload")
    Call<ResponseBody> postExcel(@Part MultipartBody.Part file, @Part("upload") RequestBody name);

    @POST("dashboard/viewfac")
    Call<FacultyResponse> viewFaculty();

    @PUT("alumnidashboard/update")
    @FormUrlEncoded
    Call<AlumniUpdateResponse> updateAlumni(@Field("email")String email,
                                            @Field("address")String address,
                                            @Field("mobile")String mobile,
                                            @Field("company_1")String company,
                                            @Field("_id")String id);

}
