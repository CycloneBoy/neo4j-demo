package com.cycloneboy.repositories;

import com.cycloneboy.domain.Actor;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CycloneBoy on 2017/6/24.
 */
@Repository
public interface ActorRepository extends GraphRepository<Actor>{
}
