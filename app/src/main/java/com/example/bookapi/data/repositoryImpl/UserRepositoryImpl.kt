package com.example.bookapi.data.repositoryImpl

import android.content.Context
import com.example.bookapi.data.model.ResultData
import com.example.bookapi.data.remote.dto.user.request.PostRateRequest
import com.example.bookapi.data.remote.dto.user.request.PostUserBooksRequest
import com.example.bookapi.data.remote.dto.user.response.AllUsers
import com.example.bookapi.data.remote.dto.user.response.PostRateResponse
import com.example.bookapi.data.remote.dto.user.response.PostUserBooksResponse
import com.example.bookapi.data.remote.service.UserApi
import com.example.bookapi.domain.repository.UserRepository
import com.example.bookapi.utils.hasConnection
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    @ApplicationContext private val context: Context
) : UserRepository {

    override suspend fun getUsers(): Flow<ResultData<AllUsers>> =
        flow<ResultData<AllUsers>>{
            if (hasConnection(context = context)) {
                emit(ResultData.HasConnection(true))
                val response = userApi.getUsers()
                emit(ResultData.Loading(true))
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResultData.Success(it))
                        emit(ResultData.Loading(false))
                    }
                } else {
                    response.errorBody()?.let {
                        emit(ResultData.Fail(it.string()))
                        emit(ResultData.Loading(false))
                    }
                }
            } else {
                emit(ResultData.HasConnection(false))
            }
        }.catch { error ->
            emit(ResultData.Loading(false))
            error.message?.let { msg ->
                emit(ResultData.Fail(msg))
            }

        }.flowOn(Dispatchers.IO)

    override suspend fun postUser(postUserBooksRequest: PostUserBooksRequest): Response<PostUserBooksResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun rateBook(postRateRequest: PostRateRequest): Response<PostRateResponse> {
        TODO("Not yet implemented")
    }
}