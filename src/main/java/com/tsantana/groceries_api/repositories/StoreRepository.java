package com.tsantana.groceries_api.repositories;

import com.tsantana.groceries_api.models.Store;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {

    @Query("SELECT s FROM Store s WHERE LOWER(s.name) = LOWER(:name)")
    List<Store> findAllByName(@Param("name") String name);

    // TODO(Return the distance in the response)
    @Query("""
        SELECT s FROM Store s JOIN s.address a
        WHERE FUNCTION('ST_DWithin', a.location, :point, :radius) = true
        ORDER BY FUNCTION('ST_Distance', a.location, :point)
        """)
    List<Store> findAllByLocation(@Param("point") final Point point, @Param("radius") final Double radius);
}
