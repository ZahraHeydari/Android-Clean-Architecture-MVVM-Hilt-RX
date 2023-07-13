package com.android.domain.usecase

import com.android.domain.model.Album
import com.android.domain.repository.AlbumRepository
import com.android.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetAlbumsUseCase @Inject constructor(
    private val repository: AlbumRepository
) : SingleUseCase<List<Album>>() {

    override fun buildUseCaseSingle(): Single<List<Album>> {
        return repository.getAlbums()
    }
}
