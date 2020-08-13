package com.example.doanltdd.GiaoDien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanltdd.R;

import java.util.concurrent.Executor;

public class VanTay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_van_tay);
        TextView mgs_txt = findViewById(R.id.txt_msg);
        Button login_btn=findViewById(R.id.login_btn);

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()){
            case  BiometricManager.BIOMETRIC_SUCCESS:
                mgs_txt.setText("xin chờ login");
                mgs_txt.setTextColor(Color.parseColor("#Fafafa"));
                break;
            case  BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                mgs_txt.setText("loi");
                login_btn.setVisibility(View.GONE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                mgs_txt.setText("loi");
                login_btn.setVisibility(View.GONE);
                break;
            case  BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                mgs_txt.setText("loi");
                login_btn.setVisibility(View.GONE);
                break;
        }
        Executor executor = ContextCompat.getMainExecutor(this);
        final BiometricPrompt biometricPrompt = new BiometricPrompt(VanTay.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),"login Success",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VanTay.this, MainTrangChu.class);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Đăng nhập")
                .setDescription("Mời bạn đặt ngón tay vào")
                .setNegativeButtonText("Cancel")
                .build();
        //
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);
            }
        });

    }
}