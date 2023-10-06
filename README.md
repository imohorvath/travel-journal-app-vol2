# Travel Journal App
🌍✈️✨ **Your Digital Passport to Memories!** ✨✈️🌍

## About

*Embark on a journey like never before with the ultimate Travel Journal App for wanderlust souls!* 🌟 *Whether you're scaling the icy mountains, basking in a tropical paradise, or exploring hidden urban gems, our Travel Journal App ensures you never miss a moment.*

## Features

📚 **Create Journals:** Users can create personalized journals for their travel experiences, complete with a title and cover image.

📝 **Create Notes:** Users can add notes to journals, plain text and also upload images, to capture specific moments and memories.

👪 **Invite Travel Buddies:** Users can add contributors to their own journals. This way they can edit journals together, they can add and edit notes.

🌆 **Image Fetching:** The app automatically fetches related images using the Pexels API based on journal titles, providing visually appealing cover images.

📲 **User Authentication and Authorization:** Secure user authentication using JSON Web Tokens (JWT) and bcrypt for password hashing. Spring Security ensures that only authorized users can access and manage their own journals and notes.

*Don't let memories fade, paint them in vibrant hues, store them securely, and share the magic. Your journey deserves the best narrative. Begin yours today with Travel Journal App!* 🌄📖✨

## Technologies

[![Java][Java]][Java-url]
[![Spring][Spring]][Spring-url]
[![Postgres][Postgres]][Postgres-url]
[![Javascript][Javascript]][Javascript-url]
[![React][React.js]][React-url]
[![React Router][React Router]][React Router-url]
[![MUI][MUI]][MUI-url]

## Installation Guide

### Prerequisites:

✅ Docker Desktop installed on your system. [![Docker][Docker]][Docker-url]

✅ Git installed on your system. [![Git][Git]][Git-url]

### Steps:

#### 1.  Clone the Repository:

Open bash terminal and navigate to the directory where you want to download the application.

```bash
git clone https://github.com/imohorvath/travel-journal-app-vol2.git
```

#### 2.  Navigate to the Application Directory:

```bash
cd travel-journal-app-vol2
```

#### 3. Run the Application with Docker:

Launch the Docker Desktop application.
In Bash simply use the following command to start the application:

```bash
docker compose up -d
```

#### 4. Access the Application:

Open your browser and type the following url: http://localhost:8080/

#### 5. Stopping the Application:

In order to stop the application, you should use this command in Bash:

```bash
docker compose down --volumes
```

## How to use?

### Sign in with default user

You can sign in the application with a default user:
- email: testuser@gmail.com
- password: Test!2345 

### Register new user

Fill in the form with the required data and click on SIGN UP.

### Create Journal

Either you signed in or up, you will be navigated to the main page where your own and contributed journals are listed if you have any.
This is the page where you can create a new journal. 

#### Add title

Fist you should give a title to your journal.

#### Add contributor

Choose a contributor from the dropdown menu who will be allowed to edit the journal too. This shared journal will show up in his contributed journals list.

Note that if you haven't registered any other users yet you will see only the default user in the contributors dropdown menu.

### Create notes

You can create notes by submitting text or uploading images or both.

### Logout
In order to sign in with a different account you should log out with the current user.

## Authors

👩‍🦰 [imohorvath](https://github.com/imohorvath)

## Road Map

- [X] User can create journal and add contributors
- [X] User can create notes in a journal
- [X] User can delete journals and notes
- [X] User can sign in the app
- [X] New user can be registered in the app
- [X] User can upload images to a note
- [ ] User has access to profile
- [ ] User can edit profile

<!-- Badge links -->
[Java]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[Spring]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[Postgres]: https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white
[JavaScript]: https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React Router]: https://img.shields.io/badge/React_Router-CA4245?style=for-the-badge&logo=react-router&logoColor=white
[MUI]: https://img.shields.io/badge/MUI-%230081CB.svg?style=for-the-badge&logo=mui&logoColor=white
[Docker]: https://img.shields.io/badge/docker-%230db7ed.svg?style=plastic&logo=docker&logoColor=white
[Git]: https://img.shields.io/badge/git-%23F05033.svg?style=plastic&logo=git&logoColor=white

<!-- Project url -->
[Java-url]: https://docs.oracle.com/en/java/javase/17/ 
[Spring-url]: https://spring.io/
[Postgres-url]: https://www.postgresql.org/
[JavaScript-url]: https://developer.mozilla.org/en-US/docs/Web/JavaScript
[React-url]: https://reactjs.org/
[React Router-url]: https://reactrouter.com/en/main 
[MUI-url]: https://mui.com/
[Docker-url]: https://docs.docker.com/get-docker/
[Git-url]: https://git-scm.com/downloads
