package com.cycloneboy.repositories;

import com.cycloneboy.domain.Movie;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by CycloneBoy on 2017/6/24.
 */
@Repository
public interface MovieRepository extends GraphRepository<Movie>{
    Movie findByTitle(@Param("title") String title);
}
