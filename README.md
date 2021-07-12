# Asian Countries
This app shows a list of all Asian countries using a REST API. It is built entirely in Java. I was given the task of making this app as an assignment by a company named **Miskaa**.

**The app shows the following points about each country:**
* Name of the country
* Name of the capital of the country
* An image of the flag of the country
* The region in which the country lies
* The subregion of the country
* The population of the country
* Country codes of the countries with which the country shares borders
* Names of the officially recognized languages in the country

## Tech Stack
* RxJava
* Manual Dependency Injection
* Retrofit and OkHTTP
* Room Persistence
* Sharp library to load SVGs into image views
* MVVM Architecture
* Use of LiveData

## Known issues (or possible improvements I could make if I had more time)
* Integrate OkHTTP with Retrofit (to reduce redundancy)
* Integrate RxJava with the Retrofit API instead of a Callback object
* Improve the way the app loads SVGs using the Sharp library
* Use RxJava Flowables instead of LiveData
