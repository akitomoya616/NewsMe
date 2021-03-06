# NewsMe
This is an Android App demo created by Sitong Lu alone (still WIP).

[Figma Design](https://www.figma.com/file/Q6frTbws8q8O3tCfAkyvdt/Untitled?node-id=18%3A105)

## Key Features
- [2022/5/4] Add a rounded box in drawable for making the outer frame for all boxes/buttons in the app rounded (check [Home Page xml file](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/res/layout/layout_main.xml) and [file for storing rounded framework design](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/res/drawable/bg_rounded_box.xml)).


- [2022/5/5] Add a sliding/navigation menu in home page (check [Home Page Layout Design (contains both slide and home page)](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/res/layout/activity_home.xml), [Slide Menu Helper File](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/java/com/example/testapp/SlideMenuActivity.java), [Home Page Java File](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/java/com/example/testapp/HomeActivity.java), [Home Page Layout Design](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/res/layout/layout_main.xml) and [Slide Page Layout Design](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/res/layout/layout_menu.xml)).


- [2022/5/5] Two ways to call `setOnClickListener` on buttons for handling their events once being clicked (check [HomeActivity.java](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/java/com/example/testapp/HomeActivity.java)).

- [2022/5/7] Make Weather page a vertically scrollable page, together with radio buttons and checkboxes (each set with `setOnCheckedChangeListener` method) (check [WeatherActivity.java](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/java/com/example/testapp/WeatherActivity.java) and [activity_weather.xml](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/res/layout/activity_weather.xml)).

- [2022/5/7] Gif demo of the login function, the navigation drawer menu, and scrollable page:
![demo gif](/pic/Login_Drawer_Scroll.gif)
  
- [2022/5/21] Change app icon to a new one by adding new image asset (with automatic size adjustment) using the following [tutorial](https://developer.android.com/studio/write/image-asset-studio#create-adaptive).
![app icon](app/src/main/ic_launcher-playstore.png)

- [2022/5/23] Implemented the Weather page using 
  - **Reminder**: for using Android 9 and above, please let all url starting with `https` BUT NOT with `http`, otherwise JsonObjectRequest will not respond!
  - [WeatherActivity.java](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/java/com/example/testapp/WeatherActivity.java) (mainly responsible for getting user location, weather info using API call, displaying today's weather info at the center of the screen, passing weather forecast info for the next few hours to WeatherAdapter, and making all the buttons on the weather page runnable), 
  - [WeatherAdapter.java](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/java/com/example/testapp/WeatherAdapter.java) (mainly responsible for getting `weather_model_arraylist` that contains the forecasted weather for the coming hours, and put each data into specific weather card), 
  - [WeatherModel.java](https://github.com/akitomoya616/NewsMe/blob/main/app/src/main/java/com/example/testapp/WeatherModel.java) (initialized object that stores `time`, `temperature`, `weather icon`, `wind speed` info to be putted into `weather_model_arraylist`), 
  - [card_back.xml](app/src/main/res/drawable/card_back.xml) (designed the background color and the shape for card created in `weather_item.xml`), 
  - [activity_weather.xml](app/src/main/res/layout/activity_weather.xml) (weather page UI layout), 
  - [weather_item.xml](app/src/main/res/layout/weather_item.xml) (designed the weather card format displayed at the bottom of the page).

Weather Demo (takes a little time to get geographical access for the first time)
![weather demo gif](/pic/newsme_weather_demo.gif)

