package com.koto.advancedandroid.trending;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.koto.advancedandroid.R;
import com.koto.advancedandroid.di.ScreenScope;
import com.koto.advancedandroid.model.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope
class TrendingReposViewModel {

    private final BehaviorRelay<List<Repo>> reposRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    TrendingReposViewModel() {
    }

    Observable<List<Repo>> repos() {
        return reposRelay;
    }

    Observable<Boolean> loading() {
        return loadingRelay;
    }

    Observable<Integer> error() {
        return errorRelay;
    }

    Consumer<List<Repo>> reposUpdated() {
        errorRelay.accept(-1);
        return reposRelay;
    }

    Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    Consumer<Throwable> onError() {
        return throwable -> {
            Timber.e(throwable, "Error loading Repos");
            errorRelay.accept(R.string.api_error_repos);
        };
    }
}
