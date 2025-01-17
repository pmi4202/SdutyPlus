package com.d205.data.api

import com.d205.data.model.BaseResponse
import com.d205.data.model.user.UserResponse
import com.d205.domain.model.user.UserDto
import retrofit2.http.*

interface UserApi {

    // 닉네임 중복 체크
    @GET("user/check/{nickname}")
    suspend fun checkNickname(@Path("nickname") nickname: String): BaseResponse<Boolean>

    // 카카오 로그인
    @POST("user/kakao/login")
    suspend fun loginKakaoUser(@Body token: String): BaseResponse<UserResponse>

    // 네이버 로그인
    @POST("user/naver/login")
    suspend fun loginNaverUser(@Body token: String): BaseResponse<UserResponse>

    // 테스트 로그인
    @POST("user/test/login")
    suspend fun loginTestUser(): BaseResponse<UserResponse>

    // 회원 가입
    @POST("user/reg")
    suspend fun joinUser(@Body user: UserDto): BaseResponse<UserResponse>

    // 프로필 수정
    @PUT("user")
    suspend fun updateUser(@Body user: UserDto): BaseResponse<UserResponse>

    // 유저 정보 조회
    @GET("user")
    suspend fun getUser(): BaseResponse<UserResponse>

    // 유저 정보 삭제
    @DELETE("user")
    suspend fun deleteUser(): BaseResponse<Boolean>

    // jwt 사용 가능 여부 확인
    @GET("user/token")
    suspend fun checkJwt(): BaseResponse<Boolean>
}