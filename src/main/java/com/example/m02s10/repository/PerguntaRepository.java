package com.example.m02s10.repository;

import com.example.m02s10.entity.PerguntaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<PerguntaEntity,Long> {
}
