# Introduction #

I started working in the Facebook puzzles problems and I realized I could automate the process of generating the final Java distribution package "puzzle-keyword.tar.gz" using ANT.

You can download the distribution http://code.google.com/p/marcellodesales-cs-research/downloads/detail?name=facebook-puzzle-java-distribution-eclipse.tar.gz

Details on the puzzles, how to submit them and more are located at http://www.facebook.com/careers/puzzles.php

# Details #

This script creates the puzzle distribution file keyword.tar.gz ready to be emailed... Based on pre-defined source-code structure containing the build.xml published at http://www.davideisenstat.com/fbpfaq/#hoppity-in-java, and your original source-code (when you solve the problems).

I like to have my classes organized into packages, so it is easier to have them separate. I just organized them into levels and keyword. Once the build system runs, it will remove the package declaration as expected, as the java class needs to be in the root directory of the resulting distribution .tar.gz.

So, the download provided in this page will include an Eclipse Java project with the main build.xml and the build-puzzle.xml template, as well as the first puzzle solution copied from http://www.davideisenstat.com/fbpfaq/#hoppity-in-java (I had developed my own solution before I found this one... I think you can develop yours as well). You can help you verify if you have all the environment set.

I followed the instructions on http://www.davideisenstat.com/fbpfaq/#hoppity-in-java and make it easier to write the solutions. Also, helping the ones who wants to avoid the process of packaging the solutions quicker...

# INSTRUCTIONS #

  1. This package contains a Java project already configured with the expected structure. Feel free to modify to your liking;
  1. Create your solution in the package "level" as a Java class in lower-case. You can adjust and remove the keyword package and only have the puzzle java classes in the level directory. Don't forget to update the build.xml;
  1. The build.xml below will create the uncommented puzzles based on the keywords. Once you are finished with a solution, just uncomment the section on the task "distribute-puzzles". The generated keyword.tar.gz will contain the build.xml from the FAQ page and your Java class ready to be emailed. The file is created under "dist/level/keyword.tar.gz. (See the output of running with the java solution to the first puzzle below).
  1. Good Luck!

```
<project name="Facebook Puzzles Distribution" default="distribute-puzzles">

    <!--
        @author: Marcello de Sales (marcello.desales@gmail.com)
        http://www.facebook.com/marcello.desales
        http://linkedin.com/in/marcellodesales
    -->

    <macrodef name="distribute-puzzle">
        <attribute name="keyword" />
        <attribute name="level" />
        <sequential>
            <echo message="Building the Java pizzle distribution of the puzzle @{keyword} at dist/@{level}/@{keyword}"/>
            <delete dir="dist/@{level}/@{keyword}"/>
            <mkdir dir="dist/@{level}/@{keyword}"/>
            <copy file="src/@{level}/@{keyword}.java" tofile="dist/@{level}/@{keyword}/@{keyword}.java" />
            <copy file="build-puzzle.xml" tofile="dist/@{level}/@{keyword}/build.xml" />
            <replace file="dist/@{level}/@{keyword}/build.xml" token="KEYWORD" value="@{keyword}" />
            <exec executable="sed" description="Removing the package from the Java class">
                <arg line="-i '1d' dist/@{level}/@{keyword}/@{keyword}.java" />
            </exec>
            <tar destfile="dist/@{level}/@{keyword}.tar" basedir="dist/@{level}/@{keyword}/"/>
            <gzip destfile="dist/@{level}/@{keyword}.tar.gz" src="dist/@{level}/@{keyword}.tar"/>
            <delete file="dist/@{level}/@{keyword}.tar" />
        </sequential>
    </macrodef>

    <macrodef name="distribute-horsdoeuvre">
        <attribute name="keyword" />
        <sequential>
            <property name="puzzle-level" value="horsdoeuvre"/>
            <distribute-puzzle keyword="@{keyword}" level="${puzzle-level}"/>
        </sequential>
    </macrodef>

    <macrodef name="distribute-snack">
        <attribute name="keyword" />
        <sequential>
            <property name="puzzle-level" value="snack"/>
            <distribute-puzzle keyword="@{keyword}" level="${puzzle-level}"/>
        </sequential>
    </macrodef>

    <macrodef name="distribute-meal">
        <attribute name="keyword" />
        <sequential>
            <property name="puzzle-level" value="meal"/>
            <distribute-puzzle keyword="@{keyword}" level="${puzzle-level}"/>
        </sequential>
    </macrodef>

    <macrodef name="distribute-buffet">
        <attribute name="keyword" />
        <sequential>
            <property name="puzzle-level" value="buffet"/>
            <distribute-puzzle keyword="@{keyword}" level="${puzzle-level}"/>
        </sequential>
    </macrodef>

    <macrodef name="distribute-snack">
        <attribute name="keyword" />
        <sequential>
            <property name="puzzle-level" value="snack"/>
            <distribute-puzzle keyword="@{keyword}" level="${puzzle-level}"/>
        </sequential>
    </macrodef>

    <target name="distribute-puzzles">
        <distribute-horsdoeuvre keyword="hoppity" />
        <!--
        <distribute-horsdoeuvre keyword="meepmeep" />

        <distribute-snack keyword="liarliar" />
        <distribute-snack keyword="breathalyzer" />
        <distribute-snack keyword="gattaca" />
        <distribute-snack keyword="simonsays" />
        <distribute-snack keyword="dancebattle" />
        <distribute-snack keyword="smallworld" />
        <distribute-snack keyword="usrbincrash" />

        <distribute-meal keyword="rushhour" />
        <distribute-meal keyword="battleship" />
        <distribute-meal keyword="fridgemadness" />
        <distribute-meal keyword="peaktraffic" />
        <distribute-meal keyword="swarm" />

        <distribute-buffet keyword="dinoisland" />
        <distribute-buffet keyword="sophie" />
        <distribute-buffet keyword="facebull" />
        -->
    </target>

</project>
```

