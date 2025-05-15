package com.chainplus.supplier.repository;

import com.chainplus.supplier.entity.Supplier;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, ObjectId> {
    Optional<Supplier> findByName(String name);
}

