package com.example.licencjat.stuff;

import com.example.licencjat.stuff.models.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StuffRepository extends JpaRepository<Stuff, String> {
}
