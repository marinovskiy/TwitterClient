package ua.marinovskiy.twitterclient.screens.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initViews(savedInstanceState);
        if (getToolbar() != null) {
            setSupportActionBar(getToolbar());
        }
        if (!TextUtils.isEmpty(getActivityTitle()) && getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getActivityTitle());
        }
    }

    @Override
    protected final void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @Nullable
    protected abstract Toolbar getToolbar();

    @Nullable
    protected abstract String getActivityTitle();

    protected abstract void initViews(@Nullable Bundle savedInstanceState);

}
