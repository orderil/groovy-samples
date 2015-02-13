package com.blogspot.mrhaki

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.codehaus.groovy.control.CompilerConfiguration

/**
 * Created by ilazarev on 2/12/2015.
 * http://mrhaki.blogspot.com/2011/11/groovy-goodness-create-our-own-script.html
 *
 */

// Simple Car class to save state and distance.
//@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
@ToString(includeNames = true, includeFields = true)
@EqualsAndHashCode
class Car {
    String state
    Long distance = 0
}

// Custom Script with methods that change the Car's state.
// The Car object is passed via the binding.
abstract class CarScript extends Script {
    def start() {
        this.binding.car.state = 'started'
    }

    def stop() {
        this.binding.car.state = 'stopped'
    }

    def drive(distance) {
        this.binding.car.distance += distance
    }
}

// Use custom CarScript.
def compilerConfiguration = new CompilerConfiguration()
compilerConfiguration.scriptBaseClass = CarScript.class.name

// Define Car object here, so we can use it in assertions later on.
def car = new Car()
// Add to script binding (CarScript references this.binding.car).
def binding = new Binding(car: car)

// Configure the GroovyShell.
def shell = new GroovyShell(this.class.classLoader, binding, compilerConfiguration)

// Simple DSL to start, drive and stop the car.
// The methods are defined in the CarScript class.
def carDsl = '''
start()
drive 20
stop()
'''

// Run DSL script.
shell.evaluate carDsl

// Checks to see that Car object has changed.
assert car.distance == 20
assert car.state == 'stopped'
println car
