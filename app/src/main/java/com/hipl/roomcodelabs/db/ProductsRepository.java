package com.hipl.roomcodelabs.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.hipl.roomcodelabs.db.dao.ProductDao;
import com.hipl.roomcodelabs.db.entities.Product;

import java.util.List;

/**
 * Created by karanjm on 14-09-2018.
 */

public class ProductsRepository {
    private ProductDao mProductDao;
    private LiveData<List<Product>> mAllProducts;

    public ProductsRepository(Application application) {
        ProductRoomDB db = ProductRoomDB.getDatabase(application);
        mProductDao = db.productDao();
        mAllProducts = mProductDao.getAllProducts();
    }

    /**
     * Method to get all rows from local database.
     * @return - list of products.
     */
    public LiveData<List<Product>> getAllWords() {
        return mAllProducts;
    }

    /**
     * Method to insert single product to local db table.
     * @param Product - product to add.
     */
    public void insert(Product Product) {
        new insertAsyncTask(mProductDao).execute(Product);
    }

    /**
     * Method to edit specific product.
     * @param Product - product to edit.
     */
    public void edit(Product Product) {
        new editAsyncTask(mProductDao).execute(Product);
    }

    /**
     * method to remove product from local db.
     * @param Product
     */
    public void delete(Product Product) {
        new deleteAsyncTask(mProductDao).execute(Product);
    }

    /**
     * Method to search particular product from product table.
     * @param pname - name of product.
     * @param pdesc - description of product.
     * @param pprice - price of product.
     * @return
     */
    public Product getprod(String pname, String pdesc, double pprice) {
        return mProductDao.getSpecificProd(pname, pdesc, pprice);
    }

    private static class insertAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao mAsyncTaskDao;

        insertAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Product... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class editAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao mAsyncTaskDao;

        editAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Product... params) {
            mAsyncTaskDao.edit(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao mAsyncTaskDao;

        deleteAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Product... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
