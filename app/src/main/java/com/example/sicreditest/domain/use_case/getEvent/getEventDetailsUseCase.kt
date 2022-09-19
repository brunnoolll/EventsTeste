package com.example.sicreditest.domain.use_case.getEvent

import com.example.sicreditest.commom.Constant
import com.example.sicreditest.commom.Resource
import com.example.sicreditest.data.remote.dto.toDetails
import com.example.sicreditest.domain.model.EventDetails
import com.example.sicreditest.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class getEventDetailsUseCase @Inject constructor(
    private val repository : EventRepository
){
    operator fun invoke(eventId : String) : Flow<Resource<EventDetails>> = flow {
        try {
            emit(Resource.Loading<EventDetails>())
            val eventDetails = repository.getEventById(eventId).toDetails()
            emit(Resource.Sucess<EventDetails>(eventDetails))
        }catch (e : HttpException){
            emit(Resource.Error<EventDetails>(e.localizedMessage?:
            Constant.ERROR_HTTP))
        }catch (e : IOException){
            emit(Resource.Error<EventDetails>(Constant.ERROR_HTTP_SERVER))
        }
    }
}