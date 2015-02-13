package com.dzone.alexstavelev.sample3

/**
 * Created by ilazarev on 2/6/2015.
 * http://java.dzone.com/articles/groovy-closures-owner-delegate
 *
 * Ok, let's take a look at an example where it can't find a variable in the closure and can't find it on the closure's this scope,
 * but it can find's it in the closure's owner scope.
 *
 * In this case, Groovy can't find myString in the nestedClosure or in the this scope.
 * It then checks the owner scope, which for the nestedClosure is the outerClosure.
 * It finds myString there and uses that.
 *
 */
class MyClass {
    def outerClosure = {
        def myString = "outerClosure";
        def nestedClosure = {
            println myString;  // outputs outerClosure
        }
        nestedClosure()
    }
}

def outerClosure = new MyClass().outerClosure
outerClosure()
