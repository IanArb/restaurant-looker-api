package com.ianarbuckle.restaurantlooker.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.ianarbuckle.restaurantlooker.authentication.model.AuthBody
import com.ianarbuckle.restaurantlooker.booking.model.*
import com.ianarbuckle.restaurantlooker.restaurants.model.Dish
import com.ianarbuckle.restaurantlooker.restaurants.model.Location
import com.ianarbuckle.restaurantlooker.restaurants.model.Price
import com.ianarbuckle.restaurantlooker.restaurants.model.Restaurant
import com.ianarbuckle.restaurantlooker.tables.model.Column
import com.ianarbuckle.restaurantlooker.tables.model.Row
import com.ianarbuckle.restaurantlooker.tables.model.Tables
import java.time.LocalDateTime
import java.time.Month


/**
 * @author ianarbuckle on 28/05/2018.
 */
object TestUtils {

    fun buildRestaurantsModel(): MutableList<Restaurant> {
        val restaurants = mutableListOf<Restaurant>()

        val restaurantData = createRestaurant()

        restaurants.add(restaurantData)

        return restaurants
    }

    fun createRestaurant(): Restaurant {
        val dateTime = LocalDateTime.of(2020, Month.JANUARY, 1, 10, 10, 30)
        return Restaurant("1", dateTime,  "Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                Location(0.5f, 0.10f), "OPEN", createMenu(), "")
    }

    private fun createMenu(): MutableList<Dish> {
        val lunch = mutableListOf<Dish>()

        lunch.add(Dish("STARTER", "Soup of the day", "Fresh soup of the day", Price("EUR", 5.5f)))
        lunch.add(Dish("PIZZA", "MARGHERITA", "Tomato Sauce, Mozzarella, Parmesan & Fresh Basil", Price("EUR", 9f)))
        lunch.add(Dish("DESERT", "Cheese cake", "Strawberry cheese cake", Price("EUR", 6.5f)))

        return lunch
    }

    fun buildBookingsModel(): MutableList<Booking> {
        val bookings = mutableListOf<Booking>()

        bookings.add(createBooking())

        return bookings
    }

    fun createBooking(): Booking {
        val dateTime = LocalDateTime.of(2020, Month.JANUARY, 1, 10, 10, 30)

        val owner = Owner("24345-34534-34534", "John Doe", "iarbuckle@mail.com", PhoneNumber(353, 1234567890), false, dateTime.toString(), "17:00")
        val details = RestaurantDetails("Crillo's", "imageUrl", "address", Location(0.0f, 0.0f))
        return Booking("1", owner, details, Table("14", "RESERVED", TableCharacteristics("FAMILY", 8, true)))
    }

    fun createTables(): Tables {
        val rows = ArrayList<Row>()
        val columns = ArrayList<Column>()
        val table = com.ianarbuckle.restaurantlooker.tables.model.Table("1", "AVAILABLE", com.ianarbuckle.restaurantlooker.tables.model.TableCharacteristics("FAMILY", 4, false))
        val column = Column(table)
        val row = Row(columns)

        columns.add(column)
        rows.add(row)

        return Tables("1", "Buckle's", rows)
    }

    fun createAuthUser(): AuthBody = AuthBody("1234-1234-1234-1234", "ian@mail.com", "password", false)

    fun createUser(): AuthBody = AuthBody("1234-1234-1234-1234", "ian.arbuckle@mail.com", "password", false)

    fun asJsonString(obj: Any): String {
        try {
            val mapper = ObjectMapper()
            mapper.registerModule(JodaModule())
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            return mapper.writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

}