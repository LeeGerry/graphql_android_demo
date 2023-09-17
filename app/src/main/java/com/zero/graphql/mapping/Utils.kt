package com.zero.graphql.mapping

import com.zero.graphql.CountriesQuery
import com.zero.graphql.CountryQuery
import com.zero.graphql.domain.DetailedCountry
import com.zero.graphql.domain.SimpleCountry

fun CountryQuery.Country.toDetailedCountry() = DetailedCountry(
    code = code,
    name = name,
    emoji = emoji,
    capital = capital ?: "No Capital",
    currency = currency ?: "Non",
    languages = languages.map { it.name },
    continent = continent.name
)

fun CountriesQuery.Country.toSimpleCountry() = SimpleCountry(
    code = code,
    name = name,
    emoji = emoji,
    capital = capital ?: "No Capital",
)
