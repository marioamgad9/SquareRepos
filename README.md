# SquareRepos
An app that requests square repositories from GitHub 10 at a time and stores them in a local storage

## Features:
  - Is built using the MVVM Architecture to provide decoupling of concerns
  - Implements Android Architecture Components (Room - LiveData - ViewModel)
  - Efficient network calls done on a AsyncTaskLoader
  - Infinite RecyclerView which requests the data 10 items at a time and caches them in a local data source
  - The Room database acts as the true source of data, and to get new data the network call updates the database.
  
