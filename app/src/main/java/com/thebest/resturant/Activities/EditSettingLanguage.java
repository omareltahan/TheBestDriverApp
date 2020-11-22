package com.thebest.resturant.Activities;

import android.os.Bundle;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import com.thebest.resturant.LocaleHelper;
import com.thebest.resturant.R;

public class EditSettingLanguage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_language);
        ((RadioGroup)findViewById(R.id.radio)).setOnCheckedChangeListener((radioGroup, i) -> {
            if (((RadioGroup)findViewById(R.id.radio)).getCheckedRadioButtonId() == R.id.arabic)
                LocaleHelper.setLocale(EditSettingLanguage.this, "ar");
            else
                LocaleHelper.setLocale(EditSettingLanguage.this, "en");
            finish();
        });
        findViewById(R.id.back).setOnClickListener(view -> {
            finish();
        });
    }
}