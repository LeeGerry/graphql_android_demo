package com.zero.graphql.data

import com.apollographql.apollo3.ApolloClient
import com.zero.graphql.CountriesQuery
import com.zero.graphql.CountryQuery
import com.zero.graphql.domain.CountryClient
import com.zero.graphql.domain.DetailedCountry
import com.zero.graphql.domain.SimpleCountry
import com.zero.graphql.mapping.toDetailedCountry
import com.zero.graphql.mapping.toSimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient.query(CountryQuery(code)).execute().data?.country?.toDetailedCountry()
    }

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient.query(CountriesQuery())
            .execute().data?.countries?.map { it.toSimpleCountry() } ?: emptyList()
    }

}
