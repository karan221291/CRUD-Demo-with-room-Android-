package com.hipl.roomcodelabs.db.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.hipl.roomcodelabs.db.ProductsRepository;
import com.hipl.roomcodelabs.db.entities.Product;

import java.util.List;

/**
 * Created by karanjm on 15-09-2018.
 */

public class ProductViewModel extends AndroidViewModel {

    private ProductsRepository mRepository;

    private LiveData<List<Product>> mAllProducts;

    public ProductViewModel(Application application) {
        super(application);
        mRepository = new ProductsRepository(application);
        mAllProducts = mRepository.getAllWords();
    }

    public LiveData<List<Product>> getmAllProducts() {
        return mAllProducts;
    }

    public void insert(Product product) {
        mRepository.insert(product);
    }

    public void edit(Product product) {
        mRepository.edit(product);
    }

    public void delete(Product product) {
        mRepository.delete(product);
    }

    public Product getProd(String pname, String pdesc, double pprice) {
        return mRepository.getprod(pname, pdesc, pprice);
    }
}