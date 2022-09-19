package com.example.sicreditest.domain.use_case.setCheckin

import com.example.sicreditest.commom.Constant
import com.example.sicreditest.commom.Resource
import com.example.sicreditest.data.remote.dto.CheckinDto
import com.example.sicreditest.domain.model.EventCheckinRequest
import com.example.sicreditest.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class setCheckInUseCase @Inject constructor(
    private val repository : EventRepository
) {
    operator fun invoke(request : EventCheckinRequest) : Flow<Resource<CheckinDto>> = flow {
        try {
            emit(Resource.Loading<CheckinDto>())
            val response = repository.checkInEvent(request)
            emit(Resource.Sucess<CheckinDto>(response))
        }catch (e : HttpException){
            emit(Resource.Error<CheckinDto>(e.localizedMessage?:
            Constant.ERROR_HTTP_SERVER))
        }catch (e : IOException){
            emit(Resource.Error<CheckinDto>(Constant.ERROR_HTTP_SERVER))
        }
    }
}