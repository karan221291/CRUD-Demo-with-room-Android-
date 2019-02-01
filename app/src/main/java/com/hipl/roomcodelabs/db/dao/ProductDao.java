package com.hipl.roomcodelabs.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.hipl.roomcodelabs.db.entities.Product;

import java.util.List;

/**
 * Created by karanjm on 14-09-2018.
 */

@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);

    @Update
    void edit(Product product);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM product_table")
    void deleteAll();

    @Query("SELECT * from product_table ORDER BY p_name ASC")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM product_table WHERE p_sell_price = :pprice and p_name like :pname and p_desc like :pdesc")
    Product getSpecificProd(String pname, String pdesc, double pprice);
}

