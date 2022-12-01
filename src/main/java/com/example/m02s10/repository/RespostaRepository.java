package com.example.m02s10.repository;

import com.example.m02s10.entity.RespostaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespostaRepository extends JpaRepository<RespostaEntity,Long> {

    @Query("SELECT r FROM RespostaEntity r WHERE r.perguntaEntity.id = :id") //JPQL
    public List<RespostaEntity> findRespostasPorId_Pergunta(Long id);

    public  List<RespostaEntity> findRespostaEntityByPerguntaEntity_Id(Long id);

}
