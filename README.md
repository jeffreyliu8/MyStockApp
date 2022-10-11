# MyStockApp


1. The architectural approach you took and why

Using MVVM with dagger hilt. Mainly this app have 3 layers, UI, view model where main business logic,
and repositories for fetching/saving data


2. The trade offs you made and why

For such small app with limited features, there is not that much tough decision IMHO that I need to make.
Just (pretty much) going with the latest and greatest from Google.

3. How to run your project

Just hit the play button in Android Studio one you have open the project with a phone or emulator.
Try not to go with very old phone/sdk/api.


4. 3rd party libraries or copied code you may have used

Hilt, retrofit, compose. If any, my old repositories on github https://github.com/jeffreyliu8/github-search
https://github.com/android/compose-samples/tree/main/Jetsnack


5.What area(s) did you focus on when working on the project? The architecture and data flow? The UI? Something else?
I think a few that was not perfectly straight forward for me was what I should do with the currency as USD, do I need to 
worry about other currencies, localizations, how the stock price amount should be formed on the screen,
the rest I believe I would just following standard android/google design pattern from official sites
or youtube channels.


![Output sample](https://github.com/jeffreyliu8/MyStockApp/blob/main/ScreenShot.png)
