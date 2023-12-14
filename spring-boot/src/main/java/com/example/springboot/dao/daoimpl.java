package com.example.springboot.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.Supplier;

@Repository
public class daoimpl implements dao{
    MongoTemplate template;

    public daoimpl(MongoTemplate template){
        this.template=template;
    }
    public void updateSupplierName(String oldName, String newName){
        Criteria criteria = Criteria.where("SupplierName").is(oldName);
        Query query = new Query(criteria);
        Update update = new Update().set("SupplierName", newName);
        template.updateFirst(query, update, Supplier.class);
    } 

    public boolean idExsist(int supplierId){
        Criteria criteria1= Criteria.where("supplierId").is(supplierId);
        Query query1 = new Query(criteria1);
        
        // List<Supplier> list = new ArrayList<>();
        // list=template.find(query,Supplier.class);
        long list=template.count(query1,Supplier.class);
        if(list>0){
            return true;
        }
        return false;
    }
    @Override
    public boolean rawMaterialIdExsist(int rawMaterialId) {
        Criteria criteria= Criteria.where("_id").is(rawMaterialId);
        Query query = new Query(criteria);
        
        List<Supplier> list = new ArrayList<>();
        list=template.find(query,Supplier.class);
        if(list.size()>0){
            return true;
        }
        return false;
    }
}
