<!-- .slide: -->
# Ajout de projectreactor
* Maven ajout dans le pom.xml : 
```xml[]
<dependency>
    <groupId>io.projectreactor</groupId>
    <artifactId>reactor-core</artifactId>
    <version>3.x.x.RELEASE</version>
</dependency>
```
* Groovy DSL build.gradle :
```json[]
dependencies {
    // ... autres dépendances
    implementation 'io.projectreactor:reactor-core:3.x.x.RELEASE'
}
```
* Kotlin DSL build.gradle.kts :
```json[]
dependencies {
    // ... autres dépendances
    implementation("io.projectreactor:reactor-core:3.x.x.RELEASE")
}
```
Notes:

