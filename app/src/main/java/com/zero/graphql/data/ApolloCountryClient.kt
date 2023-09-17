package com.zero.graphql.data

import com.apollographql.apollo3.ApolloClient
import com.zero.graphql.domain.CountryClient
import com.zero.graphql.domain.DetailedCountry
import com.zero.graphql.domain.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {

    override suspend fun getCountry(code: String): DetailedCountry? {
        TODO("Not yet implemented")
    }

    override suspend fun getCountries(): List<SimpleCountry> {
        TODO("Not yet implemented")
    }

}
