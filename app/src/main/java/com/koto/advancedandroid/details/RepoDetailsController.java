package com.koto.advancedandroid.details;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bluelinelabs.conductor.Controller;
import com.koto.advancedandroid.R;
import com.koto.advancedandroid.base.BaseController;

public class RepoDetailsController extends BaseController {

    static final String REPO_NAME_KEY = "repo_name";
    static final String REPO_OWNER_KEY = "repo_owner";

    public static Controller newInstance(String repoName, String repoOwner) {
        Bundle bundle = new Bundle();
        bundle.putString(REPO_NAME_KEY, repoName);
        bundle.putString(REPO_OWNER_KEY, repoOwner);

        return new RepoDetailsController(bundle);
    }

    public RepoDetailsController(@Nullable Bundle bundle) {
        super(bundle);
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_repo_details;
    }
}
