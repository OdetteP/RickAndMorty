The assignment contains 

We need an android app that lists ‘Rick and Morty’ episodes and provides some extra details for each one of them. Use this [API](https://rickandmortyapi.com/documentation/#rest) to retrieve all the needed data.

In detail:

- The app should list all episodes
- On the list, each episode should show the name, air date (in dd/mm/yyyy format) and the code of the episode.
- Show a text message specifying that the user has reached the end of the list (after all episodes are loaded on the screen)
- Tapping on the episode should list all the id’s of the characters of that episode
- Tapping on each character’s id should show the details page of that character (image, name, status, species, name of the origin and the total number of episodes the character appears in)
- Provide an export functionality to export the character details (name, status, species, name of the origin and the total number of episodes) in a file and store it locally so it can be opened by another app, such as document reader or file explorer.

You can use any colours, fonts and layouts that you think are a proper fit for this app. For this assessment we don’t care too much about the design. Just make sure that the app looks like an app 🙂.

The app should be written in Kotlin and you shouldn’t spend more than a weekend on it. You are free to use third party libraries if required and it can be delivered via a github repository.

**It is not a requirement, but you can get extra points for**:

- Implementing a persistence mechanism for the data received from the api
- Writing unit and ui tests

