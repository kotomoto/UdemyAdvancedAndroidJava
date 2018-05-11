package com.koto.advancedandroid.trending;

import com.koto.advancedandroid.data.RepoRequester;
import com.koto.advancedandroid.di.ScreenScope;
import com.koto.advancedandroid.model.Repo;

import javax.inject.Inject;

@ScreenScope
public class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

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

    @Override
    public void onRepoClicked(Repo repo) {

    }
}
