package com.example.sicreditest.domain.use_case.getEventList

import com.example.sicreditest.commom.Constant
import com.example.sicreditest.commom.Resource
import com.example.sicreditest.data.remote.dto.toEvent
import com.example.sicreditest.domain.model.EventItem
import com.example.sicreditest.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class getEventUseCase @Inject constructor(
    private val repository : EventRepository
) {
    operator fun invoke() : Flow<Resource<List<EventItem>>> = flow {
        try {
            emit(Resource.Loading<List<EventItem>>())
            val Events = repository.getEvent().map { it.toEvent() }
            emit(Resource.Sucess(Events))
        }catch (e : HttpException){
            emit(Resource.Error<List<EventItem>>(e.localizedMessage?:
            Constant.ERROR_HTTP))
        }catch (e : IOException){
            emit(Resource.Error<List<EventItem>>(Constant.ERROR_HTTP_SERVER))
        }
    }
}