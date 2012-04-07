package test.groovy.accept

import main.java.FizzBuzz
import spock.lang.Specification

class FizzBuzzJudge extends Specification{
    FizzBuzz sut

    def setup() {
        sut = new FizzBuzz()
    }

    def "三の倍数"() {
        expect:
            sut.says(3) == "Fizz"
            sut.says(18) == "Fizz"
    }

    def "五の倍数"() {
        expect:
            sut.says(5) == "Buzz"
            sut.says(35) == "Buzz"
    }

     def "三と五の倍数"() {
        expect:
            sut.says(15) == "FizzBuzz"
            sut.says(150) == "FizzBuzz"
    }

     def "その他"() {
        expect:
            sut.says(4) == "4"
            sut.says(7) == "7"
    }

     def "ゼロの場合"() {
        when:
            sut.says(0)
        then:
            thrown(RuntimeException)
    }

     def "マイナスの場合"() {
        when:
            sut.says(-3);
        then:
            thrown(RuntimeException)
    }
}
