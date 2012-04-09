package main.groovy

import main.java.*

class JapaneseSyllabary implements features.JapaneseSyllabary {

    def oneRomanToKanaMap =
        [a:'あ', i:'い', u:'う', e:'え', o:'お', n:'ん']

    def twoRomanToKanaMap =
        [k:[a:'か', i:'き', u:'く', e:'け', o:'こ'],
         s:[a:'さ', i:'し', u:'す', e:'せ', o:'そ'],
         t:[a:'た', i:'ち', u:'つ', e:'て', o:'と'],
         n:[a:'な', i:'に', u:'ぬ', e:'ね', o:'の', n:'ん'],
         h:[a:'は', i:'ひ', u:'ふ', e:'へ', o:'ほ'],
         m:[a:'ま', i:'み', u:'む', e:'め', o:'も'],
         y:[a:'や', u:'ゆ', o:'よ'],
         r:[a:'ら', i:'り', u:'る', e:'れ', o:'ろ'],
         w:[a:'わ', o:'を']]

    @Override
    String execute(char ... str) {
        if (str.length == 1) {
            def kana = oneRomanToKanaMap[str[0].toString()]
            if (kana != null) return kana
        }
        else if (str.length == 2) {
            def kanaColumn = twoRomanToKanaMap[str[0].toString()]
            if (kanaColumn != null) {
                def kana = kanaColumn[str[1].toString()]
                if (kana != null) return kana
            }
        }
        throw new RuntimeException()
    }
}
