![cover](../other_resources/w3.png)

In the week three we will be disscusing content related to the following topics.

## Week Three

- Combining Play with a frontend (angular) and exposing an API ( coding ).
- Pros and Cons of cofigurations.

## Steps to combine angular frontend and play backend.

1. Create a play-java-starter project using ```sbt new playframework/play-java-seed.g8```
2. Remove all the contents within public directory, remove all default controllers, view and services.
3. Make a ```ui``` directory in the root folder to contain the frontend project.
4. Create an angular project using ```ng new app_name```
5. Replace the ```/ui/package.json``` file ```"scripts{ }"``` section in the angular project with following code-snippet.

```
"scripts": {
    "ng": "ng",
    "lint": "ng lint",
    "e2e": "ng e2e",
    "start": "ng serve --open --proxy-config src/proxy.conf.js --host 0.0.0.0",
    "test": "ng test --watch",
    "test:ci": "ng test --watch=false --browsers ChromeHeadless",
    "test:coverage": "ng test --watch=true --code-coverage=true",
    "test:coverage:ci": "ng test --watch=false --code-coverage=true --browsers ChromeHeadless",
    "build:dev": "ng build --progress --output-path ../public",
    "build:prod": "ng build --progress --prod --output-path ../public"
  }

```

6. Create a ```proxy.conf.js``` file in the ```/ui/src``` directory and add following code-snippet.

```
const PROXY_CONFIG = {
  "**": {
    "target": "http://localhost:9000",
    "secure": false,
    "bypass": function (req) {
      if (req && req.headers && req.headers.accept && req.headers.accept.indexOf("html") !== -1) {
        console.log("Skipping proxy for browser request.");
        return "/index.html";
      }
    }
  }
};

module.exports = PROXY_CONFIG;
```

7. Navigate to backend codebase ```/project``` directory and create a file called [FrontendCommands.scala](https://github.com/dilum1995/IIT-PlayFramework-Session/blob/main/week_three/java-play-angular/project/FrontendCommands.scala) and paste fllowing frontend build commands.

```
object FrontendCommands {
  val dependencyInstall: String = "npm install"
  val test: String = "npm run test:ci"
  val serve: String = "npm run start"
  val build: String = "npm run build:prod"
}
```

8. Inside ```/project``` directory and create another file called [FrontendRunHook.scala](https://github.com/dilum1995/IIT-PlayFramework-Session/blob/main/week_three/java-play-angular/project/FrontendRunHook.scala) to include the code which triggers **play run hooks** to run frontend servers.


9. Navigate to root dirctory and create a file called [ui-build.sbt](https://github.com/dilum1995/IIT-PlayFramework-Session/blob/main/week_three/java-play-angular/ui-build.sbt) to contain the play sbt task mapping to frontend build tasks.

10. Create [FrontendController.scala](https://github.com/dilum1995/IIT-PlayFramework-Session/blob/main/week_three/java-play-angular/app/controllers/FrontendController.scala) file inside the ```app/controllers/``` to contain all static resource associate routes.

11. Update the [routes](https://github.com/dilum1995/IIT-PlayFramework-Session/blob/main/week_three/java-play-angular/conf/routes) file in the ```conf/routes``` with your routes.

12. Add ```apiPrefix``` configuration key in [application.conf](https://github.com/dilum1995/IIT-PlayFramework-Session/blob/main/week_three/java-play-angular/conf/application.conf) which is referred by this controller.

### Other Sample Congfigurations 
- [play-angular2-typescript-java | GitHub Repo](https://github.com/detinho/play-angular2-typescript-java)
- [angular-seed-play-java | GitHub Repo](https://github.com/dynobjx/angular-seed-play-java)
- [play-java-bower-angular-bootstrap-seed | GitHub Repo](https://github.com/aaronroe/play-java-bower-angular-bootstrap-seed)

### For further knowledge

- [Running Frontend and Backend Dev Servers Together](https://vsupalov.com/combine-frontend-and-backend-development-servers/)
- [What are CORS?](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS)
- [Ahead-of-time (AOT) compilation](https://angular.io/guide/aot-compiler)
- [What are CDNs?](https://en.wikipedia.org/wiki/Content_delivery_network)
- [Reverse Proxy](https://en.wikipedia.org/wiki/Reverse_proxy)
- [npm scripts](https://docs.npmjs.com/cli/v6/using-npm/scripts)
- [Angular CLI](https://cli.angular.io)
