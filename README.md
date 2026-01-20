# Hytale Plugin Template
[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/D1D41SN93D)

A **template** for creating plugins/mods for the Hytale Server.

## How to Use This Template

### Prerequisites

- **Java 25+** - [Download here](https://adoptium.net/) (If using IntelliJ, do it through the IDE)
- **IDE** - IntelliJ IDEA, Eclipse or VS Code (Only tested on IntelliJ)

### Initial Setup

**Step 1**: Clone or copy this template to your project
```bash
git clone <repo-url> my-plugin-hytale
cd my-plugin-hytale
```

**Step 2**: Rename the project in `settings.gradle.kts`
```kotlin
rootProject.name = "template"  // Change this!
```

**Step 3**: Rename the package
Rename the plugin package (`com.paiique.template`) to whatever you want.

**Step 4**: Rename the main class
Rename `TemplatePlugin` to your desired name (In CamelCase please)

**Step 5**: Compile the plugin to test
```bash
./gradlew build
```
or
`Gradle Elephant > Tasks > build > *build*` 

**Step 6**: Add your Git repository
Use the command below to remove my repository:
```bash
git remote rm origin
``` 
Then use the command below to add yours: 
```bash 
git remote add origin <your-repo-url>
``` 

Done! Have fun!
