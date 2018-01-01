package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.network.response.MovieCreditResponse;
import com.etiennelawlor.moviehub.data.network.response.MovieResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.domain.MovieDetailsDomainContract;
import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsPresenter;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsUiContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MovieDetailsPresenterTest {

    // region Test Doubles

    // Mocks
    @Mock
    private MovieDetailsUiContract.View mockMovieDetailsView;
    @Mock
    private MovieDetailsDomainContract.UseCase mockMovieDetailsUseCase;

    // Stubs
    private ArgumentCaptor<DisposableSingleObserver> disposableSingleObserverArgumentCaptor;
    // endregion

    // region Member Variables
    private MovieDetailsDomainModel movieDetailsDomainModel;
    private MovieDetailsPresenter movieDetailsPresenter;
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        movieDetailsPresenter = new MovieDetailsPresenter(mockMovieDetailsView, mockMovieDetailsUseCase);
    }

    // region Test Methods
//    @Test(expected = IOException.class)
    @Test
    public void onLoadMovieDetails_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        MovieResponse movie = new MovieResponse();
        movie.setId(1);
        List<MovieCreditResponse> cast = new ArrayList<>();
        List<MovieCreditResponse> crew = new ArrayList<>();
        List<MovieResponse> similarMovies = new ArrayList<>();
        String rating = "";
        movieDetailsDomainModel = new MovieDetailsDomainModel(movie, cast, crew, similarMovies, rating);

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onLoadMovieDetails(movie.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        disposableSingleObserverArgumentCaptor = ArgumentCaptor.forClass(DisposableSingleObserver.class);
        verify(mockMovieDetailsUseCase).getMovieDetails(anyInt(), disposableSingleObserverArgumentCaptor.capture());
        disposableSingleObserverArgumentCaptor.getValue().onError(new UnknownHostException());

        verify(mockMovieDetailsView).showErrorView();
    }

    @Test
    public void onLoadMovieDetails_shouldShowMovieDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        MovieResponse movie = new MovieResponse();
        movie.setId(1);
        List<MovieCreditResponse> cast = new ArrayList<>();
        List<MovieCreditResponse> crew = new ArrayList<>();
        List<MovieResponse> similarMovies = new ArrayList<>();
        String rating = "";
        movieDetailsDomainModel = new MovieDetailsDomainModel(movie, cast, crew, similarMovies, rating);

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onLoadMovieDetails(movie.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        disposableSingleObserverArgumentCaptor = ArgumentCaptor.forClass(DisposableSingleObserver.class);
        verify(mockMovieDetailsUseCase).getMovieDetails(anyInt(), disposableSingleObserverArgumentCaptor.capture());
        disposableSingleObserverArgumentCaptor.getValue().onSuccess(movieDetailsDomainModel);

        verify(mockMovieDetailsView).showMovieDetails(movieDetailsDomainModel);
    }

    @Test
    public void onPersonClick_shouldOpenPersonDetails() {
        // 1. (Given) Set up conditions required for the test
        PersonResponse person = new PersonResponse();

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onPersonClick(person);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).openPersonDetails(person);

        verifyZeroInteractions(mockMovieDetailsUseCase);
    }

    @Test
    public void onMovieClick_shouldOpenMovieDetails() {
        // 1. (Given) Set up conditions required for the test
        MovieResponse movie = new MovieResponse();

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onMovieClick(movie);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).openMovieDetails(movie);

        verifyZeroInteractions(mockMovieDetailsUseCase);
    }

    @Test
    public void onScrollChange_shouldShowToolbarTitle_whenScrolledPastThreshold() {
        // 1. (Given) Set up conditions required for the test
        boolean isScrolledPastThreshold = true;

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onScrollChange(isScrolledPastThreshold);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).showToolbarTitle();

        verifyZeroInteractions(mockMovieDetailsUseCase);
    }

    @Test
    public void onScrollChange_shouldHideToolbarTitle_whenNotScrolledPastThreshold() {
        // 1. (Given) Set up conditions required for the test
        boolean isScrolledPastThreshold = false;

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onScrollChange(isScrolledPastThreshold);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).hideToolbarTitle();

        verifyZeroInteractions(mockMovieDetailsUseCase);
    }

    @Test
    public void onDestroyView_shouldClearSubscriptions() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockMovieDetailsView);
        verify(mockMovieDetailsUseCase).clearDisposables();
    }

    // endregion
}
