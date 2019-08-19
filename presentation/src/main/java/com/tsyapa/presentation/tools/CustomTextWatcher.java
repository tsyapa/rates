package com.tsyapa.presentation.tools;

import android.text.Editable;
import android.text.TextWatcher;

public interface CustomTextWatcher extends TextWatcher {

    @Override
    default void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    default void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    default void afterTextChanged(Editable editable) { }
}
