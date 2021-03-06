package com.etiennelawlor.moviehub.presentation.persons;

import com.etiennelawlor.moviehub.presentation.base.BasePresenter;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.PersonsPresentationModel;

import java.util.List;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface PersonsPresentationContract {

    interface View {
        void showEmptyView();
        void hideEmptyView();
        void showErrorView();
        void hideErrorView();
        void setErrorText(String errorText);
        void showLoadingView();
        void hideLoadingView();
        void addHeader();
        void addFooter();
        void removeFooter();
        void showErrorFooter();
        void showLoadingFooter();
        void addPersonsToAdapter(List<PersonPresentationModel> persons);
        void loadMoreItems();
        void setPersonsPresentationModel(PersonsPresentationModel personsPresentationModel);

        // Navigation methods
        void openPersonDetails(PersonPresentationModel person);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularPersons(int currentPage);
        void onPersonClick(PersonPresentationModel person);
        void onScrollToEndOfList();
    }
}
