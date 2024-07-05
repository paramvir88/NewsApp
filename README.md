# Articles Headlines App
This 'Articles Headlines' app covers the problem statment given in the Medibank assignment here: https://github.com/Medibank/coding-challenge-android?tab=readme-ov-file.

   
# Functional Requirement
Following features (as per the problem statement) are implemented:

* Bottom Tab bar for main navigation.
* 3 tabs - Headlines, Sources, Saved.
    * Headelines - screen to display lis of headlines.
        * Display Headlines as per user selected sources.
        * Each rows has title, description, author and thumbnail.
        * Tapping on row opens headline url to read within app webview.
        * Save button on Headlines Detail page.
    * Sources
        * To display list of available sources.
        * User can select multiple sources.
        * Sources selection persists during user session.
    * Saved
        * Displays list of previously saved headlines.
        * Tapping opens article for reading.
        * User can delete previously saved article.
     
# Non-functional requirements, APIs & frameworks

* MVVM with Clean Architecture.
* Hilt for Dependency Injection.
* Modular architecture with two modules: app module and data module.
* Unit tests using Junit and MockK.
* Shared Preferences for persistent storage of selected sources.
* File system storage for Saved articles.
* Retrofit for HTTP.
* Glide for image downloading.


# Screenshots
<img src = "https://github.com/paramvir88/NewsApp/assets/1575084/5af8a62a-4e20-4958-a0e3-66f2a500d37a" width="220">
<img src = "https://github.com/paramvir88/NewsApp/assets/1575084/ac0c4eea-52b5-4231-805e-a7837c448407a" width="220">
<img src = "https://github.com/paramvir88/NewsApp/assets/1575084/778ad3b9-1161-4e0b-b450-f55ce0818e68" width="220">
<img src = "https://github.com/paramvir88/NewsApp/assets/1575084/9ac2deda-aa8a-4b2d-893d-58ef05e2ab4a" width="220">
<img src = "https://github.com/paramvir88/NewsApp/assets/1575084/b3551ebf-3850-4148-8796-6844bb2b4ec5" width="220">

Package Structure:
## The Complete Project Folder Structure

```
└───com
    └───paramvir
        └───news
            │
            ├───common
            │   ├───di
            │   │
            │   ├───network    
            │   │
            │   ├───ui
            │   │       
            │   └───utils
            │ 
            │
            ├───headlines
            │   │
            │   └───data
            │   │     
            │   ├───domain
            │   │
            │   └──views
            ├───saved
            │   │
            │   └───data
            │   │     
            │   ├───domain
            │   │
            │   └──views
            │
            ├───sources
            │   └───data
            │   │     
            │   ├───domain
            │   │
            │   └──views
         



└───com
    └───paramvir
        └───news
            │
            ├───headlines.data
            │ 
            │
            ├───sources.data


```





# Architecture
The Articles Headlines App app followed Clean Architecture with MVVM pattern. Below are the highlights:
* Follows Clean architecture guielines to force inward dependencies.
* Two modeules created : app module and data module. Module separate enfore dependency flow and prevents from accidentally using classes from opposite direction.
* Use-cases are recommended in clean archtecture but not used here as the scope does not demand.
* Repository pattern is used to interact with data.




# Modularization
Articles Headlines App fully modularized application. Please find all modules details below: 

![ArticleHeadlines Architecture drawio](https://github.com/paramvir88/NewsApp/assets/1575084/ef248db1-e967-44fa-91d3-dda98ea6f40a)




# API Usage
#### Source API Endpoint
##### HTTP Method: GET
URL: https://newsapi.org/v2/top-headlines/sources?apiKey=API_KEY

#### Headlines API Endpoint
##### HTTP Method: GET
URL: https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY

# Testing
Junit4 and mockito used to write unit tests for all business logics . All Viewmodel codes are covered with the Unit tests.
