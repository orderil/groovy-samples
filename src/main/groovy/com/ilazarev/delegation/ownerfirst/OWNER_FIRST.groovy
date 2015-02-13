package com.ilazarev.delegation.ownerfirst

/**
 * <pre>
 * will output :
 * <pre>
 * 30
 * 70
 * [x:10, y:20]
 * </pre>
 */
class Test {
    def x = 30
    def y = 40

    def run() {
        def data = [x: 10, y: 20]
        def cl = { y = x + y }
        cl.delegate = data
        cl.resolveStrategy = Closure.OWNER_FIRST      // this is used by default
        cl()
        println x
        println y
        println data
    }
}

new Test().run()
