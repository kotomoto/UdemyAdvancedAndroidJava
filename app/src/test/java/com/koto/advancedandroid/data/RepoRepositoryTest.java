package com.koto.advancedandroid.data;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.koto.advancedandroid.model.Repo;
import com.koto.advancedandroid.testutils.TestUtils;

import io.reactivex.Single;

import javax.inject.Provider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class RepoRepositoryTest {

    @Mock
    Provider<RepoRequester> repoRequesterProvider;

    @Mock
    RepoRequester repoRequester;

    private RepoRepository repository;
    private TrendingReposResponse trendingReposResponse;
    private Repo javaDesignPatternsRepo;
    private Repo otherRepo;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(repoRequesterProvider.get()).thenReturn(repoRequester);

        trendingReposResponse = TestUtils.loadJson("mock/get_trending_repos.json", TrendingReposResponse.class);
        when(repoRequester.getTrendingRepos()).thenReturn(Single.just(trendingReposResponse.repos()));

        javaDesignPatternsRepo = trendingReposResponse.repos().get(0);
        otherRepo = trendingReposResponse.repos().get(1);

        repository = new RepoRepository(repoRequesterProvider);
    }

    @Test
    public void getTrendingRepos() {
        repository.getTrendingRepos().test().assertValue(trendingReposResponse.repos());

        ArrayList<Repo> modifiedList = new ArrayList<>(trendingReposResponse.repos());
        modifiedList.remove(0);
        when(repoRequester.getTrendingRepos()).thenReturn(Single.just(modifiedList));

        repository.getTrendingRepos().test().assertValue(trendingReposResponse.repos());
    }

    @Test
    public void getRepo() {
        repository.getTrendingRepos().subscribe();

        when(repoRequester.getRepo(anyString(), anyString())).thenReturn(Single.just(otherRepo));

        repository.getRepo("iluwatar", "java-design-patterns").test().assertValue(javaDesignPatternsRepo);
        repository.getRepo("NotInCache", "NotInCache").test().assertValue(otherRepo);
    }
}