The build-puzzle.xml (copied from http://www.davideisenstat.com/fbpfaq/#hoppity-in-java and added the token KEYWORD to be replaced).

```
<!--
    SEND ONLY YOUR .java FILES AND build.xml, AS SEPARATE ATTACHMENTS!
    To use this buildfile for, e.g., facebull: change "hoppity" to "facebull" and put main() in facebull.java .
    All .java files in the root directory will be compiled.
    -->
<project>
    <property name="keyword" value="KEYWORD" />
    <javac srcdir="." destdir="." />
    <!-- enlarge the maximum heap size -->
    <echo message="#!/bin/bash&#x0A;java -cp &quot;${0%/*}&quot; -Xmx1024M ${keyword} &quot;$1&quot;&#x0A;" file="${keyword}" />
    <!-- not necessary, but convenient -->
    <chmod file="${keyword}" perm="a+x" />
</project>
```

Running "ant distribute-puzzles" will generate all the puzzles you place on the task "distribute-puzzles".

```
Buildfile: /home/marcello/Documents/open-source/facebook-puzzles-java/build.xml
distribute-puzzles:
     [echo] Building the Java pizzle distribution of the puzzle hoppity at dist/horsdoeuvre/hoppity
   [delete] Deleting directory /home/marcello/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre/hoppity
    [mkdir] Created dir: /home/marcello/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre/hoppity
     [copy] Copying 1 file to /home/marcello/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre/hoppity
     [copy] Copying 1 file to /home/marcello/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre/hoppity
      [tar] Building tar: /home/marcello/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre/hoppity.tar
     [gzip] Building: /home/marcello/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre/hoppity.tar.gz
   [delete] Deleting: /home/marcello/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre/hoppity.tar
BUILD SUCCESSFUL
Total time: 542 milliseconds
```

# Verifying the solution before emailing it #

I did a lot of mistakes before getting a correct submission. Once you get the distribution, I guess their robot will:

  1. Untar the keyword.tar.gz, if that is the extension.
  1. Run ant in the root directory.
  1. Run the command "keyword".

The build.xml developed at http://www.davideisenstat.com/fbpfaq/#hoppity-in-java makes this magic happen. Untar the puzzle example first, and then run ant.

```
marcello@puppet-master:~/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre$ tar zxpvf hoppity.tar.gz 
build.xml
hoppity.java
```

```
marcello@puppet-master:~/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre$ ls -la
total 20
drwxr-xr-x 2 marcello marcello 4096 May  4 01:33 .
drwxr-xr-x 3 marcello marcello 4096 May  4 00:48 ..
-rw-r--r-- 1 marcello marcello  619 May  4 01:03 build.xml
-rw-r--r-- 1 marcello marcello 4026 May  4 01:03 hoppity.java
-rw-r--r-- 1 marcello marcello 1854 May  4 01:03 hoppity.tar.gz
```

The, execute ant...

```
marcello@puppet-master:~/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre$ ant
Buildfile: /home/marcello/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre/build.xml
    [javac] /home/marcello/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre/build.xml:8: warning: 'includeantruntime' was not set, defaulting to build.sysclasspath=last; set to false for repeatable builds
    [javac] Compiling 1 source file to /home/marcello/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre

BUILD SUCCESSFUL
Total time: 1 second
```

At this point, you will get a script created with the name of the puzzle, the .class of your solution, all in the root directory, as displayed below.

```
marcello@puppet-master:~/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre$ ls -la
total 28
drwxr-xr-x 2 marcello marcello 4096 May  4 02:09 .
drwxr-xr-x 3 marcello marcello 4096 May  4 00:48 ..
-rw-r--r-- 1 marcello marcello  619 May  4 01:03 build.xml
-rwxr-xr-x 1 marcello marcello   54 May  4 02:09 hoppity
-rw-r--r-- 1 marcello marcello 2495 May  4 02:09 hoppity.class
-rw-r--r-- 1 marcello marcello 4026 May  4 01:03 hoppity.java
-rw-r--r-- 1 marcello marcello 1854 May  4 01:03 hoppity.tar.gz
```

Try running the executable script with the test input file. I had created one for the first solution on the /tmp/hoppity.txt file.

```
marcello@puppet-master:~/Documents/open-source/facebook-puzzles-java/dist/horsdoeuvre$ ./hoppity /tmp/hoppity.txt 
Hoppity
Hophop
Hoppity
Hoppity
Hophop
Hoppity
Hop
```