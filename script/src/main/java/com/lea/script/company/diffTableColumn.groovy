package com.lea.script.company

import groovy.sql.Sql


Map dbConnParams = [
        url     : 'jdbc:mysql://192.168.40.14:3306/gateway_container?characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8',
        user    : 'root',
        password: '123456',
        driver  : 'com.mysql.cj.jdbc.Driver'
]

def sql = Sql.newInstance(dbConnParams)


def old_gate_way_tables = "SELECT DISTINCT TABLE_NAME FROM information_schema. `TABLES` WHERE TABLE_SCHEMA = 'qz_gateway'"

sql.query(old_gate_way_tables){it ->
    print(it)
}


