package com.lea.kakaf.service

import com.lea.kakaf.KakafApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(classes = KakafApplication)
class SendTestTest extends Specification {

    @Autowired
    SendTest sendTest

    @Unroll
    def "Send #message to #topic"() {

        when:
        sendTest.send(message, topic)
        Thread.sleep(4000L)
        then:
        true

        where:
        message << ['test1', 'test2', 'test3']
        topic << ['linkou', 'linkou', 'linkou']
    }
}
