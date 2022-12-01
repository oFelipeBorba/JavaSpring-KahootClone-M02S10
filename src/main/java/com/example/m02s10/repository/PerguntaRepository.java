package com.example.m02s10.repository;

import com.example.m02s10.entity.AssuntoEntity;
import com.example.m02s10.entity.PerguntaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerguntaRepository extends JpaRepository<PerguntaEntity,Long> {

    public List<PerguntaEntity> findPerguntaEntitiesByAssuntoEntity_Id(Long id);
}
