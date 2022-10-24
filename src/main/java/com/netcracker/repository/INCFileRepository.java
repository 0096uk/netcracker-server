package com.netcracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netcracker.entity.NCFile;



public interface INCFileRepository extends JpaRepository<NCFile, Long>{

	List<NCFile> findAll();
}
