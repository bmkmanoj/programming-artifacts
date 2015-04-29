# Introduction #

This is one of the problems discussed on the book Heads First Object-Oriented Analysis and Design (HF OOAD). This wiki shows my solution to this problem using Java 6, Google Guava, and JUnit 4.

## Related Links ##

  * http://www.coderanch.com/t/495510/java/java/Case-Study-Sales-Tax

# Problem Statement #

Basic sales tax is applicable at a rate of 10% on all goods, except books,
food, and medical products that are exempt. Import duty is an additional
sales tax applicable on all imported goods at a rate of 5%, with no
exemptions.

When I purchase items I receive a receipt which lists the name of all the
items and their price (including tax), finishing with the total cost of the
items, and the total amounts of sales taxes paid. The rounding rules for
sales tax are that for a tax rate of n%, a shelf price of p contains
(np/100 rounded up to the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these shopping
baskets...

```
Input 1:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

Output 1:
1 book : 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83
```

```
Input 2:
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50

Output 2:
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65
Total: 65.15
```

```
Input 3: 
1 imported bottle of perfume at 27.99 
1 bottle of perfume at 18.99 
1 packet of headache pills at 9.75 
1 box of imported chocolates at 11.25 

Output 3: 
1 imported bottle of perfume: 32.19 
1 bottle of perfume: 20.89 
1 packet of headache pills: 9.75 
1 imported box of chocolates: 11.85 
Sales Taxes: 6.70 
Total: 74.68 
```

# Solving the problem my way #

Solving the problem involved starting bottom up using Test-Driven Development:
writing the invoice file reader, design and implementation of the data wrappers
of invoice objects using Object-Oriented Analysis and Design, to a solution
designed with reusable components in Model-View-Controller along with other
design-patterns. Additional libraries used were JUnit 4 and Google Guava.

  1. Copied the input and output data from the email to the data directory to start working on initial test cases (Test-Drive Development).
  1. Started with the implementation of the TextFileReader and its unit tests at TextFileReaderTests.java. Since there is no requirement on performance, I thought that would be useful to implement this component using NIO APIs and expect files that are larger than the examples. Also added 2 options of who consumes the data loaded from the text files: buffers and listeners.
  1. After being able to load an invoice data into containers/listeners, the model was designed taking into account extensibility: a ShoppingBasket is a "container" object aggregated with BasketItems (just to make the implementation easier, but conceptually a shopping basket is composed of BasketItems). To supposed extensibility, different types of BasketItems can be implemented by implementing that interface. A BasketItems object is a representation of a single row of the invoice. In order to support Multi-Valued state of imported, exempt and all regular products, EnumSet was used instead of Bit Maps (usual way to represent that) in ProjectTaxRateType. An Abstract Factory was created to be responsible to create ShoppingBasket!<BasketIntems!> instances from an invoice text representation. The last decision made was to model the price as a Money.java class so that it is easier to decrease coupling of the NumberFormatter.currencyFormatter. The model Unit tests were created and refactored several times while developing the other layers, but the basic representation of invoice objects were verified before moving to the other layers.
  1. After having the Model ready, the concept of Services was added to provide the real implementation of requirements and allow code reuse using singleton (Enums as best option as Effective Java 2nd suggests). In order to speed the process of writing the solution, the controller SalesControllerTests.java was put in place to indirectly exercise the services created and validate the scenarios of viewing invoice files. In order to decouple steps, the InvoiceReaderService reuses the TextFileReader utility to load a ShoppingBasket from an invoice text file with invoice objects representation. To decouple the the calculations from the ShoppingBasket, the SalesCalculatorService implements the methods to read the data from the wrapper objects. The strategy of calculating tax values is based on the ProductTaxRateTypes. Finally, in order decouple the visualization of the objects, the BasketItemsDecorator is the service responsible for providing the Strategies SALE\_ITEM\_STRATEGY (printing the same input input text) or RECEIPT\_ITEM\_STRATEGY (to print the expected output). All the scenarios are verified by the SalesControllerTests using the files in the "data" directory.
  1. The view was the last piece designed. As the standard output was the primary view, I have added an InvoiceView interface with the contracts of for the view. Then, different view strategies were implemented: ConsoleViewStrategy.java and StringBuilderViewStrategy.java, where the former prints the invoice to the System.out and the latter to a container (StringBuilder). Tests were not added as the SalesControllerTests exercises the needed pieces.
  1. The last piece added was the InvoiceApp.java, which includes the main method and verification of the input and potential error messages and code.

# Running the solution #

Checkout the source code:

  * svn checkout http://programming-artifacts.googlecode.com/svn/trunk/workspaces/hacking/invoice  invoice

  * Execute the default ant task

```xml

/u1/.../invoice $ ant

Buildfile: /u1/workspaces/open-source/hacking/invoice/build.xml
clean:
compile:
[mkdir] Created dir: /u1/workspaces/open-source/hacking/invoice/dist/bin
[javac] Compiling 22 source files to /u1/workspaces/open-source/hacking/invoice/dist/bin
test:
[mkdir] Created dir: /u1/workspaces/open-source/hacking/invoice/dist/test-reports
[junit] Running com.googlecode.progrartifacts.AllSalesTests
[junit] Tests run: 13, Failures: 0, Errors: 0, Time elapsed: 0.383 sec
[junit] Running com.googlecode.progrartifacts.sales.invoice.controller.SalesControllerTests
[junit] Tests run: 2, Failures: 0, Errors: 0, Time elapsed: 0.216 sec
[junit] Running com.googlecode.progrartifacts.sales.invoice.model.BasketItemFactoryTests
[junit] Tests run: 4, Failures: 0, Errors: 0, Time elapsed: 0.112 sec
[junit] Running com.googlecode.progrartifacts.sales.invoice.model.ShoppingBasketTests
[junit] Tests run: 1, Failures: 0, Errors: 0, Time elapsed: 0.037 sec
[junit] Running com.googlecode.progrartifacts.util.TextFileReaderTests
[junit] Tests run: 6, Failures: 0, Errors: 0, Time elapsed: 0.063 sec
run-test-data:
clean:
[delete] Deleting directory /u1/workspaces/open-source/hacking/invoice/dist
compile:
[mkdir] Created dir: /u1/workspaces/open-source/hacking/invoice/dist/bin
[javac] Compiling 22 source files to /u1/workspaces/open-source/hacking/invoice/dist/bin
[echo] Executing 'java com.google.code.progrartifacts.sales.InvoiceApp /u1/workspaces/open-source/hacking/invoice/data/input1'
[java] 1 book: 12.49
[java] 1 music CD: 16.49
[java] 1 chocolate bar: 0.85
[java] Sales Taxes: 1.50
[java] Total: 29.83
clean:
[delete] Deleting directory /u1/workspaces/open-source/hacking/invoice/dist
compile:
[mkdir] Created dir: /u1/workspaces/open-source/hacking/invoice/dist/bin
[javac] Compiling 22 source files to /u1/workspaces/open-source/hacking/invoice/dist/bin
[echo] Executing 'java com.google.code.progrartifacts.sales.InvoiceApp /u1/workspaces/open-source/hacking/invoice/data/input2'
[java] 1 imported box of chocolates: 10.5
[java] 1 imported bottle of perfume: 54.65
[java] Sales Taxes: 7.65
[java] Total: 65.15
clean:
[delete] Deleting directory /u1/workspaces/open-source/hacking/invoice/dist
compile:
[mkdir] Created dir: /u1/workspaces/open-source/hacking/invoice/dist/bin
[javac] Compiling 22 source files to /u1/workspaces/open-source/hacking/invoice/dist/bin
[echo] Executing 'java com.google.code.progrartifacts.sales.InvoiceApp /u1/workspaces/open-source/hacking/invoice/data/input3'
[java] 1 imported bottle of perfume: 32.19
[java] 1 bottle of perfume: 20.89
[java] 1 packet of headache pills: 9.75
[java] 1 box of imported chocolates: 11.85
[java] Sales Taxes: 6.70
[java] Total: 74.68
BUILD SUCCESSFUL
Total time: 8 seconds
```