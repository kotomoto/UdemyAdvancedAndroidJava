package com.koto.advancedandroid.trending;

import com.koto.advancedandroid.data.RepoRequester;
import com.koto.advancedandroid.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class TrendingReposPresenter {

    private TrendingReposViewModel viewModel;
    private RepoRequester repoRequester;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRequester repoRequester) {
        this.viewModel = viewModel;
        this.repoRequester = repoRequester;

        loadRepos();
    }

    private void loadRepos() {
        repoRequester.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((data, throwable) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError());
    }
}
