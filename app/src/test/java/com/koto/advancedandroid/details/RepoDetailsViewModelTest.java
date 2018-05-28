package com.koto.advancedandroid.details;

import com.koto.advancedandroid.R;
import com.koto.advancedandroid.model.Contributor;
import com.koto.advancedandroid.model.Repo;
import com.koto.advancedandroid.testutils.TestUtils;
import com.squareup.moshi.Types;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class RepoDetailsViewModelTest {

    private RepoDetailsViewModel viewModel;

    private Repo repo = TestUtils.loadJson("mock/get_repo.json", Repo.class);
    private List<Contributor> contributors = TestUtils.loadJson("mock/get_contributors.json", Types.newParameterizedType(List.class, Contributor.class));

    @Before
    public void setUp() {
        viewModel = new RepoDetailsViewModel();
    }

    @Test
    public void details() throws Exception {
        viewModel.processRepo().accept(repo);

        viewModel.details().test().assertValue(
                RepoDetailState.builder()
                        .loading(false)
                        .name("java-design-patterns")
                        .description("Design patterns implemented in Java")
                        .createdDate("Aug 09, 2014")
                        .updatedDate("May 14, 2018")
                        .build()
        );
    }

    @Test
    public void contributors() throws Exception {
        viewModel.processContributors().accept(contributors);

        viewModel.contributors().test().assertValue(
                ContributorState.builder()
                        .loading(false)
                        .contributors(contributors)
                        .build()
        );
    }

    @Test
    public void detailsError() throws Exception {
        viewModel.detailsError().accept(new IOException());

        viewModel.details().test().assertValue(
                RepoDetailState.builder()
                        .loading(false)
                        .errorRes(R.string.api_error_single_repo)
                        .build()
        );
    }

    @Test
    public void contributorsError() throws Exception {
        viewModel.contributorsError().accept(new IOException());

        viewModel.contributors().test().assertValue(
                ContributorState.builder()
                        .loading(false)
                        .errorRes(R.string.api_error_contributors)
                        .build()
        );
    }
}