package com.d205.data.repository.user.remote

import android.annotation.SuppressLint
import android.util.Log
import com.d205.data.api.UserApi
import com.d205.data.model.user.UserEntity
import com.d205.data.model.user.UserResponse
import com.d205.domain.model.user.UserDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


private const val TAG = "UserRemoteDataSourceImpl"
class UserRemoteDataSourceImpl @Inject constructor(
    private val userApi: UserApi
): UserRemoteDataSource {

    @SuppressLint("LongLogTag")
    override fun joinUser(user: UserDto): Flow<UserEntity> = flow {
        Log.d(TAG, "joinUser: $user")
        val response = userApi.updateProfile(user)
        Log.d(TAG, "joinUser api response : $response")

        if(response.status == 200 && response.data != null) {
            emit(response.data)
        }
        else {
            emit(UserEntity())
        }
    }


    @SuppressLint("LongLogTag")
    override fun checkNickname(nickname: String): Flow<Boolean> = flow {
        Log.d(TAG, "checkNickname: $nickname")
        val response = userApi.checkNickname(nickname)
        Log.d(TAG, "checkNickname api response : $response")

        if(response.status == 200 && response.data != null) {
            emit(response.data)
        }
        else {
            emit(false)
        }
    }

    @SuppressLint("LongLogTag")
    override fun loginKakaoUser(token: String): Flow<UserResponse> = flow {
        Log.d(TAG, "loginKakaoUser: $TAG token : $token")

        Log.d(TAG, "loginKakaoUser: start api!")
        val response = userApi.loginKakaoUser(token)
        Log.d(TAG, "loginKakaoUser: response : ${response.data}")
        if (response.status == 200 && response.data != null) {
            emit(response.data)
        } else {
            emit(UserResponse())
        }
    }


    @SuppressLint("LongLogTag")
    override fun loginNaverUser(token: String): Flow<UserResponse> = flow {
        Log.d(TAG, "loginNaverUser: $TAG token : $token")

        Log.d(TAG, "loginNaverUser: start api!")
        val response = userApi.loginNaverUser(token)
        Log.d(TAG, "loginNaverUser: response code : ")
        if (response.status == 200 && response.data != null) {
            emit(response.data)
        } else {
            emit(UserResponse())
        }
    }

    @SuppressLint("LongLogTag")
    override fun getUser(): Flow<UserResponse>  = flow {
        val response = userApi.getUser()
        if(response.status == 200 && response.data != null) {
            emit(response.data)
        }
        else {
            emit(UserResponse())
        }
    }
}