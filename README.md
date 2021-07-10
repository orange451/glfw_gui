# glfw_gui

Library used when deploying GLFW application through MiniJVM for desktop. Contains stub classes for GLFM to prevent compilation errors.

Maven:
```maven
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  
	<dependency>
	    <groupId>com.github.orange451</groupId>
	    <artifactId>glfw_gui</artifactId>
	    <version>Tag</version>
	</dependency>
```

Gradle:
```maven
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
	dependencies {
	        implementation 'com.github.orange451:glfw_gui:Tag'
	}
```
