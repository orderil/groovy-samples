package com.dzone.alexstavelev.sample4

/**
 * Created by ilazarev on 2/6/2015.
 * http://java.dzone.com/articles/groovy-closures-owner-delegate
 *
 * Now, let's take a look at an example where Groovy can't find a variable in the closure,
 * or on this or the owner scope but can find it in on closure's delegate scope.
 * As discussed earlier the delegate scope is the same as the owner scope, unless it is explicitly changed.
 * So, to make this a bit more interesting, let's change the delegate.
 */
class MyOtherClass {
    String myString = "I am over in here in MyOtherClass"
}

class MyClass {
    def closure = {
        println myString
    }
}

def closure = new MyClass().closure
println "closure.delegate was    : " + closure.delegate
closure.delegate = new MyOtherClass()
println "closure.delegate became : " + closure.delegate
closure()   // outputs: "I am over in here in myOtherClass"

