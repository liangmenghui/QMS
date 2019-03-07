package com.web.materiel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.materiel.entity.MaterielInfo;

public interface MaterielInfoDao extends JpaRepository<MaterielInfo, Long> {

    public MaterielInfo findById(long id);
}
