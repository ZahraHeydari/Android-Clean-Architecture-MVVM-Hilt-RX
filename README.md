# ArtGallery
A sample android app that shows how to use ViewModels and Room together with RxJava, in Kotlin by Clean Architecture.

### Implemented by Clean Architecture
The following diagram shows the structure of this project with 3 layers:
- Presentation
- Domain
- Data

<br>
<p align="center">
  <img src="https://github.com/ZahraHeydari/ArtGallery/blob/master/diagram.png" width="750"/>
</p>
<br>


### Scenario
Used https://jsonplaceholder.typicode.com/ as a public api to generate fake data for testing

At a glance:

- Created a list of Album
- In the Item of each Album, showed user name.
- When user taps on Album, new page will be shown which includes list of photos.
- when user taps on photo, show image bigger through transition.
- Were Written tests to completely cover Exceptions/Expectations
- And: 
    - Supported orientation change
    - Supported offline mode