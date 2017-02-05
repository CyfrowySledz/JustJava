package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int quantity=0;
    Locale locale = Locale.getDefault();
    Currency currency = Currency.getInstance(locale);
    String currencySymbol = currency.getSymbol();
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view){
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        float total = 0.0f;
        String summary = "";
        String subject = getString(R.string.app_name) + " order for " + name;

        total = calculatePrice(quantity, hasWhippedCream, hasChocolate);
        summary = createOrderSummary(total, hasWhippedCream, hasChocolate);
        displayMessage(summary);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void display(int number) {
        TextView quantityTextView=(TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public void increment (View view){
        if (quantity == 5) {
            Toast toast = Toast.makeText(this, "You can't have more than " + quantity + " coffees!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity=quantity+1;
        display(quantity);
    }

    public void decrement (View view) {
        if (quantity == 0) {
            Toast toast = Toast.makeText(this, "You can't have less then 0 coffee!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity=quantity-1;
        display(quantity);
    }

    public float calculatePrice(int quantity, boolean hasWhippedCream, boolean hasChocolate) {
        float coffeePrice = 5.2f;
        float whippedCreamPrice = 1.0f;
        float chocolatePrice = 2.0f;
        float total = coffeePrice;

        if (hasChocolate)
            total += chocolatePrice;
        if (hasWhippedCream)
            total += whippedCreamPrice;

        return total * quantity;
    }

    private String createOrderSummary(float total, boolean hasWhippedCream, boolean hasChocolate) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        name = nameField.getText().toString();
        String cream = getString(R.string.no);
        String chocolate = getString(R.string.no);

        if (hasWhippedCream)
            cream = getString(R.string.yes);
        if (hasChocolate)
            chocolate = getString(R.string.yes);


        String summary = getString(R.string.order_summary_name, name);
        summary += "\n" + getString(R.string.order_summary_topping1) + " " + cream;
        summary += "\n" + getString(R.string.order_summery_topping2) + " " + chocolate;
        summary += "\n" + getString(R.string.quantity) + ": " + quantity;
        summary += "\n" + getString(R.string.total) + ": " + currencySymbol + " " + new DecimalFormat("##.##").format(total);
        summary += "\n" + getString(R.string.thank_you);
        return summary;

    }
}
