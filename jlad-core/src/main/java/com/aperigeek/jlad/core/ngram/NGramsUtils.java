/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aperigeek.jlad.core.ngram;

import java.util.ArrayList;

/**
 *
 * @author viv
 */
public class NGramsUtils {

    /**
     * Returns all n-grams for a given word.
     * 
     * @param word The word to extract n-grams from
     * @return all n-grams found in this word
     */
    /*
     * TODO: static is bad. my eyes hurt. remove.
     */
    public static String[] ngramify(String word) {
        ArrayList<String> ngrams = new ArrayList<String>();
        int i = 0;
        while (i < word.length() - 3) {
            ngrams.add(word.substring(i, i + 2));
            ngrams.add(word.substring(i, i + 3));
            ngrams.add(word.substring(i, i + 4));
            i++;
        }
        while (i < word.length() - 2) {
            ngrams.add(word.substring(i, i + 2));
            ngrams.add(word.substring(i, i + 3));
            i++;
        }
        while (i < word.length() - 1) {
            ngrams.add(word.substring(i, i + 2));
            i++;
        }
        return ngrams.toArray(new String[ngrams.size()]);
    }
}
