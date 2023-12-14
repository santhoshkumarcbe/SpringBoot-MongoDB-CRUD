package com.example.springboot.dao;

public interface dao {
    void updateSupplierName(String oldName, String newName);
    boolean idExsist(int supplierId);
    boolean rawMaterialIdExsist(int rawMaterialId);
}