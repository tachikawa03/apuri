package com.example.apuri.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DateTimeRepository extends JpaRepository {
    @Query(value = "select now()", nativeQuery = true)
    String getNow();
}
