package com.lea.script

import java.util.regex.Matcher
import java.util.regex.Pattern

//String name = 'kitty is not my love'
//
//def regex = /(no\w+).*(lo\w+)/
//
//
//
//def ans = name =~ regex
//
//println ans[0]

def strings = """http://59.202.58.68/gateway/api/difficultAllowanceInfo.htm
http://59.202.58.68/gateway/api/doctorRegistrationInfo.htm
http://59.202.58.68/gateway/api/drugWholesaleInfo.htm
http://59.202.58.68/gateway/api/drugRetailInfo.htm
http://59.202.58.68/gateway/api/directCompanyNetworkInfo.htm
http://59.202.58.68/gateway/api/culturalCenterBasicInfo.htm
http://59.202.58.68/gateway/api/countryInfo.htm
http://59.202.58.68/gateway/api/economicTypeInfo.htm
http://59.202.58.68/gateway/api/economicIndustryInfo.htm
http://59.202.58.68/gateway/api/001003044/dataSharing/defenseReviewAgencyInfo.htm
"""

def lines = strings.lines()

def pattern = ip4 = ~"\\b(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\\b(:\\d+)?"

for(it in lines){

    def matcher = pattern.matcher(it)


    if(matcher.matches()){
        println "kk"
        println matcher[0]
    }

}



