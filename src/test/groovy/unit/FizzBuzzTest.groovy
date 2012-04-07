package test.groovy.unit

import spock.lang.*
import main.java.FizzBuzz

class FizzBuzzTest extends Specification{

    FizzBuzz sut

    def setup() {
        sut = new FizzBuzz()
    }

    def "3の倍数かつ15の倍数でないときFizzを返す"() {
        expect:
            sut.says(number) == 'Fizz'
        where:
            number << [3, 6, 9, 12, 18, 21]
    }

    def "5の倍数かつ15の倍数でないときBuzzを返す"() {
        expect:
            sut.says(number) == 'Buzz'
        where:
            number << [5, 10, 20, 25, 35, 40]
    }

    def "15の倍数のときFizzBuzzを返す"() {
        expect:
            sut.says(number) == 'FizzBuzz'
        where:
            number << [15, 30, 45, 60, 75, 90]
    }
    
    def "3の倍数でも5の倍数でもないときその数字を返す"() {
        expect:
            sut.says(number) == number.toString()
        where:
            number << [1, 2, 4, 7, 8, 11, 13]
    }

    def "0以下の数字のときその数字を返す"(){
        when:
            sut.says(number)
        then:
            thrown(RuntimeException)
        where:
            number << [0, -1, -2, -3, -4, -5, -6]
    }
}
