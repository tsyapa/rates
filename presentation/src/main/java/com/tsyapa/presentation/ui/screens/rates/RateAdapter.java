package com.tsyapa.presentation.ui.screens.rates;

import android.graphics.drawable.PictureDrawable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tsyapa.presentation.R;
import com.tsyapa.presentation.ui_model.Rate;
import com.tsyapa.presentation.tools.CustomTextWatcher;
import com.tsyapa.presentation.tools.Utils;
import com.tsyapa.presentation.tools.glide.GlideApp;
import com.tsyapa.presentation.tools.glide.SvgSoftwareLayerSetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.tsyapa.presentation.Constants.IMAGE_URL;
import static com.tsyapa.presentation.Constants.SVG_SUFFIX;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.RateViewHolder> {

    public interface OnBaseAmountChanged {

        void onBaseAmountChanged(double amount);

        void onBaseCurrencyChanged(String currency);
    }

    private OnBaseAmountChanged callback;
    private RecyclerView recyclerView;

    private List<Rate> rates;

    RateAdapter(OnBaseAmountChanged callback) { this.callback = callback; }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RateViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RateViewHolder holder, int position) {
        Rate rate = rates.get(position);
        holder.itemView.setTag(position);
        if (rate != null) {
            String svgUrl = IMAGE_URL + rate.getCurrency() + SVG_SUFFIX;
            GlideApp.with(holder.ivFlag).as(PictureDrawable.class)
                    .load(svgUrl)
                    .listener(new SvgSoftwareLayerSetter())
                    .into(holder.ivFlag);

            holder.tvCurrency.setText(rate.getCurrency());

            double amount = rate.getAmount();
            if (amount > 0) {
                //check if amount is decimal
                holder.etAmount.setText(amount % 1 == 0 ? String.valueOf((int) amount)
                        : String.format(Locale.getDefault(), "%.2f", amount));
            } else {
                holder.etAmount.setText("");
            }
            holder.etAmount.setSelection(holder.etAmount.getText().length());
        }
    }

    @Override
    public int getItemCount() { return rates != null ? rates.size() : 0; }

    void updateRates(@NonNull Map<String, Double> mapRates, Rate baseRate) {
        if (rates == null) {
            this.rates = transformMapToList(mapRates);
            rates.add(0, baseRate);
            recalculateAmounts(baseRate.getAmount());
            notifyDataSetChanged();
        } else {
            setNewRatesAndUpdateAmounts(rates, mapRates, baseRate);
        }
    }

    private List<Rate> transformMapToList(Map<String, Double> mapRates) {
        List<Rate> rates = new ArrayList<>(mapRates.size());
        for (Map.Entry<String, Double> entry : mapRates.entrySet()) {
            rates.add(new Rate(entry.getKey(), entry.getValue()));
        }
        return rates;
    }

    private void setNewRatesAndUpdateAmounts(List<Rate> rates, Map<String, Double> mapNewRates, Rate baseRate) {
        for (int i = 0; i < rates.size(); i++) {
            Rate rate = rates.get(i);
            Double newRate = mapNewRates.get(rate.getCurrency());
            if (newRate != null && rate.getRate() != newRate) {
                rate.setRate(newRate);
                recalculateAmount(baseRate.getAmount(), rate);
                notifyItemChanged(i);
            }
        }
    }

    private void recalculateAmounts(double amountOfFirstElement) {
        for (Rate rate : rates) {
            recalculateAmount(amountOfFirstElement, rate);
        }
    }

    private void recalculateAmount(double amountOfFirstElement, Rate rate) {
        rate.setAmount(amountOfFirstElement * rate.getRate());
    }

    private void moveItemToTop(int positionFrom) {
        if (positionFrom != 0) {
            Rate rate = rates.get(positionFrom);
            rates.remove(positionFrom);
            rates.add(0, rate);
            notifyItemRangeChanged(0, positionFrom + 1);
            notifyItemMoved(positionFrom, 0);
            if (callback != null) {
                callback.onBaseCurrencyChanged(rate.getCurrency());
            }
        }
    }

    class RateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CustomTextWatcher {

        ImageView ivFlag;
        TextView tvCurrency;
        EditText etAmount;

        RateViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFlag = itemView.findViewById(R.id.iv_item_rate_flag);
            tvCurrency = itemView.findViewById(R.id.tv_item_rate_currency);
            etAmount = itemView.findViewById(R.id.et_item_rate_amount);

            itemView.setOnClickListener(this);
            etAmount.addTextChangedListener(this);
            etAmount.setOnFocusChangeListener((View view, boolean hasFocus) -> {
                if (hasFocus && (int) itemView.getTag() != 0) {
                    onClick(itemView);
                }
            });
        }

        @Override
        public void onClick(View view) {
            moveItemToTop((int) view.getTag());
            if (recyclerView.getLayoutManager() != null) {
                recyclerView.getLayoutManager().scrollToPosition(0);
            }
            etAmount.post(() -> Utils.showKeyboard(etAmount.getContext(), etAmount));
        }

        @Override
        public void afterTextChanged(Editable editable) {
            int position = (int) itemView.getTag();
            // recalculate amounts if first position or move item to top if not
            if (position == 0) {
                double amount = editable.length() > 0 ? Double.valueOf(editable.toString()) : 0;
                recalculateAmounts(amount);
                if (recyclerView != null && !recyclerView.isComputingLayout()) {
                    notifyItemRangeChanged(1, rates.size() - 1);
                }
                if (callback != null) {
                    callback.onBaseAmountChanged(amount);
                }
            } else {
                if (recyclerView != null && !recyclerView.isComputingLayout()) {
                    moveItemToTop(position);
                }
            }
        }
    }
}