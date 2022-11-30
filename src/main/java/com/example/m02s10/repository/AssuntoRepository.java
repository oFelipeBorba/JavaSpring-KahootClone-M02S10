package com.example.m02s10.repository;

import com.example.m02s10.entity.AssuntoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepository extends JpaRepository<AssuntoEntity,Long> {
}
