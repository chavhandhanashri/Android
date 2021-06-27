package com.example.rozorpay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    Button bt_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_pay = findViewById(R.id.bt_pay);

        String samount ="100";

        int amount =Math.round(Float.parseFloat(samount)*100);

        bt_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Checkout checkout = new Checkout();

                //set key id
               checkout.setKeyID("rzp_test_UhpnPJHz6IuoBA");

               //set image
                checkout.setImage(R.drawable.rzp_logo);

                JSONObject object = new JSONObject();


                try {
                    //put name
                    object.put("name","Chorus Razorpay");

                    //put description
                    object.put("description","Test Payment");
                    //put theme color
                    object.put("theme.color","#0093DD");
                    //Put currency unit
                    object.put("currency","INR");
                    //put amount
                    object.put("amount",amount);
                    //put mobile number
                    object.put("prefill.contact","9404355299");
                    //put email
                    object.put("prefill.email","chorusraz@rzp.com");

                    //open razorpay checkout activity
                    checkout.open(MainActivity.this,object);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    public void onPaymentSuccess(String s) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set title
        builder.setTitle("paynent ID");

        builder.setMessage(s);

        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}