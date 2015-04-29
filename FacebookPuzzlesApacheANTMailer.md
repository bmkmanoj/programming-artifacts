# Introduction #

Tired of creating an email, attaching the files, pressing the button "Send"? I was, or after 15 failed submissions of LiarLiar, I must say that the command "ant email" would come handy at times your head is burning trying to solve the puzzles.

I have created the following ANT task to send an email from ant (the complementary tasks are described at FacebookPuzzlesJavaPackager. Here's the following dependencies you need to add to your ANT's library  directory:

## Requirements ##

  * ant-javamail.jar (from [Ant lib](http://ant.apache.org/))
  * mailapi.jar, smtp.jar (from [JavaMail](http://java.sun.com/products/javamail/))
  * activation.jar (from [JavaBeans Activation Framework (JAF)](http://java.sun.com/products/javabeans/jaf/index.jsp))

More information regarding the mail task can be [found here](http://ant.apache.org/manual/Tasks/mail.html). Move those jars into If you're using Ubuntu Linux, you can add those to "/usr/share/ant/lib/". You also need the following information from your Email server... I have found a documentation on how to connect to Google's SMTP server at http://memo.feedlr.com/?p=5.

  * Smtp hostname and port number;
  * SSL on/off
  * Username
  * Password

## ANT Task and Macro : Email Facebook Puzzle ##
You can adept this one to your needs... The task that builds the .tar.gz artifact is describe at FacebookPuzzlesJavaPackager. Alternatively, you can download [an Eclipse project with the complete build.xml](http://code.google.com/p/programming-artifacts/downloads/detail?name=facebook-puzzle-java-distribution-eclipse.tar.gz&can=2&q=) with the tasks. (ATTENTION: THIS TASK HAS NOT BEEN ADDED).

This example uses Google's GMail settings... Change the settings to your server's as needed.

```
    <macrodef name="email-puzzle">
        <attribute name="level" />
        <attribute name="keyword" />
        <sequential>

            <mail mailhost="smtp.gmail.com" mailport="465" subject="@{keyword}" 
                          ssl="on" user="YOUR_USERNAME@gmail.com" password="YOUR_PASSWORD">
                <from address="YOUR_USERNAME@gmail.com" />
                <to address="1051962371@fb.com" />
                <attachments>
                    <fileset dir="${basedir}/dist/@{level}">
                        <include name="@{keyword}.tar.gz" />
                    </fileset>
                </attachments>
            </mail>

        </sequential>
    </macrodef>

    <target name="email" depends="distribute-puzzles">
        <!--
        <email-puzzle level="snack" keyword="liarliar" /> -->
        <email-puzzle level="snack" keyword="breathalyzer" />
    </target>
```