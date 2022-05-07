uFitness
==============================
![workflow](https://github.com/wheelbarrowru/uFitness/actions/workflows/main.yml/badge.svg)
![Coverage](.github/badges/jacoco.svg)
![jdk](https://img.shields.io/badge/JDK-11-blue)

## Team:
- [Ildar Khabibulin](https://github.com/wheelbarrowru)
- [Maksum Valeev](https://github.com/mvtValeev)

## Running the application

The project is a standard Maven project. Firstly, you should connect your database. We advise you to use the same names as us,
which you can find in [properties](src/main/resources/application.properties). You can use [data.sql](src/main/resources/data.sql) for testing. To run the project from the command line,
type `mvnw` (Windows), or `./mvnw` (Mac & Linux), then open
http://localhost:8080 in your browser.

You can also import the project to your IDE of choice as you would with any
Maven project. Read more on [how to import Vaadin projects to different 
IDEs](https://vaadin.com/docs/latest/flow/guide/step-by-step/importing) (Eclipse, IntelliJ IDEA, NetBeans, and VS Code).

We also use swagger2 so you can visit http:localhost:8080/swagger-ui.html.
//
## Deploying to 
To create a production build, call `mvnw clean package -Pproduction` (Windows),
or `./mvnw clean package -Pproduction` (Mac & Linux).
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found Production
in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/ufitness-1.0-SNAPSHOT.jar`

## Project structure

- `MainLayout.java` in `src/main/java` contains the navigation setup (i.e., the
  side/top bar and the main menu). This setup uses
  [App Layout](https://vaadin.com/components/vaadin-app-layout).
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `data` package in `src/main/java` contains server controller, dto, model, repository and service packages.
- `security` package in `src/main/java` contains security configuration files.
- `views` folder in `frontend/` contains the client-side JavaScript views of your application.
- `themes` folder in `frontend/` contains the custom CSS styles.

## Useful links

- Read the documentation at [vaadin.com/docs](https://vaadin.com/docs).
- Follow the tutorials at [vaadin.com/tutorials](https://vaadin.com/tutorials).
- Watch training videos and get certified at [vaadin.com/learn/training](https://vaadin.com/learn/training).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples at [vaadin.com/components](https://vaadin.com/components).
- View use case applications that demonstrate Vaadin capabilities at [vaadin.com/examples-and-demos](https://vaadin.com/examples-and-demos).
- Discover Vaadin's set of CSS utility classes that enable building any UI without custom CSS in the [docs](https://vaadin.com/docs/latest/ds/foundation/utility-classes). 
- Find a collection of solutions to common use cases in [Vaadin Cookbook](https://cookbook.vaadin.com/).
- Find Add-ons at [vaadin.com/directory](https://vaadin.com/directory).
- Ask questions on [Stack Overflow](https://stackoverflow.com/questions/tagged/vaadin) or join our [Discord channel](https://discord.gg/MYFq5RTbBn).
- Report issues, create pull requests in [GitHub](https://github.com/vaadin/platform).

**MIPT 2022**