package com.example.android.justjava;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;
import java.lang.String;
import android.widget.Toast;
import android.widget.CheckBox;
import android.content.Intent;
import android.net.Uri;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    public String value;
    public String addresses;
    int quantity = 0;
    boolean whippedcream = false;
    boolean chocolate = false;
    String subject = "Order summary";
    String body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void composeEmail(String addresses, String subject, String body) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{addresses});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void moreincrement(View view) {

        whippedcream = true;
        if (!chocolate)
            displayPrice(quantity * 5 + 1);
        else if (chocolate)
            displayPrice(quantity * 5 + 4);


    }

    public void moreincrements(View view) {
        chocolate = true;
        if (!whippedcream)
            displayPrice(quantity * 5 + 3);
        else if (whippedcream)
            displayPrice(quantity * 5 + 4);
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean whippedcream = whippedCreamCheckBox.isChecked();
        if (quantity == 0)
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_LONG).show();

        else {
            EditText textPersonName = (EditText) findViewById(R.id.text_Person_Name);
            value = textPersonName.getText().toString();

            EditText textEmailAddress = (EditText) findViewById(R.id.text_Email_Address);
            addresses = textEmailAddress.getText().toString();

            if ((!whippedcream) && (!chocolate))

            { body = ("Total: " + (quantity * 5) + "\nThank you!" + value);
            composeEmail(addresses, subject, body);}


            else if ((whippedcream) && (!chocolate))

            {body = ("Total: " + (quantity * 5 + 1) + "\nThank you!" + value);
            composeEmail(addresses, subject, body);}

            else if ((!whippedcream) && (chocolate))

            {body = ("Total: " + (quantity * 5 + 3) + "\nThank you!" + value);
            composeEmail(addresses, subject, body);}

            else if ((whippedcream) && (chocolate))

                body = ("Total: " + (quantity * 5 + 4) + "\nThank you!" + value);
            composeEmail(addresses, subject, body);

        }
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
   /* private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }*/

    //This method increments the quantity value by 1
    public void increment(View view) {
        if (quantity == 100)
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        else if (quantity < 100) {
            quantity++;
            display(quantity);
            displayPrice(quantity * 5);
        }
    }

    //This method decrements the quantity value by 1
    public void decrement(View view) {


        if (quantity == 0)
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        if (quantity > 0)

        {
            quantity--;
            display(quantity);
            displayPrice(quantity * 5);
        }
    }


}