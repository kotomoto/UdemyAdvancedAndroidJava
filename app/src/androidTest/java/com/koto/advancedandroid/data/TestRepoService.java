package com.koto.advancedandroid.data;

import com.koto.advancedandroid.test.TestUtils;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class TestRepoService implements RepoService {

    private boolean sendError;
    private TestUtils testUtils;

    @Inject
    TestRepoService(TestUtils testUtils) {
        this.testUtils = testUtils;
    }

    @Override
    public Single<TrendingReposResponse> getTrendingRepos() {
        if (!sendError) {
            TrendingReposResponse response = testUtils.loadJson("mock/get_trending_repos.json", TrendingReposResponse.class);
            return Single.just(response);
        }
        return Single.error(new IOException());
    }

    public void setSendError(boolean sendError) {
        this.sendError = sendError;
    }
}
