package com.guillaumelaforge.speakerdeck

import groovy.json.JsonBuilder

// https://speakerdeck.com/glaforge/what-makes-groovy-groovy-devoxx-2013
// C:\Users\igorlazarev\Dropbox\ebooks\groovy\What_makes_Groovy_groovy_-_Devoxx_2013.pdf

class Greeter {
    String owner

    String greet(String name) {
        "Hello ${name}, I am ${owner}"
    }
}

def greeter = new Greeter(owner: "Igor")
println greeter.greet("Nadya")

// Closures

def adder = { a, b -> a + b }
assert adder(1, 2) == 3
assert adder('a', 'b') == 'ab'

def intAdder = { int a, int b -> a + b }
assert intAdder(1, 2) == 3

def stringAdder = { String a, String b -> a + b }
assert stringAdder('a', 'b') == 'ab'

def doubler = { it * 2 }
assert doubler(3) == 6
assert doubler('a') == 'aa'

def sum = { ... elements -> elements.sum() }
assert sum(1, 2) == 3
assert sum('a', 'b', 'c') == 'abc'

def mult = { int a, int b = 10 -> a * b }
assert mult(2, 3) == 6
assert mult(5) == 50

def logBase10 = Math.&log10 // turn a method into a closure function
def printer = System.out.&println
assert logBase10(10) == 1
printer 'abc'

// Closures - map / filter / reduce

@groovy.transform.Immutable
class Person {
    String name
    int age
}

def persons = [
        new Person('Igor', 35),
        new Person('Nadya', 30),
        new Person('Tanyusha', 7),
        new Person('Alena', 10),
]
def names = persons
        .findAll { it.age < 18 }
        .collect { it.name.toUpperCase() }
        .sort()
        .join(', ')
assert names == 'ALENA, TANYUSHA'
println names

// Closures - resource handling

// It does take care of properly opening/closing resources.
new File('bible.txt').withReader { r ->
    new File('out.txt').withWriter { w ->
        r.eachLine { line ->
            if (line.contains('Groovy')) {
                w << line.toUpperCase()
            }
        }
    }
}

// Closures - custom control structures

void unless(boolean cond, Closure c) {
    if (!cond) {
        c()
    }
}

unless(10 < 9) {
    println "less"
}

unless(2 < 1, { println "again less" })
unless(1 < 2, { println "this should not be printed" })

// Lists

def list = ['a', 'b', 'c']
println list
list << 'd'
println list
assert list.contains('d')
assert list.findAll { it.startsWith 'a' }.size() == 1
assert list.collect { it.toUpperCase() } == ['A', 'B', 'C', 'D']
assert list.inject('') { a, b -> a + b } == 'abcd'

def map = [name: 'Guillaume', age: 36]
map.daughters = ['Marion', 'Erine']
println map
assert map['daughters'].contains('Marion')

// Regular expressions

def pattern = ~/.*foo.*/
//match
assert "Alibaba" ==~ /^.*(ba){2}$/
// find
def matcher = "Superman" =~ /([A-Z][a-z]+)man/
println "matcher[0]=" + matcher[0]
assert matcher[0][0] == "Superman"
assert matcher[0][1] == "Super"

"75001 Paris".find(/(\d{5})\s(\w+)/) { match, zip, town ->
    println "The Zip code of ${town} is ${zip}"
}

// Ranges

def range = 'a'..'z'
assert range.contains('m')
assert range.contains('z')

def exclusive = 1..<10
assert !exclusive.contains(10)

def reverse = 10..0
assert reverse[0] == 10
assert reverse[-1] == 0 // Negative index count from the end
assert reverse[-2] == 1

// Strings, GStrings, multiline strings

def name = 'Groovy'
def gString = """
Dear Mr ${name},
You're the winner of the lottery!
Yours sincerely,
Dave
"""
assert gString.toString().contains('Groovy')
println "gString=" + gString

// Surprising numbers...

System.out.println(2.0 - 1.1)   // in Java it gives 0.89999999999999
System.out.println(3 / 2)       // in Java it gives 1

assert 2.0 - 1.1 == 0.9
assert 3 / 2 == 1.5

// Powerful switch/case on steroids

/*
def obj = 123
def result = ""
switch (obj) {
    case 123:
        result = "number 123"; break
    case "abc":
        result = "string abc"; break
    case String:
        result = "is a string"; break
    case Integer:
        result = "is an Integer"; break
    case [1, 2, 3]:
        result = "in list"; break
    case ~/.*o+.*//*
:
        result = "regex match "; break
    case { it < 3 }:
        result = "closure criteria "; break
    default:
        result = "unknown"
}
assert result == "number 123"
*/

class SwitchTester {
    static String doSwitch(Object param) {
        switch (param) {
            case 123:
                "number ${param}"; break
            case "abc":
                "string abc"; break
            case String:
                "is a string ${param}"; break
            case Integer:
                "is an Integer"; break
            case [1, 2, 3]:
                "in list"; break
            case ~/.*o+.*/:
                "regex match"; break
            case { it < 3 }:
                "closure criteria "; break
            default:
                "unknown"
        }
    }
}

def switchTester = new SwitchTester()
assert switchTester.doSwitch(123) == "number 123"
assert switchTester.doSwitch("lake") == "is a string lake"
assert switchTester.doSwitch({}) == "regex match"

// Multiple assignment and destructuring

def (a, b) = ['A', 'B']

// Classic "swap"
(a, b) = [b, a]
assert a == 'B' && b == 'A'

// With types
def (int i, int j) = [1, 2]

// Method returning a list
def geocode(String place) {
    return [45.4, 2.3]
}

// Destructuring
def (la, lo) = geocode("Paris")
assert la == 45.4 && lo == 2.3
println "la=" + la
println "lo=" + lo


class Point {
    double x, y

    // Method signature convention:; getAt(int)
    double getAt(int idx) {
        if (idx == 0) {
            x
        } else if (idx == 1) {
            y
        } else {
            throw new Exception("Wrong index")
        }
    }
}

// Transparent destructuring
def (x, y) = new Point(x: 48.3, y: 3.5)
assert x == 48.3 && y == 3.5

// Builders - JSON builder

/*
def builder = new groovy.json.JsonBuilder()
def root = builder.person {
    name 'Guillaume'
    age 36
    daughters 'Marion', 'Erine'
    address { // Closure blocks delimiting the structure
        street '1 Main Street'
        zip 75001
        city 'Paris'
    }
}
*/


def builder = new JsonBuilder()
def root = builder.people {
    person {
        firstName 'Guillame'
        lastName 'Laforge'
        // Named arguments are valid values for objects too
        address(
                city: 'Paris',
                country: 'France',
                zip: 12345,
        )
        married true
        // a list of values
        conferences 'JavaOne', 'Gr8conf'
    }
}

// creates a data structure made of maps (Json object) and lists (Json array)
assert root instanceof Map
println "root=" + root.toString()

assert builder.toString() == '{"people":{"person":{"firstName":"Guillame","lastName":"Laforge","address":{"city":"Paris","country":"France","zip":12345},"married":true,"conferences":["JavaOne","Gr8conf"]}}}'
println "builder=" + builder
