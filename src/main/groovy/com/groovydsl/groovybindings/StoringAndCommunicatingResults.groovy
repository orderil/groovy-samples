package com.groovydsl.groovybindings

/**
 * Created by igorlazarev on 2/8/2015.
 * http://techbus.safaribooksonline.com/9781847196903/ch08lvl1sec46?percentage=56.172&reader=html
 *
 * Storing and communicating results.
 *
 * Capturing the values of variables set in the DSL can also be done through the binding.
 * The delegate is set by each calling closure, so any variables defined in the scope of the DSL blocks will be available as binding variables to the calling closures.
 */
def binding = new Binding()
binding.outerBlock = { closure ->
    closure.delegate = delegate
    closure()
    println "outerBlock: " + binding.message
}

binding.innerBlock = { closure ->
    closure.delegate = delegate
    closure()
    println "innerBlock: " + binding.message
}

// We can define a closure that captures a string value from the DSL, as follows:
//binding.messageBlock = { closure ->
//    closure.delegate = delegate
//    binding.message = closure()
//    println "messageBlock: ${binding.message}"
//}

def shell = new GroovyShell(binding)
String scriptText = """
    outerBlock {
        innerBlock {
            // any variables defined in the scope of the DSL blocks will be available as binding variables to the calling closures.
            message = "Hello, World!"
        }
    }
    outerBlock {
        println message
    }
//    outerBlock {
//        messageBlock {
//            "Hello, World!... message"
//        }
//    }
"""

shell.evaluate(scriptText)

//println "caller: " + binding.message
