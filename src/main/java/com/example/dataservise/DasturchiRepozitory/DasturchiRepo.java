package com.example.dataservise.DasturchiRepozitory;

import com.example.dataservise.Entity.DasturchiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "Dasturchi")
public interface DasturchiRepo extends JpaRepository<DasturchiEntity,Integer> {
    boolean existsByEmail(String email);
}
