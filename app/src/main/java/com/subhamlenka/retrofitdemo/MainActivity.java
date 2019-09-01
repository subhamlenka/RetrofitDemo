package com.subhamlenka.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.subhamlenka.retrofitdemo.model.BalanceData;
import com.subhamlenka.retrofitdemo.model.BalanceResponse;
import com.subhamlenka.retrofitdemo.service.BalanceService;

import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                BalanceData balanceData=new BalanceData(1,34);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://alpha.qubiseven.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                BalanceService service = retrofit.create(BalanceService .class);


                Call<BalanceResponse> call=service.updateBalance(balanceData);
                call.enqueue(new Callback<BalanceResponse>() {
                    @Override
                    public void onResponse(Call<BalanceResponse> call, Response<BalanceResponse> response) {


                        if(response.body().getSuccess().equals("True")) {
                            Toast.makeText(getBaseContext(),"Succesfully updated", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getBaseContext(),"update failed", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<BalanceResponse> call, Throwable t) {
                        Toast.makeText(getBaseContext(),"something went wrong.", Toast.LENGTH_SHORT).show();

                        Log.v("checkResponseProfile","check here");
                        t.printStackTrace();

                    }
                });
            }
        });


    }
}
