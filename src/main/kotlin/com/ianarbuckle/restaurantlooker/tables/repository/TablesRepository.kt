package com.ianarbuckle.restaurantlooker.tables.repository

import com.ianarbuckle.restaurantlooker.tables.model.Tables
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @author ianarbuckle on 17/07/2019.
 */
@Repository
interface TablesRepository : MongoRepository<Tables, String> {
    fun findTablesByRestaurantId(restaurantId: String): Tables
}