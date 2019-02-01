package com.hipl.roomcodelabs;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hipl.roomcodelabs.db.entities.Product;
import com.hipl.roomcodelabs.db.viewmodels.ProductViewModel;

import static android.R.attr.button;
import static android.R.attr.data;

public class NewProductActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText etProdName, etProdDesc, etProdPrice;
    private Intent mdata;
    private Button button_edit, button_save;
    private ProductViewModel mProductViewModel;
    Product searchProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        etProdName = findViewById(R.id.edit_prod_name);
        etProdDesc = findViewById(R.id.edit_prod_desc);
        etProdPrice = findViewById(R.id.edit_prod_price);

        button_save = findViewById(R.id.button_save);
        button_edit = findViewById(R.id.button_edit);
        mProductViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        mdata = getIntent();
        if (mdata != null && mdata.getStringExtra(NewProductActivity.EXTRA_REPLY) != null) {
            Gson gson = new Gson();
            Product tempprod = gson.fromJson(mdata.getStringExtra(NewProductActivity.EXTRA_REPLY), Product.class);
            searchProduct = mProductViewModel.getProd(tempprod.getPName(), tempprod.getPDescription(), tempprod.getPSellPrice());
            etProdName.setText(searchProduct.getPName());
            etProdDesc.setText(searchProduct.getPDescription());
            etProdPrice.setText("" + searchProduct.getPSellPrice());
            button_edit.setVisibility(View.VISIBLE);
            button_save.setVisibility(View.GONE);
        } else {
            button_edit.setVisibility(View.GONE);
            button_save.setVisibility(View.VISIBLE);
        }

        button_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (validateUI()) {
                    Product product = new Product(etProdName.getText().toString(),
                            etProdDesc.getText().toString(),
                            Double.parseDouble(etProdPrice.getText().toString()));
                    Gson gson = new Gson();
                    String word = gson.toJson(product);
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                    finish();
                } else {
                    Toast.makeText(NewProductActivity.this, "Please fill all details.", Toast.LENGTH_SHORT).show();
                    /*setResult(RESULT_CANCELED, replyIntent);*/
                }
            }
        });

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (validateUI()) {
                    searchProduct.setPName(etProdName.getText().toString());
                    searchProduct.setPDescription(etProdDesc.getText().toString());
                    searchProduct.setPSellPrice(Double.parseDouble(etProdPrice.getText().toString()));
                    Gson gson = new Gson();
                    String word = gson.toJson(searchProduct);
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                    finish();
                } else {
                    Toast.makeText(NewProductActivity.this, "Please fill all details.", Toast.LENGTH_SHORT).show();
                    /*setResult(RESULT_CANCELED, replyIntent);*/
                }
            }
        });
    }

    public boolean validateUI() {
        boolean check = true;
        if (TextUtils.isEmpty(etProdName.getText().toString().trim())) {
            check = false;
        } else if (TextUtils.isEmpty(etProdDesc.getText().toString().trim())) {
            check = false;
        } else if (TextUtils.isEmpty(etProdPrice.getText().toString().trim())) {
            check = false;
        }
        return check;
    }
}
