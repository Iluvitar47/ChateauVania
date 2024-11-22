Retrouvez l'intégralité du contenu des tests en ouvrant dans le navigateur le fichier suivant (chemin absolu à adapter en fonction de votre architecture:
lwjgl3/build/reports/jacoco/test/html/index.html


# CASTLEVANIA

Bienvenu dans le monde de Castlevania, un jeu de plateforme en 2D. Vous incarnez un personnage qui doit traverser un chateau hanté pour atteindre le boss final. Vous devrez éviter les pièges et les ennemis qui se dresseront sur votre chemin. Vous pourrez ramasser des objets pour vous aider dans votre quête.

Ce jeu a été créé dans le cadre de notre projet Java. Il a été réalisé par un Théo Deschamps et Mathias Guignet.

Pour jouer, il vous suffit de faire tourner le fichier Lwjgl3Launcher.java.

Pour jouer, utilisez Q et D pour vous déplacer respectivement à geuche puis à droite ainsi que la barre d'espace pour sauter. Vous pouvez également attaquer en appuyant sur la touche "E".

Le but du jeu est de traverser le chateau et de battre le boss final.


Ce jeu a été créé avec la librairie LibGDX. Il a été développé en Java. 

Si vous souhaitez consulter plus d'informations sur le fonctionnement de LibGDX, vous pouvez lire la suite des explications ci-dessous ou bien vous rendre sur le site officiel de LibGDX: https://libgdx.com/

Bonne chance et bon jeu !





# LibGDX

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and a main class extending `Game` that sets the first screen.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.
