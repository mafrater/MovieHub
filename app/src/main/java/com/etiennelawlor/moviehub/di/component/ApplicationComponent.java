package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.MovieHubApplication;
import com.etiennelawlor.moviehub.di.module.AndroidModule;
import com.etiennelawlor.moviehub.di.module.ApplicationModule;
import com.etiennelawlor.moviehub.di.module.MovieDetailsModule;
import com.etiennelawlor.moviehub.di.module.MoviesModule;
import com.etiennelawlor.moviehub.di.module.NetworkModule;
import com.etiennelawlor.moviehub.di.module.PersonDetailsModule;
import com.etiennelawlor.moviehub.di.module.PersonsModule;
import com.etiennelawlor.moviehub.di.module.SearchModule;
import com.etiennelawlor.moviehub.di.module.TelevisionShowDetailsModule;
import com.etiennelawlor.moviehub.di.module.TelevisionShowsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, AndroidModule.class} )
public interface ApplicationComponent {
    // Setup injection targets
    void inject(MovieHubApplication target);

    NetworkComponent linkNetworkComponent(NetworkModule networkModule);
    MoviesComponent linkMoviesComponent(MoviesModule moviesModule);
    MovieDetailsComponent linkMovieDetailsComponent(MovieDetailsModule movieDetailsModule);
    TelevisionShowsComponent linkTelevisionShowsComponent(TelevisionShowsModule televisionShowsModule);
    TelevisionShowDetailsComponent linkTelevisionShowDetailsComponent(TelevisionShowDetailsModule televisionShowDetailsModule);
    PersonsComponent linkPersonsComponent(PersonsModule personsModule);
    PersonDetailsComponent linkPersonDetailsComponent(PersonDetailsModule personDetailsModule);
    SearchComponent linkSearchComponent(SearchModule searchModule);
}
