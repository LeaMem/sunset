package com.lea.script

class Student {
    private String name

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }
}

def filePath = "/Users/lea/Desktop/deploy_absorb.sh"

def lines = new File(filePath).readLines()

lines = lines.unique()

lines.eachWithIndex { String entry, int i -> println entry}


