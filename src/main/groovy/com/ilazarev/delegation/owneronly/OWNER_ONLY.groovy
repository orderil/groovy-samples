package com.ilazarev.delegation.owneronly

/**
 * <pre>
 * will throw "No such property: z" error because even if the z variable is declared in the delegate, no lookup is made.
 * </pre>
 */
class Test {
    def x = 30
    def y = 40

    def run() {
        def data = [x: 10, y: 20, z: 30]
        def cl = { y = x + y + z }          // z does not exist in the owner
        cl.delegate = data
        cl.resolveStrategy = Closure.OWNER_ONLY
        cl()
        println x
        println y
        println data
    }
}

new Test().run()
