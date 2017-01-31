package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity=0;
    String priceMessage = "Free";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view){
        int price = quantity * 5;

        priceMessage = "Total = " + price + "\nThank you!";
        displayMessage(priceMessage);

    }

    private void display(int number) {
        TextView quantityTextView=(TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void displayPrice(int number) {
        TextView priceTextView=(TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    public void increment (View view){
        quantity=quantity+1;
        display(quantity);
    }

    public void decrement (View view) {
        quantity=quantity-1;
        display(quantity);
    }
}
