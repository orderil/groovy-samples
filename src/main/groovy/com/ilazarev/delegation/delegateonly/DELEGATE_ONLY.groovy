package com.ilazarev.delegation.delegateonly

/**
 * <pre>
 * will throw an error because even if the owner declares a "z" field, the resolution strategy will bypass lookup in the owner.
 * </pre>
 */
class Test {
    def x = 30
    def y = 40
    def z = 50

    def run() {
        def data = [x: 10, y: 20]
        def cl = { y = x + y + z }      // z does not exist in the delegate
        cl.delegate = data
        cl.resolveStrategy = Closure.DELEGATE_ONLY
        cl()
        println x
        println y
        println data
    }
}

new Test().run()