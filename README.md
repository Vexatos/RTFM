# cilli Manual
Based on the manual code in TIS-3D.

*This mod requires Java 8!*

## License / Use in Modpacks
This mod is [licensed under the **MIT license**](LICENSE). All **assets are public domain**, unless otherwise stated; all are free to be distributed as long as the license / source credits are kept. This means you can use this mod in any mod pack **as you please**. I'd be happy to hear about you using it, though, just out of curiosity.

## Extending
In general, please refer to [the API](src/main/java/li/cil/manual/api), everything you need to know should be explained in the Javadoc of the API classes and interfaces.
### Gradle
To add a dependency to cillimanual for use in your mod, add the following to your `build.gradle`:

```groovy
repositories {
  maven {
    url = "http://maven.cil.li/"
  }
}
dependencies {
  compile "li.cil.manual:cillimanual:${config.cillimanual.version}"
}
```

Where `${config.cillimanual.version}` is the version you'd like to build against.
