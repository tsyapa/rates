package com.tsyapa.presentation.ui.screens.base;

import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.tsyapa.presentation.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<VM extends ViewModel> extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    protected VM viewModel;

    protected View btnBack;
    protected View toolbar;
    protected View scrollView;
    protected RecyclerView recyclerView;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(provideViewModelClass());

        btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(view -> onBackPressed());
        }

        toolbar = findViewById(R.id.toolbar);
        scrollView = findViewById(R.id.scroll_view);
        recyclerView = findViewById(R.id.recycler_view);
        setToolbarElevationOnScroll(toolbar, scrollView);
        setToolbarElevationOnScroll(toolbar, recyclerView);

        initUi();
        configure();
    }

    @LayoutRes
    protected abstract int getContentView();

    protected abstract Class<VM> provideViewModelClass();

    protected abstract void initUi();

    protected abstract void configure();

    protected VM getViewModel() { return viewModel; }

    protected void setVisibility(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void setToolbarElevationOnScroll(View toolbar, View scrollableView) {
        if (toolbar != null && scrollableView != null) {
            toolbar.setStateListAnimator(AnimatorInflater.loadStateListAnimator(this,
                    R.animator.sla_toolbar));
            scrollableView.getViewTreeObserver().addOnScrollChangedListener(() ->
                    toolbar.setSelected(scrollableView.canScrollVertically(-1)));
        }
    }
}