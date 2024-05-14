package com.example.exrate.client;


public interface ExrateApiClient {

    /**
     * Get the exchange rate from the lookup server.
     *
     * @return String result
     */
    String getExrate();

}
