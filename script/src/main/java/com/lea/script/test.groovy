//package com.lea.script
//
//import groovy.sql.Sql
//
//
//Map dbConnParams = [
//        url     : 'jdbc:mysql://192.168.40.14:3306/qz_gateway?characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8',
//        user    : 'root',
//        password: '123456',
//        driver  : 'com.mysql.cj.jdbc.Driver'
//]
//
//def sql = Sql.newInstance(dbConnParams)
//
//
//def pattern = /\b(?:[0-9]{1,3}\.){3}[0-9]{1,3}\b(:\d+)?/
//
//def updateSql = "update interface_info set url = :url, req_example = :reqExample where id = :id"
//
//def domain = ""
//
//sql.query("select id, url, req_example  from interface_info") { it ->
//
//    while (it.next()) {
//
//        def id = it.getInt('id')
//
//        def url = it.getString('url')
//
//        def reqExample = it.getString('req_example')
//
//        url = url.replaceAll(pattern, domain)
//
//        reqExample = reqExample != null ? reqExample.replaceAll(pattern, domain) : reqExample
//
//        println "${id}\t ${url}\t ${reqExample}\t"
//
//        sql.execute(updateSql, [url: url, reqExample: reqExample, id: id])
//    }
//
//}
//
//sql.close()