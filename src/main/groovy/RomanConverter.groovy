package main.groovy

import main.java.*

class RomanConverter implements features.RomanConverter {
    @Override
    int toArabic(String roman) {
        if (roman == null || roman.isEmpty()) throw new RuntimeException()

        def romanToArabicMap = [I:1, V:5, X:10, L:50, C:100, D:500, M:1000]
        def romanAllowOnly = ['V', 'L', 'D']
        def romanAllowThreeTimes = ['I', 'X', 'C', 'M']
        def romanAllowPrev = [V:'I', X:'I', L:'X', C:'X', D:'C', M:'C']
        def romanList = roman.toCharArray().collect{it.toString()}

        romanList.countBy{it}.each {key, value ->
            // V,L,Dは2回以上出現せず、またその他も4回以上連続で出現しない
            if (romanAllowThreeTimes.any{roman ==~ /.*/+(it*4)+/.*/} ||
                romanAllowOnly.any {it == key} && value > 1)
                throw new RuntimeException()
        }

        def arabicList = []

        romanList.eachWithIndex {r, index ->
            if (romanToArabicMap[r] == null) throw new RuntimeException()
            if (arabicList.size() > 0 && arabicList[index-1] < romanToArabicMap[r]) {
                // IV,IX,XL,XC,CD,CMの組み合わせでしか自分より小さな数が前にくることはない
                if (romanAllowPrev[romanList[index]] == romanList[index-1])
                    arabicList[index-1] = -1 * arabicList[index-1]
                else
                    throw new RuntimeException()
            }
            arabicList.add(romanToArabicMap[r])
        }

        arabicList.countBy{it}.each { key, value ->
            // 2回以上同じ数で減算しない
            if (key.toString().toInteger() < 0 && value > 1)
                throw new RuntimeException()
        }

        arabicList.eachWithIndex { arabic, index ->
            // 減算した後に2回以上同じ数字がくることはない
            if (arabic < 0 && arabicList.size() > index+2 &&
                arabicList[index+1] == arabicList[index+2])
                throw new RuntimeException()
            // 減算する数と同じ数は直前に現れない
            if (arabic < 0 && index > 0 && arabicList[index-1] == -1 * arabic)
                throw new RuntimeException()
            // 減算した2つ後に同じ数は現れない
            if (arabic < 0 && arabicList.size() > index+2 &&
                arabicList[index+2] == -1 * arabic)
                throw new RuntimeException()
        }
        return (int)arabicList.sum()
    }
}
