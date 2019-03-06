# Description

It's a sample app about how to use the Flickr api. Using MVVM, RX and Retrofit downloads the last 100 public images from Flick.

# Screenshot

<img src="https://github.com/felipeespitalher/flickr/blob/master/screenshots/recent.jpg" width="250px" /> <img src="https://github.com/felipeespitalher/flickr/blob/master/screenshots/detail.jpg" width="250px" />

# Architecture

Uses MVVM as base of architecture. Repository to access remote data and Mappers to split network objects from domain objects.

# Libs

##### Dagger
    Dagger is the glue that joins all layers of architecture. It's helping to reduce coupling
    and split responsibilities between classes.

##### RX
    RX is providing an easy way to make network calls into an worker thread.
  
##### Mockk and AssertJ
    They are used used to make easy write unit test and assertion
  
##### Jacoco
    It provides a good coverage test report

# Next Steps

- Add UI Testing ( Using Espresso ou Robolectric )
- Avoid fetch data when rotate device
- Store locally Flickr api response then we'll be able to implement some cache for images ( Using OkHttp ou something else )
- Improve network error redirection using RX
- Improve layout
