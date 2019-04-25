package com.meticulous.wardrobe.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.meticulous.wardrobe.R;

/**
 * Created by c74241 on 8/7/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Dialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wardrobe_base_activity);

        progressBar = createProgressBar(this);
    }

    /**
     * Creates and shows the progress bar (spinning circle) without any text.
     *
     * @return Dialog reference so you can dismiss it later
     */
    private Dialog createProgressBar(Context context) {
        Dialog dialog = new Dialog(context);
        ProgressBar pbar = new ProgressBar(context);
        pbar.setVisibility(View.VISIBLE);
        dialog.setContentView(pbar);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    public void showProgressDialog() {
        if (progressBar != null && !progressBar.isShowing()) {
            progressBar.show();
        }
    }

    public void clearProgressDialog() {
        if (progressBar != null) {
            progressBar.dismiss();
        }
    }
}
