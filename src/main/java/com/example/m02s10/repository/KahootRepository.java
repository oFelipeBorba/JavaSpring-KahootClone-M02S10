package com.example.m02s10.repository;

import com.example.m02s10.entity.KahootEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KahootRepository extends JpaRepository<KahootEntity, Long> {
}
