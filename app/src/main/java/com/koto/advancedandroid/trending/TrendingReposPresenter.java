package com.koto.advancedandroid.trending;

import com.koto.advancedandroid.data.RepoRepository;
import com.koto.advancedandroid.di.ScreenScope;
import com.koto.advancedandroid.model.Repo;

import javax.inject.Inject;

@ScreenScope
public class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

    private TrendingReposViewModel viewModel;
    private RepoRepository repoRepository;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRepository repoRepository) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;

        loadRepos();
    }

    private void loadRepos() {
        repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((data, throwable) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError());
    }

    @Override
    public void onRepoClicked(Repo repo) {

    }
}
