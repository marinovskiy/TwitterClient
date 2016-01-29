package ua.marinovskiy.twitterclient.screens.fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initViews(savedInstanceState);
    }

    @Override
    public final void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initViews(@Nullable Bundle savedInstanceState);
}
