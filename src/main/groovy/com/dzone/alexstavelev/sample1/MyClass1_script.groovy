package com.dzone.alexstavelev.sample1

/**
 * Created by ilazarev on 2/6/2015.
 * http://java.dzone.com/articles/groovy-closures-owner-delegate
 *
 * The this value always refers to the instance of the enclosing class.
 * owner is always the same as this, except for nested closures.
 * delegate is the same as owner by default. It can be changed and we will see that in a sec.
 */
class MyClass {
    def outerClosure = {
        println "outerClosure.this     = " + this.class.name     // MyClass
        println "outerClosure.owner    = " + owner.class.name    // MyClass
        println "outerClosure.delegate = " + delegate.class.name // MyClass
        def nestedClosure = {
            println this.class.name     // MyClass
            println owner.class.name    // MyClass$_closure1
            println delegate.class.name // MyClass$_closure1
            def superNestedClosure = {
                println this.class.name     // MyClass
                println owner.class.name    // MyClass$_closure1_closure2
                println delegate.class.name // MyClass$_closure1_closure2
            }
            superNestedClosure()
        }
        nestedClosure()
    }
}

def outerClosure = new MyClass().outerClosure
outerClosure()
