package com.hipl.roomcodelabs.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.hipl.roomcodelabs.db.dao.ProductDao;
import com.hipl.roomcodelabs.db.entities.Product;

/**
 * Created by karanjm on 14-09-2018.
 */

@Database(entities = {Product.class}, version = 1)
public abstract class ProductRoomDB extends RoomDatabase {

    public abstract ProductDao productDao();

    private static volatile ProductRoomDB INSTANCE;

    static ProductRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductRoomDB.class, "product_database")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ProductDao mDao;

        PopulateDbAsync(ProductRoomDB db) {
            mDao = db.productDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //mDao.deleteAll();
            /*Product word = new Product("Product 1","This is product 1", 10.0);
            mDao.insert(word);
            word = new Product("Product 2","This is product 2", 15.0);
            mDao.insert(word);*/
            return null;
        }
    }
}
