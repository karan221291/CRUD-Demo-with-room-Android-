package com.hipl.roomcodelabs.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by karanjm on 14-09-2018.
 */
@Entity(tableName = "product_table")
public class Product {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "p_id")
    private long pId;

    public long getPId() {
        return pId;
    }

    public void setPId(long pId) {
        this.pId = pId;
    }

    @NonNull
    @ColumnInfo(name = "p_name")
    private String pName;

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    @ColumnInfo(name = "p_desc")
    private String pDescription;

    public String getPDescription() {
        return pDescription;
    }

    public void setPDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    @ColumnInfo(name = "p_sell_price")
    private Double pSellPrice;

    public Double getPSellPrice() {
        return pSellPrice;
    }

    public void setPSellPrice(Double pSellPrice) {
        this.pSellPrice = pSellPrice;
    }

    public Product(@NonNull String pName, String pDescription, Double pSellPrice) {
        this.pName = pName;
        this.pDescription = pDescription;
        this.pSellPrice = pSellPrice;
    }
}