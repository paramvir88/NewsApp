# Articles Headlines App
The Articles Headlines App is made using the following: 
1. Kotlin Language.
2. Jetpack architecure components.
3. All business logics are covered with unit tests.
4. All problem statement points are covered.

   
# Features
This application is designed to display the latest headlines and news from selected sources. There is three tabs in the application to show latest list of headlines, sources and saved articles. On click of headlines, User will navigate to webview to read full detail article.
1. Feature_1
2. feature_2
3. Feature_3
4. ...

# Screenshots
<img src = "https://github.com/paramvir88/NewsApp/assets/1575084/5af8a62a-4e20-4958-a0e3-66f2a500d37a" width="220">
<img src = "https://github.com/paramvir88/NewsApp/assets/1575084/ac0c4eea-52b5-4231-805e-a7837c448407a" width="220">
<img src = "https://github.com/paramvir88/NewsApp/assets/1575084/778ad3b9-1161-4e0b-b450-f55ce0818e68" width="220">
<img src = "https://github.com/paramvir88/NewsApp/assets/1575084/9ac2deda-aa8a-4b2d-893d-58ef05e2ab4a" width="220">
<img src = "https://github.com/paramvir88/NewsApp/assets/1575084/b3551ebf-3850-4148-8796-6844bb2b4ec5" width="220">

Package Structure:
...



# Architecture
The Articles Headlines App app followed MVVM architecture. The two major layers are:
* UI layer
* Data layer
* ...



![app_diagram](https://github.com/swatishrivastava/newsApp/assets/11307086/c7ba6a6f-b8b0-45c3-94bf-31a76c270b58)

# Modularization
Articles Headlines App fully modularized application. Please find all modules details below: 



![modules drawio](https://github.com/swatishrivastava/newsApp/assets/11307086/59635dc2-c8c9-4f4c-b4a5-e01e0efaee8d)




| Names        | Responsibilities           | Key classes  |
| ------------- |:-------------:| -----:|
| app      | Responsible to bring together all features  | NewsActivity |
| feature: headlines      | Responsible for showing list of headlines as per selected sources and showing detail article on webview        |   $12 |
| feature: sources | showing list of sources and allow user to select multiple sources at once      |    $1 |
| feature: saved_articles | showing list of saved articles to read later.      |    $1 |

# API Usage
#### Source API Endpoint
##### HTTP Method: GET
URL: https://newsapi.org/v2/top-headlines/sources?apiKey=API_KEY

#### Headlines API Endpoint
##### HTTP Method: GET
URL: https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY

# Testing
Junit4 and mockito used to write unit tests for all business logics . All Viewmodel codes are covered with the Unit tests.
