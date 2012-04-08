package test.groovy.acceptance

import main.groovy.FizzBuzz
import spock.lang.Specification

class FizzBuzzJudge extends Specification{
    FizzBuzz sut

    def setup() {
        sut = new FizzBuzz()
    }

    def "三の倍数"() {
        expect:
            sut.fizzBuzz(3) == "Fizz"
            sut.fizzBuzz(18) == "Fizz"
    }

    def "五の倍数"() {
        expect:
            sut.fizzBuzz(5) == "Buzz"
            sut.fizzBuzz(35) == "Buzz"
    }

     def "三と五の倍数"() {
        expect:
            sut.fizzBuzz(15) == "FizzBuzz"
            sut.fizzBuzz(150) == "FizzBuzz"
    }

     def "その他"() {
        expect:
            sut.fizzBuzz(4) == "4"
            sut.fizzBuzz(7) == "7"
    }

     def "ゼロの場合"() {
        when:
            sut.fizzBuzz(0)
        then:
            thrown(RuntimeException)
    }

     def "マイナスの場合"() {
        when:
            sut.fizzBuzz(-3);
        then:
            thrown(RuntimeException)
    }
}
