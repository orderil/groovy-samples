package com.guillaumelaforge.dsl.mars

// slide 50

new GroovyShell(
        new Binding([robot: new Robot()])
).evaluate(
        "import static com.guillaumelaforge.dsl.mars.Direction.*\n" +
                "robot.move left"
)