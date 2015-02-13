package com.guillaumelaforge.dsl.mars

def binding = new Binding(
        [
                robot: new Robot(),
                *    : Direction.values().collectEntries {
                    [(it.name()): it]
                }
        ]
)
def shell = new GroovyShell(binding)
shell.evaluate(
        new File("command2.groovy")
)
