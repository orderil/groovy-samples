package com.nealford.functionalthinking

/**
 * Created by igorlazarev on 11/19/2014.
 * Functional Thinking, by Neal Ford
 * http://techbus.safaribooksonline.com/book/programming/9781449365509/1dot-why/_concision_html#X2ludGVybmFsX0h0bWxWaWV3P3htbGlkPTk3ODE0NDkzNjU1MDklMkZfYV9jb21tb25fZXhhbXBsZV9odG1sJnF1ZXJ5PQ==
 */
class ProcessingEmployees {
    public static final List<String> NAMES = Arrays.asList(
            null,
            "neal", "s", "stu", "j", "rich", "bob", "aiden", "j", "ethan",
            "liam", "mason", "noah", "lucas", "jacob", "jayden", "jack"
    )

    public String cleanUpNames(listOfNames) {
        listOfNames
                .findAll { it != null && it.length() > 1 }
                .collect { it.capitalize() }
                .join ','
    }

    public static void main(String[] args) {
        System.out.println("result=" + new ProcessingEmployees().cleanUpNames(NAMES));
    }
}
