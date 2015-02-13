package com.ilazarev.delegation.delegatefirst

/**
 * <pre>
 * will output :
 * <pre>
 * 30
 * 40
 * [x:10, y:30]
 * </pre>
 */
class Test {
    def x = 30
    def y = 40

    def run() {
        def data = [x: 10, y: 20]
        def cl = { y = x + y }
        cl.delegate = data
        cl.resolveStrategy = Closure.DELEGATE_FIRST
        cl()
        println x
        println y
        println data
    }
}

new Test().run()
