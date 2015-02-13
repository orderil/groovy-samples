package com.dzone.alexstavelev.sample2

/**
 * Created by ilazarev on 2/6/2015.
 * http://java.dzone.com/articles/groovy-closures-owner-delegate
 *
 * Ok, so both the outerClosure and the nestedClosure have access to variables on the instance of the class they were defined in. That's obvious.
 * But, how exactly do they resolve the myString reference?
 * Well it's like this.
 * If the variable was not defined explicitly in the closure, the this scope is then checked, then the owner scope and then the delegatescope.
 * In this example, myString is not defined in either of the closures, so groovy checks their this references and sees the myString is defined there and uses that.
 */
class MyClass {
    String myString = "myString1"
    def outerClosure = {
        println myString;     // outputs myString1
        def nestedClosure = {
            println myString;  // outputs myString1
        }
        nestedClosure()
    }
}

MyClass myClass = new MyClass()
def outerClosure = new MyClass().outerClosure
outerClosure()

println myClass.myString