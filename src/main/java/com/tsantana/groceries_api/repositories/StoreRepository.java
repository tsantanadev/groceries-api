package com.tsantana.groceries_api.repositories;

import com.tsantana.groceries_api.models.Store;
import com.tsantana.groceries_api.vos.StoreResponse;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {

    @Query("""
        SELECT NEW com.tsantana.groceries_api.vos.StoreResponse(
            s.id,
            s.name,
            NEW com.tsantana.groceries_api.vos.AddressResponse(a),
            FUNCTION('ST_Distance', a.location, :point),
            s.createdAt,
            s.updatedAt
        )
        FROM Store s
        JOIN s.address a
        WHERE LOWER(s.name) LIKE CONCAT('%',LOWER(:name),'%')
        AND FUNCTION('ST_DWithin', a.location, :point, :radius) = true
        ORDER BY FUNCTION('ST_Distance', a.location, :point)
        """)
    List<StoreResponse> findAllByNameAndLocation(@Param("name") String name,
                                                 @Param("point") final Point point,
                                                 @Param("radius") final Double radius);

    @Query("""
        SELECT NEW com.tsantana.groceries_api.vos.StoreResponse(
            s.id,
            s.name,
            NEW com.tsantana.groceries_api.vos.AddressResponse(a),
            FUNCTION('ST_Distance', a.location, :point),
            s.createdAt,
            s.updatedAt
        )
        FROM Store s
        JOIN s.address a
        WHERE FUNCTION('ST_DWithin', a.location, :point, :radius) = true
        ORDER BY FUNCTION('ST_Distance', a.location, :point)
        """)
    List<StoreResponse> findAllByLocation(@Param("point") final Point point, @Param("radius") final Double radius);
}
