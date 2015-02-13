package com.solutionsfit.dsl.memotemplate

class MemolDslTest extends GroovyTestCase {

    void testDslUsage_outputXml() {
        MemoDsl.make {
            to "Nirav Assar"
            from "Barack Obama"
            body "How are things? We are doing well. Take care"
            idea "The economy is key"
            request "Please vote for me"
            xml
        }
    }

    void testDslUsage_outputHtml() {
        MemoDsl.make {
            to "Nirav Assar"
            from "Barack Obama"
            body "How are things? We are doing well. Take care"
            idea "The economy is key"
            request "Please vote for me"
            html
        }
    }

    void testDslUsage_outputText() {
        MemoDsl.make {
            to "Nirav Assar"
            from "Barack Obama"
            body "How are things? We are doing well. Take care"
            idea "The economy is key"
            request "Please vote for me"
            text
        }
    }
}