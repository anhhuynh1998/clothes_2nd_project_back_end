package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long > {

    Optional<Status> findStatusById(Long id);
}
