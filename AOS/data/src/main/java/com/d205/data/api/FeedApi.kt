package com.d205.data.api

import com.d205.data.model.BaseResponse
import com.d205.data.model.feed.HomeFeedResponse
import com.d205.data.model.mypage.MyFeedResponse
import com.d205.domain.model.common.PagingResult
import okhttp3.MultipartBody
import retrofit2.http.*

interface FeedApi {
    @GET("feed/writer")
    suspend fun getUserStoryList(
        @Query("page")page: Int,
        @Query("size")pageSize: Int): BaseResponse<PagingResult<MyFeedResponse>>

    @GET("feed")
    suspend fun getHomeFeeds(
        @Query("page")page: Int,
        @Query("size")pageSize: Int): BaseResponse<PagingResult<HomeFeedResponse>>

    @Multipart
    @POST("feed")
    suspend fun createFeed(@Part imageFile: MultipartBody.Part, @Part content: MultipartBody.Part): BaseResponse<Boolean>

}