package com.android.justjava;
////////////////////////////////////////////////////////////////////

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/////////////////////////////////////////////////////////////////////
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int whippedCreamNumber, price, chocolateNumber;
    boolean addWhippedCream, addChocolate;
    String name;

    public void submitOrder(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.WhippedCream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.Chocolate);
        if (whippedCream.isChecked()) {
            addWhippedCream = true;
            price = 6 * chocolateNumber;
        } else {
            addWhippedCream = false;
            whippedCreamNumber = 0;
        }
        if (chocolate.isChecked()) {
            addChocolate = true;
            price = 7 * chocolateNumber;
        } else {
            addChocolate = false;
            chocolateNumber = 0;
        }
        EditText Ename = (EditText) findViewById(R.id.name);
        name = Ename.getText().toString();
        String message = createOrderSummary(price, addWhippedCream, addChocolate, name);
        displayPrice(message);
        makeIntent("New order", message);
    }

    public void Increment(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.WhippedCream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.Chocolate);
        if (whippedCream.isChecked() && whippedCreamNumber <= 100) {
            whippedCreamNumber++;
            display(whippedCreamNumber);
        } else if (chocolate.isChecked() && chocolateNumber <= 100) {
            chocolateNumber++;
            display(chocolateNumber);
        } else if (!whippedCream.isChecked() && !chocolate.isChecked()) {
            makeToast("please choose one of the toppings first ");
            return;
        } else {
            makeToast("you have reached the maximum number of Coffees ");
        }
    }

    public void Decrement(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.WhippedCream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.Chocolate);
        if (whippedCream.isChecked() && whippedCreamNumber > 1) {
            whippedCreamNumber--;
            display(whippedCreamNumber);
        } else if (chocolate.isChecked() && chocolateNumber > 1) {
            chocolateNumber--;
            display(chocolateNumber);
        } else if (!whippedCream.isChecked() && !chocolate.isChecked()) {
            makeToast("please choose one of the toppings first ");
            return;
        } else {
            makeToast("that's the minimum number of coffee ");
        }
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name) {
        String priceMessage = getString(R.string.name,name);
        priceMessage += "\n"+getString(R.string.addwhippedCream)+addWhippedCream;
        priceMessage += "\n"+getString(R.string.quantity)+whippedCreamNumber;
        priceMessage += "\n"+getString(R.string.addchocolate)+addChocolate;
        priceMessage += "\n"+getString(R.string.quantity)+chocolateNumber;
        priceMessage += "\n"+getString(R.string.totalPrice)+price;
        priceMessage += "\n"+getString(R.string.thank_you);
        return priceMessage;
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(String message) {
        TextView text = findViewById(R.id.price_text_view);
        text.setText(message);
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    private void makeIntent(String subject, String text) {
        Intent myintent = new Intent(Intent.ACTION_SENDTO);
        myintent.setData(Uri.parse("mailto:emailaddress@emailaddress.com"));
        myintent.putExtra(Intent.EXTRA_SUBJECT, subject);
        myintent.putExtra(Intent.EXTRA_TEXT, text);
        myintent.putExtra(Intent.EXTRA_EMAIL, "salah.amse@gmail.com");
        if (myintent.resolveActivity(getPackageManager()) != null) {
            startActivity(myintent);
        }
    }
}