Проект для всех уроков из курса java junior.
Создан через maven командой:
mvn archetype:generate -DgroupId=ru.job4j -DartifactId=job4j_design -DarchetypeArtifactId=maven-archetype-simple -DarchetypeVersion=1.4 -DinteractiveMode=false

Вручную исправлен файл pom.xml.

--------------------------------------------------------------------------------
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
<modelVersion>4.0.0</modelVersion>

<groupId>ru.job4j</groupId>
<artifactId>job4j_design</artifactId>
<version>1.0-SNAPSHOT</version>

<name>job4j_design</name>

 <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>14</maven.compiler.source>
    <maven.compiler.target>14</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
    </dependency>
  </dependencies>
 </project>
--------------------------------------------------------------------------------

в папке c:\projects\job4j_design выполните команду:
mvn install

Создал проект jobj4j_design на гитхаб.

создал файл .gitignore и добавил в него строки:
*.iml
target
.idea

Связал локальный и удаленный репозитории.
Сделал коммит.
В файле pom.xml исправил <groupId>ru.job4j</groupId>.
Добавил зависимости для тестов.
<dependency>
<groupId>org.hamcrest</groupId>
<artifactId>hamcrest-all</artifactId>
<version>1.3</version>
<scope>test</scope>
</dependency>
<dependency>
<groupId>junit</groupId>
<artifactId>junit</artifactId>
<version>4.12</version>
<scope>test</scope>
</dependency>
</dependencies>

Добавляем плагины Checkstyle и JaCoCo. Второй используется для отображения покрытия тестами.
<build>
<plugins>
<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-checkstyle-plugin</artifactId>
<version>2.17</version>
<executions>
<execution>
<id>validate</id>
<phase>validate</phase>
<configuration>
<configLocation>checkstyle.xml</configLocation>
<encoding>UTF-8</encoding>
<consoleOutput>true</consoleOutput>
<failsOnError>true</failsOnError>
<includeTestSourceDirectory>true</includeTestSourceDirectory>
</configuration>
<goals>
<goal>check</goal>
</goals>
</execution>
</executions>
</plugin>
<plugin>
<groupId>org.jacoco</groupId>
<artifactId>jacoco-maven-plugin</artifactId>
<version>0.8.7</version>
<executions>
<execution>
<goals>
<goal>prepare-agent</goal>
</goals>
</execution>
<execution>
<id>report</id>
<phase>test</phase>
<goals>
<goal>report</goal>
</goals>
</execution>
</executions>
</plugin>
</plugins>
</build>


Добавляем файл checkstyle.xml в корень проекта.
Настроил плагин Checkstyle.

