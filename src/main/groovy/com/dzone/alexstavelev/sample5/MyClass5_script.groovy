package com.dzone.alexstavelev.sample5

/**
 * Created by ilazarev on 2/6/2015.
 * http://java.dzone.com/articles/groovy-closures-owner-delegate
 *
 * The ability to have so much control over the lexical scope of closures in Groovy gives enormous power.
 * Even when the delegate is set it can be change to something else, this means we can make the behavior of the closure super dynamic.
 *
 *
 *
 */
class MyOtherClass {
    String myString = "I am over in here in MyOtherClass"
}

class MyOtherClass2 {
    String myString = "I am over in here in MyOtherClass2"
}

class MyClass {
    def closure = {
        println myString
    }
}

def closure = new MyClass().closure
closure.delegate = new MyOtherClass()
closure()     // outputs: I am over in here in myOtherClass

closure = new MyClass().closure
closure.delegate = new MyOtherClass2()
closure()     // outputs: I am over in here in myOtherClass2