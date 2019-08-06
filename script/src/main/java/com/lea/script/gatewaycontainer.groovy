package com.lea.script

import groovy.sql.Sql


Map dbConnParams = [
        url     : 'jdbc:mysql://192.168.40.14:3306/gateway_container?characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8',
        user    : 'root',
        password: '123456',
        driver  : 'com.mysql.cj.jdbc.Driver'
]

def sql = Sql.newInstance(dbConnParams)


def select = '''SELECT
\tx.interface_code,
\tx.interface_name,
\tr.run_sql,
\ty.source_code
FROM
\tprovider_interface x,
\tdata_source y,
\tdomain_space d,
\tinterface_relation r
WHERE
\td.id = x.domain_id
\tAND d.source_id = y.id
\tAND r.interface_code = x.interface_code'''

sql.query(select){it ->

    while(it.next()){

        String runSql = it.getString(3)

        String interfaceCode = it.getString(1)

        String interfaceName = it.getString(2)

        String sourceCode = it.getString(4)

        def (selectQuery, condition)  = runSql.split('where', 2)

        def split = condition.split('and')



        //插入 interface_info 表
        sql.execute("insert into interface_info(source_code, interface_code, interface_name, select_query,  interface_status) " +
            " values($sourceCode, $interfaceCode, $interfaceName, $selectQuery, 1 ")

        //插入 param表
        split.eachWithIndex { String con, int i ->
            println "insert into interface_param(interface_code, where_query, order_num, must_in, param_name) " +
                    "values (${it.getString(1)}, ${con}, ${i + 1}, 1, ${con.split('=')[0]})"
        }




    }
}

sql.close()