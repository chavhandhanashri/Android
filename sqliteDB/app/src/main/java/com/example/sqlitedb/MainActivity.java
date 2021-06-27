package com.example.sqlitedb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText shopNameEdt,  shopLatitudeEdt, shopLongitudeEdt;
    private Button addCourseBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        shopNameEdt = findViewById(R.id.idEdtShopName);
        shopLatitudeEdt = findViewById(R.id.idEdtShopLatitude);
        shopLongitudeEdt = findViewById(R.id.idEdtShopLongitude);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        // below line is to add on click listener for our add shop button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String shopName = shopNameEdt.getText().toString();
                String shopLatitude = shopLatitudeEdt.getText().toString();
                String shopLongitude = shopLongitudeEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (shopName.isEmpty()  && shopLatitude.isEmpty() && shopLongitude.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewShop(shopName, shopLatitude, shopLongitude);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Shop has been added.", Toast.LENGTH_SHORT).show();
                shopNameEdt.setText("");
                shopLatitudeEdt.setText("");
                shopLongitudeEdt.setText("");
            }
        });
    }
}
