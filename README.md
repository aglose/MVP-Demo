# MVP-Demo

This application is a demonstration utilizing several design concepts and frameworks. 

## Key Concepts
Model View Presenter (MVP) design pattern for presentation layer
* https://blog.mindorks.com/essential-guide-for-designing-your-android-app-architecture-mvp-part-1-74efaf1cda40
* https://medium.com/@cervonefrancesco/model-view-presenter-android-guidelines-94970b430ddf
## Repository pattern for data layer
Utilizing the new Object Relational Mapping (ORM) tool, named Room, for data storage
* https://developer.android.com/topic/libraries/architecture/room.html
Unit testing
Some Clean Architecture principals
* https://github.com/android10/Android-CleanArchitecture
Clean, organized and well-commented code

## Basic MVP design structure
MVP is a popular design pattern for the presentation layer of an application. While there are many articles regarding the best design pattern to use the main popular ones are MVP and MVVM. They both have their advantages and disadvantages. For this demo, I have chosen MVP since I have a better understanding of the design pattern and implementation. Some complexity arises when utilizing ViewPagers and one of the main things I wanted to address in this demo is how to overcome this issue. 

For MVP design with a standard Activity -> Fragment -> Presenter interaction, the coding implementation is fairly straightforward. Create a Contract class for the View and Presenter which the Activity/Fragment and PresenterClass implement respectively. An issue arises when you have an Activity with multiple fragments, often experience with a ViewPager.

This issue has been remedied with the Root Controller and Landing Page Presenter. The Root Controller creates the necessary Presenters. From there the Landing Page Presenter (LDP) takes control and essentially is the "Parent Presenter." This Presenter implements all 3 Contracts for the Fragments listed (Wardrobe, Calendar, More). Therefore, the LDP can delegate all the necessary tasks to appropriate "Child Presenters."

One extra benefit of having a Parent Presenter is having a solid communication between Fragments and Activities via the Presenters. All lists, utilizing RecyclerView, are controlled by the Presenters which modularizes how data is distributed. The LDP is quite a large class because it has a lot of responsibility. 

Another key component of the LDP is to be the Presenter to the MainActivity. While most UI components are handled at the Fragment level, there are some components at the Activity level which are controlled by the LDP.

## Walkthrough Example:
1. User clicks a button in WardrobeFragment
2. LandingPagePresenter captures initial click and delegates the WardrobePresenter 
4. WardrobePresenter finds necessary data via Room
5. Once data retrieval is complete the WardrobePresenter calls the WardrobeFragment to display the data
