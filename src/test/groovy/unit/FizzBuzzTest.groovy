package test.groovy.unit

import spock.lang.Specification
import main.groovy.FizzBuzz

class FizzBuzzTest extends Specification{

    FizzBuzz sut

    def setup() {
        sut = new FizzBuzz()
    }

    def "3の倍数かつ5の倍数でないときFizzを返す"() {
        expect:
            sut.fizzBuzz(number) == 'Fizz'
        where:
            number << [3, 6, 12]
    }

    def "5の倍数かつ3の倍数でないときBuzzを返す"() {
        expect:
            sut.fizzBuzz(number) == 'Buzz'
        where:
            number << [5, 10, 20]
    }

    def "3と5の倍数のときFizzBuzzを返す"() {
        expect:
            sut.fizzBuzz(number) == 'FizzBuzz'
        where:
            number << [15, 30, 45]
    }
    
    def "3の倍数でも5の倍数でもないときその数字を返す"() {
        expect:
            sut.fizzBuzz(number) == number.toString()
        where:
            number << [1, 2, 4]
    }

    def "0以下の数字のときRuntimeExceptionを発生する"(){
        when:
            sut.fizzBuzz(number)
        then:
            thrown(RuntimeException)
        where:
            number << [0, -1, -2]
    }
}